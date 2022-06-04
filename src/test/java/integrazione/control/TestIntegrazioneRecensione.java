package integrazione.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.recensione.RecensioneServlet;
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
import model.partecipazione.PartecipazioneBean;
import model.partecipazione.PartecipazioneDAO;
import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;
import model.utente.giocatore.GiocatoreBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.HashTool;

// TODO: Auto-generated Javadoc
/**
 * The Class TestIntegrazioneRecensione.
 */
public class TestIntegrazioneRecensione {

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
  private RecensioneServlet servlet;

  /**
   * Sets the up.
   *
   * @throws SQLException the SQL exception
   */
  @Before
  public void setUp() throws SQLException, NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);
    hashTool = new HashTool();
    servlet = new RecensioneServlet();
    when(req.getSession()).thenReturn(session);

    PartecipazioneDAO tester = new PartecipazioneDAO();
    PartecipazioneBean bean = new PartecipazioneBean();
    bean = new PartecipazioneBean();
    bean.setEvento("evento2");
    bean.setUtente("gio4@email.it");
    tester.doSave(bean);
    bean.setEvento("evento2");
    bean.setUtente("mario@mario.it");
    tester.doSave(bean);
    bean.setEvento("evento2");
    bean.setUtente("pino@pino.it");
    tester.doSave(bean);

    RecensioneDAO rd = new RecensioneDAO();
    RecensioneBean rb = new RecensioneBean();
    rb.setRecensore("gio4@email.it");
    rb.setRecensito("mario@mario.it");
    rb.setEvento("evento2");
    rb.setRecensione(4);
    rd.doSave(rb);

  }

  /**
   * Cerca giocatori.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void cercaGiocatori() throws ServletException, IOException, SQLException {

    GiocatoreBean g4 = new GiocatoreBean();
    g4.setUsername("pino");
    g4.setEmail("gio4@gmail.it");
    g4.setNome("Pino");
    g4.setCognome("Inglese");
    g4.setEncPassword(hashTool.hashSHA256("pino"));
    g4.setTelefono("3665423187");
    g4.setDataNascita(Date.valueOf("2000-09-09"));
    g4.setNazioneResidenza("Italia");
    g4.setProvinciaResidenza("Napoli");
    g4.setCittaResidenza("Napoli");
    g4.setCapResidenza("80000");
    g4.setValutazione(0);

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g4);
    when(req.getParameter("action")).thenReturn("cercagiocatori");
    when(req.getParameter("nome")).thenReturn("evento2");

    when(req.getRequestDispatcher(res.encodeRedirectURL("./DaiRecensione.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

  /**
   * Aggiungi recensione.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void aggiungiRecensione() throws ServletException, IOException, SQLException {

    GiocatoreBean g4 = new GiocatoreBean();
    g4 = new GiocatoreBean();
    g4.setUsername("simone45");
    g4.setEmail("mario@mario.it");
    g4.setNome("Simone");
    g4.setCognome("Graziano");
    g4.setEncPassword(hashTool.hashSHA256("simone"));
    g4.setTelefono("3324561273");
    g4.setDataNascita(Date.valueOf("2000-05-09"));
    g4.setNazioneResidenza("Italia");
    g4.setProvinciaResidenza("Napoli");
    g4.setCittaResidenza("Napoli");
    g4.setCapResidenza("80000");
    g4.setValutazione(0);

    when(req.getParameter("rec")).thenReturn("set");
    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g4);

    RecensioneBean bean = new RecensioneBean();
    bean.setRecensore("mario@mario.it");
    bean.setRecensito("pino@pino.it");
    bean.setEvento("evento2");
    bean.setRecensione(4);

    when(req.getParameter("nomeEvento")).thenReturn(bean.getEvento());
    when(req.getParameter("nomeG")).thenReturn(bean.getRecensito());
    when(req.getParameter("descrizione")).thenReturn(bean.getDescrizione());
    when(req.getParameter("valutazione")).thenReturn("4");

    when(req.getAttribute("saveok")).thenReturn("saveok");

    when(req.getRequestDispatcher(res.encodeRedirectURL("./EventiRecenti.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    assertEquals("saveok", req.getAttribute("saveok"));
  }

  /**
   * Elimina recensione.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void eliminaRecensione() throws ServletException, IOException, SQLException {

    GiocatoreBean g4 = new GiocatoreBean();
    g4 = new GiocatoreBean();
    g4.setUsername("simone45");
    g4.setEmail("mario@mario.it");
    g4.setNome("Simone");
    g4.setCognome("Graziano");
    g4.setEncPassword(hashTool.hashSHA256("simone"));
    g4.setTelefono("3324561273");
    g4.setDataNascita(Date.valueOf("2000-05-09"));
    g4.setNazioneResidenza("Italia");
    g4.setProvinciaResidenza("Napoli");
    g4.setCittaResidenza("Napoli");
    g4.setCapResidenza("80000");
    g4.setValutazione(0);

    when(req.getParameter("rec")).thenReturn("el");
    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g4);

    RecensioneBean bean = new RecensioneBean();
    bean.setRecensore("mario@mario.it");
    bean.setRecensito("pino@pino.it");
    bean.setEvento("evento2");
    bean.setRecensione(4);

    when(req.getParameter("nomeG")).thenReturn(bean.getRecensito());
    when(req.getParameter("nomeEvento")).thenReturn(bean.getEvento());

    when(req.getAttribute("deleteok")).thenReturn("deleteok");

    when(req.getRequestDispatcher(res.encodeRedirectURL("./EventiRecenti.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    assertEquals("deleteok", req.getAttribute("deleteok"));
  }

  /**
   * Tear down.
   *
   * @throws Exception the exception
   */
  @After
  public void tearDown() throws Exception {
    PartecipazioneDAO tester = new PartecipazioneDAO();
    PartecipazioneBean bean = new PartecipazioneBean();
    bean = new PartecipazioneBean();
    bean.setEvento("evento2");
    bean.setUtente("gio4@email.it");
    tester.doDelete(bean.getUtente(), bean.getEvento());
    bean.setEvento("evento2");
    bean.setUtente("mario@mario.it");
    tester.doDelete(bean.getUtente(), bean.getEvento());
    bean.setEvento("evento2");
    bean.setUtente("pino@pino.it");
    tester.doDelete(bean.getUtente(), bean.getEvento());

    RecensioneDAO rd = new RecensioneDAO();
    RecensioneBean rb = new RecensioneBean();
    rb.setRecensore("gio4@email.it");
    rb.setRecensito("mario@mario.it");
    rb.setEvento("evento2");
    rb.setRecensione(4);
    rd.doDelete(rb.getRecensore(), rb.getRecensito(), rb.getEvento());
  }

}
