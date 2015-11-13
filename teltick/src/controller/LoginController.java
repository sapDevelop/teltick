package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modell.entitaeten.factory.MitarbeiterFactory;
import modell.entitaeten.implementierung.ImpMitarbeiter;
import modell.entitaeten.interfaces.Mitarbeiter;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		Mitarbeiter m1 = (Mitarbeiter)session.getAttribute("angemeldeterMitarbeiter");
		if (m1 == null) m1 = (Mitarbeiter)MitarbeiterFactory.getInstance();
	
		//Abfragen ob Benutzer vorhanden
		if (username.equals("test") && password.equals("test")) {
			m1.setAngemeldet(true);
			session.setAttribute("angemeldeterMitarbeiter", m1);
			response.sendRedirect("./");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("./");
	}

	
}
