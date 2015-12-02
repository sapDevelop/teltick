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
	
	//Mitarbeiter ID ermitteln
	Vector<Mitarbeiter> vm = dm.getAlleMitarbeiter(true);
	
	int mId = 999;
	for(int i = 0; i<vm.size(); i++)
	{
		if(vm.get(i).getLoginName().equals("test")&& vm.get(i).getVorname().equals("testv")&&vm.get(i).getName().equals("testn"))
		{
			mId = vm.get(i).getMitarbeiterId();
		}
	}
	m.setMitarbeiterId(mId);
	
	//Ticket anlegen
	Ticket t1 = TicketFactory.getInstance();
	t1.setTitel("titel2");
	t1.setBeschreibung("beschreibung2");
	t1.setErstelldatum(DatumFactory.getInstance().getDatum());
	t1.setVerfasserId(mId);
	
	//Ticket speichern
	DaoTicket dt = DaoTicketFactory.getInstance();
	noFehler = dt.setTicket(t1);
	
	//Ticket ID holen
	int ticketId = dt.getTicketId(t1);
	
	//Ticketzuweisung setzten
	Ticketzuweisung tz = TicketzuweisungFactory.getInstance();
	tz.setMitarbeiterId(mId);
	tz.setTicketId(ticketId);
	tz.setZeitpunkt(DatumFactory.getInstance().getDatum());
	
	dt.setZuweisung(tz);
	
	
	//Ticket wieder aus der DB holen
	Ticket t2 = dt.getTicket(ticketId);
	
	//Benutzer und Ticket wieder löschen
	dt.loescheZuweisung(ticketId);
	dt.loescheTicket(ticketId);
	dm.loescheVonDB(m);
	
	
	//Vergleiche
	Assert.assertTrue(noFehler);
	Assert.assertEquals(t1.getTitel(), t2.getTitel());
	Assert.assertEquals(t1.getBeschreibung(), t2.getBeschreibung());
	Assert.assertEquals(mId, t2.getVerfasserId());
	Assert.assertEquals(ticketId, t2.getTicketId());
	
	}
}
