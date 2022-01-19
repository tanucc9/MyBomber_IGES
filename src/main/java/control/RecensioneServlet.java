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
import model.dao.GestoreDAO;
import model.dao.GiocatoreDAO;
import model.dao.RecensioneDAO;
import model.dao.StrutturaDAO;

	@WebServlet("/recensione")
	public class RecensioneServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
            
		public RecensioneDAO rdt;
		public GiocatoreDAO gdt;
		
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
			String action = request.getParameter("action");
			RecensioneDAO recensioneDao;
			if(rdt==null)
			recensioneDao = new RecensioneDAO();
			else {
				   recensioneDao=rdt;
			     }
			String nomeE=(String)request.getParameter("nome");
			
			try {
				
			    if(action!=null)
			    {
				if(action.equalsIgnoreCase("cercagiocatori")){ 
					ArrayList<String> daRecensire;
					ArrayList<RecensioneBean> recensiti;//gi� sono stati recensiti
					daRecensire = recensioneDao.doRetrieveDaRecensire(giocatore.getEmail(), nomeE);
					recensiti = recensioneDao.doRetrieveRecensiti(giocatore.getEmail(), nomeE);
					request.setAttribute("giocatoriDaRecensire", daRecensire);
					request.setAttribute("giocatoriRecensiti", recensiti);
					request.setAttribute("nomeEvento", nomeE);
				}}
				
				} 
			catch (SQLException e) {
				try (PrintWriter out =response.getWriter())
				{
					out.println(e.getMessage());
					out.println(e.getStackTrace());
				}
				e.getStackTrace();
			}
				
			RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./DaiRecensione.jsp"));
			dispatcher.forward(request, response);
			
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String rec = request.getParameter("rec");
			GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
			RecensioneDAO recensioneDao;
			if(rdt==null)
			recensioneDao = new RecensioneDAO();
			else {
				    recensioneDao=rdt;
			     }
			
			if(rec!=null)
			{
				
	 try {
			if(rec.equalsIgnoreCase("set")) {
				
				String nomeEvento = (String)request.getParameter("nomeEvento");
				String recensito = (String)request.getParameter("nomeG");			
				String descrizione = (String)request.getParameter("descrizione");
				float valutazione = Float.parseFloat((String)request.getParameter("valutazione"));	
				RecensioneBean recensione = new RecensioneBean();
				recensione.setRecensione(valutazione);
				recensione.setDescrizione(descrizione);
				recensione.setEvento(nomeEvento);
				recensione.setRecensito(recensito);
				recensione.setRecensore(giocatore.getEmail());
				recensioneDao.doSave(recensione);
				
				GiocatoreDAO giocatoredao;
				if(gdt==null)
				giocatoredao=new GiocatoreDAO();
				
				else {
					    giocatoredao=gdt;
				     }
				
				float nuovamedia=recensioneDao.doRetrieveMedia(recensito);
			
				
				GiocatoreBean recensitobean=giocatoredao.doRetrieveByKey(recensito);
				recensitobean.setValutazione(nuovamedia);
				
				giocatoredao.doUpdate(recensitobean);
				request.setAttribute("saveok", "saveok");
				RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./EventiRecenti.jsp"));
				dispatcher.forward(request, response);
				return;
				
			}
			else if(rec.equalsIgnoreCase("el")) {
				String recensito = (String)request.getParameter("nomeG");			
				String nomeEvento = (String)request.getParameter("nomeEvento");
				recensioneDao.doDelete(giocatore.getEmail(), recensito, nomeEvento);
				
				GiocatoreDAO giocatoredao;
				if(gdt==null)
					giocatoredao=new GiocatoreDAO();
					
					else {
						    giocatoredao=gdt;
					     }
				
				float nuovamedia=recensioneDao.doRetrieveMedia(recensito);
			
				
				GiocatoreBean recensitobean=giocatoredao.doRetrieveByKey(recensito);
				recensitobean.setValutazione(nuovamedia);
				
				giocatoredao.doUpdate(recensitobean);
				request.setAttribute("deleteok", "deleteok");
				RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./EventiRecenti.jsp"));
				dispatcher.forward(request, response);
				return;
			   } 
	}catch (SQLException e) {
	try (PrintWriter out =response.getWriter())
	     {
					out.println(e.getMessage());
					out.println(e.getStackTrace());
	     }
				e.getStackTrace();
			}
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./DaiRecensione.jsp"));
			dispatcher.forward(request, response);
			
			
		}
	}
