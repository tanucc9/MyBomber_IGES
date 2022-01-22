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

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The e D. */
  public EventoDAO eD;

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

    EventoDAO eventoDao;
    GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
    ArrayList<EventoBean> eventiRecenti;

    try {
      if (eD == null) {
        eventoDao = new EventoDAO();
      } else {
        eventoDao = eD;
      }

      eventiRecenti = eventoDao.doRetrieveEventiRecenti(giocatore.getEmail());
      request.setAttribute("eventiRecenti", eventiRecenti);
    } catch (SQLException e) {
      e.getStackTrace();
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./EventiRecenti.jsp"));
    dispatcher.forward(request, response);

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  /*
   * protected void doPost(HttpServletRequest request, HttpServletResponse
   * response) throws ServletException, IOException { // TODO Auto-generated
   * method stub doGet(request, response); }
   */
}
