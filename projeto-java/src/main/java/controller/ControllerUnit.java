package controller;

import org.springframework.jdbc.core.JdbcTemplate;

public class ControllerUnit {
    private JdbcTemplate connection;
    
    public ControllerUnit() {
        ControllerConnectionSqlServer databaseConfig = new ControllerConnectionSqlServer();
        
        this.connection = new JdbcTemplate(databaseConfig.getDataSource());
    }
    
    public void findUnitByUserId() {
        
    }
}
