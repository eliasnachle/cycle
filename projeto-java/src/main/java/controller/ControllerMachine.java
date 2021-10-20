package controller;

import java.util.List;
import model.MachineInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class ControllerMachine {
    private JdbcTemplate connection;
    
    public ControllerMachine() {
        ControllerConnection databaseConfig = new ControllerConnection();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
    }
    
    public void registerInDatabaseNewMachine(MachineInfo machineInfo) {
        System.out.println("Iniciando cadastro da maquina");
        
        connection.update("INSERT INTO tblMaquinas("
                + "apelidoMaquina, tipoMaquina, sistemaOperacionalMaquina, idProcessador,"
                + " modeloCpu, cpuFrequencia, modeloDisco, espacoTotalDisco, espacoTotalRam"
                + ") VALUES(?,?,?,?,?,?,?,?,?)",
                machineInfo.getApelidoMaquina(), machineInfo.getTipoMaquina(), 
                machineInfo.getSistemaOperacionalMaquina(), machineInfo.getIdProcessador(),
                machineInfo.getModeloCpu(), machineInfo.getCpuFrequencia(), machineInfo.getModeloDisco(),
                machineInfo.getEspacoTotalDisco(), machineInfo.getEspacoTotalRam());
        
    }
    
    public void consultMachineInfo(MachineInfo machineInfo){
        
        List<MachineInfo> machineInfoSelect = connection.query("SELECT * FROM tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfo.class), machineInfo.getIdProcessador());
        
        machineInfo.setApelidoMaquina(machineInfoSelect.get(0).getApelidoMaquina());
        machineInfo.setTipoMaquina(machineInfoSelect.get(0).getTipoMaquina());
        machineInfo.setSistemaOperacionalMaquina(machineInfoSelect.get(0).getSistemaOperacionalMaquina());
        machineInfo.setModeloCpu(machineInfoSelect.get(0).getModeloCpu());
        machineInfo.setCpuFrequencia(machineInfoSelect.get(0).getCpuFrequencia());
        machineInfo.setIdProcessador(machineInfoSelect.get(0).getIdProcessador());
        machineInfo.setModeloDisco(machineInfoSelect.get(0).getModeloDisco());
        machineInfo.setEspacoTotalDisco(machineInfoSelect.get(0).getEspacoTotalDisco());
        machineInfo.setEspacoTotalRam(machineInfoSelect.get(0).getEspacoTotalRam());
        machineInfo.setIdUnidade(machineInfoSelect.get(0).getIdUnidade());
        
    }
}
