package basis.implementierung;

import javax.servlet.http.HttpSession;

import basis.interfaces.VorlagenFenster;

public class ImpVorlagenFenster implements VorlagenFenster{

	@Override
	public String getMeldungsbox(String content, String titel, String breite,	String hoehe, String icon, HttpSession session, boolean betriebenAufServlet) {
String iconName = icon + ".png";
		
		//Index für das Fenster bestimmen
		Integer fensterZaehlVariable = (Integer)session.getAttribute("fensterZaehlVariable");
		if (fensterZaehlVariable == null) fensterZaehlVariable = new Integer(-1);
		fensterZaehlVariable++;
		
		//Fensterzählvariable in der Session ablegen
		session.setAttribute("fensterZaehlVariable", fensterZaehlVariable);
		
		String selvet_taskleistenicon = "";
		String js = "";
		//Wenn die Methode von Serlvet aus aufgerufen wurde
		if (betriebenAufServlet){
			//Schreibt angaben für die Ajax-Übertrag zur Anzeige des Fensters in der Taskleiste
			selvet_taskleistenicon = fensterZaehlVariable.intValue() + ",bilder/fenstericons/" + iconName + "," + titel+ "\n";
		}
		//wenn von JSP-File aus
		else{
			js = "<script type=\"text/javascript\" >document.getElementById('task_leiste').innerHTML += '<img class=\"icon_task_leiste icon_task_leiste_markiert\" src=\"bilder/fenstericons/" + iconName + "\" id=\"icon_task_leiste_"+ fensterZaehlVariable.intValue() +"\" title=\""+ titel +"\" onclick=\"task_leiste_icon_clicked(this)\" />';</script>";
		}
		
		String ausgabe = selvet_taskleistenicon
				+"<div class=\"fenster\" id=\"fenster_"+ fensterZaehlVariable.intValue() + "\" style=\"left:120px; top:120px;min-width:"+ breite + ";min-height:"+ hoehe +"; z-index:3\" >"
				+"	<div class=\"fenster_kopf\" >"
				+"		<img class=\"icon_fenster\" src=\"bilder/fenstericons/" + iconName + "\" /> <span class=\"meldungsfenster_titel\">"+ titel + "</span>"
				+"		<span class=\"bereich_fenster_buttons\" >"
				+"			<span onclick=\"fenster_minimieren('fenster_"+ fensterZaehlVariable.intValue() +"');\" class=\"fensterbutton fensterbutton_minimieren\" title=\"Minimieren\" ></span>"
				+"			<span onclick=\"fenster_schliessen('fenster_"+ fensterZaehlVariable.intValue() +"');\" class=\"fensterbutton fensterbutton_schliessen\" title=\"Schlie&szlig;en\" ></span>"
				+"		</span>"
				+"		<div class=\"clear_right\" ></div>"
				+"	</div>"
				+"	<div class=\"content content_meldungsbox\" id=\"fenster_content_"+ fensterZaehlVariable.intValue() +"\" >"
				+ 		content
				+"	</div>"
				+"	<div class=\"meldungsbox_bereich_button\" >"
				+"		<input type=\"button\" value=\"Schlie&szlig;en\" class=\"button_schliessen_gross_fenster button_benutzerverwaltung\" title=\"Schlie&szlig;en\" onclick=\"fenster_schliessen('fenster_"+ fensterZaehlVariable.intValue() +"');\" />"
				+"		<div class=\"clear_right\" ></div>"
				+"	</div>"
				+"</div>"
				+js;
		
		return ausgabe;		
	}



}
