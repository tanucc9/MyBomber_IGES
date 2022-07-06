package control.evento;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log.LoggerSingleton;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.partecipazione.PartecipazioneBean;
import model.partecipazione.PartecipazioneDAO;
import model.partecipazione.PartecipazioneSquadraBean;
import model.partecipazione.PartecipazioneSquadraDAO;
import model.squadra.SquadraBean;
import model.squadra.SquadraDAO;
import model.utente.gestore.GestoreBean;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

/**
 * Servlet implementation class EsempioServlet.
 */
@WebServlet("/richieste")
public class RichiesteEventiServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The e D. */
  public EventoDAO eD;

  /** The g D. */
  public GiocatoreDAO gD;

  /** The p D. */
  public PartecipazioneDAO pD;

  private PartecipazioneSquadraDAO partecipazioneSquadraDao;

  /**
   * Do get.
   *
   * @param request  the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    EventoDAO eventoDao;
    GiocatoreDAO giocatoreDao;
    PartecipazioneDAO partecipazioneDao;
    ArrayList<GiocatoreBean> giocatori = new ArrayList<>();
    GestoreBean gestore = (GestoreBean) request.getSession().getAttribute("gestore");
    if (gestore == null) {
      RequestDispatcher dispatcher = request
              .getRequestDispatcher(response.encodeRedirectURL("./Login.jsp"));
      dispatcher.forward(request, response);
    }
    String nomeEvento = request.getParameter("nome");
    String action = request.getParameter("action");
    ArrayList<EventoBean> richieste = new ArrayList<>();

    try {
      if (eD == null) {
        eventoDao = new EventoDAO();
      } else {
        eventoDao = eD;
      }

      if (pD == null) {
        partecipazioneDao = new PartecipazioneDAO();
      } else {
        partecipazioneDao = pD;
      }

      if (gD == null) {
        giocatoreDao = new GiocatoreDAO();
      } else {
        giocatoreDao = gD;
      }

      EventoBean bean = eventoDao.doRetrieveByKey(nomeEvento);
      PartecipazioneBean partecipazione = new PartecipazioneBean();
      if (action != null) {
        if (action.equalsIgnoreCase("addE")) {
          bean.setStato("attivo");
          bean.aggiungiG();

          giocatori = giocatoreDao.doRetrieveAll();
          GiocatoreBean giocatore = null;

          for (GiocatoreBean element : giocatori) {
            if (bean.getOrganizzatore().equals(element.getEmail())) {
              giocatore = element;
            }
          }

          if (bean.getTipologia().equals("libero")) {
            partecipazione.setEvento(bean.getNome());
            if (giocatore != null) {
              partecipazione.setUtente(bean.getOrganizzatore());
              partecipazioneDao.doSave(partecipazione);
            }
          } else {
            LoggerSingleton log = LoggerSingleton.getInstance();
            if (this.partecipazioneSquadraDao == null) {
              this.partecipazioneSquadraDao = new PartecipazioneSquadraDAO();
            }

            log.debug(giocatore.toString());

            PartecipazioneSquadraBean partecipazioneSquadra = new PartecipazioneSquadraBean();
            partecipazioneSquadra.setIdSquadra(giocatore.getIdSquadra());
            partecipazioneSquadra.setIdEvento(bean.getNome());
            log.debug(partecipazioneSquadra.toString());
            this.partecipazioneSquadraDao.doSave(partecipazioneSquadra);
          }
          eventoDao.doUpdate(bean);
        } else if (action.equalsIgnoreCase("deleteE")) {
          eventoDao.doDelete(nomeEvento);
        } else if (action.equalsIgnoreCase("trovarichieste")) {
          richieste = eventoDao.doRetrieveRichieste(gestore.getEmail());
          request.setAttribute("richieste", richieste);
        }
      }

    } catch (SQLException e) {
      System.out.println("Error:" + e.getMessage());
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./RichiesteEventi.jsp"));
    dispatcher.forward(request, response);

  }

  /**
   * Do post.
   *
   * @param request  the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}