package modell.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.interfaces.Mitarbeiter;

public interface RowMappingMitarbeiter {
	//Interface für das Row-Mapping
	public Mitarbeiter mapRow (ResultSet dbResult) throws SQLException;
}
