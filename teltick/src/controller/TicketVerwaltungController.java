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

import org.apache.log4j.Logger;

import logger.LogFactory;
import modell.entitaeten.factory.FensterFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticket;
import modell.entitaeten.interfaces.Ticketzuweisung;
import modell.factory.DaoFensterFactory;
import modell.factory.DaoMitarbeiterFactory;
import modell.factory.DaoTicketFactory;
import modell.interfaces.DaoMitarbeiter;

/**
 * Servlet implementation class TicketVerwaltungController
 */
@WebServlet("/TicketVerwaltungController")
public class TicketVerwaltungController extends HttpServlet {
	private static Logger log = LogFactory.getInstance(BenutzerverwaltungController.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TicketVerwaltungController() {
		super();
		// TODO Auto-generated constructor stub
	}

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

		//Zugriffsrechte Fenster abfragen
		HttpSession session = request.getSession();
		Mitarbeiter m = (Mitarbeiter) session.getAttribute("angemeldeterMitarbeiter");

		Fenster f = FensterFactory.getInstance();
		f.setId(2);

		if (m != null && m.zugriffsRechtFenster(f) ){

			String pfad_inc_jsp = null;
			
			//Wechler Button wurde gedrückt
			
			log.info("test:" + request.getParameter("submit"));

			switch (request.getParameter("submit")) {
			case "AuswahlAenderung":

				switch (request.getParameter("AuswahlAenderung")) {
				case "Eigene Tickets":
					
					log.info("Radio Button geklickt: Eigene Tickets anzeigen");
					pfad_inc_jsp = "ticketAnzeigenVerwaltung.jsp";
					request.setAttribute("ticketauswahl", 0);
					request.setAttribute("radioButton", "Eigene Tickets");
					break;

				case "Alle Tickets":
					
					log.info("Radio Button geklickt: Alle Tickets anzeigen");
					pfad_inc_jsp = "ticketAnzeigenVerwaltung.jsp";
					request.setAttribute("ticketauswahl", 2);
					request.setAttribute("radioButton", "Alle Tickets");
					break;
				}
				
				break;

			case "Anzeigen":

				log.info("Ticket anzeigen wurde geklickt");
				Ticket t;
				Ticketzuweisung tz;
				pfad_inc_jsp = "ticketAnzeigen.jsp";
				int ticketId = -1;
				ticketId = Integer.valueOf(request.getParameter("ticket_radio"));
				
				
				
				
				if(ticketId > -1)
				{
					t = DaoTicketFactory.getInstance().getTicket(ticketId);
					request.setAttribute("Ticket", t);
					
					tz =  DaoTicketFactory.getInstance().getTicketzuweisung(ticketId);
					request.setAttribute("Zuweisung", tz);
					
					
					
				}else
				{
					//Uebergibt an die JSP-Seite die Fehlermeldung
					String meldung = "Ticket konnte nicht gefunden werden.";
					request.setAttribute("fehlermeldung", meldung);

				}
				break;

			case "Bearbeiten":
				break;
				
				
			case "zurueck":
				
				log.info("Zur\u00FCck Button geklickt!");
				pfad_inc_jsp = "ticketAnzeigenVerwaltung.jsp";
				
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
			log.info("Benutzer hat keine Berechtigung das Fenster zu Ã¶ffnen.");
			out.println(request.getParameter("id")+"\n<div class=\"fehlermeldung_in_std_fenster\">Sie haben keine Berechtigung dieses Fenster zu &ouml;ffnen.</div>");
		}
		//Wenn die Session abgelaufen ist
		else{
			log.info("Session abgelaufen.");
			out.println(request.getParameter("id")+"\n<div class=\"fehlermeldung_in_std_fenster\">Sitzung ist abgelaufen. Sie m&uuml;ssen angemeldet sein, um ein Fenster &ouml;ffnen zu k&ouml;nnen.</div>");
		}
	}
}
