package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionSqlServer;
import model.MachineInfoModel;
import model.MachineRegistryModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import slack.Monitoration;

public class ControllerRegistry {
    private final JdbcTemplate connection;
    private final Monitoration monitoration = new Monitoration();

    public ControllerRegistry() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();

        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
    }

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

        List<MachineRegistryModel> registrySelect = connection.query("SELECT TOP 1 * FROM tblRegistros WHERE idMaquina = ? ORDER BY idRegistro DESC;",
                new BeanPropertyRowMapper(MachineRegistryModel.class),
                machineInfoSelect.get(0).getIdMaquina());

        return registrySelect;
    }

}
