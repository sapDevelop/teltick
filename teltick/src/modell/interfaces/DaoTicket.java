package modell.interfaces;

import java.util.Vector;

import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticket;
import modell.entitaeten.interfaces.Ticketzuweisung;

public interface DaoTicket {
	
	//Ticket um die TicketID zu sichern
	public boolean setTicket(Ticket t);
	
	public int getTicketId(Ticket t);
		
	public boolean updateTicket(Ticket t);
		
	public Ticket getTicket(int id);
	
	public Vector<Ticket> getAlleTickets();
	
	public Vector<Ticket> getTicketsFromMitarbeiter(Mitarbeiter m);
	
	public boolean loescheTicket(int id);
	
	public boolean setZuweisung(Ticketzuweisung tz);

	public Ticketzuweisung getZuweisung(Ticket t);

	boolean loescheZuweisung(int ticketId);
	
	
}
