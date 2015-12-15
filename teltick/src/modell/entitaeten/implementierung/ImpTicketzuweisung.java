package modell.entitaeten.implementierung;

import java.sql.Timestamp;

import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticket;
import modell.entitaeten.interfaces.Ticketzuweisung;

public class ImpTicketzuweisung implements Ticketzuweisung {

	private int zuweisungsId;
	private Timestamp zeitpunkt;
	private Mitarbeiter mitarbeiter;
	private Ticket ticket;
	
	
	@Override
	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	@Override
	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	@Override
	public Ticket getTicket() {
		return ticket;
	}

	@Override
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}



	@Override
	public int getZuweisungsId() {
		return zuweisungsId;
	}

	@Override
	public void setZuweisungsId(int zuweisungsId) {
		this.zuweisungsId = zuweisungsId;

	}

	@Override
	public void setZeitpunkt(Timestamp zeitpunkt) {
		this.zeitpunkt = zeitpunkt;

	}

	@Override
	public Timestamp getZeitpunkt() {
		return zeitpunkt;
	}



}
