package modell.implementierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.factory.TicketzuweisungFactory;
import modell.entitaeten.interfaces.Ticketzuweisung;
import modell.factory.DaoMitarbeiterFactory;
import modell.factory.DaoTicketFactory;
import modell.interfaces.RowMappingTicketzuweisung;

public class ImpRowMappingTicketzuweisung implements RowMappingTicketzuweisung {

	public ImpRowMappingTicketzuweisung(){
		
	}
	
	public Ticketzuweisung mapRow(ResultSet dbResult) throws SQLException {
		
		Ticketzuweisung tz = TicketzuweisungFactory.getInstance();
		tz.setZuweisungsId(dbResult.getInt("zuweisung_id"));
		tz.setZeitpunkt(dbResult.getTimestamp("zeitpunkt"));
		tz.setMitarbeiter(DaoMitarbeiterFactory.getInstance().getMitarbeiter(dbResult.getInt("mitarbeiter_id")));
		tz.setTicket(DaoTicketFactory.getInstance().getTicket(dbResult.getInt("ticket_id")));
		
		return tz;
	}
	
	
	
}
