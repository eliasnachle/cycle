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

-- -----------------------------------------------------------------------------------------------------------

-- Configurando foreign key da tabela servidor
ALTER TABLE tblServidor ADD FOREIGN KEY (idAdministrator) REFERENCES tblAdministrator(idAdministrator);

-- Configurando foreign key da tabela registro
ALTER TABLE tblRegistrosServidor ADD FOREIGN KEY (idServidor) REFERENCES tblServidor(idServidor);

-- Configurando foreign key da tabela alerta
ALTER TABLE tblAlertasServidor ADD FOREIGN KEY (idRegistro) REFERENCES tblRegistrosServidor(idRegistro);