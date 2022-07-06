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
import model.struttura.StrutturaBean;
import model.struttura.StrutturaDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class StrutturaServlet.
 */
@WebServlet("/struttura")
public class StrutturaServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The sdao. */
  private StrutturaDAO sdao;

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

    ArrayList<StrutturaBean> strutture = new ArrayList<>();
    if (this.sdao == null) {
      this.sdao = new StrutturaDAO();
    }
    try {
      strutture = this.sdao.doRetrieveAll();
      request.setAttribute("strutture", strutture);
    } catch (SQLException e) {
      e.getStackTrace();
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./CreaEvento.jsp"));
    dispatcher.forward(request, response);

  }

  public void setSdao(StrutturaDAO sdao) {
    this.sdao = sdao;
  }
}
