CREATE DATABASE IF NOT EXISTS cycle;

USE cycle;

CREATE TABLE IF NOT EXISTS tbl_unidade (
	id_unidade INT PRIMARY KEY,
	nome_unidade VARCHAR(60),
	endereco_unidade VARCHAR(255),
	telefone_unidade VARCHAR(14),
	cnpj_unidade VARCHAR(14),
	id_usuario_contratante INT,
	id_codigo_uf INT
);

CREATE TABLE IF NOT EXISTS tbl_usuario_contratante(
	id_usuario_contratante INT PRIMARY KEY,
	nome_contratante VARCHAR(80),
	email_contratante VARCHAR(80),
	senha_contratante VARCHAR(20),
	cnpf_contratante VARCHAR(14),
	id_codigo_uf INT,
	id_plano INT
);

CREATE TABLE IF NOT EXISTS tbl_usuario_suporte (
	id_sup INT PRIMARY KEY,
	nome_suporte VARCHAR(80),
	login_suporte VARCHAR(30),
	senha_suporte VARCHAR(20),
	id_usuario_contratante INT
);

CREATE TABLE IF NOT EXISTS tbl_codigo_uf (
	id_codigo_uf INT PRIMARY KEY,
	desc_codigo_uf varchar(2)
);

CREATE TABLE IF NOT EXISTS tbl_plano (
	id_plano INT PRIMARY KEY,
	nome_plano VARCHAR(20),
	qnt_maquinas FLOAT,
	valor_plano FLOAT
);

CREATE TABLE IF NOT EXISTS tbl_maquina (
	id_maquina INT PRIMARY KEY,
	apelido_maquina VARCHAR(30),
	sistema_operacional_maquina VARCHAR(15),
	modelo_cpu VARCHAR(50),
	id_processador VARCHAR(20),
	tipo_maquina VARCHAR(10),
	id_unidade INT
);

CREATE TABLE IF NOT EXISTS tbl_registro_ram (
	id_registro_ram INT PRIMARY KEY,
	memoria_ram_total FLOAT,
	memoria_ram_em_uso FLOAT,
	memoria_ram_livre FLOAT,
	data_hora_registro DATETIME,
	id_maquina INT
);

CREATE TABLE IF NOT EXISTS tbl_registro_cpu (
	id_registro_cpu INT PRIMARY KEY,	
	cpu_em_uso FLOAT,
	cpu_frequencia FLOAT,
	temperatura_cpu FLOAT,
	data_hora_registro DATETIME,
	id_maquina INT
);

CREATE TABLE IF NOT EXISTS tbl_registro_disco (
	id_registro_disco INT PRIMARY KEY, 
	tipo_disco VARCHAR(4),
	espaco_total_disco FLOAT,
	espaco_ocupado_disco FLOAT,
	espaco_livre_disco FLOAT,
	data_hora_registro DATETIME,
	id_maquina INT
);