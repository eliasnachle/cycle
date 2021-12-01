package br.com.cycle.controller;

import br.com.cycle.connection.ConnectionMySql;
import br.com.cycle.connection.ConnectionSqlServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import br.com.cycle.model.LoginModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerLogin {
    private JdbcTemplate connectionSqlServer;
    private JdbcTemplate connectionMySQl;

    public ControllerLogin() {
        ConnectionSqlServer databaseSqlServerConfig = new ConnectionSqlServer();
        ConnectionMySql databaseMySqlConfig = new ConnectionMySql();

        this.connectionSqlServer = new JdbcTemplate(databaseSqlServerConfig.getDataSource());
        this.connectionMySQl = new JdbcTemplate(databaseMySqlConfig.getDataSource());

    }

    public List<LoginModel> consultUserInDataBase(String email, String password) throws IOException {

        String select = String.format("SELECT * FROM tblAdministrator WHERE "
                        + "emailAdministrator = '%s' and senhaAdministrator = '%s'",
                email,
                password
        );
        System.out.println("Verificando administrador no SQL Server");
        List<LoginModel> userSqlServer = connectionSqlServer.query(select, new BeanPropertyRowMapper(LoginModel.class));

        System.out.println("Verificando administrador no MySql");
        List<LoginModel> userMysql= connectionMySQl.query(select, new BeanPropertyRowMapper(LoginModel.class));

        if (userSqlServer.isEmpty() && userMysql.isEmpty()){
            System.out.println("ERRO DE CONEXÃO");
        }else if(userSqlServer.size() >= 1){
            return userSqlServer;
        }else if(userSqlServer.size() >= 1){
            return userMysql;
        }

        return new ArrayList<>();
    }

    public Boolean login(String email, String password) throws IOException {
        if (consultUserInDataBase(email,password) == null || consultUserInDataBase(email, password).isEmpty()) {
            System.out.println("Erro: Usuario não encontrado");
            return true;
        }else {
            System.out.println("Login executado");
            return false;
        }
    }
}



