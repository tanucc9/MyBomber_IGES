package control.evento;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.partecipazione.PartecipazioneBean;
import model.partecipazione.PartecipazioneDAO;
import model.struttura.StrutturaDAO;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;

@WebServlet("/cronologiaEventiServlet")
public class CronologiaEventiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public EventoDAO edao;

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
		EventoDAO eventoDAO;
		if(edao==null)
		eventoDAO = new EventoDAO();
		else
		eventoDAO=edao;
		ArrayList<EventoBean> eventi = new ArrayList<EventoBean>();
		try {
			eventi = eventoDAO.doRetrieveEventiGestore(gestore.getEmail());
			
			request.setAttribute("eventi", eventi);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./CronologiaEventi.jsp"));
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./CronologiaEventi.jsp"));
		dispatcher.forward(request, response);
	}

}	