package unita.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.utente.AreaUtenteServlet;
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

// TODO: Auto-generated Javadoc
/**
 * The Class TestAreaUtenteServlet.
 */
public class TestAreaUtenteServlet {

  /** Utility tool for hashing. */
  private HashTool hashTool;

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
  private AreaUtenteServlet servlet;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);
    hashTool = new HashTool();
    servlet = new AreaUtenteServlet();
    when(req.getSession()).thenReturn(session);

  }

  /**
   * Mostra giocatore.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void mostraGiocatore() throws ServletException, IOException {
    GiocatoreBean gi = new GiocatoreBean();
    gi.setUsername("pino");
    gi.setEmail("pino@pino.it");
    gi.setNome("Pino");
    gi.setCognome("Inglese");
    gi.setEncPassword(hashTool.hashSHA256("pino"));
    gi.setTelefono("3665423187");
    gi.setDataNascita(Date.valueOf("2000-09-09"));
    gi.setNazioneResidenza("Italia");
    gi.setProvinciaResidenza("Napoli");
    gi.setCittaResidenza("Napoli");
    gi.setCapResidenza("80000");
    gi.setValutazione(0);
    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(null);
    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(gi);
    when(req.getAttribute("cu")).thenReturn("giocatore");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./AreaUtente.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
    assertEquals(req.getAttribute("cu"), "giocatore");

  }

  /**
   * Mostra gestore.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void mostraGestore() throws ServletException, IOException {
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");
    when(session.getAttribute("gestore")).thenReturn(g);
    when(req.getAttribute("cu")).thenReturn("gestore");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./AreaUtente.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
    String controllo = (String) req.getAttribute("cu");
    assertEquals("gestore", controllo);
  }

}
