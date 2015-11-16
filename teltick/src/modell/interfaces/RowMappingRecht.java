package modell.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Recht;

public interface RowMappingRecht {
	public Recht mapRow(ResultSet dbResult, Fenster f) throws SQLException;
}
