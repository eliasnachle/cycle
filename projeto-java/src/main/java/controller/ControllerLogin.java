package controller;

import connection.ConnectionSqlServer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import loggers.Logge;
import model.LoginModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerLogin {
    private JdbcTemplate connection;
    private Logge logge;
    
    public ControllerLogin() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
        this.logge = new Logge();
    }
   
    public List<LoginModel> consultUserData(String email, String password){
        String dataLog = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        String select = String.format("SELECT * FROM tblUsuariosContratante WHERE "
                        + "emailContratante = '%s' and senhaContratante = '%s'",
                email,
                password
        );

        List<LoginModel> user = new ArrayList<>();

        try {
            user = connection.query(select, new BeanPropertyRowMapper(LoginModel.class));
            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora da consulta: %s \n" +
                            "Procurando o usuario com email '%s' \n" +
                            "Usuario encontrado\n\n" +
                            "Login efetuado com sucesso", dataLog, email));

        } catch (Exception e) {
            logge.guardarLog(
                    String.format("-----------------Consultando banco Azure---------------------\n\n" +
                            "Data e hora da consulta: %s \n" +
                            "Procurando o usuario com email '%s' \n" +
                            "** Usuario não encontrado ** \n\n" +
                            "Exception: %s", dataLog, email, e));

        }
            
        return user;
    }
}


