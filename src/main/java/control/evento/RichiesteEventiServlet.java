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

import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.partecipazione.PartecipazioneBean;
import model.partecipazione.PartecipazioneDAO;
import model.partecipazione.PartecipazioneSquadraBean;
import model.partecipazione.PartecipazioneSquadraDAO;
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
  private EventoDAO eD;

  /** The g D. */
  private GiocatoreDAO gD;

  /** The p D. */
  private PartecipazioneDAO pD;

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

    ArrayList<GiocatoreBean> giocatori = new ArrayList<>();
    GestoreBean gestore = (GestoreBean) request.getSession().getAttribute("gestore");
    if (gestore == null) {
      RequestDispatcher dispatcher = request
              .getRequestDispatcher(response.encodeRedirectURL("./Login.jsp"));
      dispatcher.forward(request, response);
      return;
    }
    String nomeEvento = request.getParameter("nome");
    String action = request.getParameter("action");
    ArrayList<EventoBean> richieste = new ArrayList<>();

    try {
      if (this.eD == null) {
        this.eD = new EventoDAO();
      }

      if (this.pD == null) {
        this.pD = new PartecipazioneDAO();
      }

      if (this.gD == null) {
        this.gD = new GiocatoreDAO();
      }

      EventoBean bean = this.eD.doRetrieveByKey(nomeEvento);
      PartecipazioneBean partecipazione = new PartecipazioneBean();
      if (action != null) {
        if (action.equalsIgnoreCase("addE")) {
          bean.setStato("attivo");
          bean.aggiungiG();

          giocatori = this.gD.doRetrieveAll();
          GiocatoreBean giocatore = null;

          for (GiocatoreBean element : giocatori) {
            if (bean.getOrganizzatore().equals(element.getEmail())) {
              giocatore = element;
            }
          }

          if (bean.getTipologia().equals("libero")) {
            partecipazione.setNomeEvento(bean.getNome());
            if (giocatore != null) {
              partecipazione.setUtente(bean.getOrganizzatore());
              this.pD.doSave(partecipazione);
            }
          } else {
            if (this.partecipazioneSquadraDao == null) {
              this.partecipazioneSquadraDao = new PartecipazioneSquadraDAO();
            }

            PartecipazioneSquadraBean partecipazioneSquadra = new PartecipazioneSquadraBean();
            partecipazioneSquadra.setIdSquadra(giocatore.getIdSquadra());
            partecipazioneSquadra.setIdEvento(bean.getNome());
            this.partecipazioneSquadraDao.doSave(partecipazioneSquadra);
          }
          this.eD.doUpdate(bean);
        } else if (action.equalsIgnoreCase("deleteE")) {
          this.eD.doDelete(nomeEvento);
        } else if (action.equalsIgnoreCase("trovarichieste")) {
          richieste = this.eD.doRetrieveRichieste(gestore.getEmail());
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
    doGet(request, response);
  }

  public void seteD(EventoDAO eD) {
    this.eD = eD;
  }

  public void setgD(GiocatoreDAO gD) {
    this.gD = gD;
  }

  public void setpD(PartecipazioneDAO pD) {
    this.pD = pD;
  }

  public void setPartecipazioneSquadraDao(PartecipazioneSquadraDAO partecipazioneSquadraDao) {
    this.partecipazioneSquadraDao = partecipazioneSquadraDao;
  }
}