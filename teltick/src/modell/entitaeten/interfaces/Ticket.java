package modell.entitaeten.interfaces;

public interface Ticket {

	public int getTicketId();
	
	public void setTicketId(int ticketId);

	//timesstamp?
	public String getErstelldatum();

	public void setErstelldatum(String erstelldatum);

	public String getBeschreibung();

	public void setBeschreibung(String beschreibung);

	public String getTitel();

	public void setTitel(String titel);

	public int getVerfasserId();

	public void setVerfasserId(int verfasserId);

}
