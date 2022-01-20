package integrazione.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import control.RichiesteEventiServlet;
import model.bean.EventoBean;
import model.bean.GestoreBean;
import model.bean.GiocatoreBean;
import model.bean.PartecipazioneBean;
import model.dao.EventoDAO;
import model.dao.GiocatoreDAO;
import model.dao.PartecipazioneDAO;

public class TestIntegrazioneRichiesteEventi {

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

	
	private RichiesteEventiServlet servlet;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		servlet = new RichiesteEventiServlet();
		when(req.getSession()).thenReturn(session);

	}
	
	@Test
	public void cercaRichieste() throws ServletException, IOException, SQLException {
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");

		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getParameter("action")).thenReturn("trovaRichieste");
		when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
		
	}
	
	@Test
	public void accettaRichieste() throws ServletException, IOException, SQLException {
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getParameter("nome")).thenReturn("Evento 333");
		when(req.getParameter("action")).thenReturn("addE");
		when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);

	}
	
	@Test
	public void rifiutaRichieste() throws ServletException, IOException, SQLException {
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getParameter("nome")).thenReturn("Evento 333");
		when(req.getParameter("action")).thenReturn("deleteE");
		when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);

	}
}
