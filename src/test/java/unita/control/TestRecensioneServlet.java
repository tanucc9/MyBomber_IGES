package unita.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.recensione.RecensioneServlet;
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
import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.HashTool;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRecensioneServlet.
 */
public class TestRecensioneServlet {

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

  /** The rec dao. */
  @Mock
  RecensioneDAO recDao = new RecensioneDAO();

  /** The g dao. */
  @Mock
  GiocatoreDAO gDao = new GiocatoreDAO();

  /** The servlet. */
  private RecensioneServlet servlet;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() throws NoSuchAlgorithmException {
    MockitoAnnotations.openMocks(this);
    hashTool = new HashTool();
    servlet = new RecensioneServlet();
    when(req.getSession()).thenReturn(session);
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

    servlet.rdt = recDao;
    GiocatoreBean g4 = new GiocatoreBean();
    g4.setUsername("pino");
    g4.setEmail("pino@pino.it");
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
    when(req.getParameter("nome")).thenReturn("evento3");

    ArrayList<RecensioneBean> list = new ArrayList<>();
    RecensioneBean r2 = new RecensioneBean();

    r2.setRecensore("pino@pino.it");
    r2.setRecensito("mario@mario.it");
    r2.setEvento("evento3");
    r2.setRecensione(2);
    r2.setDescrizione("scarso");
    list.add(r2);

    r2.setRecensore("pino@pino.it");
    r2.setRecensito("simone@simone.it");
    r2.setEvento("evento3");
    r2.setRecensione(2);
    r2.setDescrizione("scarso");
    list.add(r2);
    when(recDao.doRetrieveDaRecensire(g4.getEmail(), r2.getEvento())).thenReturn(null);
    when(recDao.doRetrieveRecensiti(g4.getEmail(), r2.getEvento())).thenReturn(list);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./DaiRecensione.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

  /**
   * Cerca giocatori 2.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void cercaGiocatori2() throws ServletException, IOException, SQLException {

    servlet.rdt = recDao;
    GiocatoreBean g4 = new GiocatoreBean();
    g4.setUsername("mario");
    g4.setEmail("mario@mario.it");
    g4.setNome("Mario");
    g4.setCognome("Calabrese");
    g4.setEncPassword(hashTool.hashSHA256("mario"));
    g4.setTelefono("3452167543");
    g4.setDataNascita(Date.valueOf("2000-03-03"));
    g4.setNazioneResidenza("Italia");
    g4.setProvinciaResidenza("Avellino");
    g4.setCittaResidenza("Avellino");
    g4.setCapResidenza("80076");
    g4.setValutazione(0);

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g4);
    when(req.getParameter("action")).thenReturn("cercagiocatori");
    when(req.getParameter("nome")).thenReturn("evento3");

    ArrayList<String> list = new ArrayList<>();

    list.add("pino@pino.it");
    list.add("simone@simone.it");

    when(recDao.doRetrieveDaRecensire(g4.getEmail(), "evento3")).thenReturn(list);
    when(recDao.doRetrieveRecensiti(g4.getEmail(), "evento3")).thenReturn(null);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./DaiRecensione.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

  /**
   * Null action.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void nullAction() throws ServletException, IOException, SQLException {

    servlet.rdt = recDao;
    GiocatoreBean g4 = new GiocatoreBean();
    g4.setUsername("mario");
    g4.setEmail("mario@mario.it");
    g4.setNome("Mario");
    g4.setCognome("Calabrese");
    g4.setEncPassword(hashTool.hashSHA256("mario"));
    g4.setTelefono("3452167543");
    g4.setDataNascita(Date.valueOf("2000-03-03"));
    g4.setNazioneResidenza("Italia");
    g4.setProvinciaResidenza("Avellino");
    g4.setCittaResidenza("Avellino");
    g4.setCapResidenza("80076");
    g4.setValutazione(0);

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g4);
    when(req.getParameter("action")).thenReturn(null);
    when(req.getParameter("nome")).thenReturn("evento3");

    ArrayList<String> list = new ArrayList<>();

    list.add("pino@pino.it");
    list.add("simone@simone.it");

    when(recDao.doRetrieveDaRecensire(g4.getEmail(), "evento3")).thenReturn(list);
    when(recDao.doRetrieveRecensiti(g4.getEmail(), "evento3")).thenReturn(null);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./DaiRecensione.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

  /**
   * Altri action.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void altriAction() throws ServletException, IOException, SQLException {

    servlet.rdt = recDao;
    GiocatoreBean g4 = new GiocatoreBean();
    g4.setUsername("mario");
    g4.setEmail("mario@mario.it");
    g4.setNome("Mario");
    g4.setCognome("Calabrese");
    g4.setEncPassword(hashTool.hashSHA256("mario"));
    g4.setTelefono("3452167543");
    g4.setDataNascita(Date.valueOf("2000-03-03"));
    g4.setNazioneResidenza("Italia");
    g4.setProvinciaResidenza("Avellino");
    g4.setCittaResidenza("Avellino");
    g4.setCapResidenza("80076");
    g4.setValutazione(0);

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g4);
    when(req.getParameter("action")).thenReturn("pippo");
    when(req.getParameter("nome")).thenReturn("evento3");

    ArrayList<String> list = new ArrayList<>();

    list.add("pino@pino.it");
    list.add("simone@simone.it");

    when(recDao.doRetrieveDaRecensire(g4.getEmail(), "evento3")).thenReturn(list);
    when(recDao.doRetrieveRecensiti(g4.getEmail(), "evento3")).thenReturn(null);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./DaiRecensione.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);

  }

  /**
   * No test.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void noTest() throws ServletException, IOException, SQLException {

    GiocatoreBean g4 = new GiocatoreBean();
    g4.setUsername("mario");
    g4.setEmail("mario@mario.it");
    g4.setNome("Mario");
    g4.setCognome("Calabrese");
    g4.setEncPassword(hashTool.hashSHA256("mario"));
    g4.setTelefono("3452167543");
    g4.setDataNascita(Date.valueOf("2000-03-03"));
    g4.setNazioneResidenza("Italia");
    g4.setProvinciaResidenza("Avellino");
    g4.setCittaResidenza("Avellino");
    g4.setCapResidenza("80076");
    g4.setValutazione(0);

    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g4);
    when(req.getParameter("action")).thenReturn("pippo");
    when(req.getParameter("nome")).thenReturn("evento3");

    ArrayList<String> list = new ArrayList<>();

    list.add("pino@pino.it");
    list.add("simone@simone.it");
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

    servlet.rdt = recDao;
    servlet.gdt = gDao;
    GiocatoreBean g4 = new GiocatoreBean();
    g4 = new GiocatoreBean();
    g4.setUsername("simone45");
    g4.setEmail("simon@simon.it");
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
    bean.setRecensore("simone@simone.it");
    bean.setRecensito("pino@pino.it");
    bean.setEvento("Evento 333");
    bean.setRecensione(4);

    when(req.getParameter("nomeEvento")).thenReturn(bean.getEvento());
    when(req.getParameter("nomeG")).thenReturn(bean.getRecensito());
    when(req.getParameter("descrizione")).thenReturn(bean.getDescrizione());
    when(req.getParameter("valutazione")).thenReturn("4");

    when(recDao.doRetrieveMedia(bean.getRecensito())).thenReturn(bean.getRecensione());
    GiocatoreBean rs = new GiocatoreBean();
    rs.setUsername("pino");
    rs.setEmail("pino@pino.it");
    rs.setNome("Pino");
    rs.setCognome("Inglese");
    rs.setEncPassword(hashTool.hashSHA256("pino"));
    rs.setTelefono("3665423187");
    rs.setDataNascita(Date.valueOf("2000-09-09"));
    rs.setNazioneResidenza("Italia");
    rs.setProvinciaResidenza("Napoli");
    rs.setCittaResidenza("Napoli");
    rs.setCapResidenza("80000");
    rs.setValutazione(0);

    when(gDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(rs);

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

    servlet.rdt = recDao;
    servlet.gdt = gDao;
    GiocatoreBean g4 = new GiocatoreBean();
    g4 = new GiocatoreBean();
    g4.setUsername("simone45");
    g4.setEmail("simon@simon.it");
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
    bean.setRecensore("simone@simone.it");
    bean.setRecensito("pino@pino.it");
    bean.setEvento("Evento 333");
    bean.setRecensione(4);

    when(req.getParameter("nomeEvento")).thenReturn(bean.getEvento());
    when(req.getParameter("nomeG")).thenReturn(bean.getRecensore());

    when(recDao.doRetrieveMedia(bean.getRecensito())).thenReturn(bean.getRecensione());
    GiocatoreBean rs = new GiocatoreBean();
    rs.setUsername("pino");
    rs.setEmail("pino@pino.it");
    rs.setNome("Pino");
    rs.setCognome("Inglese");
    rs.setEncPassword(hashTool.hashSHA256("pino"));
    rs.setTelefono("3665423187");
    rs.setDataNascita(Date.valueOf("2000-09-09"));
    rs.setNazioneResidenza("Italia");
    rs.setProvinciaResidenza("Napoli");
    rs.setCittaResidenza("Napoli");
    rs.setCapResidenza("80000");
    rs.setValutazione(0);

    when(gDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(rs);

    when(req.getAttribute("deleteok")).thenReturn("deleteok");

    when(req.getRequestDispatcher(res.encodeRedirectURL("./EventiRecenti.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    assertEquals("deleteok", req.getAttribute("deleteok"));
  }

  /**
   * Altri rec.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void altriRec() throws ServletException, IOException, SQLException {

    servlet.rdt = recDao;
    servlet.gdt = gDao;
    GiocatoreBean g4 = new GiocatoreBean();
    g4 = new GiocatoreBean();
    g4.setUsername("simone45");
    g4.setEmail("simon@simon.it");
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

    when(req.getParameter("rec")).thenReturn("altro");
    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g4);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./DaiRecensione.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
  }
}
