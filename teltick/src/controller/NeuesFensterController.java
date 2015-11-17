package controller;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modell.entitaeten.interfaces.Mitarbeiter;

/**
 * Servlet implementation class NeuesFensterController
 */
@WebServlet("/NeuesFenster")
public class NeuesFensterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		
		HttpSession session =  request.getSession();
		//Überprüft, ob der Benutzer angemeldet ist
		Mitarbeiter m = (Mitarbeiter) session.getAttribute("angemeldeterMitarbeiter");
		if ( m != null  && m.isAngemeldet()){		
			Integer fensterZaehlVariable = (Integer)session.getAttribute("fensterZaehlVariable");
			if (fensterZaehlVariable == null) fensterZaehlVariable = new Integer(-1);
			fensterZaehlVariable++;
			
			request.setAttribute("titel", "Beispiel Submit-Umleitung mit Ajax ©Benedikt Brüntrup");
			request.setAttribute("id", fensterZaehlVariable.intValue());
			request.setAttribute("inhalt", "beispielFormular.jsp");
			request.setAttribute("left", "60px");
			request.setAttribute("top", "60px");
			request.setAttribute("z_index", "1");
			request.setAttribute("minWidth", "435px");
			request.setAttribute("minHeight", "152px");
			
			session.setAttribute("fensterZaehlVariable", fensterZaehlVariable);
			
			RequestDispatcher rd = request.getRequestDispatcher("./fenster.jsp");
			rd.forward(request, response);
		}
		//Wenn der Benutzer nicht angemeldet ist
		else{
			response.getOutputStream().println("<h1>Sitzung ist abgelaufen. Sie m&uuml;ssen angemeldet sein, um ein Fenster &ouml;ffnen zu k&ouml;nnen.</h1>");
		}
				
	}

}
