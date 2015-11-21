package basis.factory;

import basis.implementierung.ImpFeldFehlermeldung;
import basis.interfaces.FeldFehlermeldung;

public class FeldFehlermeldungFactory {

	public static FeldFehlermeldung getInstance(){
		return new ImpFeldFehlermeldung();
	}
	
	public static FeldFehlermeldung getInstance(String meldungswort, String artikel, String feldname){
		return new ImpFeldFehlermeldung(meldungswort, artikel, feldname);
	}

}
