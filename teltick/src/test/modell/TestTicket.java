package test.modell;

import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Test;

import basis.factory.DatumFactory;
import controller.NeuesTicketController;
import junit.framework.Assert;
import logger.LogFactory;
import modell.entitaeten.factory.FensterFactory;
import modell.entitaeten.factory.MitarbeiterFactory;
import modell.entitaeten.factory.RechtFactory;
import modell.entitaeten.factory.TicketFactory;
import modell.entitaeten.factory.TicketzuweisungFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Recht;
import modell.entitaeten.interfaces.Ticket;
import modell.entitaeten.interfaces.Ticketzuweisung;
import modell.factory.DaoFensterFactory;
import modell.factory.DaoMitarbeiterFactory;
import modell.factory.DaoTicketFactory;
import modell.implementierung.ImpDaoFenster;
import modell.interfaces.DaoFenster;
import modell.interfaces.DaoMitarbeiter;
import modell.interfaces.DaoTicket;

public class TestTicket {
	
	private static Logger log = LogFactory.getInstance(TestTicket.class.getName());

	@Test
	public void ticketAnlegenTest() {
		
	log.info("Test TicketAnlegen gestartet.");
		
	boolean noFehler;
	
	//Fenster holen  
	DaoFenster df = DaoFensterFactory.getInstance();
	Fenster f = df.getFenster(1);
	
	//Rechte setzten
	Recht r = RechtFactory.getInstance();
	r.setBezeichung("Neues Ticket");
	r.setZugehoerigesFenster(f);
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
	m.setRechte(vr);
	
	//Mitarbeiter und Rechte in DB schreiben
	DaoMitarbeiter dm = DaoMitarbeiterFactory.getInstance();
	dm.speicherInDB(m);
	dm.updateRechteMitarbeiter(m);
	
	
	//Ticket anlegen
	Ticket t1 = TicketFactory.getInstance();
	t1.setTitel("titel2");
	t1.setBeschreibung("beschreibung2");
	t1.setErstelldatum(DatumFactory.getInstance().getDatum());
	t1.setVerfasser(m);
	
	//Ticket speichern
	DaoTicket dt = DaoTicketFactory.getInstance();
	noFehler = dt.setTicket(t1);
	
	//Ticket ID holen
	int ticketId = dt.getTicketId(t1);
	
	//Ticketzuweisung setzten
	Ticketzuweisung tz = TicketzuweisungFactory.getInstance();
	tz.setMitarbeiter(m);
	tz.setTicket(t1);
	tz.setZeitpunkt(DatumFactory.getInstance().getDatum());
	
	dt.setZuweisung(tz);
	
	
	//Ticket wieder aus der DB holen
	Ticket t2 = dt.getTicket(ticketId);
	
	//Benutzer und Ticket wieder löschen
	Assert.assertTrue(dt.loescheZuweisung(ticketId));
	Assert.assertTrue(dt.loescheTicket(ticketId));
	Assert.assertTrue(dm.loescheVonDB(m));
	
	
	//Vergleiche
	Assert.assertTrue(noFehler);
	Assert.assertEquals(t1.getTitel(), t2.getTitel());
	Assert.assertEquals(t1.getBeschreibung(), t2.getBeschreibung());
	Assert.assertEquals(m.getMitarbeiterId(), t2.getVerfasser().getMitarbeiterId());
	Assert.assertEquals(ticketId, t2.getTicketId());
	
	}
}
