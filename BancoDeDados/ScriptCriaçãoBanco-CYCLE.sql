-- CRIANDO O BANCO DE DADOS
CREATE DATABASE CYCLE;

USE CYCLE;

-- CRIANDO AS TABELAS
CREATE TABLE tbl_gerencia (
id_gerente INT PRIMARY KEY AUTO_INCREMENT,
nome_gerente VARCHAR(80),
email_contato_gerente VARCHAR(80),
senha_gerente VARCHAR(20),
telefone_contato_gerente VARCHAR(14)
);

CREATE TABLE tbl_equipe_sup (
id_sup INT PRIMARY KEY AUTO_INCREMENT,
nome_sup VARCHAR(80),
email_sup VARCHAR(80),
senha_sup VARCHAR(20)
);

CREATE TABLE tbl_unidade (
id_unidade INT PRIMARY KEY AUTO_INCREMENT,
nome_unidade VARCHAR(60),
endereco_unidade VARCHAR(255),
contato_unidade VARCHAR(14),
cnpj_unidade VARCHAR(14)
);

CREATE TABLE tbl_codigo_uf (
id_codigo INT PRIMARY KEY AUTO_INCREMENT,
desc_cod_uf VARCHAR(2)
);

CREATE TABLE tbl_matriz (
id_matriz INT PRIMARY KEY AUTO_INCREMENT,
nome_matriz VARCHAR(70),
endereco_matriz VARCHAR(255),
cnpj_matriz VARCHAR(14)
);

CREATE TABLE tbl_maquina (
id_totem INT PRIMARY KEY AUTO_INCREMENT,
apelido_maquina VARCHAR(30),
sistema_operacional_maquina VARCHAR(15),
modelo_CPU VARCHAR(50),
qnt_memoria_ram VARCHAR(5),
qnt_memoria_disco VARCHAR(6),
tipo_maquina VARCHAR(10)
);

CREATE TABLE tbl_registro (
id_registro INT PRIMARY KEY AUTO_INCREMENT,
cpu FLOAT,
memoria_ram FLOAT,
memoria_disco FLOAT,
data_hora_registro DATETIME
);