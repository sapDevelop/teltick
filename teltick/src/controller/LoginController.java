package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basis.factory.Md5HashVerfahrenSingletonFactory;
import modell.entitaeten.factory.MitarbeiterFactory;
import modell.entitaeten.implementierung.ImpMitarbeiter;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.factory.DaoMitarbeiterFactory;
import modell.interfaces.DaoMitarbeiter;

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
		char[] password = Md5HashVerfahrenSingletonFactory.getInstance().chiffriereText(request.getParameter("password")).toCharArray();
		
		HttpSession session = request.getSession();
				
		//Login mit DAO-Verwendung
		DaoMitarbeiter dao = DaoMitarbeiterFactory.getInstance();
		Mitarbeiter m = dao.getValue(username, password );
		if ( m != null ){
			m.setAngemeldet(true);
			session.setAttribute("angemeldeterMitarbeiter", m);
			response.sendRedirect("./");
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("./");
	}

	
}
