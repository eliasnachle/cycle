CREATE DATABASE IF NOT EXISTS cycle;

USE cycle;

CREATE TABLE tblUnidades (
	idUnidade INT PRIMARY KEY AUTO_INCREMENT,
	nomeUnidade VARCHAR(60),
	enderecoUnidade VARCHAR(255),
	telefoneUnidade VARCHAR(14),
	cnpjUnidade VARCHAR(14),
	idUsuarioContratante INT,
	idCodigoUf INT
);

CREATE TABLE tblUsuariosContratante(
	idUsuarioContratante INT PRIMARY KEY AUTO_INCREMENT,
	nomeContratante VARCHAR(80),
	emailContratante VARCHAR(80),
	senhaContratante VARCHAR(20),
	cpfContratante VARCHAR(14),
    keyContratante VARCHAR(30),
	idPlano INT
);

CREATE TABLE tblUsuariosSuporte (
	idUsuarioSuporte INT PRIMARY KEY AUTO_INCREMENT,
	nomeSuporte VARCHAR(80),
	emailSuporte VARCHAR(30),
	senhaSuporte VARCHAR(20),
	idUsuarioContratante INT
);

CREATE TABLE IF NOT EXISTS tblCodigosUf (
	idCodigoUf INT PRIMARY KEY AUTO_INCREMENT,
	descCodigoUf varchar(2)
);

CREATE TABLE tblPlanos (
	idPlano INT PRIMARY KEY AUTO_INCREMENT,
	nomePlano VARCHAR(20),
	qntMaquinas INT,
	valorPlano DOUBLE
);

CREATE TABLE tblMaquinas (
	idMaquina INT PRIMARY KEY AUTO_INCREMENT,
	apelidoMaquina VARCHAR(30),
    tipoMaquina VARCHAR(10),
	sistemaOperacionalMaquina VARCHAR(15),
    idProcessador VARCHAR(20),
	modeloCpu VARCHAR(80),
    cpuFrequencia DOUBLE,
    modeloDisco VARCHAR(80),
    espacoTotalDisco DOUBLE,
    espacoTotalRam DOUBLE,
	idUnidade INT
);

CREATE TABLE tblRegistros (
	idRegistro INT PRIMARY KEY AUTO_INCREMENT,
    cpuEmUso DOUBLE,
    temperaturaCpu DOUBLE,
    espacoLivreDisco DOUBLE,
    espacoLivreRam DOUBLE,
    dataHoraRegistro DATETIME,
    idMaquina INT
);

CREATE TABLE tblAlertas (
	idAlerta INT PRIMARY KEY AUTO_INCREMENT,
    componenteInstavel VARCHAR(10),
    nivelCriticidade VARCHAR(10),
    descAlerta VARCHAR(255),
    dataHoraAlerta DATETIME,
    idRegistro INT
);