package modell.implementierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.factory.MitarbeiterFactory;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.interfaces.RowMappingBasis;

public class ImpRowMappingMitarbeiter implements RowMappingBasis<Mitarbeiter> {

	@Override
	public Mitarbeiter mapRow(ResultSet dbResult) throws SQLException {
		Mitarbeiter m = MitarbeiterFactory.getInstance();
		m.setMitarbeiterId(dbResult.getInt("mitarbeiter_id"));
		m.setEmail(dbResult.getString("email"));
		m.setVorname(dbResult.getString("vorname"));
		m.setLoginName(dbResult.getString("login_name"));
		m.setName(dbResult.getString("name"));
		m.setPasswort(dbResult.getString("passwort").toCharArray());
		return m;
	}
}
