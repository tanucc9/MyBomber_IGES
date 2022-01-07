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
import model.dao.GiocatoreDAO;
import model.dao.PartecipazioneDAO;

	/**
	 * Servlet implementation class EsempioServlet
	 */
	@WebServlet("/richieste")
	public class RichiesteEventiServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			EventoDAO eventoDao = new EventoDAO();
			GiocatoreDAO giocatoreDao = new GiocatoreDAO();
			ArrayList<GiocatoreBean> giocatori = new ArrayList<GiocatoreBean>();
			GestoreBean gestore = (GestoreBean) request.getSession().getAttribute("gestore");
			PartecipazioneDAO partecipazioneDao = new PartecipazioneDAO();
			String nomeEvento = request.getParameter("nome");
			String action = request.getParameter("action");
			ArrayList<EventoBean> richieste = new ArrayList<EventoBean>();
			
			try {
				EventoBean bean = eventoDao.doRetrieveByKey(nomeEvento);
				PartecipazioneBean partecipazione = new PartecipazioneBean();
				if(action!=null)
				{
				if(action.equalsIgnoreCase("addE")) {
					bean.setStato("attivo");
					bean.aggiungiG();
					partecipazione.setEvento(bean.getNome());
					giocatori = giocatoreDao.doRetrieveAll();
					for(int i = 0; i < giocatori.size(); i++) {
						if(bean.getOrganizzatore().equals(giocatori.get(i).getEmail())) {
							partecipazione.setUtente(bean.getOrganizzatore());
							partecipazioneDao.doSave(partecipazione);
						}
					}
					eventoDao.doUpdate(bean);
					}
				else if(action.equalsIgnoreCase("deleteE")) {
					eventoDao.doDelete(nomeEvento);
				}
				else if(action.equalsIgnoreCase("trovarichieste")){
					richieste = eventoDao.doRetrieveRichieste(gestore.getEmail());
					request.setAttribute("richieste", richieste);
				}
				}
				
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
				
			RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./RichiesteEventi.jsp"));
			dispatcher.forward(request, response);
			
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}