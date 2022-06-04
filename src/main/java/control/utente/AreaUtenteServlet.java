package control.utente;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.utente.gestore.GestoreBean;
import model.utente.giocatore.GiocatoreBean;

// TODO: Auto-generated Javadoc
/**
 * The Class AreaUtenteServlet.
 */
@WebServlet("/areaUtenteServlet")
public class AreaUtenteServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new area utente servlet.
   */
  public AreaUtenteServlet() {

  }

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
    GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");

    if (giocatore != null) {
      request.setAttribute("cu", "giocatore");
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(response.encodeRedirectURL("./AreaUtente.jsp"));
      dispatcher.forward(request, response);
    }
    if (gestore != null) {
      request.setAttribute("cu", "gestore");
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(response.encodeRedirectURL("./AreaUtente.jsp"));
      dispatcher.forward(request, response);
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