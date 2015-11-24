package modell.entitaeten.implementierung;

import modell.entitaeten.interfaces.Ticket;

public class ImpTicket implements Ticket {
	
	private int ticketId,verfasserId;
	private String erstelldatum, beschreibung, titel;

	@Override
	public int getTicketId() {
		return ticketId;
	}
	
	@Override
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
		
	}

	@Override
	public String getErstelldatum() {
		return erstelldatum;
	}

	@Override
	public void setErstelldatum(String erstelldatum) {
		this.erstelldatum = erstelldatum;
	}

	@Override
	public String getBeschreibung() {
		return beschreibung;
	}

	@Override
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	@Override
	public String getTitel() {
		return titel;
	}

	@Override
	public void setTitel(String titel) {
		this.titel = titel;
	}

	@Override
	public int getVerfasserId() {
		return verfasserId;
	}

	@Override
	public void setVerfasserId(int verfasserId) {
		this.verfasserId = verfasserId;
	}

	



}
