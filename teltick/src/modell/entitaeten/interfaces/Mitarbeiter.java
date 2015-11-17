package modell.entitaeten.interfaces;

import java.util.Vector;

public interface Mitarbeiter {
	
	public String getEmail();
	
	public void setEmail(String email);
	
	public String getVorname();
	
	public void setVorname(String vorname);
	
	public String getLoginName();
	
	public void setLoginName(String loginName);
	
	public String getName();
	
	public void setName(String name);
	
	public char[] getPasswort();
	
	public void setPasswort(char[] passwort);
	
	public int getMitarbeiterId();
	
	public void setMitarbeiterId(int mitarbeiterId);
	
	public boolean isAngemeldet(); 

	public void setAngemeldet(boolean angemeldet);
	
	public Vector<Recht> getRechte();

	public void setRechte(Vector<Recht> rechte);
	
	public boolean zugriffsRechtFenster(Fenster f);
}
