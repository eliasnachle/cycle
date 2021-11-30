package br.com.cycle.controller;

import br.com.cycle.connection.ConnectionMySql;
import br.com.cycle.connection.ConnectionSqlServer;
import br.com.cycle.model.ServerModel;
import br.com.cycle.model.ServerRegistryModel;
import br.com.cycle.slack.Monitoration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ControllerRegistryServerInfo {
    private JdbcTemplate connectionSqlServer;
    private JdbcTemplate connectionMySQl;

    private Monitoration monitoration;


    public ControllerRegistryServerInfo() {
        ConnectionSqlServer databaseSqlServerConfig = new ConnectionSqlServer();
        ConnectionMySql databaseMySqlConfig = new ConnectionMySql();

        this.connectionSqlServer = new JdbcTemplate(databaseSqlServerConfig.getDataSource());
        this.connectionMySQl = new JdbcTemplate(databaseMySqlConfig.getDataSource());
        this.monitoration = new Monitoration();
    }

    public void insertNewRegistry(ServerModel serverModel, ServerRegistryModel serverRegistryModel) throws IOException{

        List<ServerModel> sqlServerConsult = connectionSqlServer.query("SELECT * FROM "
                        + "tblServidor WHERE idProcessador = ?", new BeanPropertyRowMapper<>(ServerModel.class),
                serverModel.getIdProcessador());

        List<ServerModel> mySqlConsult = connectionMySQl.query("SELECT * FROM "
                        + "tblServidor WHERE idProcessador = ?", new BeanPropertyRowMapper<>(ServerModel.class),
                serverModel.getIdProcessador());

        if (sqlServerConsult.get(0).getModeloDisco2().equals("Sem segundo disco")) {
            connectionSqlServer.update("INSERT INTO tblRegistrosServidor(cpuEmUso, espacoLivreDisco1, espacoLivreDisco2, espacoLivreRam, dataHoraRegistro, idServidor) "
                            + "VALUES(ROUND(?, 2, 1), ROUND(?, 2, 1), ?, ROUND(?, 2, 1), ?, ?)",
                    serverRegistryModel.getCpuEmUso(),
                    serverRegistryModel.getEspacoLivreDisco1(),
                    0.0,
                    serverRegistryModel.getEspacoLivreRam(),
                    LocalDateTime.now(),
                    sqlServerConsult.get(0).getIdServidor());
        } else {
            connectionSqlServer.update("INSERT INTO tblRegistrosServidor(cpuEmUso, espacoLivreDisco1, espacoLivreDisco2, espacoLivreRam, dataHoraRegistro, idServidor) "
                            + "VALUES(ROUND(?, 2, 1), TRUNCATE(?,2), ROUND(?, 2, 1), ROUND(?, 2, 1), ?, ?)",
                    serverRegistryModel.getCpuEmUso(),
                    serverRegistryModel.getEspacoLivreDisco1(),
                    serverRegistryModel.getEspacoLivreDisco1(),
                    serverRegistryModel.getEspacoLivreRam(),
                    LocalDateTime.now(),
                    sqlServerConsult.get(0).getIdServidor());
        }

        if (mySqlConsult.get(0).getModeloDisco2().equals("Sem segundo disco")) {
            connectionMySQl.update("INSERT INTO tblRegistrosServidor(cpuEmUso, espacoLivreDisco1, espacoLivreDisco2, espacoLivreRam, dataHoraRegistro, idServidor) "
                            + "VALUES(TRUNCATE(?,2), TRUNCATE(?,2), ?, TRUNCATE(?,2), ?, ?)",
                    serverRegistryModel.getCpuEmUso(),
                    serverRegistryModel.getEspacoLivreDisco1(),
                    0.0,
                    serverRegistryModel.getEspacoLivreRam(),
                    LocalDateTime.now(),
                    mySqlConsult.get(0).getIdServidor());
        } else {
            connectionMySQl.update("INSERT INTO tblRegistrosServidor(cpuEmUso, espacoLivreDisco1, espacoLivreDisco2, espacoLivreRam, dataHoraRegistro, idServidor) "
                            + "VALUES(TRUNCATE(?,2), TRUNCATE(?,2), TRUNCATE(?,2), TRUNCATE(?,2), ?, ?)",
                    serverRegistryModel.getCpuEmUso(),
                    serverRegistryModel.getEspacoLivreDisco1(),
                    serverRegistryModel.getEspacoLivreDisco1(),
                    serverRegistryModel.getEspacoLivreRam(),
                    LocalDateTime.now(),
                    mySqlConsult.get(0).getIdServidor());
        }
    }
}
