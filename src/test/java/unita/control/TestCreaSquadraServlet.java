package unita.control;

import java.io.IOException;
import java.sql.Date;

import control.squadra.CreaSquadraServlet;
import junit.framework.TestCase;
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

public class TestCreaSquadraServlet extends TestCase {

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
    private GiocatoreDAO gioDao;

    @Mock
    private SquadraDAO squadraDao;

    private CreaSquadraServlet servlet;
    private GiocatoreBean giocatore;
    private SquadraBean squadra;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.openMocks(this);
        servlet = new CreaSquadraServlet();
        servlet.setGioDao(gioDao);
        servlet.setSquadraDao(squadraDao);
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

        squadra = new SquadraBean();
        squadra.setNome("test");
        squadra.setNomeAbbreviato("tes");
        squadra.setCitta("test");
        squadra.setLogo(1);
        squadra.setDescrizione("test test test test test test test test test test test test test test test");
        squadra.setIdSquadra(1);
        squadra.setCapitano(giocatore.getEmail());
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreaSquadra() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);

        when(req.getParameter("nomeSquadra")).thenReturn("test");
        when(req.getParameter("nomeAbbr")).thenReturn("tes");
        when(req.getParameter("citta")).thenReturn("test");
        when(req.getParameter("id_logo_scelto")).thenReturn("1");
        when(req.getParameter("descr")).thenReturn("test test test test test test test test test test test test test test test");

        when(squadraDao.doRetrieveByKey(Mockito.anyInt())).thenReturn(null);
        when(squadraDao.doRetrieveByName(Mockito.anyString()))
                .thenReturn(null)
                .thenReturn(squadra);

        when(req.getRequestDispatcher(res.encodeRedirectURL("./AreaUtente.jsp"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }

    @Test
    public void testCreaSquadraConLogoDefault() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);

        when(req.getParameter("nomeSquadra")).thenReturn("test");
        when(req.getParameter("nomeAbbr")).thenReturn("tes");
        when(req.getParameter("citta")).thenReturn("test");
        when(req.getParameter("id_logo_scelto")).thenReturn("0");
        when(req.getParameter("descr")).thenReturn("test test test test test test test test test test test test test test test");

        when(squadraDao.doRetrieveByKey(Mockito.anyInt())).thenReturn(null);
        when(squadraDao.doRetrieveByName(Mockito.anyString()))
                .thenReturn(null)
                .thenReturn(squadra);

        when(req.getRequestDispatcher(res.encodeRedirectURL("./AreaUtente.jsp"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }

    @Test
    public void testErrorLunghezzaMin() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);

        when(req.getParameter("nomeSquadra")).thenReturn("");
        when(req.getParameter("nomeAbbr")).thenReturn("");
        when(req.getParameter("citta")).thenReturn("");
        when(req.getParameter("id_logo_scelto")).thenReturn("1");
        when(req.getParameter("descr")).thenReturn("test test");

        when(req.getRequestDispatcher(res.encodeRedirectURL("./CreaSquadra.jsp"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }

    @Test
    public void testErrorLunghezzaMax() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);

        when(req.getParameter("nomeSquadra")).thenReturn("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        when(req.getParameter("nomeAbbr")).thenReturn("wwww");
        when(req.getParameter("citta")).thenReturn("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        when(req.getParameter("id_logo_scelto")).thenReturn("1");
        when(req.getParameter("descr")).thenReturn("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");

        when(req.getRequestDispatcher(res.encodeRedirectURL("./CreaSquadra.jsp"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }

    @Test
    public void testErrorFormato() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);

        when(req.getParameter("nomeSquadra")).thenReturn("1234");
        when(req.getParameter("nomeAbbr")).thenReturn("1324");
        when(req.getParameter("citta")).thenReturn("1234");
        when(req.getParameter("id_logo_scelto")).thenReturn("1");
        when(req.getParameter("descr")).thenReturn("test test");

        when(req.getRequestDispatcher(res.encodeRedirectURL("./CreaSquadra.jsp"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }

    @Test
    public void testErrorDuplicato() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);

        when(req.getParameter("nomeSquadra")).thenReturn("test");
        when(req.getParameter("nomeAbbr")).thenReturn("tes");
        when(req.getParameter("citta")).thenReturn("test");
        when(req.getParameter("id_logo_scelto")).thenReturn("1");
        when(req.getParameter("descr")).thenReturn("test test test test test test test test test test test test test test test");

        when(squadraDao.doRetrieveByKey(Mockito.anyInt())).thenReturn(null);
        when(squadraDao.doRetrieveByName(Mockito.anyString())).thenReturn(squadra);

        when(req.getRequestDispatcher(res.encodeRedirectURL("./CreaSquadra.jsp"))).thenReturn(rd);

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