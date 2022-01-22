package unita.control;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.utente.RegistrazioneServlet;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.struttura.StrutturaBean;
import model.struttura.StrutturaDAO;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRegistrazioneServlet.
 */
public class TestRegistrazioneServlet {

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

  /** The gio dao. */
  @Mock
  GiocatoreDAO gioDao = new GiocatoreDAO();

  /** The ges dao. */
  @Mock
  GestoreDAO gesDao = new GestoreDAO();

  /** The s dao. */
  @Mock
  StrutturaDAO sDao = new StrutturaDAO();

  /** The servlet. */
  private RegistrazioneServlet servlet;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new RegistrazioneServlet();
    servlet.gdt = gioDao;
    servlet.sdt = sDao;
    servlet.gedt = gesDao;
    when(req.getSession()).thenReturn(session);
  }

  /**
   * Registrazione giocatore ok.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @throws SQLException     the SQL exception
   */
  @Test
  public void registrazioneGiocatoreOk() throws ServletException, IOException, SQLException {
    when(req.getParameter("cf")).thenReturn("giocatore");

    when(req.getParameter("nome")).thenReturn("pino");
    when(req.getParameter("cognome")).thenReturn("pino");
    when(req.getParameter("email")).thenReturn("cpinoi@pino.it");
    when(req.getParameter("username")).thenReturn("ukipino");
    when(req.getParameter("password")).thenReturn("pino");
    when(req.getParameter("nazione")).thenReturn("italia");
    when(req.getParameter("provincia")).thenReturn("napoli");
    when(req.getParameter("citta")).thenReturn("napoli");
    when(req.getParameter("cap")).thenReturn("80050");
    when(req.getParameter("telefono")).thenReturn("5433453443");
    when(req.getParameter("data")).thenReturn("2000-06-06");

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("ukipino");
    g.setEmail("cpinoi@pino.it");
    g.setNome("pino");
    g.setCognome("pino");
    g.setPassword("pino");
    g.setTelefono("5433453443");
    g.setDataNascita(Date.valueOf("2000-06-06"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("napoli");
    g.setCittaResidenza("napoli");
    g.setCapResidenza("80050");
    g.setValutazione(0);

    when(gioDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null).thenReturn(g);
    when(gioDao.doRetrieveByUsername(ArgumentMatchers.anyString())).thenReturn(null);
    when(session.getAttribute("giocatore")).thenReturn(g);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);

    assertEquals(g, session.getAttribute("giocatore"));
  }

  /**
   * Registrazione giocatore no.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void registrazioneGiocatoreNo() throws ServletException, IOException {
    when(req.getParameter("cf")).thenReturn("giocatore");

    when(req.getParameter("nome")).thenReturn("pino");
    when(req.getParameter("cognome")).thenReturn("pino");
    when(req.getParameter("email")).thenReturn("pino@pino.it");
    when(req.getParameter("username")).thenReturn("ukpino");
    when(req.getParameter("password")).thenReturn("pino");
    when(req.getParameter("nazione")).thenReturn("italia");
    when(req.getParameter("provincia")).thenReturn("napoli");
    when(req.getParameter("citta")).thenReturn("napoli");
    when(req.getParameter("cap")).thenReturn("80050");
    when(req.getParameter("telefono")).thenReturn("5433453443");
    when(req.getParameter("data")).thenReturn("2000-06-06");

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("ukpino");
    g.setEmail("pino@pino.it");
    g.setNome("pino");
    g.setCognome("pino");
    g.setPassword("pino");
    g.setTelefono("5433453443");
    g.setDataNascita(Date.valueOf("2000-06-06"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("napoli");
    g.setCittaResidenza("napoli");
    g.setCapResidenza("80050");
    g.setValutazione(0);

    when(gioDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(g);
    when(gioDao.doRetrieveByUsername(ArgumentMatchers.anyString())).thenReturn(g);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Registrazione giocatore errore email.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void registrazioneGiocatoreErroreEmail() throws ServletException, IOException {
    when(req.getParameter("cf")).thenReturn("giocatore");

    when(req.getParameter("nome")).thenReturn("pino");
    when(req.getParameter("cognome")).thenReturn("pino");
    when(req.getParameter("email")).thenReturn("pino@pino.it");
    when(req.getParameter("username")).thenReturn("ukpino");
    when(req.getParameter("password")).thenReturn("pino");
    when(req.getParameter("nazione")).thenReturn("italia");
    when(req.getParameter("provincia")).thenReturn("napoli");
    when(req.getParameter("citta")).thenReturn("napoli");
    when(req.getParameter("cap")).thenReturn("80050");
    when(req.getParameter("telefono")).thenReturn("5433453443");
    when(req.getParameter("data")).thenReturn("2000-06-06");

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("ukpino");
    g.setEmail("pino@pino.it");
    g.setNome("pino");
    g.setCognome("pino");
    g.setPassword("pino");
    g.setTelefono("5433453443");
    g.setDataNascita(Date.valueOf("2000-06-06"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("napoli");
    g.setCittaResidenza("napoli");
    g.setCapResidenza("80050");
    g.setValutazione(0);

    when(gioDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(g);
    when(gioDao.doRetrieveByUsername(ArgumentMatchers.anyString())).thenReturn(null);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Registrazione giocatore errore username.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void registrazioneGiocatoreErroreUsername() throws ServletException, IOException {
    when(req.getParameter("cf")).thenReturn("giocatore");

    when(req.getParameter("nome")).thenReturn("pino");
    when(req.getParameter("cognome")).thenReturn("pino");
    when(req.getParameter("email")).thenReturn("pino@pino.it");
    when(req.getParameter("username")).thenReturn("ukpino");
    when(req.getParameter("password")).thenReturn("pino");
    when(req.getParameter("nazione")).thenReturn("italia");
    when(req.getParameter("provincia")).thenReturn("napoli");
    when(req.getParameter("citta")).thenReturn("napoli");
    when(req.getParameter("cap")).thenReturn("80050");
    when(req.getParameter("telefono")).thenReturn("5433453443");
    when(req.getParameter("data")).thenReturn("2000-06-06");

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("ukpino");
    g.setEmail("pino@pino.it");
    g.setNome("pino");
    g.setCognome("pino");
    g.setPassword("pino");
    g.setTelefono("5433453443");
    g.setDataNascita(Date.valueOf("2000-06-06"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("napoli");
    g.setCittaResidenza("napoli");
    g.setCapResidenza("80050");
    g.setValutazione(0);

    when(gioDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null);
    when(gioDao.doRetrieveByUsername(ArgumentMatchers.anyString())).thenReturn(g);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Registrazione giocatore errore.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void registrazioneGiocatoreErrore() throws ServletException, IOException {
    when(req.getParameter("cf")).thenReturn("giocatore");

    when(req.getParameter("nome")).thenReturn("pino");
    when(req.getParameter("cognome")).thenReturn("pino");
    when(req.getParameter("email")).thenReturn("pino@pino.it");
    when(req.getParameter("username")).thenReturn("ukpino");
    when(req.getParameter("password")).thenReturn("pino");
    when(req.getParameter("nazione")).thenReturn("italia");
    when(req.getParameter("provincia")).thenReturn("napoli");
    when(req.getParameter("citta")).thenReturn("napoli");
    when(req.getParameter("cap")).thenReturn("80050");
    when(req.getParameter("telefono")).thenReturn("5433453443");
    when(req.getParameter("data")).thenReturn("2000-06-06");

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("ukpino");
    g.setEmail("pino@pino.it");
    g.setNome("pino");
    g.setCognome("pino");
    g.setPassword("pino");
    g.setTelefono("5433453443");
    g.setDataNascita(Date.valueOf("2000-06-06"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("napoli");
    g.setCittaResidenza("napoli");
    g.setCapResidenza("80050");
    g.setValutazione(0);

    when(gioDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null).thenReturn(null);
    when(gioDao.doRetrieveByUsername(ArgumentMatchers.anyString())).thenReturn(null);

    when(req.getAttribute("errorReg")).thenReturn("errore");

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    assertEquals("errore", req.getAttribute("errorReg"));
  }

  /**
   * Registrazione gestore ok.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void registrazioneGestoreOk() throws ServletException, IOException {
    when(req.getParameter("cf")).thenReturn("gestore");

    when(req.getParameter("emailG")).thenReturn("tgino@gino.it");
    when(req.getParameter("telefonoGestore")).thenReturn("5433453443");
    when(req.getParameter("nomeG")).thenReturn("gino");
    when(req.getParameter("cognomeG")).thenReturn("rossi");
    when(req.getParameter("strutturaG")).thenReturn("ginol");
    when(req.getParameter("nazioneG")).thenReturn("italia");
    when(req.getParameter("provinciaG")).thenReturn("napoli");
    when(req.getParameter("cittaG")).thenReturn("napoli");
    when(req.getParameter("capG")).thenReturn("80050");
    when(req.getParameter("indirizzoG")).thenReturn("via gino, 22");
    when(req.getParameter("telefonoStruttura")).thenReturn("3213453443");
    when(req.getParameter("passwordG")).thenReturn("tgino");

    GestoreBean g = new GestoreBean();
    StrutturaBean s = new StrutturaBean();

    g.setEmail("tgino@gino.it");
    g.setNome("gino");
    g.setCognome("rossi");
    g.setPassword("tgino");
    g.setTelefono("5433453443");
    g.setStruttura("ginol");

    s.setNome("ginol");
    s.setCap("80050");
    s.setCitta("napoli");
    s.setIndirizzo("via gino, 22");
    s.setNazione("italia");
    s.setProvincia("napoli");
    s.setTelefono("3213453443");
    // mariolone
    when(gesDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null).thenReturn(g);
    when(sDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null).thenReturn(s);

    when(session.getAttribute("gestore")).thenReturn(g);
    when(req.getRequestDispatcher(res.encodeRedirectURL("./RichiesteEventi.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
    assertEquals(g, session.getAttribute("gestore"));
  }

  /**
   * Registrazione gestore no.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void registrazioneGestoreNo() throws ServletException, IOException {
    when(req.getParameter("cf")).thenReturn("gestore");

    when(req.getParameter("emailG")).thenReturn("tgino@gino.it");
    when(req.getParameter("telefonoGestore")).thenReturn("5433453443");
    when(req.getParameter("nomeG")).thenReturn("gino");
    when(req.getParameter("cognomeG")).thenReturn("rossi");
    when(req.getParameter("strutturaG")).thenReturn("ginol");
    when(req.getParameter("nazioneG")).thenReturn("italia");
    when(req.getParameter("provinciaG")).thenReturn("napoli");
    when(req.getParameter("cittaG")).thenReturn("napoli");
    when(req.getParameter("capG")).thenReturn("80050");
    when(req.getParameter("indirizzoG")).thenReturn("via gino, 22");
    when(req.getParameter("telefonoStruttura")).thenReturn("3213453443");
    when(req.getParameter("passwordG")).thenReturn("tgino");

    GestoreBean g = new GestoreBean();
    StrutturaBean s = new StrutturaBean();

    g.setEmail("tgino@gino.it");
    g.setNome("gino");
    g.setCognome("rossi");
    g.setPassword("tgino");
    g.setTelefono("5433453443");
    g.setStruttura("ginol");

    s.setNome("ginol");
    s.setCap("80050");
    s.setCitta("napoli");
    s.setIndirizzo("via gino, 22");
    s.setNazione("italia");
    s.setProvincia("napoli");
    s.setTelefono("3213453443");

    when(gesDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(g);
    when(sDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(s);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Registrazione gestore errore struttura.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void registrazioneGestoreErroreStruttura() throws ServletException, IOException {
    when(req.getParameter("cf")).thenReturn("gestore");

    when(req.getParameter("emailG")).thenReturn("tgino@gino.it");
    when(req.getParameter("telefonoGestore")).thenReturn("5433453443");
    when(req.getParameter("nomeG")).thenReturn("gino");
    when(req.getParameter("cognomeG")).thenReturn("rossi");
    when(req.getParameter("strutturaG")).thenReturn("ginol");
    when(req.getParameter("nazioneG")).thenReturn("italia");
    when(req.getParameter("provinciaG")).thenReturn("napoli");
    when(req.getParameter("cittaG")).thenReturn("napoli");
    when(req.getParameter("capG")).thenReturn("80050");
    when(req.getParameter("indirizzoG")).thenReturn("via gino, 22");
    when(req.getParameter("telefonoStruttura")).thenReturn("3213453443");
    when(req.getParameter("passwordG")).thenReturn("tgino");

    GestoreBean g = new GestoreBean();
    StrutturaBean s = new StrutturaBean();

    g.setEmail("tgino@gino.it");
    g.setNome("gino");
    g.setCognome("rossi");
    g.setPassword("tgino");
    g.setTelefono("5433453443");
    g.setStruttura("ginol");

    s.setNome("ginol");
    s.setCap("80050");
    s.setCitta("napoli");
    s.setIndirizzo("via gino, 22");
    s.setNazione("italia");
    s.setProvincia("napoli");
    s.setTelefono("3213453443");

    when(gesDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null);
    when(sDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(s);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Registrazione gestore errore email.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void registrazioneGestoreErroreEmail() throws ServletException, IOException {
    when(req.getParameter("cf")).thenReturn("gestore");

    when(req.getParameter("emailG")).thenReturn("tgino@gino.it");
    when(req.getParameter("telefonoGestore")).thenReturn("5433453443");
    when(req.getParameter("nomeG")).thenReturn("gino");
    when(req.getParameter("cognomeG")).thenReturn("rossi");
    when(req.getParameter("strutturaG")).thenReturn("ginol");
    when(req.getParameter("nazioneG")).thenReturn("italia");
    when(req.getParameter("provinciaG")).thenReturn("napoli");
    when(req.getParameter("cittaG")).thenReturn("napoli");
    when(req.getParameter("capG")).thenReturn("80050");
    when(req.getParameter("indirizzoG")).thenReturn("via gino, 22");
    when(req.getParameter("telefonoStruttura")).thenReturn("3213453443");
    when(req.getParameter("passwordG")).thenReturn("tgino");

    GestoreBean g = new GestoreBean();
    StrutturaBean s = new StrutturaBean();

    g.setEmail("tgino@gino.it");
    g.setNome("gino");
    g.setCognome("rossi");
    g.setPassword("tgino");
    g.setTelefono("5433453443");
    g.setStruttura("ginol");

    s.setNome("ginol");
    s.setCap("80050");
    s.setCitta("napoli");
    s.setIndirizzo("via gino, 22");
    s.setNazione("italia");
    s.setProvincia("napoli");
    s.setTelefono("3213453443");

    when(gesDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(g);
    when(sDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Registrazione gestore errore.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void registrazioneGestoreErrore() throws ServletException, IOException {
    when(req.getParameter("cf")).thenReturn("gestore");

    when(req.getParameter("emailG")).thenReturn("tgino@gino.it");
    when(req.getParameter("telefonoGestore")).thenReturn("5433453443");
    when(req.getParameter("nomeG")).thenReturn("gino");
    when(req.getParameter("cognomeG")).thenReturn("rossi");
    when(req.getParameter("strutturaG")).thenReturn("ginol");
    when(req.getParameter("nazioneG")).thenReturn("italia");
    when(req.getParameter("provinciaG")).thenReturn("napoli");
    when(req.getParameter("cittaG")).thenReturn("napoli");
    when(req.getParameter("capG")).thenReturn("80050");
    when(req.getParameter("indirizzoG")).thenReturn("via gino, 22");
    when(req.getParameter("telefonoStruttura")).thenReturn("3213453443");
    when(req.getParameter("passwordG")).thenReturn("tgino");

    GestoreBean g = new GestoreBean();
    StrutturaBean s = new StrutturaBean();

    g.setEmail("tgino@gino.it");
    g.setNome("gino");
    g.setCognome("rossi");
    g.setPassword("tgino");
    g.setTelefono("5433453443");
    g.setStruttura("ginol");

    s.setNome("ginol");
    s.setCap("80050");
    s.setCitta("napoli");
    s.setIndirizzo("via gino, 22");
    s.setNazione("italia");
    s.setProvincia("napoli");
    s.setTelefono("3213453443");

    when(gesDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null).thenReturn(null);
    when(sDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null).thenReturn(null);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doPost(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Do get.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void doGet() throws ServletException, IOException {

    when(req.getRequestDispatcher(res.encodeRedirectURL("./Registrazione.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
  }
}
