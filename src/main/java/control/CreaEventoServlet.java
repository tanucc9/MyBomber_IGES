package control;

	import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;	
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.EventoBean;
import model.bean.GestoreBean;
import model.bean.GiocatoreBean;
import model.dao.EventoDAO;
import model.dao.GestoreDAO;

	/**
	 * Servlet implementation class EsempioServlet
	 */
	@WebServlet("/creaEvento")
	public class CreaEventoServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			EventoDAO eventoDao = new EventoDAO();
			GestoreDAO gestoreDAO = new GestoreDAO();
			GestoreBean gestore = new GestoreBean();
			EventoBean evento = new EventoBean();
			
			String data = request.getParameter("data e ora").substring(0, 11);
			String ora = request.getParameter("data e ora").substring(12);
			ora = ora.concat(":00");
			gestore = gestoreDAO.doRetrieveByStruttura(request.getParameter("struttura"));
			evento.setNome(request.getParameter("nome"));
			evento.setDescrizione(request.getParameter("descrizione"));
			evento.setStruttura(request.getParameter("struttura"));
			evento.setData(Date.valueOf(data));
			evento.setOra(Time.valueOf(ora));
			evento.setGestore(gestore.getEmail());
			
			//Se l'utente che sta utilizzando l'applicazione è un giocatore
			if(request.getSession().getAttribute("currentSessionUser") instanceof GiocatoreBean) {
				GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("currentSessionUser");
				evento.setOrganizzatore(giocatore.getEmail());
			}
			
			//Se l'utente che sta utilizzando l'applicazione è un gestore
			else {
				evento.setOrganizzatore(gestore.getEmail());
			}
			evento.setStato("richiesta");
			
			try {
				eventoDao.doSave(evento);
			}
			catch(SQLException e) {
				e.printStackTrace();
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