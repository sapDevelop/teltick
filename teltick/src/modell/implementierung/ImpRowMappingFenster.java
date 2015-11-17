package modell.implementierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.factory.FensterFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.interfaces.RowMappingFenster;

public class ImpRowMappingFenster implements RowMappingFenster {

	public Fenster mapRow(ResultSet dbResult) throws SQLException {
		
		Fenster f = FensterFactory.getInstance();
		f.setId(dbResult.getInt("fenster_id"));
		f.setMinBreite(dbResult.getInt("min_breite"));
		f.setMinHoehe(dbResult.getInt("min_hoehe"));
		f.setDialog_von(dbResult.getInt("dialog_von"));
		f.setTitel(dbResult.getString("titel"));
		f.setPfadJspDatei(dbResult.getString("jsp_datei"));
		f.setIcon(dbResult.getString("icon"));
		
		return f;
	}

}
