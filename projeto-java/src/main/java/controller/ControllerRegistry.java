package controller;

import com.github.britooo.looca.api.core.Looca;
import java.time.LocalDateTime;
import java.util.List;
import model.MachineInfoModel;
import model.MachineRegistryModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerRegistry {
    private JdbcTemplate connection;
    private Looca looca;
    
    public ControllerRegistry() {
        ControllerConnectionSqlServer databaseConfig = new ControllerConnectionSqlServer();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
        this.looca = new Looca();
    }
    
    public void registerInDatabaseNewRegistry() {
        
        List<MachineInfoModel> machineInfoSelect = connection.query("SELECT * FROM "
                + "tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfoModel.class), 
                looca.getProcessador().getId());
        
        connection.update("INSERT INTO tblRegistros(cpuEmUso, temperaturaCpu, espacoLivreDisco, espacoLivreRam, dataHoraRegistro, idMaquina) "
                + "VALUES(?,?,ROUND(?, 2, 1), ROUND(?, 2, 1),?,?)",
                (double) Math.round(looca.getProcessador().getUso()), looca.getTemperatura().getTemperatura(),
                looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / 1024.0 / 1024 / 1024,
                looca.getMemoria().getDisponivel() / 1024.0 / 1024 / 1024, 
                LocalDateTime.now(), machineInfoSelect.get(0).getIdMaquina());
       
    }
    
    public List<MachineRegistryModel> consultMachineRegister() {
        
        MachineRegistryModel machineRegistry = new MachineRegistryModel();
        
        List<MachineInfoModel> machineInfoSelect = connection.query("SELECT * FROM "
                + "tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfoModel.class), 
                looca.getProcessador().getId());
        
        List<MachineRegistryModel> registrySelect = connection.query("SELECT TOP 1 * FROM tblRegistros WHERE idMaquina = ? ORDER BY idRegistro DESC;", 
                new BeanPropertyRowMapper(MachineRegistryModel.class), 
                machineInfoSelect.get(0).getIdMaquina());
        
        return registrySelect;
    }
}
