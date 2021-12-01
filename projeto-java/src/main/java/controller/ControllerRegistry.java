package controller;

<<<<<<< HEAD
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
=======
import com.github.britooo.looca.api.core.Looca;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
>>>>>>> origin
import java.util.List;

import connection.ConnectionSqlServer;
import model.MachineInfoModel;
import model.MachineRegistryModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import slack.Monitoration;

public class ControllerRegistry {
<<<<<<< HEAD
    private final JdbcTemplate connection;
    private final Monitoration monitoration = new Monitoration();
=======
    private JdbcTemplate connection;
    private Looca looca;
    private Monitoration monitoration = new Monitoration();
>>>>>>> origin

    public ControllerRegistry() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();

        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
    }

<<<<<<< HEAD
    public void registerInDatabaseNewRegistry(MachineInfoModel machineInfoModel, MachineRegistryModel machineRegistryModel, ControllerMachineInfo controllerMachineInfo) throws IOException, InterruptedException {

        List<MachineInfoModel> machineInfoSelect = new ArrayList<>();

        try {
            machineInfoSelect = controllerMachineInfo.consultMachineInfo(machineInfoModel);
        }catch (Exception e){
            System.out.println(e);
        }

        try {
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
                                + "VALUES(ROUND(?, 2, 1), ROUND(?, 2, 1), ?, ROUND(?, 2, 1), CURRENT_TIMESTAMP, ?)",
                        machineRegistryModel.getCpuEmUso(),
                        machineRegistryModel.getEspacoLivreDisco1(),
                        machineRegistryModel.getEspacoLivreDisco2(),
                        machineRegistryModel.getEspacoLivreRam(),
                        machineInfoSelect.get(0).getIdMaquina());
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        verifyAlert(machineInfoModel, machineRegistryModel, machineInfoSelect.get(0).getIdMaquina());
    }

    public void verifyAlert(MachineInfoModel machineInfoModel, MachineRegistryModel machineRegistryModel, String idMaquina) {

        LocalDateTime horarioPC = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

        Double disco1Total = machineInfoModel.getEspacoTotalDisco1();
        Double disco1Disponivel = machineRegistryModel.getEspacoLivreDisco1();

        Double disco2Total = machineInfoModel.getEspacoTotalDisco2();
        Double disco2Disponivel = machineRegistryModel.getEspacoLivreDisco2();

        Double ramTotal = machineInfoModel.getEspacoTotalRam();
        Double ramDisponivel = machineRegistryModel.getEspacoLivreRam();


        if(machineRegistryModel.getCpuEmUso() > 92.0) {

            try {
                monitoration.enviarMensagem(String.format("A CPU está com auto uso \n" +
                        "Uso atual é de: %.2f", machineRegistryModel.getCpuEmUso()));
            } catch (Exception e) {
                System.out.println(e);;
            }

            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idMaquina) VALUES (?,?,?,?,?)",
                    "CPU", "alta", "CPU está com alto uso", horarioPC, idMaquina);

        } else if (machineRegistryModel.getCpuEmUso() > 98.0) {

            try {
                this.monitoration.enviarMensagem(String.format("A CPU está proxima do limite \n" +
                        "Uso atual é de: %.2f", machineRegistryModel.getCpuEmUso()));
            } catch (Exception e) {
                System.out.println(e);
            }

            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idMaquina) VALUES (?,?,?,?,?)",
                    "CPU", "extrema", "CPU está no limite ou muito proxima", horarioPC, idMaquina);

        }
        if(ramTotal % 100 * ramDisponivel > 92.0) {

            try {
                monitoration.enviarMensagem(String.format("A RAM está sobrecarregando \n" +
                        "Uso atual é de: %.2f", machineRegistryModel.getEspacoLivreRam()));
            } catch (Exception e) {
                System.out.println(e);;
            }

            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idMaquina) VALUES (?,?,?,?,?)",
                    "RAM", "alta", "RAM está com alto uso", horarioPC, idMaquina);

        } else if (ramTotal % 100 * ramDisponivel > 98.0) {

            try {
                monitoration.enviarMensagem(String.format("A RAM está sobrecarregando \n" +
                        "Uso atual é de: %.2f", ramTotal - ramDisponivel));
            } catch (Exception e) {
                System.out.println(e);;
            }

            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idMaquina) VALUES (?,?,?,?,?)",
                    "RAM", "extrema", "RAM está no limite ou muito proxima", horarioPC, idMaquina);

        }

        if(disco1Total % 100 * disco1Disponivel < 90.0 ) {

            try {
                monitoration.enviarMensagem(String.format("A Disco está sobrecarregando \n" +
                        "Uso atual é de: %.2f", disco1Total - disco1Disponivel ));
            } catch (Exception e) {
                System.out.println(e);;
            }

            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idMaquina) VALUES (?,?,?,?,?)",
                    "Disco 1: " + machineInfoModel.getModeloDisco1(), "alta", "Disco acima do limite", horarioPC, idMaquina);

        } else if(disco1Total % 100 * disco1Disponivel < 98.0 ) {

            try {
                monitoration.enviarMensagem(String.format("A Disco está sobrecarregando \n" +
                        "Uso atual é de: %.2f", disco1Total - disco1Disponivel ));
            } catch (Exception e) {
                System.out.println(e);;
            }

            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idMaquina) VALUES (?,?,?,?,?)",
                    "Disco 1: " + machineInfoModel.getModeloDisco1(), "extrema", "Disco acima do limite", horarioPC, idMaquina);
        }

        if(disco2Total % 100 * disco2Disponivel < 90.0 ) {

            try {
                monitoration.enviarMensagem(String.format("A Disco está sobrecarregando \n" +
                        "Uso atual é de: %.2f", disco2Total - disco2Disponivel ));
            } catch (Exception e) {
                System.out.println(e);
            }

            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idMaquina) VALUES (?,?,?,?,?)",
                    "Disco 2: " + machineInfoModel.getModeloDisco2(), "alta", "Disco acima do limite", horarioPC, idMaquina);

        } else if(disco2Total % 100 * disco2Disponivel < 98.0 ) {

            try {
                monitoration.enviarMensagem(String.format("A Disco está sobrecarregando \n" +
                        "Uso atual é de: %.2f", disco2Total - disco2Disponivel ));
            } catch (Exception e) {
                System.out.println(e);;
            }

            connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                            ", nivelCriticidade" +
                            ", descAlerta" +
                            ", dataHoraAlerta" +
                            ", idMaquina) VALUES (?,?,?,?,?)",
                    "Disco 2: " + machineInfoModel.getModeloDisco2(), "extrema", "Disco acima do limite", horarioPC, idMaquina);
        }

    }

    public List<MachineRegistryModel> consultMachineRegister(MachineInfoModel machineInfoModel) {

        List<MachineInfoModel> machineInfoSelect = connection.query("SELECT * FROM "
                        + "tblMaquinas WHERE idProcessador = ?", new BeanPropertyRowMapper(MachineInfoModel.class),
                machineInfoModel.getIdProcessador());
=======
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
>>>>>>> origin

        List<MachineRegistryModel> registrySelect = connection.query("SELECT TOP 1 * FROM tblRegistros WHERE idMaquina = ? ORDER BY idRegistro DESC;",
                new BeanPropertyRowMapper(MachineRegistryModel.class),
                machineInfoSelect.get(0).getIdMaquina());

        return registrySelect;
    }

}
