package integrazione.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.evento.PartecipaEventiServlet;
import java.io.IOException;
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
import model.partecipazione.PartecipazioneSquadraBean;
import model.partecipazione.PartecipazioneSquadraDAO;
import model.squadra.SquadraBean;
import model.utente.giocatore.GiocatoreBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.HashTool;

/**
 * The Class TestIntegrazionePartecipaEventi.
 */
public class TestIntegrazionePartecipaEventi {

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
  private PartecipaEventiServlet servlet;
  private HashTool hashTool;
  private SquadraBean squadra;
  private GiocatoreBean g;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);

    servlet = new PartecipaEventiServlet();
    hashTool = new HashTool();
    when(req.getSession()).thenReturn(session);

    g = new GiocatoreBean();
    g.setUsername("mario");
    g.setEmail("mario@mario.it");
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
    g.setIdSquadra(3);

    squadra = new SquadraBean();
    squadra.setIdSquadra(3);
    squadra.setNome("Lyons");
    squadra.setNomeAbbreviato("lyo");
    squadra.setDescrizione("Lorem ipsum lorem ipsum lorem ipsum lorem ipsum.");
    squadra.setCitta("Napoli");
    squadra.setLogo(3);
    squadra.setCapitano("mario@mario.it");
  }

  @After
  public void tearDown() throws Exception {
    PartecipazioneDAO pd = new PartecipazioneDAO();
    PartecipazioneSquadraDAO psd = new PartecipazioneSquadraDAO();
    EventoDAO evd = new EventoDAO();
    PartecipazioneSquadraBean ps = new PartecipazioneSquadraBean();

    ps.setIdEvento("evento-squadra-2");
    ps.setIdSquadra(3);

    pd.doDelete("mario@mario.it", "evento3");
    psd.doDelete(ps);

    EventoBean eb = evd.doRetrieveByKey("evento3");
    eb.setNumPartecipanti(3);
    eb.setValutazione(0);
    evd.doUpdate(eb);

    EventoBean evSquadra = evd.doRetrieveByKey("evento-squadra-2");
    evSquadra.setNumPartecipanti(1);
    evSquadra.setValutazione(0);
    evd.doUpdate(evSquadra);
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
    ArrayList<EventoBean> list = new ArrayList<>();
    ArrayList<String> listr = new ArrayList<>();
    EventoBean ev = new EventoBean();
    ev.setCode("evento3");
    ev.setNome("evento3");
    ev.setDescrizione("sdfghgfds");
    ev.setStruttura("playk");
    ev.setData(Date.valueOf("2022-01-15"));
    ev.setOra(1);
    ev.setGestore("gino@gino.it");
    ev.setOrganizzatore("simone@simone.it");
    ev.setStato("attivo");
    ev.setValutazione(0);
    ev.setNumPartecipanti(3);
    ev.setTipologia("libero");

    list.add(ev);
    listr.add(ev.toString());

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);
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
   * Partecipa evento.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void partecipaEvento() throws ServletException, IOException, SQLException {
    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);
    when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(squadra);
    when(req.getParameter("code")).thenReturn("evento3");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);

    servlet.doPost(req, res);

    verify(rd).forward(req, res);
  }

  @Test
  public void partecipaEventoSquadra() throws ServletException, IOException, SQLException {
    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);
    when((SquadraBean) req.getSession().getAttribute("squadra")).thenReturn(squadra);
    when(req.getParameter("code")).thenReturn("evento-squadra-2");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);

    servlet.doPost(req, res);

    verify(rd).forward(req, res);
  }
}
