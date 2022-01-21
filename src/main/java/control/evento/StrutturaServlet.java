package control.evento;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.struttura.StrutturaBean;
import model.struttura.StrutturaDAO;

@WebServlet("/struttura")
public class StrutturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public StrutturaDAO sdao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<StrutturaBean> strutture = new ArrayList<StrutturaBean>();
		StrutturaDAO strutturaDao;
		if(sdao==null)
		strutturaDao = new StrutturaDAO();
		else
		strutturaDao=sdao;
		try {
			strutture = strutturaDao.doRetrieveAll();
			request.setAttribute("strutture", strutture);
		}
		catch (SQLException e) {
			e.getStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("./CreaEvento.jsp"));
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
