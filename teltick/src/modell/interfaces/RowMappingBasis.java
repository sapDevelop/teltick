package modell.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMappingBasis<returnType> {
	//Interface für das Row-Mapping
	public returnType mapRow (ResultSet dbResult) throws SQLException;
}
