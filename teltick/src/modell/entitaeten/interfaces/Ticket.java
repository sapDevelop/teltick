package modell.entitaeten.interfaces;

import java.sql.Timestamp;

public interface Ticket {

	public int getTicketId();
	
	public void setTicketId(int ticketId);

	public Timestamp getErstelldatum();

	public void setErstelldatum(Timestamp erstelldatum);

	public String getBeschreibung();

	public void setBeschreibung(String beschreibung);

	public String getTitel();

	public void setTitel(String titel);

	public int getVerfasserId();

	public void setVerfasserId(int verfasserId);

}
