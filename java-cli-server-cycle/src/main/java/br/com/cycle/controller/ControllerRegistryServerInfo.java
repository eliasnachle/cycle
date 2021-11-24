package br.com.cycle.controller;

import br.com.cycle.connection.ConnectionSqlServer;
import br.com.cycle.model.ModelServerInfo;
import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerRegistryServerInfo {
    private JdbcTemplate connection;

    public ControllerRegistryServerInfo() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();

        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
    }

    public void insertNewRegistry(ModelServerInfo modelServerInfo, Boolean loginValidado){

        if (loginValidado == false){
            System.out.println("Registrando informações da maquina");

            connection.update("INSERT INTO tblRegistrosServidor" +
                    "(sistemaOperacionalMaquina, modeloCpu, cpuEmUso, " +
                    "espacoTotalDisco1, espacoTotalDisco2, " +
                    "espacoLivreDisco1, espacoLivreDisco2," +
                    "espacoTotalRam, espacoLivreRam, dataHoraRegistro)" +
                    "values (?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)", modelServerInfo.getSistemaOperacionalMaquina(), modelServerInfo.getModeloCpu(),
                    modelServerInfo.getCpuEmUso(), modelServerInfo.getEspacoTotalDisco1(),modelServerInfo.getEspacoTotalDisco2(),
                    modelServerInfo.getEspacoTotalDisco1(), modelServerInfo.getEspacoTotalDisco2(),
                    modelServerInfo.getEspacoTotalRam(),modelServerInfo.getEspacoLivreRam());
        } else {

        }

    }
}
