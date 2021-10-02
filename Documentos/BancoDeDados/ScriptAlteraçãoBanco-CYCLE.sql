-- Configurando foreign key da tabela unidade
ALTER TABLE tbl_unidade ADD FOREIGN KEY (id_usuario_contratante) REFERENCES tbl_usuario_contratante(id_usuario_contratante);
ALTER TABLE tbl_unidade ADD FOREIGN KEY (id_codigo_uf) REFERENCES tbl_codigo_uf(id_codigo_uf);

-- Configurando foreign key da tabela contratante
ALTER TABLE tbl_usuario_contratante ADD FOREIGN KEY (id_codigo_uf) REFERENCES tbl_codigo_uf(id_codigo_uf);
ALTER TABLE tbl_usuario_contratante ADD FOREIGN KEY (id_plano) REFERENCES tbl_plano(id_plano);

-- Configurando foreign key da tabela usuario
ALTER TABLE tbl_usuario_suporte ADD FOREIGN KEY (id_usuario_contratante) REFERENCES tbl_usuario_contratante(id_usuario_contratante);

-- Configurando foreign key da tabela maquina
ALTER TABLE tbl_maquina ADD FOREIGN KEY (id_unidade) REFERENCES tbl_unidade(id_unidade);

-- Configurando foreign key da tabela registro ram
ALTER TABLE tbl_registro_ram ADD FOREIGN KEY (id_maquina) REFERENCES tbl_maquina(id_maquina);

-- Configurando foreign key da tabela registro cpu
ALTER TABLE tbl_registro_cpu ADD FOREIGN KEY (id_maquina) REFERENCES tbl_maquina(id_maquina);

-- Configurando foreign key da tabela registro disco
ALTER TABLE tbl_registro_disco ADD FOREIGN KEY (id_maquina) REFERENCES tbl_maquina(id_maquina);