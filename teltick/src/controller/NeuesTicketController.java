package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import basis.factory.DatumFactory;
import basis.factory.FehlermeldungPflichtfeldNichtAusgefuelltSingletonFactory;
import basis.factory.FeldFehlermeldungFactory;
import basis.interfaces.EingabePruefung;
import basis.interfaces.FeldFehlermeldung;
import logger.LogFactory;
import modell.entitaeten.factory.FensterFactory;
import modell.entitaeten.factory.TicketFactory;
import modell.entitaeten.factory.TicketzuweisungFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticket;
import modell.entitaeten.interfaces.Ticketzuweisung;
import modell.factory.DaoFensterFactory;
import modell.factory.DaoMitarbeiterFactory;
import modell.factory.DaoTicketFactory;
import modell.interfaces.DaoMitarbeiter;

/**
 * Servlet implementation class NeuesTicketController
 */
@WebServlet("/NeuesTicketController")
public class NeuesTicketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = LogFactory.getInstance(NeuesTicketController.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NeuesTicketController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.info("NeuesTicketController aufgerufen");
		
		PrintWriter out = response.getWriter();

		String jsp_file = "";
		
		//Ticketersteller holen
		HttpSession session =  request.getSession();
		Mitarbeiter m = (Mitarbeiter) session.getAttribute("angemeldeterMitarbeiter");
		
		Fenster f = FensterFactory.getInstance();
		f.setId(1);

		//Ueberpruefen ob alle Felder gesetzt
		if ( request.getParameter("id") != null && m != null && m.zugriffsRechtFenster(f)){

			FeldFehlermeldung[] arrPflichtfelder = {
					FeldFehlermeldungFactory.getInstance("Titel", "einen", "titel"),
					FeldFehlermeldungFactory.getInstance("Beschreibung", "eine", "beschreibung"),
			};

			EingabePruefung eingabePruefung1 = FehlermeldungPflichtfeldNichtAusgefuelltSingletonFactory.getInstance();

			//Erstellt eine Fehlermeldung, wenn nicht alle Felder ausgefüllt sind
			Vector<FeldFehlermeldung> nichtAusgefuellteFelder = eingabePruefung1.getVectorNichtausgefuellteFelder(arrPflichtfelder, request);
			String meldung = eingabePruefung1.getMeldungPflichtfelderNichtAusgefuellt(nichtAusgefuellteFelder);

			//Vector der die Feldnamen enthält, die rot markiert werden sollen
			Vector<String> feldnamenUnalsgefuellteFelder = eingabePruefung1.getVectorFeldnamenNichtAusgefuellteFelder(nichtAusgefuellteFelder);

			

			Timestamp time = DatumFactory.getInstance().getDatum();

			//Ticketdaten setzten
			Ticket t = TicketFactory.getInstance();
			t.setTitel(request.getParameter("titel"));
			t.setBeschreibung(request.getParameter("beschreibung"));
			t.setVerfasserId(m.getMitarbeiterId());
			t.setErstelldatum(time);	

			

			boolean fehler = false;
			if (meldung != ""){
				fehler = true;
			}

			//Zeigt auf dem Desktop eine Fehlermeldung an, wenn die Eingabe nicht gültig war
			if (fehler){
				//Übergibt an die JSP-Seite die Fehlermeldung
				request.setAttribute("fehlermeldung", meldung);

				request.setAttribute("vorgang", "neuesTicket");

				//alle Rechte, für die Rechteliste ermitteln
				request.setAttribute("listeRechte", DaoFensterFactory.getInstance().getAlleFenster());

				//Übergibt an die JSP-Seite, welche Felder rot markiert werden sollen
				request.setAttribute("felderFehler", feldnamenUnalsgefuellteFelder);

				/*
				 * Alte Eingabe in einen Obekt von Typ-Mitarbeiter speichern, 
				 * damit die Werter auf der JSP-Seite mit der Programmierung der Änderungsfunktion angezeigt werden kann
				 */
				request.setAttribute("Ticket", t);

				jsp_file = "neuesTicket.jsp";



			}else  {



				//Ticket in DB schreiben
				boolean noFehler = DaoTicketFactory.getInstance().setTicket(t);
				
				int ticketId = DaoTicketFactory.getInstance().getTicketId(t);
				
				t.setTicketId(ticketId);

				//Ticketzuweisung in DB schreiben
				Ticketzuweisung tz = TicketzuweisungFactory.getInstance();
				tz.setMitarbeiterId(Integer.valueOf(request.getParameter("zugewiesen")));
				tz.setTicketId(ticketId);
				tz.setZeitpunkt(time);
				DaoTicketFactory.getInstance().setZuweisung(tz);
				
				t.setZuweisung(tz);

				log.info("Ticket angelegt(ID):" + ticketId);
				
				DaoMitarbeiter dm = DaoMitarbeiterFactory.getInstance();
				Mitarbeiter mZuweisung = dm.getMitarbeiter(Integer.valueOf(request.getParameter("zugewiesen")));
				request.setAttribute("Zuweisung", mZuweisung.getVorname() + " " + mZuweisung.getName());
				
				request.setAttribute("RechtTicketvorschau", true);
				
				request.setAttribute("Ticket", t);
				
				//Weiter leiten an ticket anzeigen
				jsp_file = "ticketAnzeigen.jsp";
			}


			if (jsp_file != null){
				log.info("Für den Button entsprechende JSP-Seite wird geladen.");
				RequestDispatcher rd = request.getRequestDispatcher(jsp_file);
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

}
