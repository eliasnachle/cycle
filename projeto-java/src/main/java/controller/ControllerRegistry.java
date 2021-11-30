package controller;

import com.github.britooo.looca.api.core.Looca;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import connection.ConnectionSqlServer;
import model.MachineInfoModel;
import model.MachineRegistryModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import slack.Monitoration;

public class ControllerRegistry {
    private JdbcTemplate connection;
    private Looca looca;
    private Monitoration monitoration = new Monitoration();

    public ControllerRegistry() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();

        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
        this.looca = new Looca();
    }

    public void registerInDatabaseNewRegistry(MachineInfoModel machineInfoModel, MachineRegistryModel machineRegistryModel) throws IOException, InterruptedException {

        Double disco = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / 1024.0 / 1024 / 1024;
        Double discoTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal() / 1024.0 / 1024 / 1024;
        Double validacao = disco - discoTotal;

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
        if(looca.getProcessador().getUso() > 92.0) {
            LocalDateTime horarioPC = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

            monitoration.enviarMensagem(String.format("A CPU está sobrecarregando \n" +
                    "Uso atual é de: %.2f", looca.getProcessador().getUso()));
            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idRegistro) VALUES (?,?,?,?,?)",
                    "CPU", "alta", "CPU acima do limite", horarioPC, machineInfoSelect.get(0).getIdMaquina());
        }
        if(looca.getMemoria().getEmUso() > 92.0) {
            LocalDateTime horarioPC = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

            monitoration.enviarMensagem(String.format("A RAM está sobrecarregando \n" +
                    "Uso atual é de: %.2f", looca.getProcessador().getUso()));
            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idRegistro) VALUES (?,?,?,?,?)",
                    "RAM", "alta", "RAM acima do limite", horarioPC, machineInfoSelect.get(0).getIdMaquina());
        }
        if(validacao < validacao * 0.9 ) {
            LocalDateTime horarioPC = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

            monitoration.enviarMensagem(String.format("A Disco está sobrecarregando \n" +
                    "Uso atual é de: %.2f", looca.getProcessador().getUso()));
            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idRegistro) VALUES (?,?,?,?,?)",
                    "Disco", "alta", "Disco acima do limite", horarioPC, machineInfoSelect.get(0).getIdMaquina());
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
