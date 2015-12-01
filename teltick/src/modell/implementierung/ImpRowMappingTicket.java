package modell.implementierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.factory.TicketFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Ticket;
import modell.interfaces.RowMappingTicket;

public class ImpRowMappingTicket implements RowMappingTicket{

	public ImpRowMappingTicket(){
		
	}
	
	public Ticket mapRow(ResultSet dbResult) throws SQLException {
		
		Ticket t = TicketFactory.getInstance();
		t.setErstelldatum(dbResult.getString("erstelldatum"));
		t.setBeschreibung(dbResult.getString("beschreibung"));
		t.setTitel(dbResult.getString("titel"));
		t.setVerfasserId(dbResult.getInt("verfasser"));
		
		return t;
	}
	
	
}
