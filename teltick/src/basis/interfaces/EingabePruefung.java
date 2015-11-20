package basis.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface EingabePruefung {
	public String getMeldungPflichtfelderNichtAusgefuellt(String[][] arrArtikelFeldbezeichnung, HttpServletRequest request);
	public boolean emailAdresseGueltig(String adresse);
}
