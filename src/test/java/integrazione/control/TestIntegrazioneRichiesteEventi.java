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
import model.utente.gestore.GestoreBean;
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

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);
    hashTool = new HashTool();
    servlet = new RichiesteEventiServlet();
    when(req.getSession()).thenReturn(session);

  }

  /**
   * Cerca richieste.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void cercaRichieste() throws ServletException, IOException, SQLException {
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");

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
  public void accettaRichieste() throws ServletException, IOException, SQLException {
    EventoBean bean = new EventoBean();
    EventoDAO ed = new EventoDAO();
    PartecipazioneDAO pd = new PartecipazioneDAO();
    bean.setNome("evento435");
    bean.setDescrizione("mitico evento");
    bean.setStruttura("playk");
    bean.setData(Date.valueOf("2023-01-03"));
    bean.setOra(2);
    bean.setGestore("gino@gino.it");
    bean.setOrganizzatore("simone@simone.it");
    bean.setStato("richiesta");
    bean.setValutazione(0);
    bean.setNumPartecipanti(10);
    ed.doSave(bean);
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("nome")).thenReturn("evento435");
    when(req.getParameter("action")).thenReturn("addE");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
    pd.doDelete(bean.getOrganizzatore(), bean.getNome());
    ed.doDelete("evento435");
  }

  /**
   * Rifiuta richieste.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void rifiutaRichieste() throws ServletException, IOException, SQLException {
    EventoBean bean = new EventoBean();
    EventoDAO ed = new EventoDAO();
    bean.setNome("evento435");
    bean.setDescrizione("mitico evento");
    bean.setStruttura("playk");
    bean.setData(Date.valueOf("2023-01-03"));
    bean.setOra(2);
    bean.setGestore("gino@gino.it");
    bean.setOrganizzatore("simone@simone.it");
    bean.setStato("richiesta");
    bean.setValutazione(0);
    bean.setNumPartecipanti(10);
    ed.doSave(bean);

    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("nome")).thenReturn("evento435");
    when(req.getParameter("action")).thenReturn("deleteE");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

}
