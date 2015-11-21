package basis.implementierung;

import basis.interfaces.FeldFehlermeldung;

public class ImpFeldFehlermeldung implements FeldFehlermeldung {

	private String meldungswort, artikel, feldname;
	
	public ImpFeldFehlermeldung(){
		
	}
	
	public ImpFeldFehlermeldung(String meldungswort, String artikel, String feldname) {
		this.meldungswort = meldungswort;
		this.artikel = artikel;
		this.feldname = feldname;
	}

	@Override
	public void setMeldungswort(String wort) {
		meldungswort = wort;		
	}

	@Override
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}

	@Override
	public void setFeldname(String feldname) {
		this.feldname = feldname;
	}

	@Override
	public String getMeldungswort() {
		return meldungswort;
	}

	@Override
	public String getArtikel() {
		return artikel;
	}

	@Override
	public String getFeldname() {
		return feldname;
	}


}
