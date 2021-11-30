package br.com.cycle.controller;

import br.com.cycle.connection.ConnectionMySql;
import br.com.cycle.connection.ConnectionSqlServer;
import br.com.cycle.model.ServerModel;
import br.com.cycle.slack.Monitoration;
import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ControllerRegistryMachineServer {
    private JdbcTemplate connectionSqlServer;
    private JdbcTemplate connectionMySQl;

    public ControllerRegistryMachineServer() {
        ConnectionSqlServer databaseSqlServerConfig = new ConnectionSqlServer();
        ConnectionMySql databaseMySqlConfig = new ConnectionMySql();

        this.connectionSqlServer = new JdbcTemplate(databaseSqlServerConfig.getDataSource());
        this.connectionMySQl = new JdbcTemplate(databaseMySqlConfig.getDataSource());

    }

    public Boolean registerInDatabaseNewMachine(ServerModel serverModel, String idContratante) throws IOException {


        // Validar se server existe no sql Server
        List<ServerModel> sqlServerConsult =
                connectionSqlServer.query("SELECT * FROM tblServidor WHERE idProcessador = ?",
                        new BeanPropertyRowMapper(ServerModel.class),
                        serverModel.getIdProcessador());
        // Validar se server existe no mysql
        List<ServerModel> mySqlConsult =
                connectionMySQl.query("SELECT * FROM tblServidor WHERE idProcessador = ?",
                        new BeanPropertyRowMapper(ServerModel.class),
                        serverModel.getIdProcessador());


        if (sqlServerConsult.size() > 0 && mySqlConsult.size() > 0) {
            return false;
        } else {
            if(sqlServerConsult == null || sqlServerConsult.isEmpty()) {
                System.out.println("Inserindo novo servidor AWS MySql no Azure");
                this.connectionSqlServer.update("INSERT INTO tblServidor"
                                + "(apelidoServidor, sistemaOperacionalServidor, idProcessador,"
                                + " modeloCpu, cpuFrequencia, modeloDisco1, espacoTotalDisco1,"
                                + "modeloDisco2, espacoTotalDisco2, espacoTotalRam, idAdministrator)"
                                + "VALUES(?,?,?,?,ROUND(?, 2, 1),?,ROUND(?, 2, 1),?,ROUND(?, 2, 1),ROUND(?, 2, 1), ?)",
                        serverModel.getApelidoServidor(),
                        serverModel.getSistemaOperacionalServidor(), serverModel.getIdProcessador(),
                        serverModel.getModeloCpu(), serverModel.getCpuFrequencia(), serverModel.getModeloDisco1(),
                        serverModel.getEspacoTotalDisco1(), serverModel.getModeloDisco2(),
                        serverModel.getEspacoTotalDisco2(), serverModel.getEspacoTotalRam(), idContratante);

            }
            if(mySqlConsult == null || mySqlConsult.isEmpty()) {
                System.out.println("Inserindo novo servidor AWS MySql no MysqlLocal");
                this.connectionMySQl.update("INSERT INTO tblServidor"
                                + "(apelidoServidor, sistemaOperacionalServidor, idProcessador,"
                                + " modeloCpu, cpuFrequencia, modeloDisco1, espacoTotalDisco1,"
                                + "modeloDisco2, espacoTotalDisco2, espacoTotalRam, idAdministrator)"
                                + "VALUES(?,?,?,?,TRUNCATE(?,2),?,TRUNCATE(?,2),?,TRUNCATE(?,2),TRUNCATE(?,2), ?)",
                        serverModel.getApelidoServidor(),
                        serverModel.getSistemaOperacionalServidor(), serverModel.getIdProcessador(),
                        serverModel.getModeloCpu(), serverModel.getCpuFrequencia(), serverModel.getModeloDisco1(),
                        serverModel.getEspacoTotalDisco1(), serverModel.getModeloDisco2(),
                        serverModel.getEspacoTotalDisco2(), serverModel.getEspacoTotalRam(), idContratante);

            }
            return false;
        }
    }

}
