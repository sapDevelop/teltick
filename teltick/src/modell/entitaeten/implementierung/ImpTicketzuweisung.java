package modell.entitaeten.implementierung;

import java.sql.Timestamp;

import modell.entitaeten.interfaces.Ticketzuweisung;

public class ImpTicketzuweisung implements Ticketzuweisung {

	private int zuweisungsId, mitarbeiterId, ticketId;
	private Timestamp zeitpunkt;

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

	@Override
	public void setMitarbeiterId(int mitarbeiterId) {
		this.mitarbeiterId = mitarbeiterId;
	}

	@Override
	public int getMitarbeiterId() {
		return mitarbeiterId;
	}

	@Override
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	@Override
	public int getTicketId() {
		return ticketId;
	}

}
