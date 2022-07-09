package integrazione.control;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.evento.RichiesteEventiServlet;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.partecipazione.PartecipazioneDAO;
import model.partecipazione.PartecipazioneSquadraDAO;
import model.utente.gestore.GestoreBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.HashTool;

// TODO: Auto-generated Javadoc
/**
 * The Class TestIntegrazioneRichiesteEventi.
 */
public class TestIntegrazioneRichiesteEventi {

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
  private RichiesteEventiServlet servlet;
  private GestoreBean g;
  private EventoBean ev;
  private EventoBean evSquadra;
  private EventoDAO ed;
  private PartecipazioneDAO pd;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException, SQLException {
    MockitoAnnotations.openMocks(this);
    hashTool = new HashTool();
    servlet = new RichiesteEventiServlet();
    when(req.getSession()).thenReturn(session);

    g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");

    ev = new EventoBean();
    evSquadra = new EventoBean();
    ed = new EventoDAO();
    ev.setNome("evento435");
    ev.setDescrizione("mitico evento");
    ev.setStruttura("playk");
    ev.setData(Date.valueOf("2023-01-03"));
    ev.setOra(2);
    ev.setGestore("gino@gino.it");
    ev.setOrganizzatore("simone@simone.it");
    ev.setStato("richiesta");
    ev.setValutazione(0);
    ev.setNumPartecipanti(10);
    ev.setTipologia("libero");
    ed.doSave(ev);

    evSquadra.setNome("evento435 squadra");
    evSquadra.setDescrizione("mitico evento");
    evSquadra.setStruttura("playk");
    evSquadra.setData(Date.valueOf("2023-01-03"));
    evSquadra.setOra(2);
    evSquadra.setGestore("gino@gino.it");
    evSquadra.setOrganizzatore("gio4@email.it");
    evSquadra.setStato("richiesta");
    evSquadra.setValutazione(0);
    evSquadra.setNumPartecipanti(10);
    evSquadra.setTipologia("squadra");
    ed.doSave(evSquadra);

    pd = new PartecipazioneDAO();
  }

  @After
  public void tearDown() throws SQLException {
    pd.doDelete(ev.getOrganizzatore(), ev.getNome());
    ed.doDelete("evento435");
    ed.doDelete("evento435 squadra");
  }


    /**
     * Cerca richieste.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      Signals that an I/O exception has occurred.
     * @throws SQLException     the SQL exception
     */
  @Test
  public void cercaRichieste() throws ServletException, IOException {
    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("action")).thenReturn("trovaRichieste");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
  }

  /**
   * Accetta richieste.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void accettaRichieste() throws ServletException, IOException {
    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("nome")).thenReturn("evento435");
    when(req.getParameter("action")).thenReturn("addE");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
  }

  /**
   * Rifiuta richieste.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void rifiutaRichieste() throws ServletException, IOException {

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("nome")).thenReturn("evento435");
    when(req.getParameter("action")).thenReturn("deleteE");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
  }

  @Test
  public void accettaRichiesteSquadra() throws ServletException, IOException {
    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("nome")).thenReturn("evento435 squadra");
    when(req.getParameter("action")).thenReturn("addE");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
  }

}
