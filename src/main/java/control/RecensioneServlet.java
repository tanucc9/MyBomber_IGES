package control;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.dao.GiocatoreDAO;
import model.dao.RecensioneDAO;

	@WebServlet("/recensione")
	public class RecensioneServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
			String action = request.getParameter("action");
			RecensioneDAO recensioneDao = new RecensioneDAO();
			String nomeE=(String)request.getAttribute("nome");
			try {
				
				if(action.equalsIgnoreCase("addR")) {
					String nomeEvento = request.getParameter("nomeEvento");
					String recensito = request.getParameter("nomeG");			
					String descrizione = request.getParameter("descrizione");
					float valutazione = Float.parseFloat(request.getParameter("valutazione"));	
					RecensioneBean recensione = new RecensioneBean();
					recensione.setRecensione(valutazione);
					recensione.setDescrizione(descrizione);
					recensione.setEvento(nomeEvento);
					recensione.setRecensito(recensito);
					recensione.setRecensore(giocatore.getEmail());
					recensioneDao.doSave(recensione);
					GiocatoreDAO giocatoredao=new GiocatoreDAO();
					float nuovamedia=recensioneDao.doRetrieveMedia(recensito);
					GiocatoreBean recensitobean=giocatoredao.doRetrieveByKey(recensito);
					recensitobean.setValutazione(nuovamedia);
					giocatoredao.doUpdate(recensitobean);				
				}
				else if(action.equalsIgnoreCase("deleteR")) {
					String recensito = request.getParameter("nomeG");			
					String nomeEvento = request.getParameter("nomeEvento");
					recensioneDao.doDelete(giocatore.getEmail(), recensito, nomeEvento);
				}
				
				else if(action.equalsIgnoreCase("cercagiocatori")){ 
					ArrayList<String> daRecensire = new ArrayList<String>();
					ArrayList<String> recensiti = new ArrayList<String>();//gi� sono stati recensiti
					daRecensire = recensioneDao.doRetrieveDaRecensire(giocatore.getEmail(), nomeE);
					recensiti = recensioneDao.doRetrieveRecensiti(giocatore.getEmail(), nomeE);
					request.setAttribute("giocatoriDaRecensire", daRecensire);
					request.setAttribute("giocatoriRecensiti", recensiti);
					request.setAttribute("nomeEvento", nomeE);
				}
			} 
			catch (SQLException e) {
				try (PrintWriter out =response.getWriter())
				{
					out.println(e.getMessage());
				}
				e.getStackTrace();
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