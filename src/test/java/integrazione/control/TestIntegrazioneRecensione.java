package integrazione.control;

import static org.mockito.Mockito.*;
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import control.recensione.RecensioneServlet;
import model.partecipazione.PartecipazioneBean;
import model.partecipazione.PartecipazioneDAO;
import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;
import model.struttura.StrutturaBean;
import model.struttura.StrutturaDAO;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;


public class TestIntegrazioneRecensione {

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
	
	
	private RecensioneServlet servlet;
	
	
	@Before
	public void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		servlet= new RecensioneServlet();
		when(req.getSession()).thenReturn(session);
		
		PartecipazioneDAO tester=new PartecipazioneDAO();
		PartecipazioneBean bean=new PartecipazioneBean();
		bean = new PartecipazioneBean();
		bean.setEvento("evento2");
		bean.setUtente("gio4@email.it");
		tester.doSave(bean);
		bean.setEvento("evento2");
		bean.setUtente("mario@mario.it");
		tester.doSave(bean);
		bean.setEvento("evento2");
		bean.setUtente("pino@pino.it");
		tester.doSave(bean);
		
		RecensioneDAO rd=new RecensioneDAO();
		RecensioneBean rb= new RecensioneBean();
		rb.setRecensore("gio4@email.it");
		rb.setRecensito("mario@mario.it");
		rb.setEvento("evento2");
		rb.setRecensione(4);
		rd.doSave(rb);
		
	}
	
	@Test
	public void cercaGiocatori() throws ServletException, IOException, SQLException {
		
		
		GiocatoreBean g4=new GiocatoreBean();
		g4.setUsername("pino");
		g4.setEmail("gio4@gmail.it");
		g4.setNome("Pino");
		g4.setCognome("Inglese");
		g4.setPassword("pino");
		g4.setTelefono("3665423187");
		g4.setDataNascita(Date.valueOf("2000-09-09"));
		g4.setNazioneResidenza("Italia");
		g4.setProvinciaResidenza("Napoli");
		g4.setCittaResidenza("Napoli");
		g4.setCapResidenza("80000");
		g4.setValutazione(0);
		
		when((GiocatoreBean)req.getSession().getAttribute("giocatore")).thenReturn(g4);
		when(req.getParameter("action")).thenReturn("cercagiocatori");
		when(req.getParameter("nome")).thenReturn("evento2");
	
		when(req.getRequestDispatcher(res.encodeRedirectURL("./DaiRecensione.jsp"))).thenReturn(rd);
		servlet.doGet(req, res);
		verify(rd).forward(req, res);
		
	}
	
	
	
	@Test
	public void aggiungiRecensione() throws ServletException, IOException, SQLException {
		
		
		GiocatoreBean g4=new GiocatoreBean();
		g4 =new GiocatoreBean();
		g4.setUsername("simone45");
		g4.setEmail("mario@mario.it");
		g4.setNome("Simone");
		g4.setCognome("Graziano");
		g4.setPassword("simone");
		g4.setTelefono("3324561273");
		g4.setDataNascita(Date.valueOf("2000-05-09"));
		g4.setNazioneResidenza("Italia");
		g4.setProvinciaResidenza("Napoli");
		g4.setCittaResidenza("Napoli");
		g4.setCapResidenza("80000");
		g4.setValutazione(0);
		
		when(req.getParameter("rec")).thenReturn("set");
		when((GiocatoreBean)req.getSession().getAttribute("giocatore")).thenReturn(g4);
		
		RecensioneBean bean = new RecensioneBean();
		bean.setRecensore("mario@mario.it");
		bean.setRecensito("pino@pino.it");
		bean.setEvento("evento2");
		bean.setRecensione(4);
		
		when(req.getParameter("nomeEvento")).thenReturn(bean.getEvento());
		when(req.getParameter("nomeG")).thenReturn(bean.getRecensito());
		when(req.getParameter("descrizione")).thenReturn(bean.getDescrizione());
		when(req.getParameter("valutazione")).thenReturn("4");
		
		when(req.getAttribute("saveok")).thenReturn("saveok");
		
		when(req.getRequestDispatcher(res.encodeRedirectURL("./EventiRecenti.jsp"))).thenReturn(rd);
		servlet.doPost(req, res);
		verify(rd).forward(req, res);
		assertEquals("saveok", req.getAttribute("saveok"));
	}
	
	@Test
	public void eliminaRecensione() throws ServletException, IOException, SQLException {
		
		GiocatoreBean g4=new GiocatoreBean();
		g4 =new GiocatoreBean();
		g4.setUsername("simone45");
		g4.setEmail("mario@mario.it");
		g4.setNome("Simone");
		g4.setCognome("Graziano");
		g4.setPassword("simone");
		g4.setTelefono("3324561273");
		g4.setDataNascita(Date.valueOf("2000-05-09"));
		g4.setNazioneResidenza("Italia");
		g4.setProvinciaResidenza("Napoli");
		g4.setCittaResidenza("Napoli");
		g4.setCapResidenza("80000");
		g4.setValutazione(0);
		
		when(req.getParameter("rec")).thenReturn("el");
		when((GiocatoreBean)req.getSession().getAttribute("giocatore")).thenReturn(g4);
		
		RecensioneBean bean = new RecensioneBean();
		bean.setRecensore("mario@mario.it");
		bean.setRecensito("pino@pino.it");
		bean.setEvento("evento2");
		bean.setRecensione(4);
		
		when(req.getParameter("nomeG")).thenReturn(bean.getRecensito());
		when(req.getParameter("nomeEvento")).thenReturn(bean.getEvento());
		
		when(req.getAttribute("deleteok")).thenReturn("deleteok");
		
		when(req.getRequestDispatcher(res.encodeRedirectURL("./EventiRecenti.jsp"))).thenReturn(rd);
		servlet.doPost(req, res);
		verify(rd).forward(req, res);
		assertEquals("deleteok", req.getAttribute("deleteok"));
	}
	
	
	@After
	public void tearDown() throws Exception {
		PartecipazioneDAO tester=new PartecipazioneDAO();
		PartecipazioneBean bean=new PartecipazioneBean();
		bean = new PartecipazioneBean();
		bean.setEvento("evento2");
		bean.setUtente("gio4@email.it");
		tester.doDelete(bean.getUtente(),bean.getEvento());
		bean.setEvento("evento2");
		bean.setUtente("mario@mario.it");
		tester.doDelete(bean.getUtente(),bean.getEvento());
		bean.setEvento("evento2");
		bean.setUtente("pino@pino.it");
		tester.doDelete(bean.getUtente(),bean.getEvento());
		
		RecensioneDAO rd=new RecensioneDAO();
		RecensioneBean rb= new RecensioneBean();
		rb.setRecensore("gio4@email.it");
		rb.setRecensito("mario@mario.it");
		rb.setEvento("evento2");
		rb.setRecensione(4);
		rd.doDelete(rb.getRecensore(),rb.getRecensito(),rb.getEvento());
	}
	
}
