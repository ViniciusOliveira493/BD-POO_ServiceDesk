--==================  Queries  ==========================
use bdServiceDesk
--=================== Endereço =========================----
INSERT INTO tbEndereco(pessoaCPF,cep,numero,logradouro,descricao) 
	VALUES(?,?,?,?,?)

SELECT id,pessoaCPF,cep,numero,logradouro,descricao from tbendereco Where id = ?

DELETE FROM tbEndereco WHERE pessoaCPF = ?

SELECT id,pessoaCPF,cep,numero,logradouro,descricao from tbendereco Where pessoaCPF = ?

--===============  Funcionario =========================--
INSERT INTO tbfuncionario(pessoaCpf,equipeId,nivel) VALUES(?,?,?)

SELECT equipeId,nivel FROM tbfuncionario WHERE cpf=?

UPDATE tbfuncionario
SET
	equipeId = ?
    nivel = ?
WHERE cpf=?

Delete from tbfuncionario WHERE pessoacpf = ?

SELECT equipeId , nivel FROM tbfuncionario WHERE pessoaCpf=?

SELECT pessoaCpf FROM tbFuncionario Limit ?,?

--===================== Cargo Funcionário
SELECT	
	p.cpf
	, p.nome
	, CASE WHEN f.nivel = 0 THEN		
			'ADMIN'
		WHEN f.nivel = 1 THEN
			'Técnico'
		WHEN f.nivel = 2 THEN
			'Atendente'
		END AS cargo
FROM tbFuncionario AS f
	INNER JOIN tbPessoa AS p
		ON p.cpf = f.pessoaCpf

--===== Funcionarios na equipe
SELECT
	COUNT(fun.pessoaCpf)
FROM tbEquipe AS eq
	INNER JOIN tbFuncionario AS fun
		ON fun.equipeId = eq.id
GROUP BY eq.id
--===== qtd funcionarios com a senha padrao
select 
	COUNT(func.pessoaCpf) AS senhas
FROM tbFuncionario AS func
	INNER JOIN tbPessoa AS p
		ON p.cpf = func.pessoaCpf
WHERE p.senha = '123mudar'
GROUP BY p.nome
--====== quais funcionarios estão com a senha padrao
select 
	p.cpf
	, p.nome
	, p.telefone
FROM tbFuncionario AS func
	INNER JOIN tbPessoa AS p
		ON p.cpf = func.pessoaCpf
WHERE p.senha = '123mudar'
GROUP BY p.nome,p.cpf,p.telefone
--======================== Pessoa 

INSERT INTO tbPessoa(cpf,nome,senha) VALUES(?,?,?)

SELECT cpf,nome FROM tbPessoa WHERE cpf=?

UPDATE tbpessoa
	SET 
	nome=?
WHERE cpf=?

UPDATE tbpessoa
	SET 
	senha = ?
WHERE cpf = ?

Delete from tbpessoa WHERE cpf = ?

SELECT cpf,nome FROM tbPessoa WHERE cpf = ?

SELECT cpf,nome FROM tbPessoa WHERE nome LIKE ?

SELECT cpf,nome FROM tbPessoa

SELECT cpf,nome FROM tbPessoa WHERE cpf=? AND senha=?

SELECT count(pessoaCpf) as qtd FROM tbFuncionario WHERE pessoaCpf=?

SELECT	
	P.cpf
	, P.nome
	, P.telefone
	, COUNT(e.id) AS qtd_enderecos
	, CASE WHEN COUNT(e.id) > 0 THEN
		'OK'
	  ELSE
		'Solicitar o cadastro do endereço'
	  END AS situacao
FROM tbPessoa AS p
	FULL OUTER JOIN tbEndereco as e
		ON e.pessoaCPF = p.cpf
GROUP BY p.cpf,p.nome,p.telefone
ORDER BY qtd_enderecos ASC

--====== qtd solicitação pessoa
select 
	p.nome
	, p.telefone
	, COUNT(s.id) AS total_solicitacoes
	, CASE WHEN COUNT(s.id) > 40
		THEN
			'O usuário já realizou muitas solicitações'
		ELSE
			'OK'
		END AS situacao
FROM tbPessoa AS p
	INNER JOIN tbSolicitacao AS s
		ON p.cpf = s.solicitanteCpf
GROUP BY p.nome,p.cpf,p.telefone

--============================Produto

INSERT INTO tbproduto(nome) VALUES(?)

SELECT id,nome from tbproduto Where id = ?

UPDATE tbproduto
	SET 
	nome = ?
WHERE id = ?

Delete from tbproduto WHERE id = ?

SELECT id,nome FROM tbproduto WHERE nome LIKE ?

SELECT COUNT(so.id) as total_solicitacoes
FROM tbproduto AS prod
	INNER JOIN tbsolicitacao AS so
		ON so.produtoId = prod.id
WHERE prod.id = ?
GROUP BY prod.id

SELECT 
	COUNT(so.id) as total_solicitacoes
	, CASE WHEN COUNT(so.id)>30
		THEN
			'Alerta'
		WHEN COUNT(so.id)>10
			THEN
			'Atenção'
		ELSE
			'OK'
	  END AS estado
FROM tbproduto AS prod
	INNER JOIN tbsolicitacao AS so
		ON so.produtoId = prod.id
WHERE  so.dataSolicitacao > DATEADD(DAY,-7,GETDATE())
GROUP BY prod.id
--=============================== Solicitação ==============================
INSERT INTO tbsolicitacao(descricaoProblema,dataSolicitacao,produtoId,tipoProblema,setorId,solicitanteCpf) 
	VALUES(?,?,?,?,?,?)

SELECT * from tbproduto Where id = ?

UPDATE tbsolicitacao
SET 
	descricaoProblema = ?
	produtoId = ?
	tipoProblema = ?
	setorId = ?
WHERE id = ?

UPDATE tbsolicitacao
SET 
	dataResolucao = ?
	descricaoResulucao = ?
WHERE id = ?

SELECT * from tbproduto Where setorId = ?

INSERT INTO tbatendente(solicitacaoId,pessoaCpf,dateEHora) 
	VALUES(?,?,?)

--============= Solicitações Fora do prazo
select 
	s.id
	, se.nome
	, CASE WHEN s.dataResolucao IS NULL
		THEN
			CASE WHEN DATEDIFF(DAY,GETDATE(),DATEADD(DAY,((t.tempoMaximoResolucao/60)/24),s.dataSolicitacao))<0
				THEN
					'EM ATRASO'
				ELSE
					'DEVE SER RESULVIDO EM ' +  CAST(DATEDIFF(DAY,GETDATE(),DATEADD(DAY,((t.tempoMaximoResolucao/60)/24),s.dataSolicitacao)) AS varchar(20)) + ' DIAS'
				END
		ELSE
			'A SOLICITACAO JÁ FOI ATENDIDA'
		END AS situacao			
FROM tbSolicitacao AS s
	INNER JOIN tbTipoProblema AS t
		ON s.tipoProblema = t.id
	INNER JOIN tbSetor AS se
		ON s.setorId = se.id
--=======================equipe
INSERT INTO tbEquipe(nome,setorId)
	VALUES ('alfa',1)