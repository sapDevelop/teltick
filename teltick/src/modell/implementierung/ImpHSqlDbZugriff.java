package modell.implementierung;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import modell.interfaces.DBZugriff;

import java.util.Properties;

public class ImpHSqlDbZugriff implements DBZugriff{
	
	private String dbdriver;
	private String dburl, dbdatei;
	private String username;
	private String password;
	private String urlAbsolut;
	
	//private Log logger = LogFactory.getLog(ImpHSqlDbZugriff.class) ;
	
	
	public ImpHSqlDbZugriff() {
		Properties dbprops = new Properties();
		try {
			URL url = getClass().getClassLoader().getResource("db_zugangsdaten.properties");
			urlAbsolut = url.getPath().replace("db_zugangsdaten.properties", "");
			
			dbprops.load(url.openStream());

			// Verbindungsparameter auslesen
			dbdriver = dbprops.getProperty("database.driver");
			dburl    = dbprops.getProperty("database.url");
			dbdatei	 = dbprops.getProperty("database.datei");
			username = dbprops.getProperty("database.user");
			password = dbprops.getProperty("database.password");
			
			
			// 1. Datenbank-Treiber registrieren
			// Hier wird sichergestellt, dass der
			// benötigte JDBC-Treiber in der JVM
			// geladen wurde*/
			Class.forName(dbdriver);
			

		} catch (ClassNotFoundException cnfe) {
			//logger.error("Datenbanktreiber konnte nicht geladen werden", cnfe);
			System.out.println(cnfe);
		}catch (IOException e) {
			//logger.error("Could not establish a database connection", e);
			System.out.println(e);
		}
	}

	@Override
	public Connection verbinden() throws SQLException {
		
		Connection verbindung;
		
		if (!username.equals("0") && !password.equals("0")){
			verbindung = DriverManager.getConnection(dburl, username, password);
		}else{
			verbindung = DriverManager.getConnection(dburl + urlAbsolut + dbdatei);
		}
				
		return verbindung;
	}

}
