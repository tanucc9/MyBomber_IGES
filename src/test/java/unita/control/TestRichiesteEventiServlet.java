package unita.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.evento.RichiesteEventiServlet;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.HashTool;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRichiesteEventiServlet.
 */
public class TestRichiesteEventiServlet {

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

  /** The ev dao. */
  @Mock
  EventoDAO evDao = new EventoDAO();

  /** The g dao. */
  @Mock
  GiocatoreDAO gDao = new GiocatoreDAO();

  /** The p dao. */
  @Mock
  PartecipazioneDAO pDao = new PartecipazioneDAO();

  @Mock
  PartecipazioneSquadraDAO psDao = new PartecipazioneSquadraDAO();

  /** The servlet. */
  private RichiesteEventiServlet servlet;
  private HashTool hashTool;
  private GestoreBean g;
  private EventoBean ev;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);
    servlet = new RichiesteEventiServlet();
    hashTool = new HashTool();
    when(req.getSession()).thenReturn(session);

    servlet.seteD(evDao);
    servlet.setPartecipazioneSquadraDao(psDao);
    servlet.setgD(gDao);
    servlet.setpD(pDao);

    g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");

    ev = new EventoBean();
    ev.setNome("evento12");
    ev.setDescrizione("mitico evento");
    ev.setStruttura("playk");
    ev.setData(Date.valueOf("2023-01-03"));
    ev.setOra(2);
    ev.setGestore("gino@gino.it");
    ev.setOrganizzatore("simone@simone.it");
    ev.setStato("richiesta");
    ev.setValutazione(0);
    ev.setNumPartecipanti(0);
    ev.setTipologia("libero");
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
    ArrayList<EventoBean> list = new ArrayList<>();
    ArrayList<String> liststr = new ArrayList<>();

    EventoBean ev1 = new EventoBean();
    EventoBean ev2 = new EventoBean();

    list.add(ev);
    liststr.add(ev.toString());

    ev2.setNome("evento2");
    ev2.setDescrizione("grande evento");
    ev2.setStruttura("playk");
    ev2.setData(Date.valueOf("2022-01-03"));
    ev2.setOra(2);
    ev2.setGestore("gino@gino.it");
    ev2.setOrganizzatore("simone@simone.it");
    ev2.setStato("richiesta");
    ev2.setValutazione(0);
    ev2.setNumPartecipanti(0);
    ev2.setTipologia("libero");

    ev1.setNome("evento3");
    ev1.setDescrizione("sdfghgfds");
    ev1.setStruttura("playk");
    ev1.setData(Date.valueOf("2022-01-15"));
    ev1.setOra(1);
    ev1.setGestore("gino@gino.it");
    ev1.setOrganizzatore("simone@simone.it");
    ev1.setStato("richiesta");
    ev1.setValutazione(0);
    ev1.setNumPartecipanti(0);
    ev1.setTipologia("libero");

    list.add(ev1);
    liststr.add(ev1.toString());
    list.add(ev2);
    liststr.add(ev2.toString());

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("action")).thenReturn("trovaRichieste");
    when(evDao.doRetrieveEventiRecenti(g.getEmail())).thenReturn(list);
    when(req.getAttribute("richieste")).thenReturn(list);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
    ArrayList<EventoBean> cev = new ArrayList<>();
    ArrayList<String> stcev = new ArrayList<>();
    cev = (ArrayList<EventoBean>) req.getAttribute("richieste");
    for (EventoBean e : cev) {
      stcev.add(e.toString());
    }

    assertEquals(stcev, liststr);

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
    ArrayList<GiocatoreBean> giocatori = new ArrayList<GiocatoreBean>();
    GiocatoreBean gio = new GiocatoreBean();
    gio.setEmail("simone@simone.it");
    gio.setUsername("simone");
    gio.setNome("Simone");
    gio.setCognome("Graziano");
    gio.setEncPassword("19a0098e641d4bee278bb5d470d06679ffc5fdc818c3a1c52bfb7f8cde3752d3");
    gio.setTelefono("3324156789");
    giocatori.add(gio);

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("nome")).thenReturn(ev.getNome());
    when(req.getParameter("action")).thenReturn("addE");
    when(gDao.doRetrieveAll()).thenReturn(giocatori);
    when(evDao.doRetrieveByKey(ev.getCode())).thenReturn(ev);
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
  public void rifiutaRichieste() throws ServletException, IOException, SQLException {
    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("action")).thenReturn("deleteE");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
  }

  /**
   * No testing.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void noTesting() throws ServletException, IOException, SQLException {
    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("action")).thenReturn(null);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

  @Test
  public void accettaRichiesteSquadra() throws ServletException, IOException, SQLException {
    ev.setTipologia("squadra");

    ArrayList<GiocatoreBean> giocatori = new ArrayList<GiocatoreBean>();
    GiocatoreBean gio = new GiocatoreBean();
    gio.setEmail("simone@simone.it");
    gio.setUsername("simone");
    gio.setNome("Simone");
    gio.setCognome("Graziano");
    gio.setEncPassword("19a0098e641d4bee278bb5d470d06679ffc5fdc818c3a1c52bfb7f8cde3752d3");
    gio.setTelefono("3324156789");
    giocatori.add(gio);

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("nome")).thenReturn(ev.getNome());
    when(req.getParameter("action")).thenReturn("addE");
    when(gDao.doRetrieveAll()).thenReturn(giocatori);
    when(evDao.doRetrieveByKey(ev.getCode())).thenReturn(ev);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
  }

  @Test
  public void gestoreNotLogged() throws ServletException, IOException, SQLException {
    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(null);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./Login.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
  }

}
