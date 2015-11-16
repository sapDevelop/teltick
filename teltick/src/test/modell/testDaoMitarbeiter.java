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
		
		//Änder den Autostart des Rechtes 0 aus false
		m.getRechte().get(0).setAutostart(false);
		dao.update(m);
		Assert.assertEquals(dao.getMitarbeiter(0).getRechte().get(0).isAutostart(), false);
		
		
		//Überprüft, ob die Mitarbeiterrechte geladen werden können
		Assert.assertEquals(m.getRechte().size(), 1);
		Recht r = m.getRechte().get(0);
		Assert.assertNotNull(r);
		Assert.assertEquals(r.getBezeichung(), "Benutzerverwaltung");
		Assert.assertEquals(r.getZugehoerigesFenster().getTitel(), "Benutzerverwaltung");
	}
}