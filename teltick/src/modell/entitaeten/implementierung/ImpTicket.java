package modell.entitaeten.implementierung;

import java.sql.Timestamp;

import modell.entitaeten.interfaces.Ticket;
import modell.entitaeten.interfaces.Ticketzuweisung;

public class ImpTicket implements Ticket {
	
	private int ticketId,verfasserId;
	private String beschreibung, titel, datum, uhrzeit;
	

	private Timestamp erstelldatum;
	private Ticketzuweisung ticketzuweisung;

	@Override
	public String getDatum() {
		return datum;
	}
	@Override
	public void setDatum(String datum) {
		this.datum = datum;
	}
	@Override
	public String getUhrzeit() {
		return uhrzeit;
	}
	@Override
	public void setUhrzeit(String uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
	
	@Override
	public int getTicketId() {
		return ticketId;
	}
	
	@Override
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
		
	}

	@Override
	public Timestamp getErstelldatum() {
		return erstelldatum;
	}

	@Override
	public void setErstelldatum(Timestamp erstelldatum) {
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
	
	@Override
	public void setZuweisung(Ticketzuweisung ticketzuweisung){
		this.ticketzuweisung = ticketzuweisung;
	}
	
	@Override
	public Ticketzuweisung getZuweisung(){
		return ticketzuweisung;
	}
}
