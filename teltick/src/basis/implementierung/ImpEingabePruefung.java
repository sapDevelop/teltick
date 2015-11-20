package basis.implementierung;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import logger.LogFactory;

import org.apache.log4j.Logger;

import basis.interfaces.EingabePruefung;

public class ImpEingabePruefung  implements EingabePruefung{
	
	private static Logger log = LogFactory.getInstance(ImpEingabePruefung.class.getName());

	/**
	 * erstellt eine Fehlermeldung, wenn nicht alle Pflichtfelder ausgefüllt wind
	 */
	@Override
	public String getMeldungPflichtfelderNichtAusgefuellt(String[][] arrArtikelFeldbezeichnungMeldungswort, HttpServletRequest request) {
		
		Vector<String[]> vectFehler = new Vector<String[]>();
		String meldung = "";
		String loggerFeldnamenTmp = "";
		
		log.info("Es wird überprüft, ob alle Pflichtfelder ausgefüllt sind.");
		
		//überprüft, welche Felder nicht ausgefüllt sind
		for (int i = 0; i < arrArtikelFeldbezeichnungMeldungswort.length; i++){
			if (request.getParameter(arrArtikelFeldbezeichnungMeldungswort[i][2]) == ""){
				vectFehler.add(arrArtikelFeldbezeichnungMeldungswort[i]);
				loggerFeldnamenTmp += (loggerFeldnamenTmp == "") ? arrArtikelFeldbezeichnungMeldungswort[i][2] : ", " + arrArtikelFeldbezeichnungMeldungswort[i][2];
			}
		}
		
		//erstellt die Fehlermeldung für die nicht ausgefüllten Felder
		if ( vectFehler.size() > 0 ){
			meldung = "Bitte geben Sie ";
			for ( int i = 0; i < vectFehler.size(); i++){
				if ( i > 0 && i != (vectFehler.size()-1)){
					meldung += ", ";
				}else if (i > 0 && i == (vectFehler.size()-1)){
					meldung += " und ";
				}
				meldung += vectFehler.get(i)[0] + " " + vectFehler.get(i)[1];
			}
			meldung += " an.";
		}
		
		//Logger protokoliert den Vorgang
		if (loggerFeldnamenTmp == ""){
			log.info("Es sind alle Pflichtfelder ausgefüllt.");
		}else{
			log.info("Folgende Pflichtfelder sind nicht ausgefüllt: " + loggerFeldnamenTmp);
		}
		
		return meldung;
	}
	
	@Override
	public boolean emailAdresseGueltig(String adresse){
		String s = adresse;
		s = s.trim();
		
		int at, dot, len = s.length();
		
		// s nicht angegeben (oder nur Whitespaces), oder kein @ bzw .
		
		if ( (len == 0) ||
		     ((at=s.indexOf('@')) == -1) || ((dot=s.lastIndexOf('.')) == -1) )
		return false;
		
		// keine EMailadresse vor @ Zeichen oder . vor &
		
		if ( (at == 0) || (dot < at) )
		  return false;
		
		// Mindestens zwei Zeichen für die Endung
		return dot+2 < len;
	}
}
