package crud;

import java.sql.SQLException;

// Esta clase usa el patron Singleton
public class Singleton {
    private static Singleton instancia = null;
    private static DbConfig dbCon = new DbConfig(); 

    private Singleton() {
		try
		{
			var con = Singleton.dbCon.getConnection();
			System.out.println("Conectado siuuu");
		}catch(Exception e)
		{
			System.out.println("Hubo un problemilla");
		}
	}

    public static Singleton getInstance() {
        if (instancia == null) {
            instancia = new Singleton();
        }
        return instancia;
    }

	public  DbConfig getDbCon() {
		return dbCon;
	}
}
