package basis.implementierung;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import logger.LogFactory;

import org.apache.log4j.Logger;

import basis.interfaces.EingabePruefung;
import basis.interfaces.FeldFehlermeldung;

public class ImpEingabePruefung  implements EingabePruefung{
	
	private static Logger log = LogFactory.getInstance(ImpEingabePruefung.class.getName());

	
	@Override
	public String getMeldungPflichtfelderNichtAusgefuellt(FeldFehlermeldung[] arrArtikelFeldbezeichnungMeldungswort, HttpServletRequest request) {
		
		Vector<FeldFehlermeldung> vectFehler = getVectorNichtausgefuellteFelder(arrArtikelFeldbezeichnungMeldungswort, request);
		return getMeldungPflichtfelderNichtAusgefuellt(vectFehler);
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

	@Override
	public Vector<FeldFehlermeldung> getVectorNichtausgefuellteFelder(FeldFehlermeldung[] arrArtikelFeldbezeichnungMeldungswort, HttpServletRequest request) {
		
		Vector<FeldFehlermeldung> vectFehler = new Vector<FeldFehlermeldung>();
		
		log.info("Es wird überprüft, ob alle Pflichtfelder ausgefüllt sind.");
		String loggerFeldnamenTmp = "";
		//überprüft, welche Felder nicht ausgefüllt sind
		for (int i = 0; i < arrArtikelFeldbezeichnungMeldungswort.length; i++){
			if (request.getParameter(arrArtikelFeldbezeichnungMeldungswort[i].getFeldname()) == ""){
				vectFehler.add(arrArtikelFeldbezeichnungMeldungswort[i]);
				loggerFeldnamenTmp += (loggerFeldnamenTmp == "") ? arrArtikelFeldbezeichnungMeldungswort[i].getFeldname() : ", " + arrArtikelFeldbezeichnungMeldungswort[i].getFeldname();
			}
		}
		
		//Logger protokoliert den Vorgang
		if (loggerFeldnamenTmp == ""){
			log.info("Es sind alle Pflichtfelder ausgefüllt.");
		}else{
			log.info("Folgende Pflichtfelder sind nicht ausgefüllt: " + loggerFeldnamenTmp);
		}
		
		// TODO Auto-generated method stub
		return vectFehler;
	}

	@Override
	public String getMeldungPflichtfelderNichtAusgefuellt(Vector<FeldFehlermeldung> vectorNichtAusgefuellteFelder) {
		String meldung = "";
	
		//erstellt die Fehlermeldung für die nicht ausgefüllten Felder
		if ( vectorNichtAusgefuellteFelder.size() > 0 ){
			meldung = "Bitte geben Sie ";
			for ( int i = 0; i < vectorNichtAusgefuellteFelder.size(); i++){
				if ( i > 0 && i != (vectorNichtAusgefuellteFelder.size()-1)){
					meldung += ", ";
				}else if (i > 0 && i == (vectorNichtAusgefuellteFelder.size()-1)){
					meldung += " und ";
				}
				meldung += vectorNichtAusgefuellteFelder.get(i).getArtikel() + " " + vectorNichtAusgefuellteFelder.get(i).getMeldungswort();
			}
			meldung += " an.";
		}
		
		return meldung;
	}

	@Override
	public Vector<String> getVectorFeldnamenNichtAusgefuellteFelder(Vector<FeldFehlermeldung> felderNichtAusgefuellteFelder) {
		Vector<String> v = new Vector<String>();
		for(FeldFehlermeldung feld : felderNichtAusgefuellteFelder ){
			v.add(feld.getFeldname());
		}
		return v;
	}
}
