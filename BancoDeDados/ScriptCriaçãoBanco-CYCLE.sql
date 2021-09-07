CREATE DATABASE cycle;
USE cycle;

CREATE TABLE tbl_unidade (
id_unidade INT PRIMARY KEY AUTO_INCREMENT,
nome_unidade VARCHAR(60),
endereco_unidade VARCHAR(255),
telefone_unidade VARCHAR(14),
cnpj_unidade VARCHAR(14)
);

CREATE TABLE tbl_codigo_uf (
id_codigo_uf INT PRIMARY KEY AUTO_INCREMENT,
desc_codigo_uf VARCHAR(2)
);

CREATE TABLE tbl_matriz (
id_matriz INT PRIMARY KEY AUTO_INCREMENT,
nome_matriz VARCHAR(70),
endereco_matriz VARCHAR(255),
cnpj_matriz VARCHAR(14)
);

CREATE TABLE tbl_login_suporte (
id_sup INT PRIMARY KEY AUTO_INCREMENT,
nome_sup VARCHAR(80),
login_sup VARCHAR(30),
senha_sup VARCHAR(20)
);

CREATE TABLE tbl_maquina (
id_maquina INT PRIMARY KEY AUTO_INCREMENT,
nome_maquina VARCHAR(30),
sistema_operacional_maquina VARCHAR(15),
modelo_cpu VARCHAR(50),
qnt_memoria_ram VARCHAR(5),
qnt_disco VARCHAR(6),
tipo_maquina VARCHAR(11)
CHECK (tipo_maquina = "totem" or tipo_maquina = "atendimento")
);

CREATE TABLE tbl_registro (
id_registro INT PRIMARY KEY AUTO_INCREMENT,
cpu_maquina FLOAT,
memoria_ram_maquina FLOAT,
disco_maquina FLOAT,
data_registro DATETIME
);
