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
import model.bean.GiocatoreBean;
import model.bean.PartecipazioneBean;
import model.dao.EventoDAO;
import model.dao.PartecipazioneDAO;

@WebServlet("/partecipa")
public class PartecipaEventiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
		EventoDAO eventoDAO = new EventoDAO();
		ArrayList<EventoBean> eventi = new ArrayList<EventoBean>();
		
		try {
			eventi = eventoDAO.doRetrieveEventi(giocatore.getEmail());
			request.setAttribute("eventi", eventi);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./PartecipaEventi.jsp"));
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
		String nomeEvento = request.getParameter("nome");
		EventoDAO eventoDAO = new EventoDAO();
		PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO();
		PartecipazioneBean partecipazione = new PartecipazioneBean();
		partecipazione.setUtente(giocatore.getEmail());
		partecipazione.setEvento(nomeEvento);
		
		try {
			EventoBean eventoBean = eventoDAO.doRetrieveByKey(nomeEvento);
			if(eventoBean.getNumPartecipanti() < 10) {
				partecipazioneDAO.doSave(partecipazione);
				eventoBean.aggiungiG();
				eventoBean.setValutazione(eventoBean.getValutazione()+giocatore.getValutazione());
			}
				if(eventoBean.getNumPartecipanti() == 10) {
					eventoBean.setStato("completato");
				}
				eventoDAO.doUpdate(eventoBean);
			}

		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
