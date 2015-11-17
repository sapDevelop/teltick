package modell.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.interfaces.Fenster;


public interface RowMappingFenster {
		//Interface für das Row-Mapping
		public Fenster mapRow (ResultSet dbResult) throws SQLException;
}
