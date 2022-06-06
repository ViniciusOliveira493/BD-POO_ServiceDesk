CREATE DATABASE bdServiceDesk
GO
USE bdServiceDesk
CREATE TABLE tbSetor(
	id						INTEGER		IDENTITY(1,1)		NOT NULL
	, nome					VARCHAR(100)					NOT NULL
	PRIMARY KEY(id)
); 
CREATE TABLE tbEquipe(
	id						INTEGER		IDENTITY(1,1)		NOT NULL
	, nome					VARCHAR(100)					NOT NULL
	, setorId				INTEGER							NOT NULL
	PRIMARY KEY (id)
	FOREIGN KEY (setorId) REFERENCES tbSetor(id)
);
CREATE TABLE tbPessoa(
	cpf						CHAR(11)						NOT NULL	CHECK(LEN(cpf)=11)
	, nome					VARCHAR(100)					NOT NULL
	, senha					VARCHAR(64)						NOT NULL	DEFAULT('123mudar')
	, telefone				VARCHAR(11)						NOT NULL
	PRIMARY KEY(cpf)
); 
CREATE TABLE tbFuncionario(
	pessoaCpf				CHAR(11)						NOT NULL	CHECK(LEN(pessoaCpf)=11)
	, equipeId				INTEGER							NOT NULL
	, nivel					INTEGER							NOT NULL
	PRIMARY KEY(pessoaCpf)
	FOREIGN KEY (pessoaCpf) REFERENCES tbPessoa(cpf)
	, FOREIGN KEY (equipeId) REFERENCES tbEquipe(id)
);
CREATE TABLE tbEndereco(
	id						INTEGER IDENTITY(1,1)			NOT NULL
	, pessoaCPF				CHAR(11)						NOT NULL	CHECK(LEN(pessoaCPF)=11)
	, cep					CHAR(8)							NOT NULL	CHECK(LEN(cep)=8)
	, numero				INTEGER							NOT NULL
	, logradouro			VARCHAR(255)					NOT NULL
	, descricao				VARCHAR(255)					NOT NULL
	PRIMARY KEY(id)
	FOREIGN KEY (pessoaCPF) REFERENCES tbPessoa(cpf)
);
CREATE TABLE tbTipoProblema(
	id						INTEGER IDENTITY(1,1)			NOT NULL
	, nome					VARCHAR(80)						NOT NULL
	, tempoMaximoResolucao	INTEGER							NOT NULL
	PRIMARY KEY(id)
);
CREATE TABLE tbProduto(
	id						INTEGER IDENTITY(1,1)			NOT NULL
	, nome					VARCHAR(40)						NOT NULL
	PRIMARY KEY (id)
);
CREATE TABLE tbSolicitacao(
	id						INTEGER IDENTITY(1,1)			NOT NULL
	, descricaoProblema		TEXT							NOT NULL
	, dataSolicitacao		DATE							NOT NULL
	, dataResolucao			DATE							
	, descricaoResulucao	TEXT							
	, produtoId				INTEGER							NOT NULL
	, tipoProblema			INTEGER							NOT NULL
	, setorId				INTEGER							NOT NULL
	, solicitanteCpf		CHAR(11)						NOT NULL
	PRIMARY KEY (id)
	FOREIGN KEY (produtoId) REFERENCES  tbProduto(id)
	, FOREIGN KEY (produtoId) REFERENCES  tbProduto(id)
	, FOREIGN KEY (tipoProblema) REFERENCES  tbTipoProblema(id)
	, FOREIGN KEY (setorId) REFERENCES tbSetor(id)
	, FOREIGN KEY (solicitanteCpf) REFERENCES  tbPessoa(cpf)
);
CREATE TABLE tbChamado(
	id						INTEGER IDENTITY(1,1)			NOT NULL
	, dataChamado			DATE							NOT NULL
	, equipeId				INTEGER							NOT NULL
	, solicitacaoId			INTEGER							NOT NULL
	, enderecoId			INTEGER							NOT NULL
	PRIMARY KEY (id)
	FOREIGN KEY (equipeId) REFERENCES tbEquipe(id)
	, FOREIGN KEY (solicitacaoId) REFERENCES tbSolicitacao(id)
	, FOREIGN KEY (enderecoId) REFERENCES tbEndereco(id)
);
CREATE TABLE tbAtendente(
	solicitacaoId			INTEGER							NOT NULL
	, pessoaCpf				CHAR(11)						NOT NULL
	, dateEHora				DATE							NOT NULL
	PRIMARY KEY (solicitacaoId,pessoaCpf)
	FOREIGN KEY (solicitacaoId) REFERENCES tbSolicitacao(id)
	, FOREIGN KEY (pessoaCPF) REFERENCES tbPessoa(cpf)
);
--=====================INSERTS================---
INSERT INTO tbPessoa(cpf,nome,telefone)
	VALUES('12345678909','Admin','11976859456')

INSERT INTO tbEndereco(pessoaCPF,cep,numero,logradouro,descricao)
	VALUES('12345678909','03694000',2983,'Avenida Águia de Haia','Endereco interno')