package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;

import logger.LogFactory;
import modell.entitaeten.factory.FensterFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;

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
		log.info("BenutzerverwaltungController > Get aufgerufen -> Weiterleitung zur Startseite");
		response.sendRedirect("./");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		log.info("BenutzerverwaltungController > Post aufgerufen");
		
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
					case "Neuer Benutzer":
						log.info("BenutzerverwaltungController > Button: \"Neuer Benutzer\" geklickt");
						pfad_inc_jsp = "admin_benutzeruebersicht_benutzer_aendern.jsp";
						request.setAttribute("vorgang", "neuerBenutzer");
					break;
					case "Benutzer bearbeiten":
						log.info("BenutzerverwaltungController > Button: \"Benutzer ändern\" geklickt");
						pfad_inc_jsp = "admin_benutzeruebersicht_benutzer_aendern.jsp";
						request.setAttribute("vorgang", "aenderBenutzer");
					break;
					case "Aktualisieren":
					case "Abbrechen":
						log.info("BenutzerverwaltungController > Button: Aktualisieren bzw. Abbrechen geklickt");
						pfad_inc_jsp = "admin_benutzeruebersicht.jsp";
					break;
				}
				
				if (pfad_inc_jsp != null){
					log.info("BenutzerverwaltungController > Für den Button entsprechende JSP-Seite wird geladen.");
					RequestDispatcher rd = request.getRequestDispatcher(pfad_inc_jsp);
					rd.forward(request, response);
				}
			}
			//Wenn der Benutzer keine Berechtigung hat
			else if (m!= null){
				log.info("BenutzerverwaltungController > Benutzer hat keine Berechtigung das Fenster zu öffnen.");
				out.println(request.getParameter("id")+"\n<div class=\"fehlermeldung_in_std_fenster\">Sie haben keine Berechtigung dieses Fenster zu &ouml;ffnen.</div>");
			}
			//Wenn die Session abgelaufen ist
			else{
				log.info("BenutzerverwaltungController > Session abgelaufen.");
				out.println(request.getParameter("id")+"\n<div class=\"fehlermeldung_in_std_fenster\">Sitzung ist abgelaufen. Sie m&uuml;ssen angemeldet sein, um ein Fenster &ouml;ffnen zu k&ouml;nnen.</div>");
			}
		}
		//Gibt eine Meldung aus, wenn der Benutzer keine Rechte hat
		else{
			log.info("BenutzerverwaltungController > Keine Fenster-ID übertragen.");
			out.println(request.getParameter("id")+"\n<div class=\"fehlermeldung_in_std_fenster\">Es wurde keine Fenster-Id &uuml;bertragen.</div>");
		}
	}

}
