package basis.interfaces;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public interface EingabePruefung {
	public String getMeldungPflichtfelderNichtAusgefuellt(FeldFehlermeldung[] arrArtikelFeldbezeichnung, HttpServletRequest request);
	public Vector<FeldFehlermeldung> getVectorNichtausgefuellteFelder(FeldFehlermeldung[] arrArtikelFeldbezeichnungMeldungswort, HttpServletRequest request);
	public String getMeldungPflichtfelderNichtAusgefuellt(Vector<FeldFehlermeldung> vectorNichtAusgefuellteFelder);
	public boolean emailAdresseGueltig(String adresse);
	public Vector<String> getVectorFeldnamenNichtAusgefuellteFelder(Vector<FeldFehlermeldung> felderNichtAusgefuellteFelder);
}
