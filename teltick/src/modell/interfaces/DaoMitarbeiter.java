package modell.interfaces;

import modell.entitaeten.interfaces.Mitarbeiter;

public interface DaoMitarbeiter{
	public Mitarbeiter getValue(int id);
	public Mitarbeiter getValue(String login_name, char[] passwort);
	public boolean speicherInDB(Mitarbeiter r);
	public boolean loescheVonDB(Mitarbeiter r);
	public boolean update(Mitarbeiter r);
	public boolean inDBvorhanden(int id);
	public boolean inDBvorhanden(String login_name);
	public boolean zugangsdatenRichtig(String login_name, char[] passwort);
}
