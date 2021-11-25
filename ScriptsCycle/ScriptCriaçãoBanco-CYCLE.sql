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
    temperaturaCpu DOUBLE,
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

CREATE TABLE tblRegistrosServidor (
	idRegistroServidor INT PRIMARY KEY AUTO_INCREMENT,
    sistemaOperacionalMaquina VARCHAR(15), 
    modeloCpu VARCHAR(80), 
    cpuEmUso DOUBLE,
	espacoTotalDisco1 DOUBLE, 
    espacoTotalDisco2 DOUBLE,
	espacoLivreDisco1 DOUBLE,
    espacoLivreDisco2 DOUBLE,
    espacoTotalRam DOUBLE, 
    espacoLivreRam DOUBLE,
    dataHoraRegistro DATETIME
);

-- Configurando foreign key da tabela contratante
ALTER TABLE tblUsuariosContratante ADD FOREIGN KEY (idPlano) REFERENCES tblPlanos(idPlano);

-- Configurando foreign key da tabela usuario suporte
ALTER TABLE tblUsuariosSuporte ADD FOREIGN KEY (idUsuarioContratante) REFERENCES tblUsuariosContratante(idUsuarioContratante);

-- Configurando foreign key da tabela maquina
ALTER TABLE tblMaquinas ADD FOREIGN KEY (idUsuarioContratante) REFERENCES tblUsuariosContratante(idUsuarioContratante);

-- Configurando foreign key da tabela registro
ALTER TABLE tblRegistros ADD FOREIGN KEY (idMaquina) REFERENCES tblMaquinas(idMaquina);

-- Configurando foreign key da tabela alerta
ALTER TABLE tblAlertas ADD FOREIGN KEY (idRegistro) REFERENCES tblRegistros(idRegistro);