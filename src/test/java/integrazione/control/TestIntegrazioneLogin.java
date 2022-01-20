package integrazione.control;

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

import control.LoginServlet;
import model.bean.GestoreBean;
import model.bean.GiocatoreBean;
import model.dao.GestoreDAO;
import model.dao.GiocatoreDAO;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class TestIntegrazioneLogin {

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
	
	
	
	private LoginServlet servlet;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		servlet= new LoginServlet();
		when(req.getSession()).thenReturn(session);
	}
	
	@Test
	public void loginGiocatoreOk() throws ServletException, IOException {
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
	
		when(req.getParameter("email")).thenReturn("pino@pino.it");
		when(req.getParameter("password")).thenReturn("pino");
		when(session.getAttribute("giocatore")).thenReturn(g);
		when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);
		servlet.doPost(req, res);
		verify(rd).forward(req, res);
		assertEquals(g, session.getAttribute("giocatore"));
	}
	
	@Test
	public void loginGestoreOk() throws ServletException, IOException {
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
	
		when(req.getParameter("email")).thenReturn("gino@gino.it");
		when(req.getParameter("password")).thenReturn("gino");
		when(session.getAttribute("gestore")).thenReturn(g);
		when(req.getRequestDispatcher(res.encodeRedirectURL("cronologiaEventiServlet"))).thenReturn(rd);
		servlet.doPost(req, res);
		verify(rd).forward(req, res);
		assertEquals(g, session.getAttribute("gestore"));
	}
	
	@Test
	public void notGioLogged() throws ServletException, IOException   {
	
		when(req.getParameter("email")).thenReturn("pino@pino.it");
		when(req.getParameter("password")).thenReturn("x");
		when(req.getRequestDispatcher(res.encodeRedirectURL("login.jsp"))).thenReturn(rd);
		when(req.getAttribute("errorLog")).thenReturn(true);
		servlet.doPost(req, res);
		verify(rd).forward(req, res);
		boolean error = (boolean) req.getAttribute("errorLog");
		assertTrue(error);
	}
	
	@Test
	public void notGesLogged() throws ServletException, IOException   {
	
		when(req.getParameter("email")).thenReturn("gino@gino.it");
		when(req.getParameter("password")).thenReturn("x");
		when(req.getRequestDispatcher(res.encodeRedirectURL("login.jsp"))).thenReturn(rd);
		when(req.getAttribute("errorLog")).thenReturn(true);
		servlet.doPost(req, res);
		verify(rd).forward(req, res);
		boolean error = (boolean) req.getAttribute("errorLog");
		assertTrue(error);
	}
	
}
