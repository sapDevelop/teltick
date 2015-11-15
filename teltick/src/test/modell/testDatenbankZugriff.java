package test.modell;

import java.sql.Connection;
import java.sql.SQLException;

import modell.factory.HSqlDbZugriffFactory;
import modell.interfaces.DBZugriff;

import org.junit.Assert;
import org.junit.Test;

public class testDatenbankZugriff {

	@Test
	public void testVerbindungsAufbau(){
		
		//Objekt instanzieren
		DBZugriff db1 = HSqlDbZugriffFactory.getInstance();
		Assert.assertNotNull("Konfigurationsdatei konnte geladen werden", db1);
		
		//DB-Verbindung aufbauen
		try {
			Connection verbindung1 = db1.verbinden();
			Assert.assertNotNull("Verbindung wurde aufgebaut", verbindung1);
			
			verbindung1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
