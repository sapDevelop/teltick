package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import basis.factory.VorlagenFensterFactory;
import logger.LogFactory;
import tags.MeldungsboxTag;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticket;
import modell.factory.DaoFensterFactory;
import modell.factory.DaoTicketFactory;
import modell.implementierung.ImpDaoTicket;
import modell.interfaces.DaoTicket;

/**
 * Servlet implementation class NeuesFensterController
 */
@WebServlet("/NeuesFenster")
public class NeuesFensterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LogFactory.getInstance(NeuesFensterController.class.getName());
       
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().println("<h1>Sie haben keine Berechtigungen diese Datei zu &ouml;ffnen.</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Zeichensatz festlegen
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
				
		PrintWriter out = response.getWriter();
		
		
		HttpSession session =  request.getSession();
		//Überprüft, ob der Benutzer angemeldet ist
		Mitarbeiter m = (Mitarbeiter) session.getAttribute("angemeldeterMitarbeiter");
		if ( m != null  && m.isAngemeldet()){	
			
			//Zählvariable, um den Fenster eine eindeutige Id zu vergeben
			Integer fensterZaehlVariable = (Integer)session.getAttribute("fensterZaehlVariable");
			if (fensterZaehlVariable == null) fensterZaehlVariable = new Integer(-1);
			fensterZaehlVariable++;
			
			int fensterId = -1;
			
			try{
			fensterId = Integer.valueOf(request.getParameter("id"));
			}catch(NumberFormatException e){
			}
						
			//Sucht das Fenster in der DB und gibt ein Instance von Typ "Fenster" aus, wenn es gefunden wurde
			Fenster f = DaoFensterFactory.getInstance().getFenster(fensterId);
			
			if ( f != null && m != null && m.zugriffsRechtFenster(f)){
				
				Random zufallszahlen = new Random();
				
				int position[] = new int[2];
				
				for ( int i = 0; i < position.length; i++){
					do{
						position[i] = zufallszahlen.nextInt(300);
					}while( position[i] < 55);
				}			
				
				//Attribute womit das Fenster gefüllt werden soll
				request.setAttribute("titel", f.getTitel());
				request.setAttribute("id", fensterZaehlVariable.intValue());
				request.setAttribute("inhalt", f.getPfadJspDatei());
				request.setAttribute("left", new String (position[0] + "px"));
				request.setAttribute("top", new String (position[1] +"px"));
				request.setAttribute("z_index", "1");
				request.setAttribute("minWidth", new String(f.getMinBreite() + "px"));
				request.setAttribute("minHeight", new String (f.getMinHoehe() + "px"));
				request.setAttribute("icon", f.getIcon());
				request.setAttribute("fensterId", fensterId);
				
				//Fensterzählvariable in der Session ablegen
				session.setAttribute("fensterZaehlVariable", fensterZaehlVariable);
						
				
				RequestDispatcher rd = request.getRequestDispatcher("./fenster.jsp");
				rd.forward(request, response);
			
			//Wenn der Benutzer keine Zugriffsrechte besitzt
			}else if (f != null && m != null && !m.zugriffsRechtFenster(f)){
				String meldung = VorlagenFensterFactory.getInstance().getMeldungsbox("Sie haben keine Rechte das Formular zu &ouml;ffnen.", "Fehler", "300px", "150px", "fehler", session, true);
				out.print(meldung);
			}			
			else{
				String meldung = VorlagenFensterFactory.getInstance().getMeldungsbox("Fenster konnte nicht gefunden werden.", "Fehler", "300px", "150px", "fehler", session, true);
				out.print(meldung);
			}
		}
		//Wenn der Benutzer nicht angemeldet ist
		else{
			String meldung = VorlagenFensterFactory.getInstance().getMeldungsbox("Sitzung ist abgelaufen. Sie m&uuml;ssen angemeldet sein, um ein Fenster &ouml;ffnen zu k&ouml;nnen.", "Fehler", "300px", "150px", "fehler", session, true);
			out.print(meldung);
		}
				
	}

}
