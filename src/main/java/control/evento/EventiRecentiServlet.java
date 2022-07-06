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
import model.utente.giocatore.GiocatoreBean;

// TODO: Auto-generated Javadoc
/**
 * The Class EventiRecentiServlet.
 */
@WebServlet("/eventiRecenti")
public class EventiRecentiServlet extends HttpServlet {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The e D.
   */
  private EventoDAO eD;

  /**
   * Do get.
   *
   * @param request  the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
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

    ArrayList<EventoBean> eventiRecenti;

    try {
      if (this.eD == null) {
        this.eD = new EventoDAO();
      }

      eventiRecenti = this.eD.doRetrieveEventiRecenti(giocatore.getEmail());

      if (giocatore.getIdSquadra() != 0) {
        ArrayList<EventoBean> eventiRecentiSquadra = this.eD.doRetrieveEventiRecentiSquadra(giocatore.getIdSquadra());
        request.setAttribute("eventiRecentiSquadra", eventiRecentiSquadra);
      }

      request.setAttribute("eventiRecenti", eventiRecenti);
    } catch (SQLException e) {
      e.getStackTrace();
    }

    RequestDispatcher dispatcher = request
            .getRequestDispatcher(response.encodeRedirectURL("./EventiRecenti.jsp"));
    dispatcher.forward(request, response);
  }

  public void seteD(EventoDAO eD) {
    this.eD = eD;
  }
}
