package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.GestoreBean;
import model.bean.GiocatoreBean;
import model.dao.GestoreDAO;
import model.dao.GiocatoreDAO;

/**
 * Servlet implementation class EsempioServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GiocatoreDAO giocatoreDao;
	private GestoreDAO gestoreDao;
	
    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request
                .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
      dispatcher.forward(request, response);
      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
			   
		       String email = (String) request.getParameter("email");
		       String password = (String) request.getParameter("password");
		       GiocatoreDAO gd;
			   if(giocatoreDao != null) 
				   gd = giocatoreDao;
			   else {
				   gd = new GiocatoreDAO();
				   giocatoreDao = gd;
			   }
			   GestoreDAO gesd;
			   if(gestoreDao != null) 
				   gesd = gestoreDao;
			   else {
				   gesd = new GestoreDAO();
				   gestoreDao = gesd;
			   }
			   
			   GiocatoreBean giocatore = new GiocatoreBean();
			   GestoreBean gestore = new GestoreBean();
			   /*request.setAttribute("errorReg",pippo);
			   
			   RequestDispatcher dispatcher1 = request
			           .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
			   dispatcher1.forward(request, response);
			   */
					  giocatore= gd.doRetrieveByKey(email);
			          gestore = gesd.doRetrieveByKey(email);
			          if(giocatore!=null) {
				    	   if(giocatore.getPassword().equals(password))
				    	   {
				    		   request.getSession().setAttribute("giocatore",giocatore);
			        	       RequestDispatcher dispatcher = request
			                       .getRequestDispatcher(response.encodeRedirectURL("./PartecipaEventi.jsp"));
			                   dispatcher.forward(request, response);
			                   return;
				           }
				       }
				       if(gestore!=null) {
				    	   if(gestore.getPassword().equals(password))
				    	   {
				    		   request.getSession().setAttribute("gestore",gestore);
			        	       RequestDispatcher dispatcher = request
			                       .getRequestDispatcher(response.encodeRedirectURL("cronologiaEventiServlet"));
			                   dispatcher.forward(request, response);
			                   return;
				    	   }
					      
				       }
				       request.setAttribute("errorLog","errore"); 
			    	   RequestDispatcher dispatcher = request
			                   .getRequestDispatcher(response.encodeRedirectURL("./Login.jsp"));
			         dispatcher.forward(request, response);
		       
	           
	}
}
