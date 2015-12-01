package modell.interfaces;

import java.util.Vector;

import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticket;

public interface DaoTicket {
	
	//Ticket um die TicketID zu sichern
	public Ticket setLeeresTicket(Mitarbeiter m);
	
	public boolean updateTicket(Ticket t);
	
	public boolean setZuweisung(Ticket t, Mitarbeiter m);
	
	
	
	public Ticket getTicket(int id);
	
	
	
	public Vector<Ticket> getAlleTickets();
	
	
	public Vector<Ticket> getTicketsFromMitarbeiter(Mitarbeiter m);
	
	
	
	public boolean loescheTicket(int id);
	
	
}
