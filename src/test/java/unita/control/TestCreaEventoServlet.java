package unita.control;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.evento.CreaEventoServlet;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.struttura.StrutturaBean;
import model.struttura.StrutturaDAO;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// TODO: Auto-generated Javadoc
/**
 * The Class TestCreaEventoServlet.
 */
public class TestCreaEventoServlet {

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

  /** The e dao. */
  @Mock
  EventoDAO eDao = new EventoDAO();

  /** The ges dao. */
  @Mock
  GestoreDAO gesDao = new GestoreDAO();

  /** The s dao. */
  @Mock
  StrutturaDAO sDao = new StrutturaDAO();

  /** The servlet. */
  private CreaEventoServlet servlet;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new CreaEventoServlet();
    servlet.eD = eDao;
    servlet.sD = sDao;
    servlet.gD = gesDao;
    when(req.getSession()).thenReturn(session);
  }

  /**
   * Crea evento.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void creaEvento() throws ServletException, IOException {

    GestoreBean gi = new GestoreBean();
    gi.setEmail("gino@gino.it");
    gi.setNome("gino");
    gi.setCognome("pozzo");
    gi.setPassword("gino");
    gi.setTelefono("3923415443");
    gi.setStruttura("playk");

    EventoBean e = new EventoBean();
    e.setNome("newEvento");
    e.setDescrizione("Prova descrizione");
    e.setStruttura("playk");
    e.setData(Date.valueOf("2022-01-29"));
    e.setOra(22);
    e.setGestore("gino@gino.it");
    e.setOrganizzatore("simone@simone.it");
    e.setStato("richiesta");
    e.setValutazione(0);
    e.setNumPartecipanti(0);

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("gio");
    g.setEmail("gio4@email.it");
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
    session.setAttribute("giocatore", g);
    when(req.getParameter("nome")).thenReturn("newEvento");
    when(req.getParameter("descrizione")).thenReturn("Prova descrizione");
    when(req.getParameter("struttura")).thenReturn("playk");
    when(req.getParameter("data")).thenReturn("2022-01-29");
    when(req.getParameter("ora")).thenReturn("17");
    when((GiocatoreBean) req.getSession().getAttribute("giocatore")).thenReturn(g);

    StrutturaBean s = new StrutturaBean();
    s.setNome("playk");
    s.setIndirizzo("via andrea 21");
    s.setNazione("italia");
    s.setCitta("napoli");
    s.setCap("80098");
    s.setProvincia("napoli");
    s.setTelefono("3122122143");

    when(gesDao.doRetrieveByStruttura(ArgumentMatchers.anyString())).thenReturn(gi);
    when(req.getParameter("giocatore")).thenReturn(g.getEmail());
    when(eDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null);
    when(sDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(s);

    when(req.getRequestDispatcher(res.encodeRedirectURL("./PartecipaEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Errore evento struttura.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void erroreEventoStruttura() throws ServletException, IOException {
    GestoreBean gi = new GestoreBean();
    gi.setEmail("gino@gino.it");
    gi.setNome("gino");
    gi.setCognome("pozzo");
    gi.setPassword("gino");
    gi.setTelefono("3923415443");
    gi.setStruttura("playk");

    EventoBean e = new EventoBean();
    e.setNome("newEvento");
    e.setDescrizione("Prova descrizione");
    e.setStruttura("playk");
    e.setData(Date.valueOf("2022-01-29"));
    e.setOra(22);
    e.setGestore("gino@gino.it");
    e.setOrganizzatore("simone@simone.it");
    e.setStato("richiesta");
    e.setValutazione(0);
    e.setNumPartecipanti(0);

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("gio");
    g.setEmail("gio4@email.it");
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

    StrutturaBean s = new StrutturaBean();
    s.setNome("playk");
    s.setIndirizzo("via andrea 21");
    s.setNazione("italia");
    s.setCitta("napoli");
    s.setCap("80098");
    s.setProvincia("napoli");
    s.setTelefono("3122122143");

    when(req.getParameter("giocatore")).thenReturn(g.getEmail());
    when(gesDao.doRetrieveByStruttura(ArgumentMatchers.anyString())).thenReturn(gi);
    when(eDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(e);
    when(sDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null);
    when(req.getParameter("nome")).thenReturn("newEvento");
    when(req.getParameter("descrizione")).thenReturn("Prova descrizione");
    when(req.getParameter("struttura")).thenReturn("playk");
    when(req.getParameter("data")).thenReturn("2022-01-29");
    when(req.getParameter("ora")).thenReturn("17");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./CreaEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Errore evento.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void erroreEvento() throws ServletException, IOException {
    GestoreBean gi = new GestoreBean();
    gi.setEmail("gino@gino.it");
    gi.setNome("gino");
    gi.setCognome("pozzo");
    gi.setPassword("gino");
    gi.setTelefono("3923415443");
    gi.setStruttura("playk");

    EventoBean e = new EventoBean();
    e.setNome("newEvento");
    e.setDescrizione("Prova descrizione");
    e.setStruttura("playk");
    e.setData(Date.valueOf("2022-01-29"));
    e.setOra(22);
    e.setGestore("gino@gino.it");
    e.setOrganizzatore("simone@simone.it");
    e.setStato("richiesta");
    e.setValutazione(0);
    e.setNumPartecipanti(0);

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("gio");
    g.setEmail("gio4@email.it");
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

    StrutturaBean s = new StrutturaBean();
    s.setNome("playk");
    s.setIndirizzo("via andrea 21");
    s.setNazione("italia");
    s.setCitta("napoli");
    s.setCap("80098");
    s.setProvincia("napoli");
    s.setTelefono("3122122143");

    when(req.getParameter("giocatore")).thenReturn(g.getEmail());
    when(gesDao.doRetrieveByStruttura(ArgumentMatchers.anyString())).thenReturn(gi);
    when(eDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(e);
    when(sDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(s);
    when(req.getParameter("nome")).thenReturn("newEvento");
    when(req.getParameter("descrizione")).thenReturn("Prova descrizione");
    when(req.getParameter("struttura")).thenReturn("playk");
    when(req.getParameter("data")).thenReturn("2022-01-29");
    when(req.getParameter("ora")).thenReturn("17");
    when(req.getRequestDispatcher(res.encodeRedirectURL("./CreaEventi.jsp"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
  }

  /**
   * Errore struttura.
   *
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   */
  @Test
  public void erroreStruttura() throws ServletException, IOException {
    GestoreBean gi = new GestoreBean();
    gi.setEmail("gino@gino.it");
    gi.setNome("gino");
    gi.setCognome("pozzo");
    gi.setPassword("gino");
    gi.setTelefono("3923415443");
    gi.setStruttura("playk");

    EventoBean e = new EventoBean();
    e.setNome("newEvento");
    e.setDescrizione("Prova descrizione");
    e.setStruttura("playk");
    e.setData(Date.valueOf("2022-01-29"));
    e.setOra(22);
    e.setGestore("gino@gino.it");
    e.setOrganizzatore("simone@simone.it");
    e.setStato("richiesta");
    e.setValutazione(0);
    e.setNumPartecipanti(0);

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("gio");
    g.setEmail("gio4@email.it");
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

    StrutturaBean s = new StrutturaBean();
    s.setNome("playk");
    s.setIndirizzo("via andrea 21");
    s.setNazione("italia");
    s.setCitta("napoli");
    s.setCap("80098");
    s.setProvincia("napoli");
    s.setTelefono("3122122143");

    when(req.getParameter("nome")).thenReturn("newEvento");
    when(req.getParameter("descrizione")).thenReturn("Prova descrizione");
    when(req.getParameter("struttura")).thenReturn("playk");
    when(req.getParameter("data")).thenReturn("2022-01-29");
    when(req.getParameter("ora")).thenReturn("17");

    when(gesDao.doRetrieveByStruttura(ArgumentMatchers.anyString())).thenReturn(gi);
    when(eDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null);
    when(sDao.doRetrieveByKey(ArgumentMatchers.anyString())).thenReturn(null);

    when(req.getRequestDispatcher(res.encodeRedirectURL("struttura"))).thenReturn(rd);
    servlet.doGet(req, res);
    verify(rd).forward(req, res);
  }
}
