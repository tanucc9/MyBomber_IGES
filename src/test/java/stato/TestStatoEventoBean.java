package stato;

import control.cron.EventStateUpdater;
import control.evento.CreaEventoServlet;
import control.evento.PartecipaEventiServlet;
import control.evento.RichiesteEventiServlet;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.squadra.SquadraBean;
import model.utente.gestore.GestoreBean;
import model.utente.giocatore.GiocatoreBean;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import util.HashTool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TestStatoEventoBean {

	@Mock
	private HttpServletRequest req;

	@Mock
	private HttpServletResponse res;

	@Mock
	private HttpSession session;

	@Mock
	private RequestDispatcher rd;

	private EventoBean e1;
	private EventStateUpdater updater;
	private EventoDAO evDao;
	private String code;
	private HashTool hashTool;

	@BeforeAll
	public void setUp() throws NoSuchAlgorithmException {

		MockitoAnnotations.openMocks(this);

		code = "evento-test-stato";
		e1 = new EventoBean();
		e1.setNome("evento test stato");
		e1.setDescrizione("Prova descrizione");
		e1.setStruttura("playk");
		e1.setData(Date.valueOf("2022-01-29"));
		e1.setOra(22);
		e1.setGestore("gino@gino.it");
		e1.setOrganizzatore("simone@simone.it");
		e1.setValutazione(0);
		e1.setTipologia("libero");
		e1.setNumPartecipanti(0);

		updater = new EventStateUpdater();
		evDao = new EventoDAO();
		hashTool = new HashTool();
	}

	@AfterAll
	public void tearDown() throws SQLException {
		evDao.doDelete(e1.getCode());
	}

	@Test
	@Order(1)
	public void testStatoRichiesta() throws ServletException, IOException {
		CreaEventoServlet servlet = new CreaEventoServlet();

		GiocatoreBean gio = new GiocatoreBean();
		gio.setUsername("gio");
		gio.setEmail("gio4@email.it");
		gio.setNome("Giovanni");
		gio.setCognome("Falco");
		gio.setEncPassword(hashTool.hashSHA256("Gio"));
		gio.setTelefono("3334562167");
		gio.setDataNascita(Date.valueOf("2001-11-16"));
		gio.setNazioneResidenza("Italia");
		gio.setProvinciaResidenza("Caserta");
		gio.setCittaResidenza("Caserta");
		gio.setCapResidenza("89976");
		gio.setValutazione(0);

		when(req.getSession()).thenReturn(session);
		when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(gio);
		when(req.getParameter("nome")).thenReturn(e1.getNome());
		when(req.getParameter("descrizione")).thenReturn(e1.getDescrizione());
		when(req.getParameter("struttura")).thenReturn(e1.getStruttura());
		when(req.getParameter("data")).thenReturn("2022-01-29");
		when(req.getParameter("ora")).thenReturn("22");
		when(req.getParameter("switch_tipologia")).thenReturn(null);
		when(req.getRequestDispatcher(res.encodeRedirectURL(Mockito.anyString()))).thenReturn(rd);

		servlet.doGet(req, res);

		e1 = evDao.doRetrieveByKey(code);
		assertEquals("richiesta",e1.getStato());
	}

	@Test
	@Order(2)
	public void testStatoAttivo() throws ServletException, IOException, NoSuchAlgorithmException {
		RichiesteEventiServlet servlet = new RichiesteEventiServlet();

		GestoreBean ges = new GestoreBean();
		ges.setEmail("gino@gino.it");
		ges.setNome("gino");
		ges.setCognome("pozzo");
		ges.setEncPassword(hashTool.hashSHA256("gino"));
		ges.setTelefono("3923415443");
		ges.setStruttura("playk");

		when(req.getSession()).thenReturn(session);
		when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(ges);
		when(req.getParameter("code")).thenReturn(code);
		when(req.getParameter("action")).thenReturn("addE");
		when(req.getRequestDispatcher(res.encodeRedirectURL(Mockito.anyString()))).thenReturn(rd);

		servlet.doGet(req, res);

		e1 = evDao.doRetrieveByKey(code);
		assertEquals("attivo", e1.getStato());
	}

	@Test
	@Order(3)
	public void testStatoCompletato() throws SQLException, ServletException, IOException {
		e1.setNumPartecipanti(9);
		evDao.doUpdate(e1); //mocking number of players in the event

		PartecipaEventiServlet servlet = new PartecipaEventiServlet();

		GiocatoreBean gio = new GiocatoreBean();
		gio.setUsername("pierox");
		gio.setEmail("pino@pino.it");
		gio.setNome("Giovanni");
		gio.setCognome("Falco");
		gio.setEncPassword(hashTool.hashSHA256("Gio"));
		gio.setTelefono("3334562167");
		gio.setDataNascita(Date.valueOf("2001-11-16"));
		gio.setNazioneResidenza("Italia");
		gio.setProvinciaResidenza("Caserta");
		gio.setCittaResidenza("Caserta");
		gio.setCapResidenza("89976");
		gio.setValutazione(0);
		gio.setIdSquadra(2);

		when(req.getSession()).thenReturn(session);
		when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(gio);
		when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(null);
		when(req.getParameter("code")).thenReturn(code);
		when(req.getRequestDispatcher(res.encodeRedirectURL(Mockito.anyString()))).thenReturn(rd);

		servlet.doPost(req, res);

		e1 = evDao.doRetrieveByKey(code);
		assertEquals("completato", e1.getStato());
	}

		@Test
	@Order(4)
	public void testStatoIniziato() {
		long fiveMinutesAfterStart = e1.getStartTimeMillis() + 5 * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(e1, fiveMinutesAfterStart);
		assertNotNull(updEvento);
		assertEquals(updEvento.getStato(), "iniziato");

		e1.setStato(updEvento.getStato());
	}

	@Test
	@Order(5)
	public void testStatoFinito() {
		long oneHoursAndMoreAfterStart = e1.getStartTimeMillis() + 65 * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(e1, oneHoursAndMoreAfterStart);
		assertNotNull(updEvento);
		assertEquals(updEvento.getStato(), "finito");

		e1.setStato(updEvento.getStato());
	}

	@Test
	@Order(6)
	public void testStatoNonAncoraAnnullato() throws SQLException {
		e1.setData(Date.valueOf("2022-02-14"));
		e1.setOra(7);
		e1.setNumPartecipanti(8);
		e1.setStato("attivo");
		evDao.doUpdate(e1); //mocking datas to test "annullato"

		long afterTwoMinutes = e1.getStartTimeMillis() + 2L * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(e1, afterTwoMinutes);
		assertNull(updEvento);
	}

	@Test
	@Order(7)
	public void testStatoAnnullato() {
		long afterTwelveMinutes = e1.getStartTimeMillis() + 12L * 60 * 1000;
		EventoBean updEvento = updater.getEventoBeanWithNewState(e1, afterTwelveMinutes);
		assertNotNull(updEvento);
		assertEquals(updEvento.getStato(), "annullato");

		e1.setStato(updEvento.getStato());
	}
}
