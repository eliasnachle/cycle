package controller;

import connection.ConnectionSqlServer;
import java.io.IOException;
import java.util.List;
import logs.cycle.LogCycle;
import model.LoginModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerLogin {
    private JdbcTemplate connection;
    private LoginModel loginModel;
    
    public ControllerLogin() {
        ConnectionSqlServer databaseConfig = new ConnectionSqlServer();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
        this.loginModel = new LoginModel();
    }
   
    public List<LoginModel> consultUserData(String email, String password) throws IOException{
        
            String select = String.format("SELECT * FROM tblUsuariosContratante WHERE "
                + "emailContratante = '%s' and senhaContratante = '%s'", 
                email, 
                password
            );
            System.out.println("Fazendo select");
            
            //log Login
            //LogCycle.guardarLog("Fazendo Select - login");
            List<LoginModel> user = connection.query(select, new BeanPropertyRowMapper(LoginModel.class));
            
            System.out.println("Fim do select");
            
            return user; 
    }
}


