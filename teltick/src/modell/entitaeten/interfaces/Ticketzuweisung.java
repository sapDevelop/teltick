package modell.entitaeten.interfaces;

import java.sql.Timestamp;

public interface Ticketzuweisung {
	

	
	public void setZuweisungsId(int zuweisungsId);

	public int getZuweisungsId();
	
	public void setZeitpunkt(Timestamp time);
	
	public Timestamp getZeitpunkt();
	
	public void setMitarbeiterId(int mitarbeiterId);
	
	public int getMitarbeiterId();
	
	public void setTicketId(int ticketId);
	
	public int getTicketId();
	
}
