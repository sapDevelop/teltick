package modell.implementierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.factory.TicketzuweisungFactory;
import modell.entitaeten.interfaces.Ticketzuweisung;
import modell.interfaces.RowMappingTicketzuweisung;

public class ImpRowMappingTicketzuweisung implements RowMappingTicketzuweisung {

	public ImpRowMappingTicketzuweisung(){
		
	}
	
	public Ticketzuweisung mapRow(ResultSet dbResult) throws SQLException {
		
		Ticketzuweisung tz = TicketzuweisungFactory.getInstance();
		tz.setZuweisungsId(dbResult.getInt("zuweisung_id"));
		tz.setZeitpunkt(dbResult.getTimestamp("zeitpunkt"));
		tz.setMitarbeiterId(dbResult.getInt("mitarbeiter_id"));
		tz.setTicketId(dbResult.getInt("ticket_id"));
		
		return tz;
	}
	
	
	
}
