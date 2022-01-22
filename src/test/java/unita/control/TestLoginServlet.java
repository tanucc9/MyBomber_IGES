package unita.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.utente.LoginServlet;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// TODO: Auto-generated Javadoc
/**
 * The Class TestLoginServlet.
 */
public class TestLoginServlet {

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

  /** The gio dao. */
  @Mock
  GiocatoreDAO gioDao = new GiocatoreDAO();

  /** The ges dao. */
  @Mock
  GestoreDAO gesDao = new GestoreDAO();

  /** The servlet. */
  private LoginServlet servlet;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new LoginServlet();
    servlet.giocatoreDao = gioDao;
    servlet.gestoreDao = gesDao;
    when(req.getSession()).thenReturn(session);
  }

  /**
   * Login giocatore ok.
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
    g.setPassword("pino");
    g.setTelefono("3665423187");
    g.setDataNascita(Date.valueOf("2000-09-09"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("Napoli");
    g.setCittaResidenza("Napoli");
    g.setCapResidenza("80000");
    g.setValutazione(0);
    when(gioDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(g);
    when(req.getParameter("email")).thenReturn("pino@pino.it");
    when(req.getParameter("password")).thenReturn("pino");
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
    g.setPassword("gino");
    g.setTelefono("3923415443");
    g.setStruttura("playk");
    when(gesDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(g);
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
    when(gioDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null);
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
   * Not gio logged error pass.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void notGioLoggedErrorPass() throws ServletException, IOException {
    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("pino");
    g.setEmail("pino@pino.it");
    g.setNome("Pino");
    g.setCognome("Inglese");
    g.setPassword("pino");
    g.setTelefono("3665423187");
    g.setDataNascita(Date.valueOf("2000-09-09"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("Napoli");
    g.setCittaResidenza("Napoli");
    g.setCapResidenza("80000");
    g.setValutazione(0);

    when(gioDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(g);
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
    when(gesDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null);
    when(req.getParameter("email")).thenReturn("gino@gino.it");
    when(req.getParameter("password")).thenReturn("x");
    when(req.getRequestDispatcher(res.encodeRedirectURL("login.jsp"))).thenReturn(rd);
    when(req.getAttribute("errorLog")).thenReturn(true);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    boolean error = (boolean) req.getAttribute("errorLog");
    assertTrue(error);
  }

  /**
   * Not ges logged error pass.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void notGesLoggedErrorPass() throws ServletException, IOException {
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setPassword("gino");
    g.setTelefono("3923415443");
    g.setStruttura("playk");
    when(gesDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(g);

    when(req.getParameter("email")).thenReturn("gino@gino.it");
    when(req.getParameter("password")).thenReturn("x");
    when(req.getRequestDispatcher(res.encodeRedirectURL("login.jsp"))).thenReturn(rd);
    when(req.getAttribute("errorLog")).thenReturn(true);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    boolean error = (boolean) req.getAttribute("errorLog");
    assertTrue(error);
  }

  /**
   * Do get.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void doGet() throws ServletException, IOException {

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
  }

}
