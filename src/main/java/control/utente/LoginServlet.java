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

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class EsempioServlet.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The giocatore dao. */
  public GiocatoreDAO giocatoreDao;

  /** The gestore dao. */
  public GestoreDAO gestoreDao;

  public SquadraDAO squadraDAO;
  public LogoSquadraDAO logoDAO;

  /**
   * Default constructor.
   */
  public LoginServlet() {
    // TODO Auto-generated constructor stub
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
    // TODO Auto-generated method stub

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
    GiocatoreDAO gd;
    if (giocatoreDao != null) {
      gd = giocatoreDao;
    } else {
      gd = new GiocatoreDAO();
    }
    GestoreDAO gesd;
    if (gestoreDao != null) {
      gesd = gestoreDao;
    } else {
      gesd = new GestoreDAO();
    }

    GiocatoreBean giocatore = new GiocatoreBean();
    GestoreBean gestore = new GestoreBean();

    try {
      giocatore = gd.doRetrieveByAuth(email, password);
      gestore = gesd.doRetrieveByAuth(email, password);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    if (giocatore != null) {
      request.getSession().setAttribute("giocatore", giocatore);

      LogoSquadraDAO lDao;
      if (logoDAO != null) {
        lDao = logoDAO;
      } else {
        lDao = new LogoSquadraDAO();
      }

      try {
        request.getSession().setAttribute("loghiSquadra", lDao.doRetrieveAll());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }


      SquadraDAO sDao;
      if (squadraDAO != null) {
        sDao = squadraDAO;
      } else {
        sDao = new SquadraDAO();
      }

      SquadraBean squadra = sDao.doRetrieveByKey(giocatore.getIdSquadra());
      if (squadra != null)
        request.getSession().setAttribute("squadra", squadra);

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
}
