package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;

import basis.factory.FehlermeldungPflichtfeldNichtAusgefuelltSingletonFactory;
import basis.factory.FeldFehlermeldungFactory;
import basis.interfaces.EingabePruefung;
import basis.interfaces.FeldFehlermeldung;
import logger.LogFactory;
import modell.entitaeten.factory.FensterFactory;
import modell.entitaeten.factory.MitarbeiterFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Recht;
import modell.factory.DaoFensterFactory;
import modell.factory.DaoMitarbeiterFactory;
import modell.interfaces.DaoFenster;

/**
 * Servlet implementation class BenutzerverwaltungController
 */
@WebServlet("/BenutzerverwaltungController")
public class BenutzerverwaltungController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LogFactory.getInstance(BenutzerverwaltungController.class.getName());
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Get aufgerufen -> Weiterleitung zur Startseite");
		response.sendRedirect("./");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		log.info("Post aufgerufen");
		
		//Überprüft, ob der Benutzer überhaubt die Rechte hat die Rechte zu verwalten
		HttpSession session = request.getSession();
		Mitarbeiter m = (Mitarbeiter) session.getAttribute("angemeldeterMitarbeiter");
		if (request.getParameter("id") != null){
			Fenster f = FensterFactory.getInstance();
			f.setId(0);
			if (m != null && m.zugriffsRechtFenster(f) ){
				
				String pfad_inc_jsp = null;
				
				//Bestimmt, welche Button geklickt wurde
				switch (request.getParameter("submit")){
				
					/*
					 * #################### Seite "Neuer Benutzer" laden
					 */
					case "Neuer Benutzer":
						log.info("Button: \"Neuer Benutzer\" geklickt");
						pfad_inc_jsp = "admin_benutzeruebersicht_benutzer_aendern.jsp";
						request.setAttribute("vorgang", "neuerBenutzer");
						
						//alle Rechte, für die Rechteliste ermitteln
						request.setAttribute("listeRechte", DaoFensterFactory.getInstance().getAlleFenster()); 	
					break;
					
					/*
					 * #################### Seite "Benutzer bearbeiten" laden
					 */
					case "Bearbeiten":
						log.info("Button: \"Benutzer ändern\" geklickt");
						pfad_inc_jsp = "admin_benutzeruebersicht_benutzer_aendern.jsp";
						request.setAttribute("vorgang", "aenderBenutzer");
						
						//alle Rechte, für die Rechteliste ermitteln
						request.setAttribute("listeRechte", DaoFensterFactory.getInstance().getAlleFenster());
						
						//Übergibt an die JSP-Seite, welche Benutzer bearbeitet werden soll
						if (request.getParameter("benutzer_radio") != null ){
							log.info("Benutzer wird an die JSP-Seite übertragen " + request.getParameter("benutzer_radio"));
							Mitarbeiter m1 =  DaoMitarbeiterFactory.getInstance().getMitarbeiter(Integer.valueOf(request.getParameter("benutzer_radio")));
							request.setAttribute("editUser", m1);
						}
					break;
					
					/*
					 * #################### Funktion: Abbrechen, Seite aktualisieren
					 */
					case "Aktualisieren":
					case "Abbrechen":
						log.info("Button: Aktualisieren bzw. Abbrechen geklickt");
						pfad_inc_jsp = "admin_benutzeruebersicht.jsp";
					break;
					
					/*
					 * #################### Funktion: Benutzer anlegen
					 */
					case "Anlegen":
						log.info("Button: \"Anlegen\" geklickt");
						pfad_inc_jsp = contBenutzerAnlegen(request, response);
					break;
				}
								
				if (pfad_inc_jsp != null){
					log.info("Für den Button entsprechende JSP-Seite wird geladen.");
					RequestDispatcher rd = request.getRequestDispatcher(pfad_inc_jsp);
					rd.forward(request, response);
				}
			}
			//Wenn der Benutzer keine Berechtigung hat
			else if (m!= null){
				log.info("Benutzer hat keine Berechtigung das Fenster zu öffnen.");
				out.println(request.getParameter("id")+"\n<div class=\"fehlermeldung_in_std_fenster\">Sie haben keine Berechtigung dieses Fenster zu &ouml;ffnen.</div>");
			}
			//Wenn die Session abgelaufen ist
			else{
				log.info("Session abgelaufen.");
				out.println(request.getParameter("id")+"\n<div class=\"fehlermeldung_in_std_fenster\">Sitzung ist abgelaufen. Sie m&uuml;ssen angemeldet sein, um ein Fenster &ouml;ffnen zu k&ouml;nnen.</div>");
			}
		}
		//Gibt eine Meldung aus, wenn der Benutzer keine Rechte hat
		else{
			log.info("Keine Fenster-ID übertragen.");
			out.println(request.getParameter("id")+"\n<div class=\"fehlermeldung_in_std_fenster\">Es wurde keine Fenster-Id &uuml;bertragen.</div>");
		}
	}

	/**
	 * Kontroller, um einen neuen Benutzer anzulegen
	 * @param request
	 * @param response
	 */
	private String contBenutzerAnlegen(HttpServletRequest request, HttpServletResponse response){
		
		String jsp_file = "";
		
		//überprüft, ob alle wichtigen Felder ausgefüllt sind
		if ( request.getParameter("id") != null){
			
			FeldFehlermeldung[] arrPflichtfelder = {
				FeldFehlermeldungFactory.getInstance("Login-Name", "ein", "login_name"),
				FeldFehlermeldungFactory.getInstance("Vorname", "ein", "vorname"),
				FeldFehlermeldungFactory.getInstance("Name", "ein", "name"),
				FeldFehlermeldungFactory.getInstance("Email-Adresse", "eine", "email"),
				FeldFehlermeldungFactory.getInstance("Passwort", "ein", "passwort")
			};
			
			EingabePruefung eingabePruefung1 = FehlermeldungPflichtfeldNichtAusgefuelltSingletonFactory.getInstance();
			
			//Erstellt eine Fehlermeldung, wenn nicht alle Felder ausgefüllt sind
			Vector<FeldFehlermeldung> nichtAusgefuellteFelder = eingabePruefung1.getVectorNichtausgefuellteFelder(arrPflichtfelder, request);
			String meldung = eingabePruefung1.getMeldungPflichtfelderNichtAusgefuellt(nichtAusgefuellteFelder);
			
			//Vector der die Feldnamen enthält, die rot markiert werden sollen
			Vector<String> feldnamenUnalsgefuellteFelder = eingabePruefung1.getVectorFeldnamenNichtAusgefuellteFelder(nichtAusgefuellteFelder);
			
			boolean fehler = false;
			if (meldung != ""){
				fehler = true;
			}
			//Überprüft, ob die Email-Adresse um gültigen Format ist
			else if (!eingabePruefung1.emailAdresseGueltig(request.getParameter("email"))){
				meldung = "Email-Format ist ungültig.";
				feldnamenUnalsgefuellteFelder.add("email");
				fehler = true;
			}
			
			//Zeigt auf dem Desktop eine Fehlermeldung an, wenn die Eingabe nicht gültig war
			if (fehler){
				//Übergibt an die JSP-Seite die Fehlermeldung
				request.setAttribute("fehlermeldung", meldung);
				
				request.setAttribute("vorgang", "neuerBenutzer");
				
				//alle Rechte, für die Rechteliste ermitteln
				request.setAttribute("listeRechte", DaoFensterFactory.getInstance().getAlleFenster());
				
				//Übergibt an die JSP-Seite, welche Felder rot markiert werden sollen
				request.setAttribute("felderFehler", feldnamenUnalsgefuellteFelder);
				
				/*
				 * Alte Eingabe in einen Obekt von Typ-Mitarbeiter speichern, 
				 * damit die Werter auf der JSP-Seite mit der Programmierung der Änderungsfunktion angezeigt werden kann
				 */
				Mitarbeiter werteNeuerM = MitarbeiterFactory.getInstance();
				werteNeuerM.setEmail(request.getParameter("email"));
				werteNeuerM.setLoginName(request.getParameter("login_name"));
				werteNeuerM.setName(request.getParameter("name"));
				werteNeuerM.setVorname(request.getParameter("vorname"));
				werteNeuerM.setRechte(new Vector<Recht>());
				request.setAttribute("editUser", werteNeuerM);
				
				jsp_file = "admin_benutzeruebersicht_benutzer_aendern.jsp";
			}
			//Wenn die ganze Eingabe gültig war, wird der Benutzer angelegt
			else{
				
			}
		}else{
			log.error("Fenster-Id wurde nicht übermittelt-> Fenster: Benutzerverwaltung (Benutzeranlegen)");
		}
		
		return jsp_file;
	}
}
