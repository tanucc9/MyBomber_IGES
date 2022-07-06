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
import model.partecipazione.PartecipazioneBean;
import model.partecipazione.PartecipazioneDAO;
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

  /** The servlet. */
  private RichiesteEventiServlet servlet;
  private HashTool hashTool;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);
    servlet = new RichiesteEventiServlet();
    hashTool = new HashTool();
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
    servlet.seteD(evDao);
    servlet.setgD(gDao);
    servlet.setpD(pDao);
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");

    // mariello
    ArrayList<EventoBean> list = new ArrayList<>();
    ArrayList<String> liststr = new ArrayList<>();
    EventoBean bean = new EventoBean();
    bean.setNome("evento12");
    bean.setDescrizione("mitico evento");
    bean.setStruttura("playk");
    bean.setData(Date.valueOf("2023-01-03"));
    bean.setOra(2);
    bean.setGestore("gino@gino.it");
    bean.setOrganizzatore("simone@simone.it");
    bean.setStato("richiesta");
    bean.setValutazione(0);
    bean.setNumPartecipanti(0);

    EventoBean g3 = new EventoBean();
    EventoBean g4 = new EventoBean();

    list.add(bean);
    liststr.add(bean.toString());

    g4.setNome("evento2");
    g4.setDescrizione("grande evento");
    g4.setStruttura("playk");
    g4.setData(Date.valueOf("2022-01-03"));
    g4.setOra(2);
    g4.setGestore("gino@gino.it");
    g4.setOrganizzatore("simone@simone.it");
    g4.setStato("richiesta");
    g4.setValutazione(0);
    g4.setNumPartecipanti(0);

    g3.setNome("evento3");
    g3.setDescrizione("sdfghgfds");
    g3.setStruttura("playk");
    g3.setData(Date.valueOf("2022-01-15"));
    g3.setOra(1);
    g3.setGestore("gino@gino.it");
    g3.setOrganizzatore("simone@simone.it");
    g3.setStato("richiesta");
    g3.setValutazione(0);
    g3.setNumPartecipanti(0);

    list.add(g3);
    liststr.add(g3.toString());
    list.add(g4);
    liststr.add(g4.toString());

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
    servlet.seteD(evDao);
    servlet.setgD(gDao);
    servlet.setpD(pDao);
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");
    GiocatoreBean bg;
    bg = new GiocatoreBean();
    bg.setUsername("simone45");
    bg.setEmail("simon@simon.it");
    bg.setNome("Simone");
    bg.setCognome("Graziano");
    bg.setEncPassword(hashTool.hashSHA256("simone"));
    bg.setTelefono("3324561273");
    bg.setDataNascita(Date.valueOf("2000-05-09"));
    bg.setNazioneResidenza("Italia");
    bg.setProvinciaResidenza("Napoli");
    bg.setCittaResidenza("Napoli");
    bg.setCapResidenza("80000");
    bg.setValutazione(0);
    ArrayList<GiocatoreBean> ga = new ArrayList<>();
    ga.add(bg);

    EventoBean bean = new EventoBean();
    bean.setNome("evento12");
    bean.setDescrizione("mitico evento");
    bean.setStruttura("playk");
    bean.setData(Date.valueOf("2023-01-03"));
    bean.setOra(2);
    bean.setGestore("gino@gino.it");
    bean.setOrganizzatore("simone@simone.it");
    bean.setStato("richiesta");
    bean.setValutazione(0);
    bean.setNumPartecipanti(0);

    PartecipazioneBean p = new PartecipazioneBean();
    p.setEvento(bean.getNome());
    p.setUtente(bean.getOrganizzatore());

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("nome")).thenReturn(bean.getNome());
    when(req.getParameter("action")).thenReturn("addE");
    when(gDao.doRetrieveAll()).thenReturn(ga);
    when(evDao.doRetrieveByKey(bean.getNome())).thenReturn(bean);
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
    servlet.seteD(evDao);
    servlet.setgD(gDao);
    servlet.setpD(pDao);
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");

    EventoBean bean = new EventoBean();
    bean.setNome("evento12");
    bean.setDescrizione("mitico evento");
    bean.setStruttura("playk");
    bean.setData(Date.valueOf("2023-01-03"));
    bean.setOra(2);
    bean.setGestore("gino@gino.it");
    bean.setOrganizzatore("simone@simone.it");
    bean.setStato("richiesta");
    bean.setValutazione(0);
    bean.setNumPartecipanti(0);

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
    GestoreBean g = new GestoreBean();
    g.setEmail("gino@gino.it");
    g.setNome("gino");
    g.setCognome("pozzo");
    g.setEncPassword(hashTool.hashSHA256("gino"));
    g.setTelefono("3923415443");
    g.setStruttura("playk");

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(g);
    when(req.getParameter("action")).thenReturn(null);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }
}
