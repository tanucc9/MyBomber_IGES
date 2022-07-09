package unita.control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import control.squadra.CreaSquadraServlet;
import control.squadra.EliminaSquadraServlet;
import control.squadra.MostraSquadreServlet;
import control.squadra.SquadraSpecificaServlet;
import junit.framework.TestCase;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.squadra.LogoSquadraBean;
import model.squadra.LogoSquadraDAO;
import model.squadra.SquadraBean;
import model.squadra.SquadraDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import util.HashTool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestSquadraSpecificaServlet extends TestCase {

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
    private SquadraDAO squadraDao;
    @Mock
    private LogoSquadraDAO logoDao;
    @Mock
    private GiocatoreDAO gioDao;
    @Mock
    private EventoDAO evDao;

    private SquadraSpecificaServlet servlet;
    private ArrayList<GiocatoreBean> giocatori;
    private GiocatoreBean giocatore;
    private SquadraBean squadra;
    private LogoSquadraBean logo;
    private ArrayList<EventoBean> eventi;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.openMocks(this);
        servlet = new SquadraSpecificaServlet();
        servlet.setSquadraDao(squadraDao);
        servlet.setEventoDao(evDao);
        servlet.setGioDao(gioDao);
        servlet.setLogoDao(logoDao);

        when(req.getSession()).thenReturn(session);

        giocatore = new GiocatoreBean();
        giocatore.setUsername("gio");
        giocatore.setEmail("gio4@email.it");
        giocatore.setNome("Giovanni");
        giocatore.setCognome("Falco");
        HashTool hashTool = new HashTool();
        giocatore.setEncPassword(hashTool.hashSHA256("Gio"));
        giocatore.setTelefono("3334562167");
        giocatore.setDataNascita(Date.valueOf("2001-11-16"));
        giocatore.setNazioneResidenza("Italia");
        giocatore.setProvinciaResidenza("Caserta");
        giocatore.setCittaResidenza("Caserta");
        giocatore.setCapResidenza("89976");
        giocatore.setValutazione(0);
        giocatore.setIdSquadra(1);
        giocatori = new ArrayList<GiocatoreBean>();
        giocatori.add(giocatore);

        squadra = new SquadraBean();
        squadra.setNome("test");
        squadra.setNomeAbbreviato("tes");
        squadra.setCitta("test");
        squadra.setLogo(1);
        squadra.setDescrizione("test test test test test test test test test test test test test test test");
        squadra.setIdSquadra(1);
        squadra.setCapitano(giocatore.getEmail());

        logo = new LogoSquadraBean();
        logo.setIdLogoSquadra(1);
        logo.setNome("logo");
        logo.setUrl("www.logo.it");

        EventoBean ev = new EventoBean();
        ev.setNome("evento squadra");
        ev.setDescrizione("descrizione.");
        ev.setStruttura("playk");
        ev.setData(Date.valueOf("2022-01-15"));
        ev.setOra(22);
        ev.setGestore("gino@gino.it");
        ev.setOrganizzatore("gio4@email.it");
        ev.setStato("completato");
        ev.setValutazione(0);
        ev.setNumPartecipanti(2);
        ev.setTipologia("squadra");
        eventi = new ArrayList<EventoBean>();
        eventi.add(ev);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testSquadraSpecifica() throws ServletException, IOException, SQLException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);
        when(req.getParameter("idSquadra")).thenReturn("1");
        when(squadraDao.doRetrieveByKey(Mockito.anyInt())).thenReturn(squadra);
        when(logoDao.doRetrieveByKey(Mockito.anyInt())).thenReturn(logo);
        when(gioDao.doRetrieveBySquadra(Mockito.anyInt())).thenReturn(giocatori);
        when(gioDao.doRetrieveByKey(Mockito.anyString())).thenReturn(giocatore);
        when(evDao.doRetrieveEventiRecentiSquadra(Mockito.anyInt())).thenReturn(eventi);

        when(req.getRequestDispatcher(res.encodeRedirectURL("./Squadra.jsp"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }

    @Test
    public void testGiocatoreNotLogged() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(null);

        when(req.getRequestDispatcher(res.encodeRedirectURL("./"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }
}