package modell.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBZugriff {

	/**
	 * Verbindung mit der Datenbank herstellen
	 * @return DB-Verbindung
	 */
	public Connection verbinden() throws SQLException;
	
	
	
}
