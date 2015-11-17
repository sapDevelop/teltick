package modell.entitaeten.implementierung;

import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Recht;

public class ImpRecht implements Recht {
	
	private String bezeichung;
	private boolean autostart;
	private int indexDb;
	private Fenster zugehoerigesFenster;
	
	public String getBezeichung() {
		return bezeichung;
	}
	
	public void setBezeichung(String bezeichung) {
		this.bezeichung = bezeichung;
	}
	
	public boolean isAutostart() {
		return autostart;
	}
	
	public void setAutostart(boolean autostart) {
		this.autostart = autostart;
	}
	
	public int getIndexDb() {
		return indexDb;
	}
	
	public void setIndexDb(int indexDb) {
		this.indexDb = indexDb;
	}
	
	public Fenster getZugehoerigesFenster() {
		return zugehoerigesFenster;
	}
	
	public void setZugehoerigesFenster(Fenster zugehoerigesFenster) {
		this.zugehoerigesFenster = zugehoerigesFenster;
	}
	
}
