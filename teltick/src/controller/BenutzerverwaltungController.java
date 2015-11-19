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
		//response.sendRedirect("./");
		
		log.info("BenutzerverwaltungController > Get aufgerufen");
		PrintWriter out = response.getWriter();
		out.println("Hallo");
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
				if ( request.getParameter("submit").equals("Neuer Benutzer") ){
					pfad_inc_jsp = "admin_benutzeruebersicht_neuer_benutzer.jsp";
				}
				
				if (pfad_inc_jsp != null){
					RequestDispatcher rd = request.getRequestDispatcher(pfad_inc_jsp);
					rd.forward(request, response);
				}
			}
		}
		//Gibt eine Meldung aus, wenn der Benutzer keine Rechte hat
		else{
			out.println(request.getParameter("id")+"\nSie haben keine Berechtigungen die Rechte zu verwalten.");
		}
	}

}
