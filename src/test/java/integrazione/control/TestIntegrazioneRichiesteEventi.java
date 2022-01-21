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

import control.evento.RichiesteEventiServlet;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.partecipazione.PartecipazioneBean;
import model.partecipazione.PartecipazioneDAO;
import model.utente.gestore.GestoreBean;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

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
		EventoBean bean=new EventoBean();
		EventoDAO ed=new EventoDAO();
		PartecipazioneDAO pd=new PartecipazioneDAO();
		bean.setNome("evento435");
		bean.setDescrizione("mitico evento");
		bean.setStruttura("playk");
		bean.setData(Date.valueOf("2023-01-03"));
		bean.setOra(2);
		bean.setGestore("gino@gino.it");
		bean.setOrganizzatore("simone@simone.it");
		bean.setStato("richiesta");
		bean.setValutazione(0);
		bean.setNumPartecipanti(10);
		ed.doSave(bean);
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getParameter("nome")).thenReturn("evento435");
		when(req.getParameter("action")).thenReturn("addE");
		when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
		pd.doDelete(bean.getOrganizzatore(), bean.getNome());
        ed.doDelete("evento435");
	}
	
	@Test
	public void rifiutaRichieste() throws ServletException, IOException, SQLException {
		EventoBean bean=new EventoBean();
		EventoDAO ed=new EventoDAO();
		bean.setNome("evento435");
		bean.setDescrizione("mitico evento");
		bean.setStruttura("playk");
		bean.setData(Date.valueOf("2023-01-03"));
		bean.setOra(2);
		bean.setGestore("gino@gino.it");
		bean.setOrganizzatore("simone@simone.it");
		bean.setStato("richiesta");
		bean.setValutazione(0);
		bean.setNumPartecipanti(10);
		ed.doSave(bean);
		
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getParameter("nome")).thenReturn("evento435");
		when(req.getParameter("action")).thenReturn("deleteE");
		when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);

	}
	
}
