package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.EventoBean;
import model.bean.GestoreBean;
import model.dao.EventoDAO;

public class EventiRecentiServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EventoDAO eventoDao = new EventoDAO();
		GestoreBean gestore = (GestoreBean) request.getSession().getAttribute("gestore");
		ArrayList<EventoBean> eventiRecenti = new ArrayList<EventoBean>();
		
		try {
			eventiRecenti = eventoDao.doRetrieveEventiRecenti(gestore.getEmail());
			request.setAttribute("eventiRecenti", eventiRecenti);
		} 
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./EventiRecenti.jsp"));
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
