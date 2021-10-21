/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.LoginModel;
import model.MachineInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 *
 * @author Erick
 */
public class ControllerLogin {
    private JdbcTemplate db;
    
    public ControllerLogin() {
        ControllerConnection databaseConfig = new ControllerConnection();
        
        this.db = new JdbcTemplate(databaseConfig.getDataSource());
    }
   
    public List<LoginModel> consultUserData(String email, String password){
        
            String select = String.format("SELECT * FROM tblUsuariosContratante WHERE "
                + "emailContratante = '%s' and senhaContratante = '%s'", 
                email, 
                password
            );
            System.out.println("Fazendo select");
            List<LoginModel> user = db.query(select, new BeanPropertyRowMapper(LoginModel.class));
            
            System.out.println("Fim do select");
            return user; 
    }
}


