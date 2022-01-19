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
import model.dao.GestoreDAO;
import model.dao.PartecipazioneDAO;
import model.dao.StrutturaDAO;

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
		
		doGet(request, response);
	}

}	