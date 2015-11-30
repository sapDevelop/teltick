package modell.entitaeten.interfaces;

public interface Recht {
	
	public String getBezeichung();
	
	public void setBezeichung(String bezeichung);
	
	public boolean isAutostart(); 
	
	public void setAutostart(boolean autostart); 
	
	public int getIndexDb();
	
	public void setIndexDb(int indexDb); 
	
	public Fenster getZugehoerigesFenster(); 
	
	public void setZugehoerigesFenster(Fenster zugehoerigesFenster); 	
	
}
