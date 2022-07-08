package integrazione.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.utente.LoginServlet;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.utente.gestore.GestoreBean;
import model.utente.giocatore.GiocatoreBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.HashTool;

/**
 * The Class TestIntegrazioneLogin.
 */
public class TestIntegrazioneLogin {

  /** The req. */
  @Mock
  private HttpServletRequest req;

  /** The res. */
  @Mock
  private HttpServletResponse res;

  /** The ctx. */
  @Mock
  private ServletContext ctx;

  /** The session. */
  @Mock
  private HttpSession session;

  /** The rd. */
  @Mock
  private RequestDispatcher rd;

  /** The servlet. */
  private LoginServlet servlet;
  private HashTool hashTool;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);
    servlet = new LoginServlet();
    hashTool = new HashTool();
    when(req.getSession()).thenReturn(session);
  }

  /**
   * Login giocatore ok (con giocatore NON iscritto ad una squadra)
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void loginGiocatoreOk() throws ServletException, IOException {
    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("pino");
    g.setEmail("pino@pino.it");
    g.setNome("Pino");
    g.setCognome("Inglese");
    g.setEncPassword(hashTool.hashSHA256("pino"));
    g.setTelefono("3665423187");
    g.setDataNascita(Date.valueOf("2000-09-09"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("Napoli");
    g.setCittaResidenza("Napoli");
    g.setCapResidenza("80000");
    g.setValutazione(0);
    g.setIdSquadra(0);

    when(req.getParameter("email")).thenReturn("gio4@email.it");
    when(req.getParameter("password")).thenReturn("Gio");
    when(session.getAttribute("giocatore")).thenReturn(g);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    assertEquals(g, session.getAttribute("giocatore"));
  }

  /**
   * Login giocatore ok (con giocatore iscritto ad una squadra)
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void loginGiocatoreOkConSquadra() throws ServletException, IOException {
    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("gio");
    g.setEmail("gio4@email.it");
    g.setNome("Giovanni");
    g.setCognome("Falco");
    g.setEncPassword(hashTool.hashSHA256("Gio"));
    g.setTelefono("3334562167");
    g.setDataNascita(Date.valueOf("2001-11-16"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("Caserta");
    g.setCittaResidenza("Caserta");
    g.setCapResidenza("89976");
    g.setValutazione(0);
    g.setIdSquadra(2);

    when(req.getParameter("email")).thenReturn("gio4@email.it");
    when(req.getParameter("password")).thenReturn("Gio");
    when(session.getAttribute("giocatore")).thenReturn(g);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    assertEquals(g, session.getAttribute("giocatore"));
  }

  /**
   * Login gestore ok.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void loginGestoreOk() throws ServletException, IOException {
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");

    when(req.getParameter("email")).thenReturn("gino@gino.it");
    when(req.getParameter("password")).thenReturn("gino");
    when(session.getAttribute("gestore")).thenReturn(g);
    when(req.getRequestDispatcher(res.encodeRedirectURL("cronologiaEventiServlet"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    assertEquals(g, session.getAttribute("gestore"));
  }

  /**
   * Not gio logged.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void notGioLogged() throws ServletException, IOException {

    when(req.getParameter("email")).thenReturn("pino@pino.it");
    when(req.getParameter("password")).thenReturn("x");
    when(req.getRequestDispatcher(res.encodeRedirectURL("login.jsp"))).thenReturn(rd);
    when(req.getAttribute("errorLog")).thenReturn(true);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    boolean error = (boolean) req.getAttribute("errorLog");
    assertTrue(error);
  }

  /**
   * Not ges logged.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void notGesLogged() throws ServletException, IOException {

    when(req.getParameter("email")).thenReturn("gino@gino.it");
    when(req.getParameter("password")).thenReturn("x");
    when(req.getRequestDispatcher(res.encodeRedirectURL("login.jsp"))).thenReturn(rd);
    when(req.getAttribute("errorLog")).thenReturn(true);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    boolean error = (boolean) req.getAttribute("errorLog");
    assertTrue(error);
  }

}
