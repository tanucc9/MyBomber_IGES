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
import model.squadra.SquadraBean;
import model.utente.giocatore.GiocatoreBean;

// TODO: Auto-generated Javadoc
/**
 * The Class PartecipaEventiServlet.
 */
@WebServlet("/partecipa")
public class PartecipaEventiServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The e D. */
  private EventoDAO eD;

  /** The p D. */
  private PartecipazioneDAO pD;
  private PartecipazioneSquadraDAO parSquadraDao;

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

    GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
    if (giocatore == null) {
      RequestDispatcher dispatcher = request
              .getRequestDispatcher(response.encodeRedirectURL("./Login.jsp"));
      dispatcher.forward(request, response);
      return;
    }

    ArrayList<EventoBean> eventi = new ArrayList<>();

    try {
      if (this.eD == null) {
        this.eD = new EventoDAO();
      }

      eventi = this.eD.doRetrieveEventi(giocatore.getEmail());
      request.setAttribute("eventi", eventi);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./PartecipaEventi.jsp"));
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
    GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
    String nomeEvento = request.getParameter("nome");
    SquadraBean squadra = (SquadraBean) request.getSession().getAttribute("squadra");
    boolean isCaptain = squadra.getCapitano().equals(giocatore.getEmail());

    try {
      if (this.eD == null) {
        this.eD = new EventoDAO();
      }

      if (this.pD == null) {
        this.pD = new PartecipazioneDAO();
      }

      if (this.parSquadraDao == null) {
        this.parSquadraDao = new PartecipazioneSquadraDAO();
      }

      EventoBean eventoBean = this.eD.doRetrieveByKey(nomeEvento);

      if (eventoBean.getTipologia().equals("libero")) {
        if (eventoBean.getNumPartecipanti() < 10) {
          PartecipazioneBean partecipazione = new PartecipazioneBean();
          partecipazione.setUtente(giocatore.getEmail());
          partecipazione.setEvento(nomeEvento);
          this.pD.doSave(partecipazione);
          eventoBean.aggiungiG();
          eventoBean.setValutazione(eventoBean.getValutazione() + giocatore.getValutazione());
        }
        if (eventoBean.getNumPartecipanti() == 10) {
          eventoBean.setStato("completato");
        }
      } else if (eventoBean.getTipologia().equals("squadra") && isCaptain) {
        PartecipazioneSquadraBean partecipazioneSquadra = new PartecipazioneSquadraBean();
        partecipazioneSquadra.setIdSquadra(giocatore.getIdSquadra());
        partecipazioneSquadra.setIdEvento(eventoBean.getNome());
        parSquadraDao.doSave(partecipazioneSquadra);
        eventoBean.aggiungiG();
        eventoBean.setStato("completato");
      }
      this.eD.doUpdate(eventoBean);
    }

    catch (SQLException e) {
      e.printStackTrace();
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./PartecipaEventi.jsp"));
    dispatcher.forward(request, response);

  }

  public void seteD(EventoDAO eD) {
    this.eD = eD;
  }

  public void setpD(PartecipazioneDAO pD) {
    this.pD = pD;
  }

  public void setParSquadraDao(PartecipazioneSquadraDAO parSquadraDao) {
    this.parSquadraDao = parSquadraDao;
  }
}
