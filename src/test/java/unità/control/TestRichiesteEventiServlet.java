package unità.control;

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

public class TestRichiesteEventiServlet {

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
	EventoDAO evDao = new EventoDAO();
	
	@Mock
	GiocatoreDAO gDao = new GiocatoreDAO();
	
	@Mock
	PartecipazioneDAO pDao = new PartecipazioneDAO();
	
	private RichiesteEventiServlet servlet;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		servlet = new RichiesteEventiServlet();
		when(req.getSession()).thenReturn(session);

	}
	
	@Test
	public void cercaRichieste() throws ServletException, IOException, SQLException {
		servlet.eD = evDao;
		servlet.gD = gDao;
		servlet.pD = pDao;
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		
		//mariello
		ArrayList<EventoBean> list = new ArrayList<EventoBean>();
		ArrayList<String> liststr = new ArrayList<String>();
		EventoBean bean =new EventoBean();
		bean.setNome("evento12");
		bean.setDescrizione("mitico evento");
		bean.setStruttura("playk");
		bean.setData(Date.valueOf("2023-01-03"));
		bean.setOra(2);
		bean.setGestore("gino@gino.it");
		bean.setOrganizzatore("simone@simone.it");
		bean.setStato("richiesta");
		bean.setValutazione(0);
		bean.setNumPartecipanti(0);
		
		EventoBean g3=new EventoBean();
		EventoBean g4=new EventoBean();
		
		list.add(bean);
		liststr.add(bean.toString());
		
		g4.setNome("evento2");
		g4.setDescrizione("grande evento");
		g4.setStruttura("playk");
		g4.setData(Date.valueOf("2022-01-03"));
		g4.setOra(2);
		g4.setGestore("gino@gino.it");
		g4.setOrganizzatore("simone@simone.it");
		g4.setStato("richiesta");
		g4.setValutazione(0);
		g4.setNumPartecipanti(0);
		
		g3.setNome("evento3");
		g3.setDescrizione("sdfghgfds");
		g3.setStruttura("playk");
		g3.setData(Date.valueOf("2022-01-15"));
		g3.setOra(1);
		g3.setGestore("gino@gino.it");
		g3.setOrganizzatore("simone@simone.it");
		g3.setStato("richiesta");
		g3.setValutazione(0);
		g3.setNumPartecipanti(0);
		
		list.add(g3);
		liststr.add(g3.toString());
		list.add(g4);
		liststr.add(g4.toString());
		
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getParameter("action")).thenReturn("trovaRichieste");
		when(evDao.doRetrieveEventiRecenti(g.getEmail())).thenReturn(list);
		when(req.getAttribute("richieste")).thenReturn(list);
		when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
		ArrayList<EventoBean> cev = new ArrayList<EventoBean>();
		ArrayList<String> stcev = new ArrayList<String>();
		cev = (ArrayList<EventoBean>)req.getAttribute("richieste");
		for(EventoBean e : cev)
		{
			stcev.add(e.toString());
		}
		
		assertEquals(stcev,liststr);
		
	}
	
	@Test
	public void accettaRichieste() throws ServletException, IOException, SQLException {
		servlet.eD = evDao;
		servlet.gD = gDao;
		servlet.pD = pDao;
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		GiocatoreBean bg;
		bg =new GiocatoreBean();
		bg.setUsername("simone45");
		bg.setEmail("simon@simon.it");
		bg.setNome("Simone");
		bg.setCognome("Graziano");
		bg.setPassword("simone");
		bg.setTelefono("3324561273");
		bg.setDataNascita(Date.valueOf("2000-05-09"));
		bg.setNazioneResidenza("Italia");
		bg.setProvinciaResidenza("Napoli");
		bg.setCittaResidenza("Napoli");
		bg.setCapResidenza("80000");
		bg.setValutazione(0);
		ArrayList<GiocatoreBean> ga=new ArrayList<GiocatoreBean>();
		ga.add(bg);
		
		EventoBean bean =new EventoBean();
		bean.setNome("evento12");
		bean.setDescrizione("mitico evento");
		bean.setStruttura("playk");
		bean.setData(Date.valueOf("2023-01-03"));
		bean.setOra(2);
		bean.setGestore("gino@gino.it");
		bean.setOrganizzatore("simone@simone.it");
		bean.setStato("richiesta");
		bean.setValutazione(0);
		bean.setNumPartecipanti(0);
		
		PartecipazioneBean p = new PartecipazioneBean();
		p.setEvento(bean.getNome());
		p.setUtente(bean.getOrganizzatore());
		
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getParameter("nome")).thenReturn(bean.getNome());
		when(req.getParameter("action")).thenReturn("addE");
		when(gDao.doRetrieveAll()).thenReturn(ga);
		when(evDao.doRetrieveByKey(bean.getNome())).thenReturn(bean);
		when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);

	}
	
	@Test
	public void rifiutaRichieste() throws ServletException, IOException, SQLException {
		servlet.eD = evDao;
		servlet.gD = gDao;
		servlet.pD = pDao;
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		
		EventoBean bean =new EventoBean();
		bean.setNome("evento12");
		bean.setDescrizione("mitico evento");
		bean.setStruttura("playk");
		bean.setData(Date.valueOf("2023-01-03"));
		bean.setOra(2);
		bean.setGestore("gino@gino.it");
		bean.setOrganizzatore("simone@simone.it");
		bean.setStato("richiesta");
		bean.setValutazione(0);
		bean.setNumPartecipanti(0);
		
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getParameter("action")).thenReturn("deleteE");
		when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);

	}
	
	@Test
	public void noTesting() throws ServletException, IOException, SQLException {
		GestoreBean g = new GestoreBean();
		g.setEmail("gino@gino.it");
		g.setNome("gino");
		g.setCognome("pozzo");
		g.setPassword("gino");
		g.setTelefono("3923415443");
		g.setStruttura("playk");
		
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(g);
		when(req.getParameter("action")).thenReturn(null);
		when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);

	}
}
