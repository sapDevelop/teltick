package modell.implementierung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import modell.entitaeten.factory.TicketFactory;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticket;
import modell.entitaeten.interfaces.Ticketzuweisung;
import modell.factory.HSqlDbZugriffFactory;
import modell.factory.RowMappingMitarbeiterSingletonFactory;
import modell.factory.RowMappingTicketSingletonFactory;
import modell.interfaces.DBZugriff;
import modell.interfaces.DaoTicket;

public class ImpDaoTicket implements DaoTicket {

	@Override
	public Ticket getTicket(int id) {

		Ticket t = null;

		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		try {
			Connection verbindung = dbZugriff1.verbinden();

			//Lädt Ticket anhand der TicketId aus der DB
			String abfrage = "select *  from ticket where ticket_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			pstmt.setInt(1,id);
			ResultSet result = pstmt.executeQuery();

			while(result.next()){
				t = RowMappingTicketSingletonFactory.getInstance().mapRow(result);
			}

			verbindung.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}
	
	@Override
	public Vector<Ticket> getAlleTickets() {
		Vector<Ticket> ticket = new Vector<Ticket>();

		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		try {
			Connection verbindung = dbZugriff1.verbinden();

			//Lädt Ticket anhand der TicketId aus der DB
			String abfrage = "select *  from ticket;";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			ResultSet result = pstmt.executeQuery();

			while(result.next()){
				Ticket t = RowMappingTicketSingletonFactory.getInstance().mapRow(result);
				ticket.add(t);
			}

			verbindung.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ticket;
	}
	
	
	
	@Override
	public boolean updateTicket(Ticket t) {
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		boolean fehler = false;
		
		try {
			Connection verbindung = dbZugriff1.verbinden();

			
			String update = "update ticket set beschreibung = ?, titel = ? where ticket_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(update);
			pstmt.setString(1, t.getBeschreibung());
			pstmt.setString(2, t.getTitel());
			pstmt.setInt(3, t.getTicketId());
			pstmt.executeUpdate();
			
			verbindung.close();
		} catch (SQLException e) {
			fehler = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return !fehler;
	}

	@Override
	public boolean setTicket(Ticket t) {
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		
		boolean fehler = false;

		try {
			Connection verbindung = dbZugriff1.verbinden();
			String anfrage = "insert into ticket ( erstelldatum, beschreibung, titel, verfasser) values (?, ?, ?, ?)";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);

			pstmt.setTimestamp(1, t.getErstelldatum());
			pstmt.setString(2, t.getBeschreibung());
			pstmt.setString(3, t.getTitel());
			pstmt.setInt(4, t.getVerfasserId());
			pstmt.executeUpdate();

			verbindung.close();
		}catch (SQLException e) {
			fehler = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return !fehler;
	}
	
	
	public int getTicketId(Ticket t){
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		Ticket tr = null;
		
		try {
			Connection verbindung = dbZugriff1.verbinden();

			//Lädt Ticket anhand der TicketId aus der DB
			String abfrage = "select *  from ticket where titel = ? AND beschreibung = ? order by erstelldatum desc limit 1";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			pstmt.setString(1, t.getTitel());
			pstmt.setString(2, t.getBeschreibung());
			ResultSet result = pstmt.executeQuery();
			
			tr = TicketFactory.getInstance();
			
			while(result.next()){
				tr	 = RowMappingTicketSingletonFactory.getInstance().mapRow(result);
				}

			verbindung.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tr.getTicketId();
		
	}
	
	@Override
	public boolean setZuweisung(Ticketzuweisung tz) {
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		boolean fehler = false;

		try {
			Connection verbindung = dbZugriff1.verbinden();
			String anfrage = "insert into ticketzuweisung (zeitpunkt, mitarbeiter_id, ticket_id) values (?, ?, ?)";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);

			pstmt.setTimestamp(1, tz.getZeitpunkt());
			pstmt.setInt(2, tz.getMitarbeiterId());
			pstmt.setInt(3, tz.getTicketId());
			pstmt.executeUpdate();

			verbindung.close();
		}catch (SQLException e) {
			fehler = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return !fehler;
	}
	
	@Override
	public boolean loescheZuweisung(int ticketId) {
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		boolean fehler = false;

		try {
			Connection verbindung = dbZugriff1.verbinden();
			String anfrage = "delete from ticketzuweisung where ticket_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);

			pstmt.setInt(1, ticketId);
			pstmt.executeUpdate();

			verbindung.close();
		}catch (SQLException e) {
			fehler = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return !fehler;
	}

	@Override
	public Vector<Ticket> getTicketsFromMitarbeiter(Mitarbeiter m) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean loescheTicket(int ticketId) {
		
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		boolean fehler = false;

		try {
			Connection verbindung = dbZugriff1.verbinden();
			String anfrage = "delete from ticket where ticket_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);

			pstmt.setInt(1, ticketId);
			pstmt.executeUpdate();

			verbindung.close();
		}catch (SQLException e) {
			fehler = true;
			e.printStackTrace();
		}

		return !fehler;
	}

	@Override
	public Ticketzuweisung getZuweisung(Ticket t) {
		// TODO Auto-generated method stub
		return null;
	}


}
