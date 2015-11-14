package modell.implementierung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.interfaces.Mitarbeiter;
import modell.factory.HSqlDbZugriffFactory;
import modell.factory.RowMappingMitarbeiterSingletonFactory;
import modell.interfaces.DBZugriff;
import modell.interfaces.DaoMitarbeiter;

public class ImpDaoMitarbeiter implements DaoMitarbeiter {

	@Override
	public Mitarbeiter getValue(int id) {
		Mitarbeiter m = null;
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		
		try {
			Connection verbindung = dbZugriff1.verbinden();
			
			String abfrage = "select * from mitarbeiter where mitarbeiter_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			pstmt.setInt(1,id);
			
			ResultSet result = pstmt.executeQuery();
			while(result.next()){
				m = RowMappingMitarbeiterSingletonFactory.getInstance().mapRow(result);
			}
			
			verbindung.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public boolean speicherInDB(Mitarbeiter r) {
		
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		
		boolean fehler = false;
		
		try {
			Connection verbindung = dbZugriff1.verbinden();
			String anfrage = "insert into mitarbeiter (email, vorname, login_name, name, passwort) values (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);
			
			pstmt.setString(1, r.getEmail());
			pstmt.setString(2, r.getVorname());
			pstmt.setString(3, r.getLoginName());
			pstmt.setString(4, r.getName());
			pstmt.setString(5, new String(r.getPasswort()));
			pstmt.executeUpdate();
			
			
			verbindung.close();
		}catch (SQLException e) {
			fehler = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return !fehler;
	}

	@Override
	public boolean loescheVonDB(Mitarbeiter r) {
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		boolean fehler = false;
		try {
			Connection verbindung = dbZugriff1.verbinden();
			String anfrage = "delete from mitarbeiter where mitarbeiter_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);
			
			pstmt.setInt(1, r.getMitarbeiterId());
			pstmt.executeUpdate();
		
			verbindung.close();
		}catch (SQLException e) {
			fehler = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return !fehler;
	}

	@Override
	public boolean update(Mitarbeiter r) {
		boolean fehler = false;
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		try {
			Connection verbindung = dbZugriff1.verbinden();
			String anfrage = "update mitarbeiter set email = ?, vorname=?, login_name=?, name=?, passwort=? where mitarbeiter_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);
			
			pstmt.setString(1, r.getEmail());
			pstmt.setString(2, r.getVorname());
			pstmt.setString(3, r.getLoginName());
			pstmt.setString(4, r.getName());
			pstmt.setString(5, new String(r.getPasswort()));
			pstmt.setInt(6, r.getMitarbeiterId());
			pstmt.executeUpdate();
		
			verbindung.close();
		}catch (SQLException e) {
			fehler = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return !fehler;
	}

	@Override
	public Mitarbeiter getValue(String login_name, char[] passwort) {
		Mitarbeiter m = null;
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		
		try {
			Connection verbindung = dbZugriff1.verbinden();
			
			String abfrage = "select * from mitarbeiter where login_name = ? and passwort = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			pstmt.setString(1,login_name);
			pstmt.setString(2,new String(passwort));
			
			ResultSet result = pstmt.executeQuery();
			while(result.next()){
				m = RowMappingMitarbeiterSingletonFactory.getInstance().mapRow(result);
			}
			
			verbindung.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public boolean inDBvorhanden(int id) {
		return getValue(id) != null;
	}

	@Override
	public boolean zugangsdatenRichtig(String login_name, char[] passwort) {
		return getValue(login_name,passwort) != null;
	}

	@Override
	public boolean inDBvorhanden(String login_name) {
		
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		int menge = 0;
		
		try {
			Connection verbindung = dbZugriff1.verbinden();
			
			String abfrage = "select count(*) menge from mitarbeiter where login_name = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			pstmt.setString(1,login_name);
			
			ResultSet result = pstmt.executeQuery();
			while(result.next()){
				menge = result.getInt("menge");
			}
			
			verbindung.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menge != 0;
	}

}
