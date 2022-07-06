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
    servlet.seteD(evDao);
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
    when(evDao.doRetrieveEventiRecenti(g.getEmail())).thenReturn(list);
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

}
