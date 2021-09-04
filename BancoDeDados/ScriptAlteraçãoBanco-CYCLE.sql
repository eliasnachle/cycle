ALTER TABLE tbl_gerencia ADD COLUMN id_unidade INT;
ALTER TABLE tbl_gerencia ADD FOREIGN KEY (id_unidade) REFERENCES tbl_unidade(id_unidade);

ALTER TABLE tbl_unidade ADD COLUMN id_matriz INT;
ALTER TABLE tbl_unidade ADD FOREIGN KEY (id_matriz) REFERENCES tbl_matriz(id_matriz);
ALTER TABLE tbl_unidade ADD COLUMN id_suporte INT;
ALTER TABLE tbl_unidade ADD FOREIGN KEY (id_suporte) REFERENCES tbl_equipe_sup(id_sup);
ALTER TABLE tbl_unidade ADD COLUMN id_cod_uf INT;
ALTER TABLE tbl_unidade ADD FOREIGN KEY (id_cod_uf) REFERENCES tbl_codigo_uf(id_cod_uf);

ALTER TABLE tbl_matriz ADD COLUMN id_cod_uf INT;
ALTER TABLE tbl_matriz ADD FOREIGN KEY (id_cod_uf) REFERENCES tbl_codigo_uf(id_cod_uf);

ALTER TABLE tbl_equipe_sup ADD COLUMN id_supervisor INT;
ALTER TABLE tbl_equipe_sup ADD FOREIGN KEY (id_unidade) REFERENCES tbl_equipe_sup(id_sup);

ALTER TABLE tbl_maquina ADD COLUMN id_unidade INT;
ALTER TABLE tbl_maquina ADD FOREIGN KEY (id_unidade) REFERENCES tbl_unidade(id_unidade);

ALTER TABLE tbl_registro ADD COLUMN id_maquina INT;
ALTER TABLE tbl_registro ADD FOREIGN KEY (id_maquina) REFERENCES tbl_maquina(id_maquina);