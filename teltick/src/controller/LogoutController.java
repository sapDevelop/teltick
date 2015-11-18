package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import logger.LogFactory;
import modell.entitaeten.interfaces.Mitarbeiter;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static Logger log = LogFactory.getInstance(LogoutController.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("LogoutController aufgerufen");
		
		HttpSession session = request.getSession();
		Mitarbeiter m = (Mitarbeiter) session.getAttribute("angemeldeterMitarbeiter");
		
		if (m != null){
			m.setAngemeldet(false);
			session.setAttribute("angemeldeterMitarbeiter", m);
		}
		
		request.setAttribute("username", m.getLoginName());
		
		
		log.info("User: " +  m.getLoginName() + " hat sich abgemeldet!");
		
		RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
		
		rd.forward(request, response);
	}
}
