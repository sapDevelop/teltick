package basis.implementierung;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import logger.LogFactory;

import org.apache.log4j.Logger;

import basis.interfaces.VorlagenFenster;

public class ImpVorlagenFenster implements VorlagenFenster{
	
	private static Logger log = LogFactory.getInstance(ImpVorlagenFenster.class.getName());

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
				+"<div class=\"fenster\" id=\"fenster_"+ fensterZaehlVariable.intValue() + "\" style=\"left:0px; top:100px;min-width:"+ breite + ";min-height:"+ hoehe +"; z-index:3;\" >"
				+"	<div class=\"fenster_kopf\" >"
				+"		<img class=\"icon_fenster\" src=\"bilder/fenstericons/" + iconName + "\" /> <span class=\"meldungsfenster_titel\">"+ titel + "</span>"
				+"		<span class=\"bereich_fenster_buttons\" >"
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

	@Override
	public String getBestaetigungsBox(String content, String titel,	String breite, String hoehe, String icon, HttpSession session, boolean betriebenAufServlet, String methode, String aktion,String dialogKennung, ServletRequest request,int submitUmleitenNachFensterId) {
		
		log.info("Bestätigungsbox wird angezeigt.");
		
		String iconName = icon + ".png";
		
		String submitUmleiten = submitUmleitenNachFensterId == -1 ? "" : "onsubmit=\"return submitUmleiten(this, '"+ submitUmleitenNachFensterId +"');\"";
		
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
				+"<div class=\"fenster\" id=\"fenster_"+ fensterZaehlVariable.intValue() + "\" style=\"left:0px; top:100px;min-width:"+ breite + ";min-height:"+ hoehe +"; z-index:3;\" >"
				+"	<div class=\"fenster_kopf\" >"
				+"		<img class=\"icon_fenster\" src=\"bilder/fenstericons/" + iconName + "\" /> <span class=\"meldungsfenster_titel\">"+ titel + "</span>"
				+"		<span class=\"bereich_fenster_buttons\" >"
				+"			<span onclick=\"fenster_schliessen('fenster_"+ fensterZaehlVariable.intValue() +"');\" class=\"fensterbutton fensterbutton_schliessen\" title=\"Schlie&szlig;en\" ></span>"
				+"		</span>"
				+"		<div class=\"clear_right\" ></div>"
				+"	</div>"
				+"	<div class=\"content content_meldungsbox\" id=\"fenster_content_"+ fensterZaehlVariable.intValue() +"\" >"
				+ 		content
				+"	</div>"
				+"	<form method=\""+ methode +"\" action=\""+ aktion +"\" class=\"bestaetigungsbox_bereich_button\" onreset=\"fenster_schliessen('fenster_"+ fensterZaehlVariable.intValue() +"');\"  "+ submitUmleiten +" >";
		
			//Übernimmt alle Parameter für die aktuelle Anfrage
		
			log.info("Box übernimmt die Request-Parameter des Parents.");
			log.info("Folgende Parameter werden übernommen:");
			Enumeration<String> enm=request.getParameterNames();
			while(enm.hasMoreElements()){
				String parameterName = enm.nextElement();
				String wert = request.getParameter(parameterName);
				if (!parameterName.equals("submit")){
					ausgabe += "<input type=\"hidden\" value=\""+convertToHtmlSpecialChars(wert)+ "\"  name=\""+convertToHtmlSpecialChars(parameterName)+ "\"  />";
					log.info(parameterName + ": " + wert);
				}
			}
			log.info("Ende: Parameterübernahme");
		
		
		 	ausgabe += "" 
				+"		<input type=\"hidden\" value=\""+ dialogKennung +"\"  name=\"dialogKennung\"  />"
				+"		<input type=\"submit\" value=\"Ja\"  title=\"Ja\" name=\"submit\" onclick=\"submit_button=this.value;\" />"
				+"		<input type=\"reset\" value=\"Nein\"  title=\"Nein\" name=\"Abbrechen\" />"
				+"	</form>"
				+"	<div class=\"clear_right\" ></div>"
				+"</div>"
				+js;
		
		return ausgabe;	
	}

	@Override
	public String getBestaetigungsBox(String content, String titel,	String breite, String hoehe, String icon, HttpSession session,boolean betriebenAufServlet, String methode, String aktion, String dialogKennung, ServletRequest request) {
		return getBestaetigungsBox(content, titel, breite, hoehe, icon, session, betriebenAufServlet, methode, aktion,dialogKennung,request, -1);
	}
	
	public String convertToHtmlSpecialChars( String input){
		return input.replace("\"", "&quote;").replace("<", "&lt;").replace(">", "&gt;");
	}

}
