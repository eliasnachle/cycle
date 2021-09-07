-- Adição de foreign key na tabela unidade
ALTER TABLE tbl_unidade ADD COLUMN id_matriz INT;
ALTER TABLE tbl_unidade ADD FOREIGN KEY (id_matriz) REFERENCES tbl_matriz(id_matriz);
ALTER TABLE tbl_unidade ADD COLUMN id_codigo_uf INT;
ALTER TABLE tbl_unidade ADD FOREIGN KEY (id_codigo_uf) REFERENCES tbl_codigo_uf(id_codigo_uf);

-- Adição de foreign key na tabela matriz
ALTER TABLE tbl_matriz ADD COLUMN id_codigo_uf INT;
ALTER TABLE tbl_matriz ADD FOREIGN KEY (id_codigo_uf) REFERENCES tbl_codigo_uf(id_codigo_uf);

-- Adição de foreign key na tabela login suporte
ALTER TABLE tbl_login_suporte ADD COLUMN id_matriz INT;
ALTER TABLE tbl_login_suporte ADD FOREIGN KEY (id_matriz) REFERENCES tbl_matriz(id_matriz);
ALTER TABLE tbl_login_suporte ADD COLUMN id_supervisor INT;
ALTER TABLE tbl_login_suporte ADD FOREIGN KEY (id_supervisor) REFERENCES tbl_login_suporte(id_sup);

-- Adição de foreign key na tabela maquina
ALTER TABLE tbl_maquina ADD COLUMN id_unidade INT;
ALTER TABLE tbl_maquina ADD FOREIGN KEY (id_unidade) REFERENCES tbl_unidade(id_unidade);

-- Adição de foreign key na tabela registro
ALTER TABLE tbl_registro ADD COLUMN id_maquina INT;
ALTER TABLE tbl_registro ADD FOREIGN KEY (id_maquina) REFERENCES tbl_maquina(id_maquina);