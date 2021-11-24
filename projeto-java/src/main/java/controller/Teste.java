package controller;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import slack.Monitoration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Teste {
    private static JdbcOperations connection;

    public static void main(String[] args) throws IOException, InterruptedException {
        ControllerConnectionSqlServer databaseConfig = new ControllerConnectionSqlServer();

        connection = new JdbcTemplate(databaseConfig.getDataSource());

        Monitoration monitoration = new Monitoration();
        Looca looca = new Looca();

        System.out.println(String.format("%.2f", looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / 1024.0 / 1024 / 1024));


    }
}
