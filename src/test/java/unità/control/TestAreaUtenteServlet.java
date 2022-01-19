package unità.control;

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

import control.AreaUtenteServlet;
import model.bean.GestoreBean;
import model.bean.GiocatoreBean;
import model.dao.GestoreDAO;
import model.dao.GiocatoreDAO;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class TestAreaUtenteServlet {

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
	
	
	private AreaUtenteServlet servlet;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		servlet= new AreaUtenteServlet();
		when(req.getSession()).thenReturn(session);

	}
	
	@Test
	public void mostraGiocatore() throws ServletException, IOException {
		GiocatoreBean gi = new GiocatoreBean();
		gi.setUsername("pino");
		gi.setEmail("pino@pino.it");
		gi.setNome("Pino");
		gi.setCognome("Inglese");
		gi.setPassword("pino");
		gi.setTelefono("3665423187");
		gi.setDataNascita(Date.valueOf("2000-09-09"));
		gi.setNazioneResidenza("Italia");
		gi.setProvinciaResidenza("Napoli");
		gi.setCittaResidenza("Napoli");
		gi.setCapResidenza("80000");
		gi.setValutazione(0);
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(null);
		when((GiocatoreBean)req.getSession().getAttribute("giocatore")).thenReturn(gi);
		when(req.getAttribute("cu")).thenReturn("giocatore");
		when(req.getRequestDispatcher(res.encodeRedirectURL("./AreaUtente.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
		assertEquals(req.getAttribute("cu"),"giocatore");
		
	}
	
	@Test
	public void mostraGestore() throws ServletException, IOException {
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		when(session.getAttribute("gestore")).thenReturn(g);
		when(req.getAttribute("cu")).thenReturn("gestore");
		when(req.getRequestDispatcher(res.encodeRedirectURL("./AreaUtente.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
		String controllo=(String)req.getAttribute("cu");
		assertEquals("gestore",controllo);
	}
	
	
	
}
