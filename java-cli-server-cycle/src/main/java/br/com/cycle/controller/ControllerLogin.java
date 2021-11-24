package br.com.cycle.controller;

import br.com.cycle.connection.ConnectionMySql;
import br.com.cycle.connection.ConnectionSqlServer;
import java.util.List;
import br.com.cycle.model.LoginModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerLogin {
    private JdbcTemplate connection;

    public ControllerLogin() {
        ConnectionMySql databaseConfig = new ConnectionMySql();

        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
    }

    private List<LoginModel> consultUserInDataBase(String email, String password){

        String select = String.format("SELECT * FROM tblUsuariosContratante WHERE "
                        + "emailContratante = '%s' and senhaContratante = '%s'",
                email,
                password
        );
        System.out.println("Fazendo select");
        List<LoginModel> user = connection.query(select, new BeanPropertyRowMapper(LoginModel.class));

        return user;
    }

    public Boolean login(String email, String password) {
        if (consultUserInDataBase(email, password).isEmpty()) {
            System.out.println("Erro: Usuario não encontrado");
            return true;
        }else {
            System.out.println("Login executado");
            return false;
        }
    }
}



