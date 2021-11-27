package controller;

import connection.ConnectionSqlServer;
import java.io.IOException;
import java.util.List;
import logs.cycle.LogCycle;
import model.MachineInfoModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class ControllerMachineInfo {
    private JdbcTemplate connection;
    
    public ControllerMachineInfo() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
    }
    
    public void registerInDatabaseNewMachine(MachineInfoModel newMachine, String idContratante) throws IOException {
        
        System.out.println("Iniciando cadastro da maquina");
        // log cadastro da máquina
        LogCycle.guardarLog("Iniciando cadastro da maquina");
        
        connection.update("INSERT INTO tblMaquinas"
                + "(apelidoMaquina, tipoMaquina, sistemaOperacionalMaquina, idProcessador,"
                + " modeloCpu, cpuFrequencia, modeloDisco1, espacoTotalDisco1,"
                + "modeloDisco2, espacoTotalDisco2, espacoTotalRam, idUsuarioContratante)"
                + "VALUES(?,?,?,?,?,ROUND(?, 2, 1),?,ROUND(?, 2, 1),?,ROUND(?, 2, 1),ROUND(?, 2, 1), ?)",
                newMachine.getApelidoMaquina(), newMachine.getTipoMaquina(), 
                newMachine.getSistemaOperacionalMaquina(), newMachine.getIdProcessador(),
                newMachine.getModeloCpu(), newMachine.getCpuFrequencia(), newMachine.getModeloDisco1(),
                newMachine.getEspacoTotalDisco1(), newMachine.getModeloDisco2(),
                newMachine.getEspacoTotalDisco2(), newMachine.getEspacoTotalRam(), idContratante);
        
        System.out.println("Cadastro da maquina concluido");
        // log cadastro da máquina concuido
        LogCycle.guardarLog("Cadastro da maquina concluido");
    }
    
    public List<MachineInfoModel> consultMachineInfo(MachineInfoModel machineInfo){
        // log consulta sobre a maquina
        LogCycle.guardarLog("Fazendo consulta sobre a maquina");
        System.out.println("Fazendo consulta sobre a maquina");
        List<MachineInfoModel> machineInfoSelect = 
                connection.query("SELECT * FROM tblMaquinas WHERE idProcessador = ?",
                        new BeanPropertyRowMapper(MachineInfoModel.class),
                        machineInfo.getIdProcessador());
        
        return machineInfoSelect;
        
    }
}
