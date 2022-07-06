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
  public EventoDAO eD;

  /** The g D. */
  public GestoreDAO gD;

  /** The s D. */
  public StrutturaDAO sD;

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

    EventoDAO eventoDAO;
    GestoreDAO gestoreDAO;
    GestoreBean gestore = new GestoreBean();
    EventoBean evento = new EventoBean();
    StrutturaDAO strutturaDAO;

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
      if (gD == null) {
        gestoreDAO = new GestoreDAO();
      } else {
        gestoreDAO = gD;
      }

      gestore = gestoreDAO.doRetrieveByStruttura(struttura);
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

      if (eD == null) {
        eventoDAO = new EventoDAO();
      } else {
        eventoDAO = eD;
      }

      EventoBean testEvento = new EventoBean();
      testEvento = eventoDAO.doRetrieveByKey(nome);

      if (sD == null) {
        strutturaDAO = new StrutturaDAO();
      } else {
        strutturaDAO = sD;
      }

      StrutturaBean testStruttura;
      testStruttura = strutturaDAO.doRetrieveByKey(struttura);

      if (testEvento == null && testStruttura != null) {
        eventoDAO.doSave(evento);
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
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}