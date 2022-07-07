package unita.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.evento.PartecipaEventiServlet;
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
import model.squadra.SquadraBean;
import model.utente.giocatore.GiocatoreBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.HashTool;

// TODO: Auto-generated Javadoc
/**
 * The Class TestPartecipaEventiServlet.
 */
public class TestPartecipaEventiServlet {

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

  /** The p dao. */
  @Mock
  PartecipazioneDAO pDao = new PartecipazioneDAO();

  /** The servlet. */
  private PartecipaEventiServlet servlet;
  private HashTool hashTool;

  private SquadraBean squadra;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);

    servlet = new PartecipaEventiServlet();
    servlet.seteD(evDao);
    servlet.setpD(pDao);

    hashTool = new HashTool();
    when(req.getSession()).thenReturn(session);

    squadra = new SquadraBean();
    squadra.setIdSquadra(2);
    squadra.setNome("tigers");
    squadra.setNomeAbbreviato("tig");
    squadra.setDescrizione("Lorem ipsum lorem ipsum lorem ipsum lorem ipsum.");
    squadra.setCitta("Salerno");
    squadra.setLogo(2);
    squadra.setCapitano("gio4@email.it");
  }

  /**
   * Cerca eventi.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void cercaEventi() throws ServletException, IOException, SQLException {
    servlet.seteD(evDao);

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("pierox");
    g.setEmail("piero@piero.it");
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
    // mariono test

    ArrayList<EventoBean> list = new ArrayList<>();
    ArrayList<String> listr = new ArrayList<>();
    EventoBean g3 = new EventoBean();

    g3.setNome("evento3");
    g3.setDescrizione("sdfghgfds");
    g3.setStruttura("playk");
    g3.setData(Date.valueOf("2022-01-15"));
    g3.setOra(1);
    g3.setGestore("gino@gino.it");
    g3.setOrganizzatore("simone@simone.it");
    g3.setStato("attivo");
    g3.setValutazione(0);
    g3.setNumPartecipanti(3);
    list.add(g3);
    listr.add(g3.toString());

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);
    when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(squadra);
    when(evDao.doRetrieveEventi(g.getEmail())).thenReturn(list);
    when(req.getAttribute("eventi")).thenReturn(list);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
    ArrayList<EventoBean> cev = new ArrayList<>();
    ArrayList<String> stcev = new ArrayList<>();
    cev = (ArrayList<EventoBean>) req.getAttribute("eventi");
    for (EventoBean e : cev) {
      stcev.add(e.toString());
    }

    assertEquals(stcev, listr);

  }

  /**
   * No testing do get.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void noTestingDoGet() throws ServletException, IOException, SQLException {

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("pierox");
    g.setEmail("piero@piero.it");
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

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

  /**
   * Partecipa evento.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void partecipaEvento() throws ServletException, IOException, SQLException {

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("pierox");
    g.setEmail("piero@piero.it");
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

    EventoBean ev = new EventoBean();
    ev.setNome("evento3");
    ev.setDescrizione("sdfghgfds");
    ev.setStruttura("playk");
    ev.setData(Date.valueOf("2022-01-15"));
    ev.setOra(1);
    ev.setGestore("gino@gino.it");
    ev.setOrganizzatore("simone@simone.it");
    ev.setStato("attivo");
    ev.setValutazione(0);
    ev.setNumPartecipanti(4);
    ev.setTipologia("libero");

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);
    when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(squadra);
    when(req.getParameter("nome")).thenReturn("evento3");
    when(evDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(ev);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);

    servlet.doPost(req, res);

    verify(rd).forward(req, res);
  }

  /**
   * Partecipa evento completo.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void partecipaEventoCompleto() throws ServletException, IOException, SQLException {

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("pierox");
    g.setEmail("piero@piero.it");
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

    EventoBean ev = new EventoBean();
    ev.setNome("evento3");
    ev.setDescrizione("sdfghgfds");
    ev.setStruttura("playk");
    ev.setData(Date.valueOf("2022-01-15"));
    ev.setOra(1);
    ev.setGestore("gino@gino.it");
    ev.setOrganizzatore("simone@simone.it");
    ev.setStato("attivo");
    ev.setValutazione(0);
    ev.setNumPartecipanti(10);
    ev.setTipologia("libero");

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);
    when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(squadra);
    when(req.getParameter("nome")).thenReturn("evento3");
    when(evDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(ev);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);

    servlet.doPost(req, res);

    verify(rd).forward(req, res);
  }

}
