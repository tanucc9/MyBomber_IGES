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
import model.utente.gestore.GestoreBean;

/**
 * The Class CronologiaEventiServlet.
 */
@WebServlet("/cronologiaEventiServlet")
public class CronologiaEventiServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The edao. */
  public EventoDAO edao;

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
    /*
     * request.setAttribute("errorReg",pippo);
     *
     * RequestDispatcher dispatcher1 = request
     * .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
     * dispatcher1.forward(request, response);
     */
    GestoreBean gestore = (GestoreBean) request.getSession().getAttribute("gestore");
    EventoDAO eventoDAO;
    if (edao == null) {
      eventoDAO = new EventoDAO();
    } else {
      eventoDAO = edao;
    }
    ArrayList<EventoBean> eventi = new ArrayList<>();
    try {
      eventi = eventoDAO.doRetrieveEventiGestore(gestore.getEmail());

      request.setAttribute("eventi", eventi);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./CronologiaEventi.jsp"));
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

   /* RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./CronologiaEventi.jsp"));
    dispatcher.forward(request, response);*/
	  doGet(request, response);
  }

}