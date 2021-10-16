package controller;

import org.apache.commons.dbcp2.BasicDataSource;

public class ControllerConnection {
    // Atributo da classe - para ajudar na conexao com o BD
    private BasicDataSource dataSource;

    // Construtor 
    public ControllerConnection() {

        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //Esse link você pega no portal.azure
        //entre no banco do projeto e clique em "cadeia de conexão e escolha
        //a opção JDBC, cópia e cola no "setURL"
        this.dataSource.setUrl("jdbc:sqlserver://cycle-project.database.windows.net:1433;"
                + "database=database_cycle;user=grupo4_2adsb@cycle-project;"
                + "password={your_password_here};encrypt=true;trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        this.dataSource.setUsername("grupo4_2adsb");
        this.dataSource.setPassword("group4_cycle");
    }

    //Getter do DataSource
    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
