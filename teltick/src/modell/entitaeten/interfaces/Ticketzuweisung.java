package modell.entitaeten.interfaces;

public interface Ticketzuweisung {
	

	
	public void setZuweisungsId(int zuweisungsId);

	public int getZuweisungsId();
	
	public void setZeitpunkt(String zeitpunkt);
	
	public String getZeitpunkt();
	
	public void setMitarbeiterId(int mitarbeiterId);
	
	public int getMitarbeiterId();
	
	public void setTicketId(int ticketId);
	
	public int getTicketId();
	
}
