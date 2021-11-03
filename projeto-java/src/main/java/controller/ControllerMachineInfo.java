package controller;

import java.util.List;
import model.MachineInfoModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class ControllerMachineInfo {
    private JdbcTemplate connection;
    
    public ControllerMachineInfo() {
        ControllerConnectionSqlServer databaseConfig = new ControllerConnectionSqlServer();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
    }
    
    public void registerInDatabaseNewMachine(MachineInfoModel machineInfo) {
        
        System.out.println("Iniciando cadastro da maquina");

        connection.update("INSERT INTO tblMaquinas"
                + "(apelidoMaquina, tipoMaquina, sistemaOperacionalMaquina, idProcessador,"
                + " modeloCpu, cpuFrequencia, modeloDisco, espacoTotalDisco, espacoTotalRam)"
                + "VALUES(?,?,?,?,?,?,?,?,?)",
                machineInfo.getApelidoMaquina(), machineInfo.getTipoMaquina(), 
                machineInfo.getSistemaOperacionalMaquina(), machineInfo.getIdProcessador(),
                machineInfo.getModeloCpu(), machineInfo.getCpuFrequencia(), machineInfo.getModeloDisco(),
                machineInfo.getEspacoTotalDisco(), machineInfo.getEspacoTotalRam());
        
        System.out.println("Cadastro da maquina concluido");
    }
    
    public List<MachineInfoModel> consultMachineInfo(MachineInfoModel machineInfo){
        
        System.out.println("Fazendo consulta sobre a maquina");
        List<MachineInfoModel> machineInfoSelect = 
                connection.query("SELECT * FROM tblMaquinas WHERE idProcessador = ?",
                        new BeanPropertyRowMapper(MachineInfoModel.class),
                        machineInfo.getIdProcessador());
        
        return machineInfoSelect;
        
    }
}
