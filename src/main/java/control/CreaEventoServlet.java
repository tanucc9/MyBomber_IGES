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
import model.bean.StrutturaBean;
import model.dao.EventoDAO;
import model.dao.GestoreDAO;
import model.dao.StrutturaDAO;

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
			StrutturaDAO strutturaDAO = new StrutturaDAO();
			
			String data = request.getParameter("data e ora").substring(0, 11);
			String ora = request.getParameter("data e ora").substring(12);
			ora = ora.concat(":00");
			gestore = gestoreDAO.doRetrieveByStruttura(request.getParameter("struttura"));
			String nome = request.getParameter("nome");
			evento.setNome(nome);
			evento.setDescrizione(request.getParameter("descrizione"));
			String struttura = request.getParameter("struttura");
			evento.setStruttura(request.getParameter("struttura"));
			evento.setData(Date.valueOf(data));
			evento.setOra(Time.valueOf(ora));
			evento.setGestore(gestore.getEmail());
			
			//Se l'utente che sta utilizzando l'applicazione è un giocatore
			if(request.getSession().getAttribute("giocatore") != null) {
				GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
				evento.setOrganizzatore(giocatore.getEmail());
			}
			
			//Se l'utente che sta utilizzando l'applicazione è un gestore
			else {
				evento.setOrganizzatore(gestore.getEmail());
			}
			evento.setStato("richiesta");
			
			try {
				EventoBean testEvento = new EventoBean();
				testEvento = eventoDao.doRetrieveByKey(nome);
				StrutturaBean testStruttura = new StrutturaBean();
				testStruttura = strutturaDAO.doRetrieveByKey(nome);
				if(testEvento == null && testStruttura == null) {
					eventoDao.doSave(evento);
					RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./PartecipaEventi.jsp"));
					dispatcher.forward(request, response); 
				}
				else { 
					if(testEvento != null) {
						request.setAttribute("errNome", "errore");
					}
					if(testStruttura != null) {
						request.setAttribute("errStruttura", "errore");
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./CreaEventi.jsp"));
					dispatcher.forward(request, response);
				}
				
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