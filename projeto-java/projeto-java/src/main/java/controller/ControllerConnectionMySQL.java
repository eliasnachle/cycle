/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.apache.commons.dbcp2.BasicDataSource;


/**
 *
 * @author Aluno
 */
public class ControllerConnectionMySQL {
    // Atributo da classe - para ajudar na conexao com o BD
    private BasicDataSource dataSource;

    //Construtor 
    public ControllerConnectionMySQL() {

        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //Coloque o caminho do MySQL aqui em baixo
        this.dataSource.setUrl("jdbc:mysql://localhost:3306/cycle?useTimezone=true&serverTimezone=UTC"); 
        this.dataSource.setUsername("root");
        this.dataSource.setPassword("bandtec"); 

    }

    //Getter do DataSource
    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
