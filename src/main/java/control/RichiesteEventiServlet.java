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
import model.dao.EventoDAO;

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
			GestoreBean gestore = (GestoreBean) request.getSession().getAttribute("gestore");
			String nomeEvento = request.getParameter("nome");
			String action = request.getParameter("action");
			ArrayList<EventoBean> richieste = new ArrayList<EventoBean>();
			
			try {
				EventoBean bean = eventoDao.doRetrieveByKey(nomeEvento);
				if(action.equalsIgnoreCase("addE")) {
					bean.setStato("attivo");
					eventoDao.doUpdate(bean);
					}
				else if(action.equalsIgnoreCase("deleteE")) {
					eventoDao.doDelete(nomeEvento);
				}
				else {
					richieste = eventoDao.doRetrieveRichieste(gestore.getEmail());
					request.setAttribute("richieste", richieste);
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