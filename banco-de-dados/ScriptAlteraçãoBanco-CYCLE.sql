-- Configurando foreign key da tabela unidade
ALTER TABLE tblUnidade ADD FOREIGN KEY (idUsuarioContratante) REFERENCES tblUsuarioContratante(idUsuarioContratante);
ALTER TABLE tblUnidade ADD FOREIGN KEY (idCodigoUf) REFERENCES tblCodigoUf(idCodigoUf);

-- Configurando foreign key da tabela contratante
ALTER TABLE tblUsuarioContratante ADD FOREIGN KEY (idPlano) REFERENCES tblPlano(idPlano);

-- Configurando foreign key da tabela usuario suporte
ALTER TABLE tblUsuarioSuporte ADD FOREIGN KEY (idUsuarioContratante) REFERENCES tblUsuarioContratante(idUsuarioContratante);

-- Configurando foreign key da tabela maquina
ALTER TABLE tblMaquina ADD FOREIGN KEY (idUnidade) REFERENCES tblUnidade(idUnidade);

-- Configurando foreign key da tabela registro
ALTER TABLE tblRegistro ADD FOREIGN KEY (idMaquina) REFERENCES tblMaquina(idMaquina);