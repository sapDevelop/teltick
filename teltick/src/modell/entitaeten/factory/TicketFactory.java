package modell.entitaeten.factory;

import modell.entitaeten.implementierung.ImpTicket;
import modell.entitaeten.interfaces.Ticket;

public class TicketFactory {

	public static Ticket  getInstance(){
		return new ImpTicket();
		
	}
}
