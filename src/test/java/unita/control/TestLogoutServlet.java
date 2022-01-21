package unita.control;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import control.utente.LogoutServlet;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class TestLogoutServlet {

	@Mock
	private HttpServletRequest req;
	
	@Mock
	private HttpServletResponse res;
	
	@Mock
	private ServletContext ctx;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private RequestDispatcher rd;
	
	@Mock
	GiocatoreDAO gioDao = new GiocatoreDAO();
	
	@Mock
	GestoreDAO gesDao = new GestoreDAO();
	
	private LogoutServlet servlet;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		servlet= new LogoutServlet();
		when(req.getSession()).thenReturn(session);
	}
	
	@Test
	public void logoutGiocatore() throws ServletException, IOException {
		GiocatoreBean g = new GiocatoreBean();
		g.setUsername("pino");
		g.setEmail("pino@pino.it");
		g.setNome("Pino");
		g.setCognome("Inglese");
		g.setPassword("pino");
		g.setTelefono("3665423187");
		g.setDataNascita(Date.valueOf("2000-09-09"));
		g.setNazioneResidenza("Italia");
		g.setProvinciaResidenza("Napoli");
		g.setCittaResidenza("Napoli");
		g.setCapResidenza("80000");
		g.setValutazione(0);
		
		when(req.getSession().getAttribute("giocatore")).thenReturn(g);
		when(req.getRequestDispatcher(res.encodeRedirectURL("./Login.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
	}
	
	@Test
	public void logoutGestore() throws ServletException, IOException {
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		when(req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getRequestDispatcher(res.encodeRedirectURL("./Login.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
	}
	
	
	
}
