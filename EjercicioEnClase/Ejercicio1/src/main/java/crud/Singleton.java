package crud;

import java.sql.SQLException;

// Esta clase usa el patron Singleton
public class Singleton {
    private static Singleton instancia = null;
    private static DbConfig dbCon = new DbConfig(); 

    private Singleton() {}

    public static Singleton getInstance() {
        if (instancia == null) {
            instancia = new Singleton();
        }
        return instancia;
    }

    public DbConfig getDbConfig() {
        return dbCon;
    }
}
