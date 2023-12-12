package fr.erwan.psql.gql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * connexion bdd
 * récupération données
 */
public class DaoFactory {
    private String url;
    private String user;
    private String passwd;

    DaoFactory(String url, String user, String passwd) {
        this.url = url;
        this.user = user;
        this.passwd = passwd;
    }
    
    
    public static DaoFactory getInstance() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
        	System.out.println("probleme de classe  " + e.getMessage());
        }
    	DaoFactory instance = new DaoFactory("jdbc:postgresql://localhost:5432/jee", "postgres", "postgres");
        return instance;
    }

    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.passwd );
    }
}