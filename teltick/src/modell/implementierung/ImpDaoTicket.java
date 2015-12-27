package modell.implementierung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.log4j.Logger;
import logger.LogFactory;
import modell.entitaeten.factory.TicketFactory;
import modell.entitaeten.factory.TicketzuweisungFactory;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticket;
import modell.entitaeten.interfaces.Ticketzuweisung;
import modell.factory.DaoMitarbeiterFactory;
import modell.factory.HSqlDbZugriffFactory;
import modell.factory.RowMappingMitarbeiterSingletonFactory;
import modell.factory.RowMappingTicketSingletonFactory;
import modell.factory.RowMappingTicketzuweisungSingletonFactory;
import modell.interfaces.DBZugriff;
import modell.interfaces.DaoTicket;

public class ImpDaoTicket implements DaoTicket {
	
	private static Logger log = LogFactory.getInstance(ImpDaoTicket.class.getName());

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
			pstmt.setInt(4, t.getVerfasser().getMitarbeiterId());
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
	public boolean setTicketzuweisung(Ticketzuweisung tz) {
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		boolean fehler = false;

		try {
			Connection verbindung = dbZugriff1.verbinden();
			String anfrage = "insert into ticketzuweisung (zeitpunkt, mitarbeiter_id, ticket_id) values (?, ?, ?)";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);

			pstmt.setTimestamp(1, tz.getZeitpunkt());
			pstmt.setInt(2, tz.getMitarbeiter().getMitarbeiterId());
			pstmt.setInt(3, tz.getTicket().getTicketId());
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
		Vector<Ticket> ticket = new Vector<Ticket>();

		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		try {
			Connection verbindung = dbZugriff1.verbinden();

			//Lädt Ticket anhand der TicketId aus der DB
			String abfrage = "SELECT * "
					+ "FROM ticket, ticketzuweisung "
					+ "WHERE ticket.ticket_id=ticketzuweisung.ticket_id "
					+ "AND ticketzuweisung.mitarbeiter_id=?; ";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			pstmt.setInt(1, m.getMitarbeiterId());
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
	public Ticketzuweisung getTicketzuweisung(int ticketId) {

		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		Ticketzuweisung tzr = null;
		boolean fehler = false;

		try {
			Connection verbindung = dbZugriff1.verbinden();
			String anfrage = "select * from ticketzuweisung where ticket_id = ?;";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);
			pstmt.setInt(1, ticketId);
			ResultSet result = pstmt.executeQuery();

			tzr = TicketzuweisungFactory.getInstance();

			while(result.next()){
				tzr	 = RowMappingTicketzuweisungSingletonFactory.getInstance().mapRow(result);
			}

			verbindung.close();
		}catch (SQLException e) {
			fehler = true;
			e.printStackTrace();
		}

		return tzr;


	}

	@Override
	public Vector<Ticket> getTicktSuche(String suche) {

		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();

		Vector<Ticket> ticket = new Vector<Ticket>();
		String[] suchStrings = suche.split(" ");

		log.info("Anzahl der Suchwörter: " + suchStrings.length);

		for(int i = 0; i<suchStrings.length; i++)
		{
			log.info("Suche nach: " + suchStrings[i]);

			try {
				Connection verbindung = dbZugriff1.verbinden();

				//Lädt Ticket anhand der TicketId aus der DB
				String abfrage = "SELECT * "
						+ "FROM ticket, ticketzuweisung, mitarbeiter "
						+ "WHERE ticket.TICKET_ID LIKE ? "
						+ "OR ticket.beschreibung LIKE ? "
						+ "OR ticket.titel LIKE ? "
						+ "AND ticket.ticket_id = ticketzuweisung.ticket_id "
						+ "AND ticketzuweisung.mitarbeiter_id = mitarbeiter.mitarbeiter_id "
						+ "OR mitarbeiter.vorname LIKE ? "
						+ "OR mitarbeiter.name LIKE ? ";
				PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
				pstmt.setString(1, "%" + suchStrings[i] +"%");
				pstmt.setString(2, "%" + suchStrings[i] +"%");
				pstmt.setString(3, "%" + suchStrings[i] +"%");
				pstmt.setString(4, "%" + suchStrings[i] +"%");
				pstmt.setString(5, "%" + suchStrings[i] +"%");
				ResultSet result = pstmt.executeQuery();

				while(result.next()){
					Ticket t = RowMappingTicketSingletonFactory.getInstance().mapRow(result);
					//Überprüfen ob schon vorhanden
					boolean vorhanden = false;
					for(int a = 0; a< ticket.size() && !vorhanden; a++)
					{
						if(t.getTicketId()== ticket.get(a).getTicketId())
						{
							vorhanden = true;
						}
					}

					if(!vorhanden)
					{
						ticket.add(t);
					}
				}


				verbindung.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return ticket;
	}






}
