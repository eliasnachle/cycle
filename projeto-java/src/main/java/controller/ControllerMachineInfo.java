package controller;

import connection.ConnectionSqlServer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import loggers.Logge;
import model.MachineInfoModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class ControllerMachineInfo {
    private JdbcTemplate connection;
    private Logge logge;
    
    public ControllerMachineInfo() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
        this.logge = new Logge();
    }
    
    public void registerInDatabaseNewMachine(MachineInfoModel newMachine, String idContratante) {

        String dataLog = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        try {
            connection.update("INSERT INTO tblMaquinas"
                            + "(apelidoMaquina, tipoMaquina, sistemaOperacionalMaquina, idProcessador,"
                            + "modeloCpu, cpuFrequencia, modeloDisco1, espacoTotalDisco1,"
                            + "modeloDisco2, espacoTotalDisco2, espacoTotalRam, idUsuarioContratante)"
                            + "VALUES(?,?,?,?,?,ROUND(?, 2, 1),?,ROUND(?, 2, 1),?,ROUND(?, 2, 1),ROUND(?, 2, 1), ?)",
                    newMachine.getApelidoMaquina(), newMachine.getTipoMaquina(),
                    newMachine.getSistemaOperacionalMaquina(), newMachine.getIdProcessador(),
                    newMachine.getModeloCpu(), newMachine.getCpuFrequencia(), newMachine.getModeloDisco1(),
                    newMachine.getEspacoTotalDisco1(), newMachine.getModeloDisco2(),
                    newMachine.getEspacoTotalDisco2(), newMachine.getEspacoTotalRam(), idContratante);

            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora do insert: %s \n" +
                            "Inserindo dados da maquina... \n\n\n" +
                            "Dado inserido com sucesso \n\n " +
                            "Apelido da maquina: %s \n\n" +
                            "Tipo da maquina: %s \n\n" +
                            "Id Processador: %s\n\n" +
                            "---------------------###############################-----------------", dataLog, newMachine.getApelidoMaquina(), newMachine.getTipoMaquina(), newMachine.getIdProcessador()));

        } catch (Exception e){
            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora do insert: %s \n" +
                            "Inserindo dados da maquina... \n\n\n" +
                            "** Erro ao inserir o dado ** \n\n " +
                            "Apelido da maquina: %s \n\n" +
                            "Tipo da maquina: %s \n\n" +
                            "Id Processador: %s\n\n" +
                            "Exception: %s \n" +
                            "---------------------###############################-----------------", dataLog, newMachine.getApelidoMaquina(), newMachine.getIdProcessador(), newMachine.getTipoMaquina(), e ));
        }
    }
    
    public List<MachineInfoModel> consultMachineInfo(MachineInfoModel machineInfo) {

        String dataLog = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        List<MachineInfoModel> machineInfoSelect = new ArrayList<>();

        try {

            machineInfoSelect =
                    connection.query("SELECT * FROM tblMaquinas WHERE idProcessador = ?",
                            new BeanPropertyRowMapper(MachineInfoModel.class),
                            machineInfo.getIdProcessador());

            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                    "Data e hora da consulta: %s \n" +
                    "Procurando maquina pelo id do processador '%s' \n" +
                    "maquina encontrada \n\n" +
                    "---------------------###############################-----------------", dataLog, machineInfo.getIdProcessador() ));

        }catch (Exception e){
            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora da consulta: %s \n" +
                            "Procurando maquina pelo id do processador '%s' \n" +
                            "** Maquina não encontrada ** \n\n" +
                            "Exception: %s \n\n" +
                            "---------------------###############################-----------------", dataLog, machineInfo.getIdProcessador(), e));
        }

        return machineInfoSelect;
        
    }
}
