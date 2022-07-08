package integrazione.control;

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

public class TestIntegrazioneCreaSquadra extends TestCase {

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

    private CreaSquadraServlet servlet;
    private GiocatoreBean giocatore;
    private SquadraDAO sqDao;
    private GiocatoreDAO gioDao;
    private String nomeSquadra;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.openMocks(this);
        servlet = new CreaSquadraServlet();
        when(req.getSession()).thenReturn(session);

        giocatore = new GiocatoreBean();
        giocatore.setUsername("pino");
        giocatore.setEmail("pino@pino.it");
        giocatore.setNome("Pino");
        giocatore.setCognome("Inglese");
        HashTool hashTool = new HashTool();
        giocatore.setEncPassword(hashTool.hashSHA256("pino"));
        giocatore.setTelefono("3665423187");
        giocatore.setDataNascita(Date.valueOf("2000-09-09"));
        giocatore.setNazioneResidenza("Italia");
        giocatore.setProvinciaResidenza("Napoli");
        giocatore.setCittaResidenza("Napoli");
        giocatore.setCapResidenza("80000");
        giocatore.setValutazione(0);
        giocatore.setIdSquadra(0);

        gioDao = new GiocatoreDAO();
        sqDao = new SquadraDAO();

        nomeSquadra = "test";
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        SquadraBean sq = sqDao.doRetrieveByName(nomeSquadra);
        sqDao.doDelete(sq.getIdSquadra());
        gioDao.doLeaveTeam(giocatore);
    }

    @Test
    public void testCreaSquadra() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);

        when(req.getParameter("nomeSquadra")).thenReturn(nomeSquadra);
        when(req.getParameter("nomeAbbr")).thenReturn("tes");
        when(req.getParameter("citta")).thenReturn("test");
        when(req.getParameter("id_logo_scelto")).thenReturn("2");
        when(req.getParameter("descr")).thenReturn("test test test test test test test test test test test test test test test");

        when(req.getRequestDispatcher(res.encodeRedirectURL("./AreaUtente.jsp"))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }
}