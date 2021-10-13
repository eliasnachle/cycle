-- Configurando foreign key da tabela unidade
ALTER TABLE tblUnidades ADD FOREIGN KEY (idUsuarioContratante) REFERENCES tblUsuariosContratante(idUsuarioContratante);
ALTER TABLE tblUnidades ADD FOREIGN KEY (idCodigoUf) REFERENCES tblCodigosUf(idCodigoUf);

-- Configurando foreign key da tabela contratante
ALTER TABLE tblUsuariosContratante ADD FOREIGN KEY (idPlano) REFERENCES tblPlanos(idPlano);

-- Configurando foreign key da tabela usuario suporte
ALTER TABLE tblUsuariosSuporte ADD FOREIGN KEY (idUsuarioContratante) REFERENCES tblUsuariosContratante(idUsuarioContratante);

-- Configurando foreign key da tabela maquina
ALTER TABLE tblMaquinas ADD FOREIGN KEY (idUnidade) REFERENCES tblUnidades(idUnidade);

-- Configurando foreign key da tabela registro
ALTER TABLE tblRegistros ADD FOREIGN KEY (idMaquina) REFERENCES tblMaquinas(idMaquina);

-- Configurando foreign key da tabela alerta
ALTER TABLE tblAlertas ADD FOREIGN KEY (idRegistro) REFERENCES tblRegistros(idRegistro);