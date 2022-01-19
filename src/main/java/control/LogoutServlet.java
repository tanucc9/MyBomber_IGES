package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("giocatore") != null) {
			request.getSession().removeAttribute("giocatore");
		}
		if(request.getSession().getAttribute("gestore") != null) {
			request.getSession().removeAttribute("gestore");
		}
 	   RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./Login.jsp"));
      dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
