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
    
    public void registerInDatabaseNewRegistry() {
        
        List<MachineInfoModel> machineInfoSelect = connection.query("SELECT * FROM "
                + "tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfoModel.class), 
                looca.getProcessador().getId());
        
        if (machineInfoSelect.get(0).getModeloDisco2().equals("Sem segundo disco")) {
            connection.update("INSERT INTO tblRegistros(cpuEmUso, espacoLivreDisco1, espacoLivreDisco2, espacoLivreRam, dataHoraRegistro, idMaquina) "
                    + "VALUES(ROUND(?, 2, 1), ROUND(?, 2, 1), ?, ROUND(?, 2, 1), CURRENT_TIMESTAMP, ?)",
                    looca.getProcessador().getUso(),
                    looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / 1024.0 / 1024 / 1024,
                    0.0,
                    looca.getMemoria().getDisponivel() / 1024.0 / 1024 / 1024, 
                    machineInfoSelect.get(0).getIdMaquina());
        } else {
            connection.update("INSERT INTO tblRegistros(cpuEmUso, espacoLivreDisco1, espacoLivreDisco2, espacoLivreRam, dataHoraRegistro, idMaquina) "
                    + "VALUES(ROUND(?, 2, 1), ROUND(?, 2, 1), ROUND(?, 2, 1), ROUND(?, 2, 1), CURRENT_TIMESTAMP, ?)",
                    looca.getProcessador().getUso(),
                    looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / 1024.0 / 1024 / 1024,
                    looca.getGrupoDeDiscos().getVolumes().get(1).getDisponivel() / 1024.0 / 1024 / 1024,
                    looca.getMemoria().getDisponivel() / 1024.0 / 1024 / 1024, 
                    machineInfoSelect.get(0).getIdMaquina());
        }
    }
    
    public List<MachineRegistryModel> consultMachineRegister() {
        
        List<MachineInfoModel> machineInfoSelect = connection.query("SELECT * FROM "
                + "tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfoModel.class), 
                looca.getProcessador().getId());
        
        List<MachineRegistryModel> registrySelect = connection.query("SELECT TOP 1 * FROM tblRegistros WHERE idMaquina = ? ORDER BY idRegistro DESC;", 
                new BeanPropertyRowMapper(MachineRegistryModel.class), 
                machineInfoSelect.get(0).getIdMaquina());
        
        return registrySelect;
    }
}
