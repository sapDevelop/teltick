package modell.implementierung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import modell.entitaeten.factory.TicketFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Ticket;
import modell.factory.DaoMitarbeiterFactory;
import modell.factory.DaoTicketFactory;
import modell.interfaces.RowMappingTicket;

public class ImpRowMappingTicket implements RowMappingTicket{

	public ImpRowMappingTicket(){
		
	}
	
	public Ticket mapRow(ResultSet dbResult) throws SQLException {
		
		Ticket t = TicketFactory.getInstance();
		t.setErstelldatum(dbResult.getTimestamp("erstelldatum"));
		t.setDatum(new SimpleDateFormat("dd.MM.yyyy").format(dbResult.getTimestamp("erstelldatum")));
		t.setUhrzeit(new SimpleDateFormat("HH:mm").format(dbResult.getTimestamp("erstelldatum")));
		t.setBeschreibung(dbResult.getString("beschreibung"));
		t.setTitel(dbResult.getString("titel"));
		t.setVerfasser(DaoMitarbeiterFactory.getInstance().getMitarbeiter(dbResult.getInt("verfasser")));
		t.setTicketId(dbResult.getInt("ticket_id"));
				
		return t;
	}
	
	
}
