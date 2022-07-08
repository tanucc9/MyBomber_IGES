package unita.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.evento.EventiRecentiServlet;
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
import model.utente.giocatore.GiocatoreBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.HashTool;

// TODO: Auto-generated Javadoc
/**
 * The Class TestEventiRecentiServlet.
 */
public class TestEventiRecentiServlet {

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

  /** The servlet. */
  private EventiRecentiServlet servlet;
  private HashTool hashTool;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);
    servlet = new EventiRecentiServlet();
    hashTool = new HashTool();
    when(req.getSession()).thenReturn(session);
    servlet.seteD(evDao);
  }

  /**
   * Cerca eventi recenti.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void cercaEventiRecenti() throws ServletException, IOException, SQLException {
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
    g.setIdSquadra(0);

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
    bean.setStato("completato");
    bean.setValutazione(0);
    bean.setNumPartecipanti(10);
    bean.setTipologia("libero");

    EventoBean evento2 = new EventoBean();
    EventoBean evento3 = new EventoBean();

    list.add(bean);
    liststr.add(bean.toString());

    evento3.setNome("evento2");
    evento3.setDescrizione("grande evento");
    evento3.setStruttura("playk");
    evento3.setData(Date.valueOf("2022-01-03"));
    evento3.setOra(2);
    evento3.setGestore("gino@gino.it");
    evento3.setOrganizzatore("simone@simone.it");
    evento3.setStato("completato");
    evento3.setValutazione(0);
    evento3.setNumPartecipanti(10);
    evento3.setTipologia("libero");

    evento2.setNome("evento3");
    evento2.setDescrizione("sdfghgfds");
    evento2.setStruttura("playk");
    evento2.setData(Date.valueOf("2022-01-15"));
    evento2.setOra(1);
    evento2.setGestore("gino@gino.it");
    evento2.setOrganizzatore("simone@simone.it");
    evento2.setStato("attivo");
    evento2.setValutazione(0);
    evento2.setNumPartecipanti(3);
    evento2.setTipologia("libero");

    list.add(evento2);
    liststr.add(evento2.toString());
    list.add(evento3);
    liststr.add(evento3.toString());

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);
    when(evDao.doRetrieveEventiRecenti(ArgumentMatchers.anyString())).thenReturn(list);
    when(req.getAttribute("eventiRecenti")).thenReturn(list);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./EventiRecenti.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
    ArrayList<EventoBean> cev = new ArrayList<>();
    ArrayList<String> stcev = new ArrayList<>();
    cev = (ArrayList<EventoBean>) req.getAttribute("eventiRecenti");
    for (EventoBean e : cev) {
      stcev.add(e.toString());
    }

    assertEquals(stcev, liststr);
  }

  @Test
  public void cercaEventiRecentiSquadra() throws ServletException, IOException, SQLException {
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

    // mariello
    ArrayList<EventoBean> eventiRecenti = new ArrayList<>();
    ArrayList<EventoBean> eventiRecentiSquadra = new ArrayList<>();

    EventoBean evento = new EventoBean();
    EventoBean evento2 = new EventoBean();
    EventoBean evento3 = new EventoBean();
    EventoBean evSquadra = new EventoBean();

    evento.setNome("evento12");
    evento.setDescrizione("mitico evento");
    evento.setStruttura("playk");
    evento.setData(Date.valueOf("2023-01-03"));
    evento.setOra(2);
    evento.setGestore("gino@gino.it");
    evento.setOrganizzatore("simone@simone.it");
    evento.setStato("completato");
    evento.setValutazione(0);
    evento.setNumPartecipanti(10);
    evento.setTipologia("libero");

    evento3.setNome("evento2");
    evento3.setDescrizione("grande evento");
    evento3.setStruttura("playk");
    evento3.setData(Date.valueOf("2022-01-03"));
    evento3.setOra(2);
    evento3.setGestore("gino@gino.it");
    evento3.setOrganizzatore("simone@simone.it");
    evento3.setStato("completato");
    evento3.setValutazione(0);
    evento3.setNumPartecipanti(10);
    evento3.setTipologia("libero");

    evento2.setNome("evento3");
    evento2.setDescrizione("sdfghgfds");
    evento2.setStruttura("playk");
    evento2.setData(Date.valueOf("2022-01-15"));
    evento2.setOra(1);
    evento2.setGestore("gino@gino.it");
    evento2.setOrganizzatore("simone@simone.it");
    evento2.setStato("attivo");
    evento2.setValutazione(0);
    evento2.setNumPartecipanti(3);
    evento2.setTipologia("libero");

    eventiRecenti.add(evento);
    eventiRecenti.add(evento2);
    eventiRecenti.add(evento3);

    evSquadra.setNome("evento squadra");
    evSquadra.setDescrizione("descrizione.");
    evSquadra.setStruttura("playk");
    evSquadra.setData(Date.valueOf("2022-01-15"));
    evSquadra.setOra(22);
    evSquadra.setGestore("gino@gino.it");
    evSquadra.setOrganizzatore("gio4@email.it");
    evSquadra.setStato("completato");
    evSquadra.setValutazione(0);
    evSquadra.setNumPartecipanti(2);
    evSquadra.setTipologia("squadra");

    eventiRecentiSquadra.add(evSquadra);

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);
    when(evDao.doRetrieveEventiRecenti(ArgumentMatchers.anyString())).thenReturn(eventiRecenti);
    when(evDao.doRetrieveEventiRecentiSquadra(ArgumentMatchers.anyInt())).thenReturn(eventiRecentiSquadra);
    when(req.getAttribute("eventiRecenti")).thenReturn(eventiRecenti);
    when(req.getAttribute("eventiRecentiSquadra")).thenReturn(eventiRecentiSquadra);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./EventiRecenti.jsp"))).thenReturn(rd);

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
    ArrayList<EventoBean> list = new ArrayList<>();
    ArrayList<String> liststr = new ArrayList<>();
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

    EventoBean bean = new EventoBean();
    bean.setNome("evento12");
    bean.setDescrizione("mitico evento");
    bean.setStruttura("playk");
    bean.setData(Date.valueOf("2023-01-03"));
    bean.setOra(2);
    bean.setGestore("gino@gino.it");
    bean.setOrganizzatore("simone@simone.it");
    bean.setStato("completato");
    bean.setValutazione(0);
    bean.setNumPartecipanti(10);

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
    g4.setStato("completato");
    g4.setValutazione(0);
    g4.setNumPartecipanti(10);

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
    liststr.add(g3.toString());
    list.add(g4);
    liststr.add(g4.toString());

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);

    when(req.getAttribute("eventiRecenti")).thenReturn(list);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./EventiRecenti.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

  @Test
  public void giocatoreNotLogged() throws ServletException, IOException {
    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(null);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./Login.jsp"))).thenReturn(rd);

    servlet.doGet(req, res);

    verify(rd).forward(req, res);
  }
}
