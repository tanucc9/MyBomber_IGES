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

import model.bean.GiocatoreBean;
import model.bean.RecensioneBean;
import model.dao.RecensioneDAO;

	@WebServlet("/recensione")
	public class RecensioneServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
			String nomeEvento = request.getParameter("nome");
			String action = request.getParameter("action");
			String recensito = request.getParameter("nomeG");
			float valutazione = Float.parseFloat(request.getParameter("valutazione"));			
			String descrizione = request.getParameter("descrizione");
			RecensioneDAO recensioneDao = new RecensioneDAO();
			ArrayList<String> daRecensire = new ArrayList<String>();
			ArrayList<String> recensiti = new ArrayList<String>();//già sono stati recensiti
			
			try {
				if(action.equalsIgnoreCase("addR")) {
					RecensioneBean recensione = new RecensioneBean();
					recensione = new RecensioneBean();
					recensione.setRecensione(valutazione);
					recensione.setDescrizione(descrizione);
					recensione.setEvento(nomeEvento);
					recensione.setRecensito(recensito);
					recensione.setRecensore(giocatore.getEmail());
					recensioneDao.doSave(recensione);
				}
				else if(action.equalsIgnoreCase("deleteR")) {
					recensioneDao.doDelete(giocatore.getEmail(), recensito, nomeEvento);
				}
				else { 
					daRecensire = recensioneDao.doRetrieveDaRecensire(giocatore.getEmail(), nomeEvento);
					recensiti = recensioneDao.doRetrieveRecensiti(giocatore.getEmail(), nomeEvento);
					request.setAttribute("giocatoriDaRecensire", daRecensire);
					request.setAttribute("giocatoriRecensiti", recensiti);
				}
			} 
			catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
				
			RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./DaiRecensione.jsp"));
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