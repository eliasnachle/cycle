package controller;

import connection.ConnectionSqlServer;
import com.github.britooo.looca.api.core.Looca;
import java.util.List;
import model.MachineInfoModel;
import model.MachineRegistryModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerRegistry {
    private JdbcTemplate connection;
    private Looca looca;
    
    public ControllerRegistry() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
        this.looca = new Looca();
    }
    
    public void registerInDatabaseNewRegistry(MachineInfoModel machineInfo, MachineRegistryModel machineRegistryModel) {
        
        List<MachineInfoModel> machineInfoSelect = connection.query("SELECT * FROM "
                + "tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfoModel.class), 
                machineInfo.getIdProcessador());
        
        if (machineInfoSelect.get(0).getModeloDisco2().equals("Sem segundo disco")) {
            connection.update("INSERT INTO tblRegistros(cpuEmUso, espacoLivreDisco1, espacoLivreDisco2, espacoLivreRam, dataHoraRegistro, idMaquina) "
                    + "VALUES(ROUND(?, 2, 1), ROUND(?, 2, 1), ?, ROUND(?, 2, 1), CURRENT_TIMESTAMP, ?)",
                    machineRegistryModel.getCpuEmUso(),
                    machineRegistryModel.getEspacoLivreDisco1(),
                    0.0,
                    machineRegistryModel.getEspacoLivreRam(), 
                    machineInfoSelect.get(0).getIdMaquina());
        } else {
            connection.update("INSERT INTO tblRegistros(cpuEmUso, espacoLivreDisco1, espacoLivreDisco2, espacoLivreRam, dataHoraRegistro, idMaquina) "
                    + "VALUES(ROUND(?, 2, 1), ROUND(?, 2, 1), ROUND(?, 2, 1), ROUND(?, 2, 1), CURRENT_TIMESTAMP, ?)",
                    machineRegistryModel.getCpuEmUso(),
                    machineRegistryModel.getEspacoLivreDisco1(),
                    machineRegistryModel.getEspacoLivreDisco2(),
                    machineRegistryModel.getEspacoLivreRam(), 
                    machineInfoSelect.get(0).getIdMaquina());
        }
    }
    
    public List<MachineRegistryModel> consultMachineRegister(MachineInfoModel machineInfo) {
        
        List<MachineInfoModel> machineInfoSelect = connection.query("SELECT * FROM "
                + "tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfoModel.class), 
                machineInfo.getIdProcessador());
        
        List<MachineRegistryModel> registrySelect = connection.query("SELECT TOP 1 * FROM tblRegistros WHERE idMaquina = ? ORDER BY idRegistro DESC;", 
                new BeanPropertyRowMapper(MachineRegistryModel.class), 
                machineInfoSelect.get(0).getIdMaquina());
        
        return registrySelect;
    }
}
