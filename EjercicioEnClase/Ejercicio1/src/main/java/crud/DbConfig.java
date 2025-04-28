package crud;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.hibernate.HikariConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConfig {
	
    private HikariDataSource ds;
    private HikariConfig config = new HikariConfig();
    
    {
        config.setJdbcUrl("jdbc:sqlite:C:/Users/user/Downloads/sqlite/dbs/db.db");
        config.setUsername("sa");
        config.setPassword("");
        config.setConnectionTimeout(3000);
        ds = new HikariDataSource(config);
    }
    public Connection getConnection() {
        try {
            System.out.println("Conexión exitosa...");
            return ds.getConnection();

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static HikariConnectionProvider get()
    {
        return null;
    }
    
}
