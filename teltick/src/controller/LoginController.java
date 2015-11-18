package controller;

import java.io.IOException;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import basis.factory.Md5HashVerfahrenSingletonFactory;
import basis.interfaces.HashVerfahren;
import logger.LogFactory;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.factory.DaoMitarbeiterFactory;
import modell.interfaces.DaoMitarbeiter;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LogFactory.getInstance(LoginController.class.getName());
	
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
		
		log.info("LoginController aufgerufen");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HashVerfahren hash = Md5HashVerfahrenSingletonFactory.getInstance();
		String chiffrat = hash.chiffriereText(password);
		char[] passwordChar = chiffrat.toCharArray();
		
		HttpSession session = request.getSession();
		
		DaoMitarbeiter dao = DaoMitarbeiterFactory.getInstance();
		Mitarbeiter m = dao.getMitarbeiter(username, passwordChar );
		
		//Abfragen ob Benutzer vorhanden
		if ( m != null ){
			log.info("LoginController > Benutzer vorhanden");
			m.setAngemeldet(true);
			session.setAttribute("angemeldeterMitarbeiter", m);
			response.sendRedirect("./");
		}else{
			log.info("LoginController > Benutzer nicht vorhanden");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("./");
	}

	
}
