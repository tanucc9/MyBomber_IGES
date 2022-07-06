package control.utente;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.squadra.LogoSquadraDAO;
import model.squadra.SquadraBean;
import model.squadra.SquadraDAO;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

/**
 * Servlet implementation class EsempioServlet.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The giocatore dao. */
  private GiocatoreDAO giocatoreDao;

  /** The gestore dao. */
  private GestoreDAO gestoreDao;

  private SquadraDAO squadraDAO;
  private LogoSquadraDAO logoDAO;

  /**
   * Default constructor.
   */
  public LoginServlet() {}

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

    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
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

    String email = request.getParameter("email");
    String password = request.getParameter("password");
    if (this.giocatoreDao == null) {
      this.giocatoreDao = new GiocatoreDAO();
    }

    if (this.gestoreDao == null) {
      this.gestoreDao = new GestoreDAO();
    }

    GiocatoreBean giocatore = new GiocatoreBean();
    GestoreBean gestore = new GestoreBean();

    try {
      giocatore = this.giocatoreDao.doRetrieveByAuth(email, password);
      gestore = this.gestoreDao.doRetrieveByAuth(email, password);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    if (giocatore != null) {
      request.getSession().setAttribute("giocatore", giocatore);

      if (this.logoDAO == null) {
        this.logoDAO = new LogoSquadraDAO();
      }

      try {
        request.getSession().setAttribute("loghiSquadra", this.logoDAO.doRetrieveAll());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }


      if (this.squadraDAO == null) {
        this.squadraDAO = new SquadraDAO();
      }

      SquadraBean squadra = this.squadraDAO.doRetrieveByKey(giocatore.getIdSquadra());
      if (squadra != null) {
        request.getSession().setAttribute("squadra", squadra);
      }

      RequestDispatcher dispatcher = request
          .getRequestDispatcher(response.encodeRedirectURL("./PartecipaEventi.jsp"));
      dispatcher.forward(request, response);
      return;
    }
    if (gestore != null) {
      request.getSession().setAttribute("gestore", gestore);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(response.encodeRedirectURL("cronologiaEventiServlet"));
      dispatcher.forward(request, response);
      return;
    }

    //if datas didn't match in db
    request.setAttribute("errorLog", "errore");
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./Login.jsp"));
    dispatcher.forward(request, response);
  }

  public void setGiocatoreDao(GiocatoreDAO giocatoreDao) {
    this.giocatoreDao = giocatoreDao;
  }

  public void setGestoreDao(GestoreDAO gestoreDao) {
    this.gestoreDao = gestoreDao;
  }

  public void setSquadraDAO(SquadraDAO squadraDAO) {
    this.squadraDAO = squadraDAO;
  }

  public void setLogoDAO(LogoSquadraDAO logoDAO) {
    this.logoDAO = logoDAO;
  }
}
