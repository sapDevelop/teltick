package modell.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Ticket;

public interface RowMappingTicket {
	
	public Ticket mapRow (ResultSet dbResult) throws SQLException;

}
