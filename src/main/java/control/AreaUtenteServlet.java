package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.EventoBean;
import model.bean.GestoreBean;
import model.bean.GiocatoreBean;
import model.bean.PartecipazioneBean;
import model.dao.EventoDAO;
import model.dao.PartecipazioneDAO;

@WebServlet("/areaUtenteServlet")
public class AreaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AreaUtenteServlet()
    {
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /*request.setAttribute("errorReg",pippo);
        
 	   RequestDispatcher dispatcher1 = request
                .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
        dispatcher1.forward(request, response);
        */
		GestoreBean gestore = (GestoreBean) request.getSession().getAttribute("gestore");
		GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
		
		if(giocatore!=null)
		{
		  request.setAttribute("cu","giocatore");
		  RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./AreaUtente.jsp"));
		  dispatcher.forward(request, response);
		}
		if(gestore!=null)
		{
		  request.setAttribute("cu","gestore");
		  RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./AreaUtente.jsp"));
		  dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}	