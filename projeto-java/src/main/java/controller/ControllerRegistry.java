package controller;

import com.github.britooo.looca.api.core.Looca;
import java.time.LocalDateTime;
import java.util.List;
import model.MachineInfo;
import model.MachineRegistry;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerRegistry {
    private JdbcTemplate connection;
    private Looca looca;
    
    public ControllerRegistry() {
        ControllerConnection databaseConfig = new ControllerConnection();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
        this.looca = new Looca();
    }
    
    public void registerInDatabaseNewRegistry() {
        
        List<MachineInfo> machineInfoSelect = connection.query("SELECT * FROM "
                + "tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfo.class), 
                looca.getProcessador().getId());
        
        connection.update("INSERT INTO tblRegistros(cpuEmUso, temperaturaCpu, espacoLivreDisco, espacoLivreRam, dataHoraRegistro, idMaquina) VALUES(?,?,ROUND(?, 2, 1), ROUND(?, 2, 1),?,?)",
                (double) Math.round(looca.getProcessador().getUso()), looca.getTemperatura().getTemperatura(),
                looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / 1024.0 / 1024 / 1024,
                looca.getMemoria().getDisponivel() / 1024.0 / 1024 / 1024, 
                LocalDateTime.now(), machineInfoSelect.get(0).getIdMaquina());
       
    }
    
    public void consultMachineRegister(MachineRegistry machineRegistry) {
        
        List<MachineInfo> machineInfoSelect = connection.query("SELECT * FROM "
                + "tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfo.class), 
                looca.getProcessador().getId());
        
        List<MachineRegistry> registrySelect = connection.query("SELECT TOP 1 * FROM tblRegistros WHERE idMaquina = ? ORDER BY idRegistro DESC;", new BeanPropertyRowMapper(MachineRegistry.class), machineInfoSelect.get(0).getIdMaquina());
        
        machineRegistry.setIdRegistro(registrySelect.get(0).getIdRegistro());
        machineRegistry.setCpuEmUso(registrySelect.get(0).getCpuEmUso());
        machineRegistry.setEspacoLivreDisco(registrySelect.get(0).getEspacoLivreDisco());
        machineRegistry.setEspacoLivreRam(registrySelect.get(0).getEspacoLivreRam());
        machineRegistry.setDataHoraRegistro(registrySelect.get(0).getIdRegistro().toString());
        machineRegistry.setIdMaquina(registrySelect.get(0).getIdMaquina());
        
    }
}
