package control.prova;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.GiocatoreBean;
import model.dao.GiocatoreDAO;
import model.dao.PartecipazioneDAO;

/**
 * Servlet implementation class EsempioServlet
 */
@WebServlet("/EsempioServlet")
public class EsempioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EsempioServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nomeAttr = request.getParameter("nomeAttr");
		String c= new String("mario");
	
		request.setAttribute("c", c);
		
		GiocatoreBean g =new GiocatoreBean();
		g.setCapResidenza("8218219");
		g.setCittaResidenza("napoli");
		g.setCognome("djkcdjk");
		
		
		g.setDataNascita(java.sql.Date. valueOf("2000-05-01"));
		g.setEmail("testeroverdrive@gmail.com");
		g.setNazioneResidenza("africa");
		g.setNome("mario");
		g.setPassword("otto");
		g.setProvinciaResidenza("napoli");
		g.setTelefono("3923816543");
		g.setUsername("pippos");
		float t=2;
		g.setValutazione(t);
		GiocatoreDAO gd=new GiocatoreDAO();
		try {
			gd.doSave(g);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
                .getRequestDispatcher(response.encodeRedirectURL("./Index.jsp"));
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
