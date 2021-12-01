CREATE DATABASE IF NOT EXISTS cycle;

USE cycle;

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
    modeloDisco1 VARCHAR(80),
    modeloDisco2 VARCHAR(80),
    espacoTotalDisco1 DOUBLE,
    espacoTotalDisco2 DOUBLE,
    espacoTotalRam DOUBLE,
	idUsuarioContratante INT
);

CREATE TABLE tblRegistros (
	idRegistro INT PRIMARY KEY AUTO_INCREMENT,
    cpuEmUso DOUBLE,
    espacoLivreDisco1 DOUBLE,
    espacoLivreDisco2 DOUBLE,
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

-- ------------------------------------------------------------------------------------------------

CREATE TABLE tblAdministrator(
	idAdministrator INT PRIMARY KEY AUTO_INCREMENT,
	nomeAdministrator VARCHAR(80),
	emailAdministrator VARCHAR(80),
	senhaAdministrator VARCHAR(20)
);

CREATE TABLE tblServidor (
	idServidor INT PRIMARY KEY AUTO_INCREMENT,
	apelidoServidor VARCHAR(30),
	sistemaOperacionalServidor VARCHAR(15),
    idProcessador VARCHAR(20),
	modeloCpu VARCHAR(80),
    cpuFrequencia DOUBLE,
    modeloDisco1 VARCHAR(80),
    modeloDisco2 VARCHAR(80),
    espacoTotalDisco1 DOUBLE,
    espacoTotalDisco2 DOUBLE,
    espacoTotalRam DOUBLE,
	idAdministrator INT
);

CREATE TABLE tblRegistrosServidor (
	idRegistro INT PRIMARY KEY AUTO_INCREMENT,
    cpuEmUso DOUBLE,
    espacoLivreDisco1 DOUBLE,
    espacoLivreDisco2 DOUBLE,
    espacoLivreRam DOUBLE,
    dataHoraRegistro DATETIME,
    idServidor INT
);