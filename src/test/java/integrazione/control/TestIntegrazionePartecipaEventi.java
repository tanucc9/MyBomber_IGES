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
import model.utente.giocatore.GiocatoreBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// TODO: Auto-generated Javadoc
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

  /**
   * Sets the up.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new PartecipaEventiServlet();
    when(req.getSession()).thenReturn(session);

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

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("pierox");
    g.setEmail("piero@piero.it");
    g.setNome("Giovanni");
    g.setCognome("Falco");
    g.setPassword("Gio");
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

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("mario");
    g.setEmail("mario@mario.it");
    g.setNome("Giovanni");
    g.setCognome("Falco");
    g.setPassword("Gio");
    g.setTelefono("3334562167");
    g.setDataNascita(Date.valueOf("2001-11-16"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("Caserta");
    g.setCittaResidenza("Caserta");
    g.setCapResidenza("89976");
    g.setValutazione(0);

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
    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);
    when(req.getParameter("nome")).thenReturn("evento3");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);

    // ripulisco il db
    PartecipazioneDAO pd = new PartecipazioneDAO();
    EventoDAO evd = new EventoDAO();
    pd.doDelete("mario@mario.it", "evento3");
    EventoBean eb = evd.doRetrieveByKey("evento3");
    eb.setNumPartecipanti(3);
    eb.setValutazione(0);
    evd.doUpdate(eb);

  }

}
