package modell.entitaeten.implementierung;

import modell.entitaeten.interfaces.Mitarbeiter;

public class ImpMitarbeiter implements Mitarbeiter {

	private int mitarbeiterId;
	private String email, vorname, loginName, name;
	boolean angemeldet = false;
	private char[] passwort;
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public char[] getPasswort() {
		return passwort;
	}
	
	public void setPasswort(char[] passwort) {
		this.passwort = passwort;
	}
	
	public int getMitarbeiterId() {
		return mitarbeiterId;
	}
	
	public void setMitarbeiterId(int mitarbeiterId) {
		this.mitarbeiterId = mitarbeiterId;
	}

	public boolean isAngemeldet() {
		return angemeldet;
	}

	public void setAngemeldet(boolean angemeldet) {
		this.angemeldet = angemeldet;
	}
	
	
	
}
