package br.com.cycle.connection;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionMySql {
    // Atributo da classe - para ajudar na conexao com o BD
    private BasicDataSource dataSource;

    //Construtor
    public ConnectionMySql() {

        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //Coloque o caminho do MySQL aqui em baixo
        this.dataSource.setUrl("jdbc:mysql://mysql-cycle:3306/cycle?useTimezone=true&serverTimezone=UTC");
        this.dataSource.setUsername("root");
        this.dataSource.setPassword("Bandtec@123");

    }

    //Getter do DataSource
    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
