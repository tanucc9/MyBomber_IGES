package control.recensione;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class RecensioneServlet.
 */
@WebServlet("/recensione")
public class RecensioneServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The rdt. */
  public RecensioneDAO rdt;

  /** The gdt. */
  public GiocatoreDAO gdt;

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
    String action = request.getParameter("action");
    RecensioneDAO recensioneDao;
    if (rdt == null) {
      recensioneDao = new RecensioneDAO();
    } else {
      recensioneDao = rdt;
    }
    String nomeE = request.getParameter("nome");

    try {

      if (action != null) {
        if (action.equalsIgnoreCase("cercagiocatori")) {
          ArrayList<String> daRecensire;
          ArrayList<RecensioneBean> recensiti;// gi� sono stati recensiti
          daRecensire = recensioneDao.doRetrieveDaRecensire(giocatore.getEmail(), nomeE);
          recensiti = recensioneDao.doRetrieveRecensiti(giocatore.getEmail(), nomeE);
          request.setAttribute("giocatoriDaRecensire", daRecensire);
          request.setAttribute("giocatoriRecensiti", recensiti);
          request.setAttribute("nomeEvento", nomeE);
        }
      }

    } catch (SQLException e) {
      e.getStackTrace();
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./DaiRecensione.jsp"));
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
    String rec = request.getParameter("rec");
    GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
    RecensioneDAO recensioneDao;
    if (rdt == null) {
      recensioneDao = new RecensioneDAO();
    } else {
      recensioneDao = rdt;
    }

    if (rec != null) {

      try {
        if (rec.equalsIgnoreCase("set")) {

          String nomeEvento = request.getParameter("nomeEvento");
          String recensito = request.getParameter("nomeG");
          String descrizione = request.getParameter("descrizione");
          float valutazione = Float.parseFloat(request.getParameter("valutazione"));
          RecensioneBean recensione = new RecensioneBean();
          recensione.setRecensione(valutazione);
          recensione.setDescrizione(descrizione);
          recensione.setEvento(nomeEvento);
          recensione.setRecensito(recensito);
          recensione.setRecensore(giocatore.getEmail());
          recensioneDao.doSave(recensione);

          GiocatoreDAO giocatoredao;
          if (gdt == null) {
            giocatoredao = new GiocatoreDAO();
          } else {
            giocatoredao = gdt;
          }

          float nuovamedia = recensioneDao.doRetrieveMedia(recensito);

          GiocatoreBean recensitobean = giocatoredao.doRetrieveByKey(recensito);
          recensitobean.setValutazione(nuovamedia);

          giocatoredao.doUpdate(recensitobean);
          request.setAttribute("saveok", "saveok");
          RequestDispatcher dispatcher = request
              .getRequestDispatcher(response.encodeRedirectURL("./EventiRecenti.jsp"));
          dispatcher.forward(request, response);
          return;

        } else if (rec.equalsIgnoreCase("el")) {
          String recensito = request.getParameter("nomeG");
          String nomeEvento = request.getParameter("nomeEvento");
          recensioneDao.doDelete(giocatore.getEmail(), recensito, nomeEvento);

          GiocatoreDAO giocatoredao;
          if (gdt == null) {
            giocatoredao = new GiocatoreDAO();
          } else {
            giocatoredao = gdt;
          }

          float nuovamedia = recensioneDao.doRetrieveMedia(recensito);

          GiocatoreBean recensitobean = giocatoredao.doRetrieveByKey(recensito);
          recensitobean.setValutazione(nuovamedia);

          giocatoredao.doUpdate(recensitobean);
          request.setAttribute("deleteok", "deleteok");
          RequestDispatcher dispatcher = request
              .getRequestDispatcher(response.encodeRedirectURL("./EventiRecenti.jsp"));
          dispatcher.forward(request, response);
          return;
        }
      } catch (SQLException | NoSuchAlgorithmException e) {
        e.getStackTrace();
      }
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./DaiRecensione.jsp"));
    dispatcher.forward(request, response);

  }
}
