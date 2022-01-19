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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import control.EventiRecentiServlet;
import control.PartecipaEventiServlet;
import model.bean.EventoBean;
import model.bean.GiocatoreBean;
import model.dao.EventoDAO;
import model.dao.PartecipazioneDAO;

public class TestPartecipaEventiServlet {

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
	PartecipazioneDAO pDao = new PartecipazioneDAO();
	
	private PartecipaEventiServlet servlet;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		servlet= new PartecipaEventiServlet();
		when(req.getSession()).thenReturn(session);

	}
	
	@Test
	public void cercaEventi() throws ServletException, IOException, SQLException {
		servlet.eD = evDao;
		
		GiocatoreBean g = new GiocatoreBean();
		g.setUsername("pierox");
		g.setEmail("piero@piero.it");
		g.setNome("Giovanni");
		g.setCognome("Falco");
		g.setPassword("Gio");
		g.setTelefono("3334562167");
		g.setDataNascita(Date.valueOf("2001-11-16"));
		g.setNazioneResidenza("Italia");
		g.setProvinciaResidenza("Caserta");
		g.setCittaResidenza("Caserta");
		g.setCapResidenza("89976");
		g.setValutazione(0);
		// mariono test
		
		 ArrayList<EventoBean> list = new ArrayList<EventoBean>();
		 ArrayList<String> listr = new ArrayList<String>();	
			EventoBean g3=new EventoBean();
			
			g3.setNome("evento3");
			g3.setDescrizione("sdfghgfds");
			g3.setStruttura("playk");
			g3.setData(Date.valueOf("2022-01-15"));
			g3.setOra(1);
			g3.setGestore("gino@gino.it");
			g3.setOrganizzatore("simone@simone.it");
			g3.setStato("attivo");
			g3.setValutazione(0);
			g3.setNumPartecipanti(3);
			list.add(g3);
			listr.add(g3.toString());
			when((GiocatoreBean)req.getSession().getAttribute("giocatore")).thenReturn(g);
			when(evDao.doRetrieveEventi(g.getEmail())).thenReturn(list);
			when(req.getAttribute("eventi")).thenReturn(list);
			when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);
			servlet.doGet(req, res);
			verify(rd).forward(req, res);
			ArrayList<EventoBean> cev= new ArrayList<EventoBean>();
			ArrayList<String> stcev= new ArrayList<String>();
			cev=(ArrayList<EventoBean>)req.getAttribute("eventi");
			for(EventoBean e : cev)
			{
				stcev.add(e.toString());
			}
			
			assertEquals(stcev,listr);

		
	}
	
	
	@Test
	public void partecipaEvento() throws ServletException, IOException, SQLException {
		servlet.eD = evDao;
		servlet.pD = pDao;
		GiocatoreBean g = new GiocatoreBean();
		g.setUsername("pierox");
		g.setEmail("piero@piero.it");
		g.setNome("Giovanni");
		g.setCognome("Falco");
		g.setPassword("Gio");
		g.setTelefono("3334562167");
		g.setDataNascita(Date.valueOf("2001-11-16"));
		g.setNazioneResidenza("Italia");
		g.setProvinciaResidenza("Caserta");
		g.setCittaResidenza("Caserta");
		g.setCapResidenza("89976");
		g.setValutazione(0);
		
        EventoBean g3=new EventoBean();	
		g3.setNome("evento3");
		g3.setDescrizione("sdfghgfds");
		g3.setStruttura("playk");
		g3.setData(Date.valueOf("2022-01-15"));
		g3.setOra(1);
		g3.setGestore("gino@gino.it");
		g3.setOrganizzatore("simone@simone.it");
		g3.setStato("attivo");
		g3.setValutazione(0);
		g3.setNumPartecipanti(3);
		when((GiocatoreBean)req.getSession().getAttribute("giocatore")).thenReturn(g);
		when(req.getParameter("nome")).thenReturn("evento3");
		when(evDao.doRetrieveByKey(Mockito.anyString())).thenReturn(g3);
		when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);
		servlet.doPost(req, res);
		verify(rd).forward(req, res);
		
	}
	
}
