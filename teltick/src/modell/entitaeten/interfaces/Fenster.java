package modell.entitaeten.interfaces;

public interface Fenster {

	public String getTitel();
	
	public void setTitel(String titel);
	
	public String getPfadJspDatei(); 
	
	public void setPfadJspDatei(String pfadJspDatei);
	
	public int getMinHoehe(); 
	
	public void setMinHoehe(int minHoehe); 
	
	public int getMinBreite();
	
	public void setMinBreite(int minBreite);
	
	public int getId();
	
	public void setId(int id);
	
	public int getDialog_von();
	
	public void setDialog_von(int dialog_von);
	
	public String getIcon();

	public void setIcon(String icon);
	
}
