package test.modell;

import junit.framework.Assert;
import modell.entitaeten.factory.MitarbeiterFactory;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Recht;
import modell.factory.DaoMitarbeiterFactory;
import modell.interfaces.DaoMitarbeiter;

import org.junit.Test;

import basis.factory.Md5HashVerfahrenSingletonFactory;


public class testDaoMitarbeiter {

	@Test
	public void testMitarbeiter0Laden() {
		//Test lädt den Mitarbeiter mit der ID 0 von der Datenbank und Mappt in ein Objekt von Typ "Mitarbeiter"
		DaoMitarbeiter dao = DaoMitarbeiterFactory.getInstance();
		Mitarbeiter m = dao.getMitarbeiter(0);
		Assert.assertEquals("Rückgabewert des Index 0 richtig?", m.getLoginName(),"Admin");
		
		//Test der Überprüffunktion
		Assert.assertEquals(dao.inDBvorhanden(0), true);
		
		Assert.assertEquals(m.getRechte().size(), 1);
		Recht r = m.getRechte().get(0);
		Assert.assertNotNull(r);
		Assert.assertEquals(r.getBezeichung(), "Benutzerverwaltung");
	}

	@Test
	public void testMitarbeiterAnlegenBearbeitenLoeschen(){
		DaoMitarbeiter dao = DaoMitarbeiterFactory.getInstance();
		
		//legt einen neuen Mitarbeiter an
		Mitarbeiter neuerMitarbeiterTest = MitarbeiterFactory.getInstance();
		neuerMitarbeiterTest.setAngemeldet(false);
		neuerMitarbeiterTest.setEmail("test@junit.org");
		neuerMitarbeiterTest.setLoginName(String.valueOf(System.currentTimeMillis()));
		neuerMitarbeiterTest.setName("Test");
		char[] passwort = Md5HashVerfahrenSingletonFactory.getInstance().chiffriereText("P@ssw0rd").toCharArray();
		neuerMitarbeiterTest.setPasswort(passwort);
		neuerMitarbeiterTest.setVorname("Hugo");
		dao.speicherInDB(neuerMitarbeiterTest);
		
		//Überprüfen, ob der Datensatz angelegt wurde
		Mitarbeiter mitarbeiterDB = dao.getMitarbeiter(neuerMitarbeiterTest.getLoginName(), neuerMitarbeiterTest.getPasswort());
		Assert.assertEquals("Mitarbeiter erfolgreich angelegt", mitarbeiterDB.getEmail(), "test@junit.org");
		
		//Test der Überprüffunktion
		Assert.assertEquals(dao.inDBvorhanden(neuerMitarbeiterTest.getLoginName()), true);
		
		//Überprüft die Änderungsfunktion
		mitarbeiterDB.setLoginName("Hugo");
		dao.update(mitarbeiterDB);
		
		Mitarbeiter geaenderterMitarbeiter = dao.getMitarbeiter(mitarbeiterDB.getMitarbeiterId());
		Assert.assertEquals("Mitarbeiter erfolgreich geändert", geaenderterMitarbeiter.getLoginName(), "Hugo");
		
		//Test der Löschfunktion
		dao.loescheVonDB(geaenderterMitarbeiter);
		Assert.assertEquals("Datensatz gelöscht", dao.inDBvorhanden("Hugo"), false);
	}
}
