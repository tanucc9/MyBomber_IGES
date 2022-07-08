package integrazione.control;

import java.io.IOException;
import java.sql.Date;

import control.squadra.AbbandonaSquadraServlet;
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

public class TestIntegrazioneAbbandonaSquadra extends TestCase {

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

    private AbbandonaSquadraServlet servlet;
    private SquadraBean squadra;
    private GiocatoreBean giocatore;
    private GiocatoreDAO gioDao;
    private SquadraDAO sqDao;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.openMocks(this);
        servlet = new AbbandonaSquadraServlet();
        when(req.getSession()).thenReturn(session);

        int idSquadra = 2;

        gioDao = new GiocatoreDAO();
        giocatore = new GiocatoreBean();
        giocatore.setUsername("gio_test");
        giocatore.setEmail("gio4test@email.it");
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
        giocatore.setIdSquadra(idSquadra);
        gioDao.doSave(giocatore);

        sqDao = new SquadraDAO();
        squadra = sqDao.doRetrieveByKey(idSquadra);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();

        gioDao.doDelete(giocatore.getEmail());
    }

    @Test
    public void testAbbandonaSquadra() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);
        when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(squadra);
        when(req.getRequestDispatcher(res.encodeRedirectURL(Mockito.anyString()))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }
}