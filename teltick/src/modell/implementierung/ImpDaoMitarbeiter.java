package modell.implementierung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import controller.BenutzerverwaltungController;
import logger.LogFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Recht;
import modell.factory.HSqlDbZugriffFactory;
import modell.factory.RowMappingFensterSingletonFactory;
import modell.factory.RowMappingMitarbeiterSingletonFactory;
import modell.factory.RowMappingRechtSingletonFactory;
import modell.interfaces.DBZugriff;
import modell.interfaces.DaoMitarbeiter;

public class ImpDaoMitarbeiter implements DaoMitarbeiter {

	private static Logger log = LogFactory.getInstance(ImpDaoMitarbeiter.class.getName());
	
	@Override
	public Mitarbeiter getMitarbeiter(int id) {
		Mitarbeiter m = null;
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		
		try {
			Connection verbindung = dbZugriff1.verbinden();
						
			//Lädt den Mitarbeiter aus der DB
			String abfrage = "select *  from mitarbeiter where mitarbeiter_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			pstmt.setInt(1,id);
			ResultSet result = pstmt.executeQuery();
			while(result.next()){
				m = RowMappingMitarbeiterSingletonFactory.getInstance().mapRow(result);
			}
			
			if (m != null) m.setRechte(getMitarbeiterRechte(m.getMitarbeiterId()));
			
			
			
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
		Connection verbindung = null;
		try {
			verbindung = dbZugriff1.verbinden();
			
			String anfrage1 = "delete from rechte where mitarbeiter_id = ?";
			PreparedStatement pstmt1 = verbindung.prepareStatement(anfrage1);
			pstmt1.setInt(1, r.getMitarbeiterId());
			pstmt1.executeUpdate();
			
			String anfrage = "delete from mitarbeiter where mitarbeiter_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(anfrage);
			
			pstmt.setInt(1, r.getMitarbeiterId());
			pstmt.executeUpdate();
			
			verbindung.commit();
		
			verbindung.close();
		}catch (SQLException e) {
			fehler = true;
			e.printStackTrace();
			
			try {
				verbindung.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			
			updateRechteMitarbeiter(r);
			
		}catch (SQLException e) {
			fehler = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return !fehler;
	}

	@Override
	public Mitarbeiter getMitarbeiter(String login_name, char[] passwort) {
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
			if (m != null) m.setRechte(getMitarbeiterRechte(m.getMitarbeiterId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return m;
	}

	@Override
	public boolean inDBvorhanden(int id) {
		return getMitarbeiter(id) != null;
	}

	@Override
	public boolean zugangsdatenRichtig(String login_name, char[] passwort) {
		return getMitarbeiter(login_name,passwort) != null;
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

	@Override
	public Vector<Recht> getMitarbeiterRechte(int idMitarbeiter) {
		
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		Vector<Recht> rechte = new Vector<Recht>();
		
		try {
			Connection verbindung = dbZugriff1.verbinden();
			
			//Bestimmt die Rechte des Mitarbeiters
			String abfrage = "select * "
					+ "from rechte r "
					+ " inner join fenster f using (fenster_id)"
					+ "where r.mitarbeiter_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			pstmt.setInt(1,idMitarbeiter);
			
			ResultSet result = pstmt.executeQuery();
			
			while(result.next()){
				Fenster f = RowMappingFensterSingletonFactory.getInstance().mapRow(result);
				Recht r =  RowMappingRechtSingletonFactory.getInstance().mapRow(result, f);
				rechte.add(r);
			}		
			
			verbindung.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return rechte;
	}

	@Override
	public boolean updateRechteMitarbeiter(Mitarbeiter r) {
		
		boolean fehler = false;
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		Connection verbindung = null;
		try {
			verbindung = dbZugriff1.verbinden();
			verbindung.setAutoCommit(false);
			
			//alle alten Rechte löschen
			String query = "delete from rechte where mitarbeiter_id = ?";
			PreparedStatement pstmt = verbindung.prepareStatement(query);
			pstmt.setInt(1, r.getMitarbeiterId());
			pstmt.executeUpdate();
			
			//alle neuen Rechte aus den Vector übernehmen
			Vector<Recht> rechte = r.getRechte();
			query= "insert into rechte (bezeichnung, mitarbeiter_id, fensterautostart, fenster_id) values ";
			for(int i = 0; i < rechte.size(); i++){
				if (i > 0) query += ",";
				query += "(?, ?, ?, ?)";
			}
			
			pstmt = verbindung.prepareStatement(query);
			int j= 1;
			
			for (Recht r1 : rechte){				
				pstmt.setString(j, r1.getBezeichung());
				j++;
				pstmt.setInt(j, r.getMitarbeiterId());
				j++;
				pstmt.setBoolean(j, r1.isAutostart());
				j++;
				pstmt.setInt(j, r1.getZugehoerigesFenster().getId());
				j++;
			}
			pstmt.executeUpdate();
			
			verbindung.commit();
			
			verbindung.close();
		}catch (SQLException e) {
			log.error(e.getMessage());
			
			fehler = true;
			if (verbindung != null){
				try {
					verbindung.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		
		return !fehler;
	}

	@Override
	public Vector<Mitarbeiter> getAlleMitarbeiter(boolean mitAdmin) {
		
		Vector<Mitarbeiter> mitarbeiter = new Vector<Mitarbeiter>();
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		
		try {
			Connection verbindung = dbZugriff1.verbinden();
						
			//Lädt den Mitarbeiter aus der DB
			String abfrage =  mitAdmin ? "select * from mitarbeiter" : "select * from mitarbeiter where login_name != 'Admin'";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			ResultSet result = pstmt.executeQuery();
			while(result.next()){
				Mitarbeiter m = RowMappingMitarbeiterSingletonFactory.getInstance().mapRow(result);
				if (m != null) m.setRechte(getMitarbeiterRechte(m.getMitarbeiterId()));
				mitarbeiter.add(m);
			}
			
			verbindung.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mitarbeiter;
	}

}
