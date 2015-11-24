package modell.entitaeten.implementierung;

import modell.entitaeten.interfaces.Ticketzuweisung;

public class ImpTicketzuweisung implements Ticketzuweisung {

	private int zuweisungsId, mitarbeiterId, ticketId;
	private String zeitpunkt;

	@Override
	public int getZuweisungsId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZuweisungsId(int zuweisungsId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setZeitpunkt(String zeitpunkt) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getZeitpunkt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMitarbeiterId(int mitarbeiterId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMitarbeiterId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTicketId(int ticketId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTicketId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
