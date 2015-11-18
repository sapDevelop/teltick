package modell.interfaces;

import java.util.Vector;

import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Recht;

public interface DaoMitarbeiter{
	public Mitarbeiter getMitarbeiter(int id);
	
	public Mitarbeiter getMitarbeiter(String login_name, char[] passwort);
	
	public Vector<Recht> getMitarbeiterRechte(int idMitarbeiter);
	
	public boolean updateRechteMitarbeiter(Mitarbeiter r);
	
	public boolean speicherInDB(Mitarbeiter r);
	
	public boolean loescheVonDB(Mitarbeiter r);
	
	public boolean update(Mitarbeiter r);
	
	public boolean inDBvorhanden(int id);
	
	public boolean inDBvorhanden(String login_name);
	
	public boolean zugangsdatenRichtig(String login_name, char[] passwort);
	
	public Vector<Mitarbeiter> getAlleMitarbeiter();
}
