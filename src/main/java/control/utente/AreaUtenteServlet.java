package control.utente;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log.LoggerSingleton;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.evento.SortByDate;
import model.partecipazione.PartecipazioneBean;
import model.partecipazione.PartecipazioneDAO;
import model.utente.gestore.GestoreBean;
import model.utente.giocatore.GiocatoreBean;

@WebServlet("/areaUtenteServlet")
public class AreaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AreaUtenteServlet()
    {
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		  LoggerSingleton logger1 = LoggerSingleton.getInstance();
	        logger1.debug("il giocatore è dentro");
	      
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}	