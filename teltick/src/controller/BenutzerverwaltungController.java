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
import basis.factory.Md5HashVerfahrenSingletonFactory;
import basis.interfaces.EingabePruefung;
import basis.interfaces.FeldFehlermeldung;
import logger.LogFactory;
import modell.entitaeten.factory.FensterFactory;
import modell.entitaeten.factory.MitarbeiterFactory;
import modell.entitaeten.factory.RechtFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Recht;
import modell.factory.DaoFensterFactory;
import modell.factory.DaoMitarbeiterFactory;
import modell.interfaces.DaoFenster;
import modell.interfaces.DaoMitarbeiter;

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
				 * #################### Sicherheitsfrage "Benutzer löschen" anzeigen
				 */
				case "Entfernen":
					log.info("Button: \"Benutzer entfernen\" geklickt");
					request.setAttribute("scherheitsfrage_loeschen", "anzeigen");
					log.info("Link zur Anzeige der Sicherheitsfrage, ob der Benutzer wirklich gelöscht werden soll wird aufgerufen.");
					pfad_inc_jsp = "admin_benutzeruebersicht.jsp";
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
				
				/*
				 * #################### Funktion: Benutzer ändern
				 */
				case "Speichern":
					log.info("Button \"Änderung speichern\" geklickt.");
					pfad_inc_jsp = contBenutzerAendern(request, response);
				break;
				
				/*
				 * #################### Bestätigungsbox: Ja-Button wurde geklickt
				 */
				case "Ja":
					log.info("Bestätigungsbox: \"Ja-Button\" wurde geklickt");
					//Bestimmt von welcher Funktions aus die Bestätigungsbox aufgerufen wurde
					if ( request.getParameter("dialogKennung") != null){
						log.info("Erkennung, welche Funktion die Bestätigung gesendet hat:");
						switch(request.getParameter("dialogKennung")){
							//Sender: Löschfunktion
							case "sicherheitsfrage_benutzer_loeschen":
								log.info("Sender: Benutzerlöschfunktion");
								pfad_inc_jsp = contBenutzerLoeschen(request, response);
							break;
						}
					}
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
			//Überprüft, ob der Benutzername schon belegt ist
			else if (DaoMitarbeiterFactory.getInstance().inDBvorhanden(request.getParameter("login_name"))){
				fehler = true;
				meldung ="Der Login-Name ist bereits von einen anderen Mitarbeiter belegt.";
				feldnamenUnalsgefuellteFelder.add("login_name");
			}
			
			//legt aus den Eingabewerten ein Objekt des Typs "Mitarbeiter" an
			Mitarbeiter werteNeuerM = MitarbeiterFactory.getInstance();
			werteNeuerM.setEmail(request.getParameter("email"));
			werteNeuerM.setLoginName(request.getParameter("login_name"));
			werteNeuerM.setName(request.getParameter("name"));
			werteNeuerM.setVorname(request.getParameter("vorname"));
			
			//fügt den Mitarbeiter seine Rechte hinzu
			fuegeMitarbeiterRechteDesFormularHinzu(werteNeuerM,request);
			
			
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
				request.setAttribute("editUser", werteNeuerM);
				
				jsp_file = "admin_benutzeruebersicht_benutzer_aendern.jsp";
			}
			//Wenn die ganze Eingabe gültig war, wird der Benutzer angelegt
			else{
				
				werteNeuerM.setPasswort(Md5HashVerfahrenSingletonFactory.getInstance().chiffriereText(request.getParameter("passwort")).toCharArray());
				
				//speichert den Mitarbeiter in der DB ab
				DaoMitarbeiter daoMNeu = DaoMitarbeiterFactory.getInstance();
				daoMNeu.speicherInDB(werteNeuerM);
				log.info("Neuer Mitarbeiter wird in der Datenbank angelegt.");
				
				//lädt den angelegten Mitarbeiter von der Datenbank, um die Mitarbeiter-ID zu erhalten
				werteNeuerM = daoMNeu.getMitarbeiter(werteNeuerM.getLoginName(), werteNeuerM.getPasswort());
				
				//fügt den Mitarbeiter seine Rechte hinzu
				log.info("Lädt den neuen Mitarbeiter aus der Datenbank.");
				fuegeMitarbeiterRechteDesFormularHinzu(werteNeuerM,request);
				log.info("Fügt den neuen Mitarbeiter die angekreuzten Rechte hinzu.");
				daoMNeu.updateRechteMitarbeiter(werteNeuerM);
				
				jsp_file = "admin_benutzeruebersicht.jsp";
				log.info("Benutzerübersicht wird geladen.");
			}
		}else{
			log.error("Fenster-Id wurde nicht übermittelt-> Fenster: Benutzerverwaltung (Benutzeranlegen)");
		}
		
		return jsp_file;
	}
	
	private String contBenutzerAendern(HttpServletRequest request, HttpServletResponse response){
		
		String jsp_file = "";
		DaoMitarbeiter daoMAendern = DaoMitarbeiterFactory.getInstance();
		
		//überprüft, ob alle wichtigen Felder ausgefüllt sind
		if ( request.getParameter("id") != null && request.getParameter("mitarbeiter_id") != null){
			
			FeldFehlermeldung[] arrPflichtfelder = {
				FeldFehlermeldungFactory.getInstance("Login-Name", "ein", "login_name"),
				FeldFehlermeldungFactory.getInstance("Vorname", "ein", "vorname"),
				FeldFehlermeldungFactory.getInstance("Name", "ein", "name"),
				FeldFehlermeldungFactory.getInstance("Email-Adresse", "eine", "email"),
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
			//Überprüft, ob der Benutzername schon belegt ist
			else{
				Mitarbeiter werteAendernM = daoMAendern.getMitarbeiter(Integer.valueOf(request.getParameter("mitarbeiter_id")));
				if ( !werteAendernM.getLoginName().equals(request.getParameter("login_name"))&& daoMAendern.inDBvorhanden(request.getParameter("login_name"))){
					fehler = true;
					meldung ="Der Login-Name ist bereits von einen anderen Mitarbeiter belegt.";
					feldnamenUnalsgefuellteFelder.add("login_name");
				}
			}
			
			//legt aus den Eingabewerten ein Objekt des Typs "Mitarbeiter" an
			Mitarbeiter werteAendernM = daoMAendern.getMitarbeiter(Integer.valueOf(request.getParameter("mitarbeiter_id")));
			werteAendernM.setEmail(request.getParameter("email"));
			werteAendernM.setLoginName(request.getParameter("login_name"));
			werteAendernM.setName(request.getParameter("name"));
			werteAendernM.setVorname(request.getParameter("vorname"));
			
			//fügt den Mitarbeiter seine Rechte hinzu
			fuegeMitarbeiterRechteDesFormularHinzu(werteAendernM,request);
			
			
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
				request.setAttribute("editUser", werteAendernM);
				
				jsp_file = "admin_benutzeruebersicht_benutzer_aendern.jsp";
			}
			//Wenn die ganze Eingabe gültig war, wird der Benutzer angelegt
			else{
				if ( request.getParameter("passwort") != "" )
					werteAendernM.setPasswort(Md5HashVerfahrenSingletonFactory.getInstance().chiffriereText(request.getParameter("passwort")).toCharArray());
				
				//ändert den Mitarbeiter in der DB ab
				daoMAendern.update(werteAendernM);
				log.info("Mitarbeiter wird in der Datenbank geändert.");
				
				
				jsp_file = "admin_benutzeruebersicht.jsp";
				log.info("Benutzerübersicht wird geladen.");
			}
		}else{
			log.error("Fenster-Id wurde nicht übermittelt-> Fenster: Benutzerverwaltung (Benutzeranlegen)");
		}
		
		return jsp_file;
	}
	
	/**
	 * Fügt den Mitarbeiter die im Formular angekreuzten Rechte hinzu
	 * @param m der Mitarbeiter
	 */
	private void fuegeMitarbeiterRechteDesFormularHinzu(Mitarbeiter m, HttpServletRequest request){
		
		String[] angekreuzteRechte = request.getParameterValues("rechte");
		Vector<Recht> vectMitarbeiterRechte = new Vector<Recht>();
		if ( angekreuzteRechte != null){
			log.info("Mitarbeiter werden Rechte hinzugefügt.");
			for(String fensterId : angekreuzteRechte){
				Recht recht1 = RechtFactory.getInstance();
				Fenster fensterRecht = FensterFactory.getInstance();
				
				fensterRecht.setId(Integer.valueOf(fensterId));
				
				recht1.setAutostart(false);
				recht1.setBezeichung("siehe Fenster");
				recht1.setZugehoerigesFenster(fensterRecht);
				
				vectMitarbeiterRechte.add(recht1);
				
				log.info("Hinzugefügtes Recht: " + fensterId);
				
			}
		}
		m.setRechte(vectMitarbeiterRechte);
	}
	
	/**
	 * Löscht den als Parameter übergebenen Benutzer
	 * @param request
	 * @param response
	 * @return
	 */
	private String contBenutzerLoeschen(HttpServletRequest request, HttpServletResponse response){
		log.info("Benutzerlöschmethode gestartet.");
		if (request.getParameter("benutzer_radio") != null){
			DaoMitarbeiter dao = DaoMitarbeiterFactory.getInstance();
			Mitarbeiter m = dao.getMitarbeiter(Integer.valueOf(request.getParameter("benutzer_radio")));
			if (m != null){
				log.info("Löschvorgang für den Benutzer \"" + request.getParameter("benutzer_radio") + "\" gestartet.");
				if (!dao.loescheVonDB(m)){
					log.info("Der Benutzer \"" + request.getParameter("benutzer_radio") + "\" konnte nicht gelöscht werden.");
					request.setAttribute("fehlermeldung", "Der angewählte Benutzer konnte nicht gelöscht werden.");
				}
			}else{
				log.info("Benutzer \"" + request.getParameter("benutzer_radio") + "\" konnte nicht gefunden werden.");
				request.setAttribute("fehlermeldung", "Der angewählte Benutzer konnte nicht gefunden werden.");
			}
		}else{
			log.info("Kein Benutzer angegeben, der gelöscht werden soll.");
			request.setAttribute("fehlermeldung", "Kein Benutzer angegeben, der gelöscht werden soll.");
		}
		
		return "admin_benutzeruebersicht.jsp";
	}
}
