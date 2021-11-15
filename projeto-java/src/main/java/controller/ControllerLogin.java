package controller;

import java.util.List;
import model.LoginModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerLogin {
    private JdbcTemplate connection;
    
    public ControllerLogin() {
        ControllerConnectionSqlServer databaseConfig = new ControllerConnectionSqlServer();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
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


