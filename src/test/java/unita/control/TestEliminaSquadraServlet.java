package unita.control;

import java.io.IOException;
import java.sql.Date;

import control.squadra.CreaSquadraServlet;
import control.squadra.EliminaSquadraServlet;
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

public class TestEliminaSquadraServlet extends TestCase {

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

    private EliminaSquadraServlet servlet;
    private GiocatoreBean giocatore;
    private SquadraBean squadra;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.openMocks(this);
        servlet = new EliminaSquadraServlet();
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
    public void testEliminaSquadra() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);
        when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(squadra);

        when(req.getRequestDispatcher(res.encodeRedirectURL("./Squadre.jsp"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }

    @Test
    public void testGiocatoreNotLogged() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(null);
        when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(null);

        when(req.getRequestDispatcher(res.encodeRedirectURL("./"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }

    @Test
    public void testNotCaptain() throws ServletException, IOException {
        squadra.setCapitano("test@test.it");
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);
        when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(squadra);

        when(req.getRequestDispatcher(res.encodeRedirectURL("./"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }
}