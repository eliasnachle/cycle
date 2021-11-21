package connection;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionSqlServer {
    // Atributo da classe - para ajudar na conexao com o BD
    private BasicDataSource dataSource;

    // Construtor 
    public ConnectionSqlServer() {
        
        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.dataSource.setUrl("jdbc:sqlserver://cycle-project.database.windows.net:1433;database=database_cycle;"
                + "encrypt=true;"
                + "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        this.dataSource.setUsername("grupo4_2adsb");
        this.dataSource.setPassword("group4_cycle");
    }

    //Getter do DataSource
    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
