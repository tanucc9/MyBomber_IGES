package integrazione.control;

import java.io.IOException;
import java.sql.Date;

import control.squadra.UniscitiSquadraServlet;
import junit.framework.TestCase;
import model.squadra.SquadraBean;
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

public class TestIntegrazioneUniscitiSquadra extends TestCase {

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

    private UniscitiSquadraServlet servlet;
    private GiocatoreBean giocatore;
    private GiocatoreDAO gioDao;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.openMocks(this);
        servlet = new UniscitiSquadraServlet();
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
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        gioDao.doLeaveTeam(giocatore);
    }

    @Test
    public void testUniscitiSquadra() throws ServletException, IOException {
        when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(giocatore);
        when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(null);
        when(req.getParameter("id")).thenReturn("2");

        when(req.getRequestDispatcher(res.encodeRedirectURL(Mockito.anyString()))).thenReturn(rd);

        servlet.doGet(req, res);

        verify(rd).forward(req, res);
    }

}