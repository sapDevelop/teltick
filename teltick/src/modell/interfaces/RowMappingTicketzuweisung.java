package modell.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.interfaces.Ticket;
import modell.entitaeten.interfaces.Ticketzuweisung;

public interface RowMappingTicketzuweisung {
	
	public Ticketzuweisung mapRow (ResultSet dbResult) throws SQLException;

}
