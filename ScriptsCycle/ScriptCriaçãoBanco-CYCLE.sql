CREATE DATABASE IF NOT EXISTS cycle;

USE cycle;

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

CREATE TABLE tblAlertasServidor (
	idAlerta INT PRIMARY KEY AUTO_INCREMENT,
    componenteInstavel VARCHAR(10),
    nivelCriticidade VARCHAR(10),
    descAlerta VARCHAR(255),
    dataHoraAlerta DATETIME,
    idRegistro INT
);
-- -----------------------------------------------------------------------------------------------------------

-- Configurando foreign key da tabela servidor
ALTER TABLE tblServidor ADD FOREIGN KEY (idAdministrator) REFERENCES tblAdministrator(idAdministrator);

-- Configurando foreign key da tabela registro
ALTER TABLE tblRegistrosServidor ADD FOREIGN KEY (idServidor) REFERENCES tblServidor(idServidor);

-- Configurando foreign key da tabela alerta
ALTER TABLE tblAlertasServidor ADD FOREIGN KEY (idRegistro) REFERENCES tblRegistrosServidor(idRegistro);

INSERT INTO tblAdministrator (nomeAdministrator, emailAdministrator, senhaAdministrator) VALUES ("Cycle", "cycle.adm@cycle_sp.com.br", "123");