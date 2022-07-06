package control.evento;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log.LoggerSingleton;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.struttura.StrutturaBean;
import model.struttura.StrutturaDAO;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class EsempioServlet.
 */
@WebServlet("/creaEvento")
public class CreaEventoServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The e D. */
  private EventoDAO eD;

  /** The g D. */
  private GestoreDAO gD;

  /** The s D. */
  private StrutturaDAO sD;

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

    GestoreBean gestore = new GestoreBean();
    EventoBean evento = new EventoBean();

    String nome = request.getParameter("nome");
    evento.setNome(nome);
    evento.setDescrizione(request.getParameter("descrizione"));
    String struttura = request.getParameter("struttura");
    evento.setStruttura(struttura);
    String data = request.getParameter("data");
    evento.setData(Date.valueOf(data));
    String ora = request.getParameter("ora");
    evento.setOra(Integer.parseInt(ora));
    if (request.getParameter("switch_tipologia") != null)
      evento.setTipologia("squadra");
    else
      evento.setTipologia("libero");

    try {
      if (this.gD == null) {
        this.gD = new GestoreDAO();
      }

      gestore = this.gD.doRetrieveByStruttura(struttura);
      evento.setGestore(gestore.getEmail());
      // Se l'utente che sta utilizzando l'applicazione = giocatore
      if (request.getSession().getAttribute("giocatore") != null) {
        GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
        evento.setOrganizzatore(giocatore.getEmail());
      }

      // Se l'utente che sta utilizzando l'applicazione = gestore
      else {
        evento.setOrganizzatore(gestore.getEmail());
      }
      evento.setStato("richiesta");

      if (this.eD == null) {
        this.eD = new EventoDAO();
      }

      EventoBean testEvento = new EventoBean();
      testEvento = eD.doRetrieveByKey(nome);

      if (this.sD == null) {
        this.sD = new StrutturaDAO();
      }

      StrutturaBean testStruttura;
      testStruttura = this.sD.doRetrieveByKey(struttura);

      if (testEvento == null && testStruttura != null) {
        eD.doSave(evento);
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(response.encodeRedirectURL("./PartecipaEventi.jsp"));
        dispatcher.forward(request, response);
      } else {
        if (testEvento != null) {
          request.setAttribute("errNome", "errore");
        }
        if (testStruttura == null) {
          request.setAttribute("errStruttura", "errore");
        }

        RequestDispatcher dispatcher = request
            .getRequestDispatcher(response.encodeRedirectURL("struttura"));
        dispatcher.forward(request, response);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

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

  public void setgD(GestoreDAO gD) {
    this.gD = gD;
  }

  public void setsD(StrutturaDAO sD) {
    this.sD = sD;
  }
}