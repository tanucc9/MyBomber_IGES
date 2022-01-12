package control;

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

import model.bean.EventoBean;
import model.bean.GestoreBean;
import model.bean.GiocatoreBean;
import model.dao.EventoDAO;
import model.dao.GestoreDAO;
import model.dao.GiocatoreDAO;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class TestCronologiaEventiServlet {

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
	
	private CronologiaEventiServlet servlet;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		servlet= new CronologiaEventiServlet();
		when(req.getSession()).thenReturn(session);

	}
	
	@Test
	public void cercaCronologiaGestore() throws ServletException, IOException, SQLException {
		GestoreBean gi=new GestoreBean();
		gi.setEmail("gino@gino.it");
		gi.setNome("gino");
		gi.setCognome("pozzo");
		gi.setPassword("gino");
		gi.setTelefono("3923415443");
		gi.setStruttura("playk");
		
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
		bean.setStato("completato");
		bean.setValutazione(0);
		bean.setNumPartecipanti(10);
		
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
		g4.setStato("completato");
		g4.setValutazione(0);
		g4.setNumPartecipanti(10);
		
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
		liststr.add(g3.toString());
		list.add(g4);
		liststr.add(g4.toString());
		
		when((GestoreBean)req.getSession().getAttribute("gestore")).thenReturn(gi);
		when(evDao.doRetrieveEventiGestore(gi.getEmail())).thenReturn(list);
		when(req.getAttribute("eventi")).thenReturn(list);
		when(req.getRequestDispatcher(res.encodeRedirectURL("./CronologiaEventi.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
		ArrayList<EventoBean> cev= new ArrayList<EventoBean>();
		ArrayList<String> stcev= new ArrayList<String>();
		cev=(ArrayList<EventoBean>)req.getAttribute("eventi");
		for(EventoBean e : cev)
		{
			stcev.add(e.toString());
		}
		
		assertEquals(stcev,liststr);
		
	}
	
	
	
}
