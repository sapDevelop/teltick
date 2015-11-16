package modell.implementierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.factory.RechtFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Recht;
import modell.interfaces.RowMappingRecht;

public class ImpRowMappingRecht implements RowMappingRecht {

	public ImpRowMappingRecht() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Recht mapRow(ResultSet dbResult, Fenster f) throws SQLException {
		
		Recht r = RechtFactory.getInstance();
		r.setIndexDb(dbResult.getInt("rechte_id"));
		r.setBezeichung(dbResult.getString("bezeichnung"));
		r.setAutostart(dbResult.getBoolean("fensterautostart"));
		r.setZugehoerigesFenster(f);
		
		return r;
	}

}
