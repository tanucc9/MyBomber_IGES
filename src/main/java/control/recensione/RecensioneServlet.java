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

/**
 * The Class RecensioneServlet.
 */
@WebServlet("/recensione")
public class RecensioneServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The rdt. */
  private RecensioneDAO rdt;

  /** The gdt. */
  private GiocatoreDAO gdt;

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
    if (this.rdt == null) {
      this.rdt = new RecensioneDAO();
    }
    String nomeE = request.getParameter("nome");

    try {

      if (action != null) {
        if (action.equalsIgnoreCase("cercagiocatori")) {
          ArrayList<String> daRecensire;
          ArrayList<RecensioneBean> recensiti;// gi� sono stati recensiti
          daRecensire = this.rdt.doRetrieveDaRecensire(giocatore.getEmail(), nomeE);
          recensiti = this.rdt.doRetrieveRecensiti(giocatore.getEmail(), nomeE);
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
    if (this.rdt == null) {
      this.rdt = new RecensioneDAO();
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
          this.rdt.doSave(recensione);

          if (this.gdt == null) {
            this.gdt = new GiocatoreDAO();
          }

          float nuovamedia = this.rdt.doRetrieveMedia(recensito);

          GiocatoreBean recensitobean = this.gdt.doRetrieveByKey(recensito);
          recensitobean.setValutazione(nuovamedia);

          this.gdt.doUpdate(recensitobean);
          request.setAttribute("saveok", "saveok");
          RequestDispatcher dispatcher = request
              .getRequestDispatcher(response.encodeRedirectURL("./EventiRecenti.jsp"));
          dispatcher.forward(request, response);
          return;

        } else if (rec.equalsIgnoreCase("el")) {
          String recensito = request.getParameter("nomeG");
          String nomeEvento = request.getParameter("nomeEvento");
          this.rdt.doDelete(giocatore.getEmail(), recensito, nomeEvento);

          if (this.gdt == null) {
            this.gdt = new GiocatoreDAO();
          }

          float nuovamedia = this.rdt.doRetrieveMedia(recensito);

          GiocatoreBean recensitobean = this.gdt.doRetrieveByKey(recensito);
          recensitobean.setValutazione(nuovamedia);

          this.gdt.doUpdate(recensitobean);
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

  public void setRdt(RecensioneDAO rdt) {
    this.rdt = rdt;
  }

  public void setGdt(GiocatoreDAO gdt) {
    this.gdt = gdt;
  }
}
