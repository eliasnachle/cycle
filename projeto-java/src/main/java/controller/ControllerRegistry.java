package controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionSqlServer;
import loggers.Logge;
import model.MachineInfoModel;
import model.MachineRegistryModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import slack.Monitoration;

public class ControllerRegistry {

    private final JdbcTemplate connection;
    private final Monitoration monitoration;
    private Logge logge;

    public ControllerRegistry() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();

        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
        this.monitoration = new Monitoration();
        this.logge = new Logge();
    }

    public void registerInDatabaseNewRegistry(MachineInfoModel machineInfoModel, MachineRegistryModel machineRegistryModel, ControllerMachineInfo controllerMachineInfo) {

        List<MachineInfoModel> machineInfoSelect = new ArrayList<>();

        String dataLog = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        try {
            machineInfoSelect = controllerMachineInfo.consultMachineInfo(machineInfoModel);

            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora da consulta: %s \n" +
                            "Consultando informações da maquina com o id processador:  %s\n" +
                            "Consulta efetuada com sucesso \n\n" +
                            "---------------------###############################-----------------\n", dataLog, machineInfoModel.getIdProcessador()));
        }catch (Exception e){
            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora da consulta: %s \n" +
                            "Consultando informações da maquina com o id processador:  %s\n" +
                            "** Erro na consulta ** \n\n" +
                            "Exception: %s \n\n" +
                            "---------------------###############################-----------------\n", dataLog, machineInfoModel.getIdProcessador(),e));

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
            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora do insert: %s \n" +
                            "Inserindo dados da maquina... \n\n\n" +
                            "Dado inserindo com sucesso \n\n " +
                            "Apelido da maquina: %s \n\n" +
                            "Tipo da maquina: %s \n\n" +
                            "Id Processador: %s\n\n" +
                            "---------------------###############################-----------------\n", dataLog, machineInfoModel.getApelidoMaquina(), machineInfoModel.getIdProcessador()));

        } catch (Exception e) {
            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora do insert: %s \n" +
                            "Inserindo dados da maquina... \n\n\n" +
                            "** Erro ao inserir o dado ** \n\n " +
                            "Apelido da maquina: %s \n\n" +
                            "Tipo da maquina: %s \n\n" +
                            "Id Processador: %s\n\n" +
                            "---------------------###############################-----------------\n", dataLog, machineInfoModel.getApelidoMaquina(), machineInfoModel.getIdProcessador(), e));

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

        String dataLog = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        if(machineRegistryModel.getCpuEmUso() >= 94.0) {
            try {
                monitoration.enviarMensagem(String.format("A CPU está com alto uso \n" +
                        "Uso atual é de: %.2f", machineRegistryModel.getCpuEmUso()));

                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o slack \n\n" +
                                "A CPU está com alto uso \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------\n", dataLog, machineRegistryModel.getCpuEmUso()));


            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o sclack \n\n\n" +
                                "** Erro ao enviar a menssagem ** \n\n " +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------\n", dataLog, e));
            }

            try {
                connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                                ", nivelCriticidade" +
                                ", descAlerta" +
                                ", dataHoraAlerta" +
                                ", idMaquina, alertaVisivel) VALUES (?,?,?,?,?,?)",
                        "CPU", "alta", "CPU está com alto uso", horarioPC, idMaquina, 1);

                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o banco de dados \n\n" +
                                "A CPU está proxima do limite \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------\n", dataLog, machineRegistryModel.getCpuEmUso()));


            } catch (Exception e){
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Inserindo dados da maquina... \n\n\n" +
                                "** Erro ao inserir o dado ** \n\n " +
                                "Apelido da maquina: %s \n\n" +
                                "Tipo da maquina: %s \n\n" +
                                "Id Processador: %s\n\n" +
                                "---------------------###############################-----------------\n", dataLog, machineInfoModel.getApelidoMaquina(), machineInfoModel.getIdProcessador(), e));
            }


        } else if (machineRegistryModel.getCpuEmUso() > 98.0) {

            try {
                this.monitoration.enviarMensagem(String.format("A CPU está proxima do limite \n" +
                        "Uso atual é de: %.2f", machineRegistryModel.getCpuEmUso()));
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o slack \n\n" +
                                "A CPU está proxima do limite \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------\n", dataLog, machineRegistryModel.getCpuEmUso()));

            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o sclack \n\n\n" +
                                "** Erro ao enviar a menssagem ** \n\n " +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------\n", dataLog, e));
            }

            try {
                connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                                ", nivelCriticidade" +
                                ", descAlerta" +
                                ", dataHoraAlerta" +
                                ", idMaquina, alertaVisivel) VALUES (?,?,?,?,?,?)",
                        "CPU", "extrema", "CPU está no limite ou muito proxima", horarioPC, idMaquina, 1);
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o banco de dados \n\n" +
                                "A CPU está proxima do limite \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------\n", dataLog, machineRegistryModel.getCpuEmUso()));


            } catch (Exception e){
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Erro ao inserir o registro \n\n\n" +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------\n", dataLog, e));
            }


        }

        if(ramTotal / 100 * ramDisponivel >= 90.0) {

            try {
                monitoration.enviarMensagem(String.format("[Maquina %s]\n" +
                        "A RAM está sobrecarregando \n" +
                        "Uso atual é de: %.2f", idMaquina, ramTotal - ramDisponivel));

                logge.guardarLog(
                        String.format("-----------------Consultando Slack---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o slack \n\n" +
                                "A RAM está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------\n", dataLog, ramTotal - ramDisponivel));


            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando Slack---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o sclack \n\n\n" +
                                "** Erro ao enviar a menssagem ** \n\n " +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }

            try {
                connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                                ", nivelCriticidade" +
                                ", descAlerta" +
                                ", dataHoraAlerta" +
                                ", idMaquina, alertaVisivel) VALUES (?,?,?,?,?,?)",
                        "RAM", "alta", "RAM está com alto uso", horarioPC, idMaquina, 1);
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o banco de dados \n\n" +
                                "A RAM está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------", dataLog, ramTotal - ramDisponivel));
            } catch (Exception e){
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Erro ao inserir o registro \n\n\n" +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }


        } else if (ramTotal / 100 * ramDisponivel >= 98.0) {

            try {
                monitoration.enviarMensagem(String.format("[Maquina %s] \n" +
                        "A RAM está sobrecarregando \n" +
                        "Uso atual é de: %.2f", idMaquina, ramTotal - ramDisponivel));

                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o slack \n\n" +
                                "A RAM está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------", dataLog, ramTotal - ramDisponivel));


            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o sclack \n\n\n" +
                                "** Erro ao enviar a menssagem ** \n\n " +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }

            try {
                connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                                ", nivelCriticidade" +
                                ", descAlerta" +
                                ", dataHoraAlerta" +
                                ", idMaquina, alertaVisivel) VALUES (?,?,?,?,?,?)",
                        "RAM", "extrema", "RAM está no limite ou muito proxima", horarioPC, idMaquina, 1);
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o banco de dados \n\n" +
                                "A RAM está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------", dataLog, ramTotal - ramDisponivel));

            } catch (Exception e){
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Erro ao inserir o registro \n\n\n" +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }


        }

        if(disco1Total / 100 * disco1Disponivel > 88.0 ) {

            try {
                monitoration.enviarMensagem(String.format("[Maquina %s] \n" +
                        "O Disco 1 está sobrecarregando \n" +
                        "Uso atual é de: %.2f", idMaquina, disco1Total - disco1Disponivel ));

                logge.guardarLog(
                        String.format("-----------------Consultando Slack---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o slack \n\n" +
                                "O Disco 1 está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------", dataLog, disco1Total - disco1Disponivel));

            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando Slack---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o sclack \n\n\n" +
                                "** Erro ao enviar a menssagem ** \n\n " +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }

            try {
                connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                                ", nivelCriticidade" +
                                ", descAlerta" +
                                ", dataHoraAlerta" +
                                ", idMaquina, alertaVisivel) VALUES (?,?,?,?,?,?)",
                        "Disco 1: " + machineInfoModel.getModeloDisco1(), "alta", "Disco acima do limite", horarioPC, idMaquina, 1);
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o banco dados \n\n" +
                                "O Disco 1 está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------", dataLog, disco1Total - disco1Disponivel));

            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Erro ao inserir o registro \n\n\n" +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }

        } else if(disco1Total / 100 * disco1Disponivel > 95.0 ) {

            try {
                monitoration.enviarMensagem(String.format("[Maquina %s] \n" +
                        "O Disco 1 está sobrecarregando \n" +
                        "Uso atual é de: %.2f", idMaquina, disco1Total - disco1Disponivel ));

                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o slack \n\n" +
                                "O Disco 1 está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------", dataLog, disco1Total - disco1Disponivel));

            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o sclack \n\n\n" +
                                "** Erro ao enviar a menssagem ** \n\n " +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }

            try {
                connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                                ", nivelCriticidade" +
                                ", descAlerta" +
                                ", dataHoraAlerta" +
                                ", idMaquina, alertaVisivel) VALUES (?,?,?,?,?,?)",
                        "Disco 1: " + machineInfoModel.getModeloDisco1(), "extrema", "Disco acima do limite", horarioPC, idMaquina, 1);
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o banco dados \n\n" +
                                "O Disco 1 está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------", dataLog, disco1Total - disco1Disponivel));

            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Erro ao inserir o registro \n\n\n" +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }

        }

        if(disco2Total / 100 * disco2Disponivel > 88.0 ) {

            try {
                monitoration.enviarMensagem(String.format("[Maquina %s]" +
                        "O Disco 2 está sobrecarregando \n" +
                        "Uso atual é de: %.2f", idMaquina, disco2Total - disco2Disponivel ));

                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o slack \n\n" +
                                "O Disco 2 está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------", dataLog, disco2Total - disco2Disponivel));

            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o sclack \n\n\n" +
                                "** Erro ao enviar a menssagem ** \n\n " +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }

            try {
                connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                                ", nivelCriticidade" +
                                ", descAlerta" +
                                ", dataHoraAlerta" +
                                ", idMaquina, alertaVisivel) VALUES (?,?,?,?,?,?)",
                        "Disco 2: " + machineInfoModel.getModeloDisco2(), "alta", "Disco acima do limite", horarioPC, idMaquina, 1);
                logge.guardarLog(
                String.format("-----------------Consultando banco Azure---------------------\n\n" +
                        "Data e hora do insert: %s \n" +
                        "Enviando alerta para o banco de dados \n\n" +
                        "O Disco 2 está sobrecarregando \n" +
                        "Uso atual é de: %.2f \n" +
                        "---------------------###############################-----------------", dataLog, disco2Total - disco2Disponivel));

            } catch (Exception e){
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Erro ao inserir o registro \n\n\n" +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }

        } else if(disco2Total / 100 * disco2Disponivel > 95.0 ) {

            try {
                monitoration.enviarMensagem(String.format("[Maquina %s]" +
                        "O Disco 2 está sobrecarregando \n" +
                        "Uso atual é de: %.2f", idMaquina, disco2Total - disco2Disponivel ));

                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o slack \n\n" +
                                "O Disco 2 está sobrecarregando \n" +
                                "Uso atual é de: %.2f \n" +
                                "---------------------###############################-----------------", dataLog, disco2Total - disco2Disponivel));

            } catch (Exception e) {
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Enviando alerta para o sclack \n\n\n" +
                                "** Erro ao enviar a menssagem ** \n\n " +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));
            }

            try {
                connection.update("INSERT INTO tblAlertas(componenteInstavel" +
                                ", nivelCriticidade" +
                                ", descAlerta" +
                                ", dataHoraAlerta" +
                                ", idMaquina, alertaVisivel) VALUES (?,?,?,?,?,?)",
                        "Disco 2: " + machineInfoModel.getModeloDisco2(), "extrema", "Disco acima do limite", horarioPC, idMaquina, 1);
                logge.guardarLog(
                String.format("-----------------Consultando banco Azure---------------------\n\n" +
                        "Data e hora do insert: %s \n" +
                        "Enviando alerta para o banco de dados \n\n" +
                        "O Disco 2 está sobrecarregando \n" +
                        "Uso atual é de: %.2f \n" +
                        "---------------------###############################-----------------", dataLog, disco2Total - disco2Disponivel));

            }catch (Exception e){
                logge.guardarLog(
                        String.format("-----------------Consultando banco Azure---------------------\n\n" +
                                "Data e hora do insert: %s \n" +
                                "Erro ao inserir o registro \n\n\n" +
                                "Exception: %s \n" +
                                "---------------------###############################-----------------", dataLog, e));

            }

        }

    }

    public List<MachineRegistryModel> consultMachineRegister(MachineInfoModel machineInfoModel, ControllerMachineInfo controllerMachineInfo) {

        List<MachineInfoModel> machineInfoSelect = new ArrayList<>();
        List<MachineRegistryModel> registrySelect =  new ArrayList<>();

        String dataLog = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        try {
            machineInfoSelect = controllerMachineInfo.consultMachineInfo(machineInfoModel);

            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora da consulta: %s \n" +
                            "Consultando informações da maquina com o id processador:  %s\n" +
                            "Consulta efetuada com sucesso \n\n" +
                            "---------------------###############################-----------------", dataLog, machineInfoModel.getIdProcessador()));
        }catch (Exception e){
            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora da consulta: %s \n" +
                            "Consultando informações da maquina com o id processador:  %s\n" +
                            "** Erro na consulta ** \n\n" +
                            "Exception: %s \n\n" +
                            "---------------------###############################-----------------", dataLog, machineInfoModel.getIdProcessador(),e));

        }

        try {
            registrySelect = connection.query("SELECT TOP 1 * FROM tblRegistros WHERE idMaquina = ? ORDER BY idRegistro DESC;",
                    new BeanPropertyRowMapper(MachineRegistryModel.class),
                    machineInfoSelect.get(0).getIdMaquina());

            logge.guardarLog(
            String.format("-----------------Consultando banco Azure---------------------\n\n" +
                    "Data e hora da consulta: %s \n" +
                    "Consultando registro da maquina %s\n" +
                    "Consulta efetuada com sucesso \n\n" +
                    "---------------------###############################-----------------", dataLog, machineInfoSelect.get(0).getApelidoMaquina()));

        } catch (Exception e){
            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora da consulta: %s \n" +
                            "Consultando registro da maquina %s\n" +
                            "** Erro na consulta ** \n\n" +
                            "Exception: %s \n\n" +
                            "---------------------###############################-----------------", dataLog, machineInfoSelect.get(0).getApelidoMaquina(),e));

        }

        return registrySelect;
    }

}
