package modell.entitaeten.implementierung;

import modell.entitaeten.interfaces.Fenster;

public class ImpFenster implements Fenster {

	private String titel;
	private String pfadJspDatei;
	private int minHoehe, minBreite;
	private int id;
	private int dialog_von;
	private String icon;
	
	public String getTitel() {
		return titel;
	}
	
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public String getPfadJspDatei() {
		return pfadJspDatei;
	}
	
	public void setPfadJspDatei(String pfadJspDatei) {
		this.pfadJspDatei = pfadJspDatei;
	}
	
	public int getMinHoehe() {
		return minHoehe;
	}
	
	public void setMinHoehe(int minHoehe) {
		this.minHoehe = minHoehe;
	}
	
	public int getMinBreite() {
		return minBreite;
	}
	
	public void setMinBreite(int minBreite) {
		this.minBreite = minBreite;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDialog_von() {
		return dialog_von;
	}
	
	public void setDialog_von(int dialog_von) {
		this.dialog_von = dialog_von;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	
}
