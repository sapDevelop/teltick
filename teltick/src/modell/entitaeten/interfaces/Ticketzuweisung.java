package modell.entitaeten.interfaces;

import java.sql.Timestamp;

public interface Ticketzuweisung {
	

	
	public void setZuweisungsId(int zuweisungsId);

	public int getZuweisungsId();
	
	public void setZeitpunkt(Timestamp time);
	
	public Timestamp getZeitpunkt();

	Mitarbeiter getMitarbeiter();

	void setMitarbeiter(Mitarbeiter mitarbeiter);

	Ticket getTicket();

	void setTicket(Ticket ticket);
	

	
}
