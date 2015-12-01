package test.modell;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import basis.factory.DatumFactory;
import modell.entitaeten.factory.FensterFactory;
import modell.entitaeten.factory.MitarbeiterFactory;
import modell.entitaeten.factory.RechtFactory;
import modell.entitaeten.factory.TicketFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Recht;
import modell.entitaeten.interfaces.Ticket;
import modell.factory.DaoFensterFactory;
import modell.factory.DaoMitarbeiterFactory;
import modell.factory.DaoTicketFactory;
import modell.implementierung.ImpDaoFenster;
import modell.interfaces.DaoFenster;
import modell.interfaces.DaoMitarbeiter;
import modell.interfaces.DaoTicket;

public class TestTicket {

	@Test
	public void ticketAnlegenTest() {
	boolean noFehler;
	
	//Fenster holen  
	DaoFenster df = DaoFensterFactory.getInstance();
	Fenster f = df.getFenster(1);
	
	Assert.assertEquals(f.getId(), 1);
	
	//Rechte setzten
	Recht r = RechtFactory.getInstance();
	r.setZugehoerigesFenster(f);
	r.setIndexDb(1);
	Vector<Recht> vr = new Vector<Recht>();
	vr.add(r);
	
	//Mitarbeiter setzten
	Mitarbeiter m = MitarbeiterFactory.getInstance();
	m.setLoginName("test");
	m.setName("testn");
	m.setVorname("testv");
	m.setEmail("test@test.de");
	char[] test = {'h','a','l','l','o'};
	m.setPasswort(test);
	
	
	//Mitarbeiter und Rechte in DB schreiben
	DaoMitarbeiter dm = DaoMitarbeiterFactory.getInstance();
	dm.speicherInDB(m);
	m = dm.getMitarbeiter(m.getLoginName(), m.getPasswort());
	m.setRechte(vr);
	dm.updateRechteMitarbeiter(m);
	
	
	//Ticket anlegen
	Ticket t1 = TicketFactory.getInstance();
	t1.setTitel("titel2");
	t1.setBeschreibung("beschreibung2");
	t1.setErstelldatum(DatumFactory.getInstance().getDatum());
	t1.setVerfasserId(m.getMitarbeiterId());
	
	//Ticket speichern
	DaoTicket dt = DaoTicketFactory.getInstance();
	noFehler = dt.setTicket(t1);
	
	//Ticket ID holen
	int ticketId = dt.getTicketId(t1);
	
	
	//Ticket wieder aus der DB holen
	Ticket t2 = dt.getTicket(ticketId);
	
	//Benutzer und Ticket wieder l�schen
	Assert.assertEquals(dt.loescheTicket(ticketId),true);
	Assert.assertEquals(dm.loescheVonDB(m), true);
	
	//Vergleiche
	Assert.assertTrue(noFehler);
	Assert.assertEquals(t1.getTitel(), t2.getTitel());
	Assert.assertEquals(t1.getBeschreibung(), t2.getBeschreibung());
	Assert.assertEquals(m.getMitarbeiterId(), t2.getVerfasserId());
	Assert.assertEquals(ticketId, t2.getTicketId());
	
	}
}
