package unita.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.evento.CronologiaEventiServlet;
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
import model.utente.gestore.GestoreBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.HashTool;

// TODO: Auto-generated Javadoc
/**
 * The Class TestCronologiaEventiServlet.
 */
public class TestCronologiaEventiServlet {

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

  /** The ev dao. */
  @Mock
  EventoDAO evDao = new EventoDAO();

  /** The servlet. */
  private CronologiaEventiServlet servlet;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);
    hashTool = new HashTool();
    servlet = new CronologiaEventiServlet();
    when(req.getSession()).thenReturn(session);

  }

  /**
   * Cerca cronologia gestore.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void cercaCronologiaGestore() throws ServletException, IOException, SQLException {
    servlet.edao = evDao;
    GestoreBean gi = new GestoreBean();
    gi.setEmail("gino@gino.it");
    gi.setNome("gino");
    gi.setCognome("pozzo");
    gi.setEncPassword(hashTool.hashSHA256("gino"));
    gi.setTelefono("3923415443");
    gi.setStruttura("playk");

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

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(gi);
    when(evDao.doRetrieveEventiGestore(gi.getEmail())).thenReturn(list);
    when(req.getAttribute("eventi")).thenReturn(list);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./CronologiaEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
    ArrayList<EventoBean> cev = new ArrayList<>();
    ArrayList<String> stcev = new ArrayList<>();
    cev = (ArrayList<EventoBean>) req.getAttribute("eventi");
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
    GestoreBean gi = new GestoreBean();
    gi.setEmail("gino@gino.it");
    gi.setNome("gino");
    gi.setCognome("pozzo");
    gi.setEncPassword(hashTool.hashSHA256("gino"));
    gi.setTelefono("3923415443");
    gi.setStruttura("playk");

    when((GestoreBean) req.getSession().getAttribute("gestore")).thenReturn(gi);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./CronologiaEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

  /**
   * Do post.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  /*@Test
  public void doPost() throws ServletException, IOException, SQLException {

	  // when(req.getRequestDispatcher(res.encodeRedirectURL("./CronologiaEventi.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    //verify(rd).forward(req, res);

  }*/

}
