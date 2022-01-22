package unita.control;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.utente.LogoutServlet;
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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// TODO: Auto-generated Javadoc
/**
 * The Class TestLogoutServlet.
 */
public class TestLogoutServlet {

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
  private LogoutServlet servlet;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new LogoutServlet();
    when(req.getSession()).thenReturn(session);
  }

  /**
   * Logout giocatore.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void logoutGiocatore() throws ServletException, IOException {
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

    when(req.getSession().getAttribute("giocatore")).thenReturn(g);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./Login.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Logout gestore.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void logoutGestore() throws ServletException, IOException {
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setPassword("gino");
    g.setTelefono("3923415443");
    g.setStruttura("playk");
    when(req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./Login.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
  }

}
