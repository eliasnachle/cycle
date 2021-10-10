CREATE DATABASE IF NOT EXISTS cycle;

USE cycle;

CREATE TABLE tblUnidade (
	idUnidade INT PRIMARY KEY,
	nomeUnidade VARCHAR(60),
	enderecoUnidade VARCHAR(255),
	telefoneUnidade VARCHAR(14),
	cnpjUnidade VARCHAR(14),
	idUsuarioContratante INT,
	idCodigoUf INT
);

CREATE TABLE tblUsuarioContratante(
	idUsuarioContratante INT PRIMARY KEY,
	nomeContratante VARCHAR(80),
	emailContratante VARCHAR(80),
	senhaContratante VARCHAR(20),
	cnpfContratante VARCHAR(14),
	idPlano INT
);

CREATE TABLE tblUsuarioSuporte (
	idSup INT PRIMARY KEY,
	nomeSuporte VARCHAR(80),
	loginSuporte VARCHAR(30),
	senhaSuporte VARCHAR(20),
	idUsuarioContratante INT
);

CREATE TABLE IF NOT EXISTS tblCodigoUf (
	idCodigoUf INT PRIMARY KEY,
	descCodigoUf varchar(2)
);

CREATE TABLE tblPlano (
	idPlano INT PRIMARY KEY,
	nomePlano VARCHAR(20),
	qntMaquinas INT,
	valorPlano DOUBLE
);

CREATE TABLE tblMaquina (
	idMaquina INT PRIMARY KEY,
	apelidoMaquina VARCHAR(30),
    tipoMaquina VARCHAR(10),
	sistemaOperacionalMaquina VARCHAR(15),
    idProcessador VARCHAR(20),
	modeloCpu VARCHAR(50),
    cpuFrequencia DOUBLE,
    tipoDisco VARCHAR(4),
    espacoTotalDisco DOUBLE,
    espacoTotalRam DOUBLE,
	idUnidade INT
);

CREATE TABLE tblRegistro (
	idregistro INT PRIMARY KEY,
    cpuEmUso DOUBLE,
    temperaturaCpu DOUBLE,
    espacoOcupadoDisco DOUBLE,
    espacoOcupadoRam DOUBLE,
    dataHoraRegistro DATETIME,
    idMaquina INT
);