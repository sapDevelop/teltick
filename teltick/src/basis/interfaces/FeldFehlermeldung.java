package basis.interfaces;

public interface FeldFehlermeldung {
	public void setMeldungswort(String wort);
	public void setArtikel(String artikel);
	public void setFeldname(String feldname);
	public String getMeldungswort();
	public String getArtikel();
	public String getFeldname();
}
