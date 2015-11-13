package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		RequestDispatcher rd = null;
		
		System.out.println(request.getSession().getAttribute("angemeldet"));
	
		//Abfragen ob Benutzer vorhanden
		if (username.equals("test") && password.equals("test")) {
			rd = request.getRequestDispatcher("/index.jsp");
			//User user = new User(username, password);
			//request.setAttribute("user", user);
		} else {
			rd = request.getRequestDispatcher("/login.jsp");
		}
		rd.forward(request, response);
	}

}
