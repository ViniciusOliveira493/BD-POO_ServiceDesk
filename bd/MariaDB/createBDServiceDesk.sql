CREATE DATABASE bdServiceDesk
USE bdServiceDesk
CREATE TABLE tbSetor(
	id						INTEGER	PRIMARY KEY AUTO_INCREMENT		NOT NULL
	, nome					VARCHAR(100)					NOT NULL
)
CREATE TABLE tbEquipe(
	id						INTEGER	PRIMARY KEY AUTO_INCREMENT	
	, nome					VARCHAR(100)					NOT NULL
	, setorId				INTEGER							NOT NULL
	, CONSTRAINT fk_equipe_setor FOREIGN KEY (setorId) REFERENCES tbSetor(id)
)
CREATE TABLE tbPessoa(
	cpf						CHAR(11)						NOT NULL	
	, nome					VARCHAR(100)					NOT NULL
	, senha					VARCHAR(64)						NOT NULL	DEFAULT('123mudar')
	, PRIMARY KEY(cpf)
); 
CREATE TABLE tbFuncionario(
	pessoaCpf				CHAR(11)						NOT NULL	
	, equipeId				INTEGER							NOT NULL
	, nivel					INTEGER							NOT NULL
	, PRIMARY KEY(pessoaCpf)
	, constraint fk_pessoaCpfFuncionario foreign key (pessoaCpf) REFERENCES tbPessoa(cpf)
	, constraint fk_funcionarioEquipe FOREIGN KEY (equipeId) REFERENCES tbEquipe(id)
);
CREATE TABLE tbEndereco(
	id						INTEGER auto_increment			NOT NULL
	, pessoaCPF				CHAR(11)						NOT NULL	
	, cep					CHAR(8)							NOT NULL	
	, numero				INTEGER							NOT NULL
	, logradouro			VARCHAR(255)					NOT NULL
	, descricao				VARCHAR(255)					NOT NULL
	, PRIMARY KEY(id)
	, FOREIGN KEY (pessoaCPF) REFERENCES tbPessoa(cpf)
);
CREATE TABLE tbTipoProblema(
	id						INTEGER auto_increment		NOT NULL
	, nome					VARCHAR(80)						NOT NULL
	, tempoMaximoResolucao	INTEGER							NOT NULL
	, PRIMARY KEY(id)
);
CREATE TABLE tbProduto(
	id						INTEGER auto_increment			NOT NULL
	, nome					VARCHAR(40)						NOT NULL
	, PRIMARY KEY (id)
);
CREATE TABLE tbSolicitacao(
	id						INTEGER auto_increment			NOT NULL
	, descricaoProblema		TEXT							NOT NULL
	, dataSolicitacao		DATE							NOT NULL
	, dataResolucao			DATE							NOT NULL
	, descricaoResulucao	TEXT							NOT NULL
	, produtoId				INTEGER							NOT NULL
	, tipoProblema			INTEGER							NOT NULL
	, setorId				INTEGER							NOT NULL
	, solicitanteCpf		CHAR(11)						NOT NULL
	, PRIMARY KEY (id)
	, FOREIGN KEY (produtoId) REFERENCES  tbProduto(id)
	, FOREIGN KEY (produtoId) REFERENCES  tbProduto(id)
	, FOREIGN KEY (tipoProblema) REFERENCES  tbTipoProblema(id)
	, FOREIGN KEY (setorId) REFERENCES tbSetor(id)
	, FOREIGN KEY (solicitanteCpf) REFERENCES  tbPessoa(cpf)
);
CREATE TABLE tbMensagem(
	pessoaCPF				CHAR(11)						NOT NULL	
	, solicitacaoId			INTEGER							NOT NULL
	, dataEHora				DATE							NOT NULL
	, texto					VARCHAR(240)					NOT NULL
	, PRIMARY KEY(pessoaCPF,solicitacaoId,dataEHora)
	, FOREIGN KEY (pessoaCPF) REFERENCES tbPessoa(cpf)
	, FOREIGN KEY (solicitacaoId) REFERENCES tbSolicitacao(id)
);
CREATE TABLE tbChamado(
	id						INTEGER auto_increment			NOT NULL
	, dataChamado			DATE							NOT NULL
	, equipeId				INTEGER							NOT NULL
	, solicitacaoId			INTEGER							NOT NULL
	, enderecoId			INTEGER							NOT NULL
	, PRIMARY KEY (id)
	, FOREIGN KEY (equipeId) REFERENCES tbEquipe(id)
	, FOREIGN KEY (solicitacaoId) REFERENCES tbSolicitacao(id)
	, FOREIGN KEY (enderecoId) REFERENCES tbEndereco(id)
);
CREATE TABLE tbAtendente(
	solicitacaoId			INTEGER	auto_increment			NOT NULL
	, pessoaCpf				CHAR(11)						NOT NULL
	, dateEHora				DATE							NOT NULL
	, PRIMARY KEY (solicitacaoId,pessoaCpf)
	, FOREIGN KEY (solicitacaoId) REFERENCES tbSolicitacao(id)
	, FOREIGN KEY (pessoaCPF) REFERENCES tbPessoa(cpf)
);
/*--=====================INSERTS================---*/
INSERT INTO tbPessoa(cpf,nome)
	VALUES('12345678909','Admin')

INSERT INTO tbEndereco(pessoaCPF,cep,numero,logradouro,descricao)
	VALUES('12345678909','03694000',2983,'Avenida Águia de Haia','Endereco interno')