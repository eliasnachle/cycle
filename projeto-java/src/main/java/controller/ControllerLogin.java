package controller;

import connection.ConnectionSqlServer;
import java.util.List;
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
   
    public List<LoginModel> consultUserData(String email, String password){
        
            String select = String.format("SELECT * FROM tblUsuariosContratante WHERE "
                + "emailContratante = '%s' and senhaContratante = '%s'", 
                email, 
                password
            );
            System.out.println("Fazendo select");
            List<LoginModel> user = connection.query(select, new BeanPropertyRowMapper(LoginModel.class));
            
            System.out.println("Fim do select");
            
            return user; 
    }
}


