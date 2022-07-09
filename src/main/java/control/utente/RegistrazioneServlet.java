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
import model.struttura.StrutturaBean;
import model.struttura.StrutturaDAO;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;
import util.HashTool;

/**
 * Servlet implementation class EsempioServlet.
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The gdt. */
  private GiocatoreDAO gdt;

  /** The gedt. */
  private GestoreDAO gedt;

  /** The sdt. */
  private StrutturaDAO sdt;

  /**
   * Default constructor.
   */
  public RegistrazioneServlet() {}

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
    String cf = request.getParameter("cf");
    if (cf.equals("giocatore")) {
      try {

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nazione = request.getParameter("nazione");
        String provincia = request.getParameter("provincia");
        String citta = request.getParameter("citta");
        String cap = request.getParameter("cap");
        String telefono = request.getParameter("telefono");
        String data = request.getParameter("data");

        if (this.gdt == null) {
          this.gdt = new GiocatoreDAO();
        }

        GiocatoreBean testEmail = this.gdt.doRetrieveByKey(email);
        GiocatoreBean testUsername = this.gdt.doRetrieveByUsername(username);

        if (testEmail == null && testUsername == null) {
          HashTool hashTool = new HashTool();

          GiocatoreBean g = new GiocatoreBean();
          g.setCapResidenza(cap);
          g.setCittaResidenza(citta);
          g.setCognome(cognome);
          g.setDataNascita(java.sql.Date.valueOf(data));
          g.setEmail(email);
          g.setNazioneResidenza(nazione);
          g.setNome(nome);
          g.setEncPassword(hashTool.hashSHA256(password));
          g.setProvinciaResidenza(provincia);
          g.setTelefono(telefono);
          g.setUsername(username);
          float t = 0;
          g.setValutazione(t);
          this.gdt.doSave(g);
          GiocatoreBean test = this.gdt.doRetrieveByKey(email);
          if (test != null) {
            request.getSession().setAttribute("giocatore", test);
            RequestDispatcher dispatcher = request
                .getRequestDispatcher(response.encodeRedirectURL("./PartecipaEventi.jsp"));
            dispatcher.forward(request, response);
          }

          else {
            request.setAttribute("errorReg", "errore");
            RequestDispatcher dispatcher = request
                .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
            dispatcher.forward(request, response);
          }
        } else {
          if (testEmail != null) {

            request.setAttribute("emailRe", "errorEmail");

          }

          if (testUsername != null) {

            request.setAttribute("userRe", "errorUser");

          }
          RequestDispatcher dispatcher = request
              .getRequestDispatcher(response.encodeRedirectURL("./PartecipaEventi.jsp"));
          dispatcher.forward(request, response);
        }
      } catch (SQLException | NoSuchAlgorithmException e) {
        System.out.println("Error:" + e.getMessage());
      }
    } else {

      try {
        String email = request.getParameter("emailG");
        String telefono = request.getParameter("telefonoGestore");
        String nome = request.getParameter("nomeG");
        String cognome = request.getParameter("cognomeG");
        String nomeStruttura = request.getParameter("strutturaG");
        String nazione = request.getParameter("nazioneG");
        String provincia = request.getParameter("provinciaG");
        String citta = request.getParameter("cittaG");
        String cap = request.getParameter("capG");
        String indirizzo = request.getParameter("indirizzoG");
        String telefonoStruttura = request.getParameter("telefonoStruttura");
        String password = request.getParameter("passwordG");
        StrutturaDAO sd;
        if (this.gedt == null) {
          this.gedt = new GestoreDAO();
        }

        if (this.sdt == null) {
          this.sdt = new StrutturaDAO();
        }
        GestoreBean testEmail = this.gedt.doRetrieveByKey(email);
        StrutturaBean testStruttura = this.sdt.doRetrieveByKey(nomeStruttura);

        if (testEmail == null && testStruttura == null) {
          HashTool hashTool = new HashTool();

          GestoreBean ges = new GestoreBean();
          StrutturaBean sb = new StrutturaBean();
          sb.setNome(nomeStruttura);
          sb.setIndirizzo(indirizzo);
          sb.setNazione(nazione);
          sb.setProvincia(provincia);
          sb.setCitta(citta);
          sb.setCap(cap);
          sb.setTelefono(telefonoStruttura);
          this.sdt.doSave(sb);

          ges.setCognome(cognome);
          ges.setEmail(email);
          ges.setNome(nome);
          ges.setEncPassword(hashTool.hashSHA256(password));
          ges.setTelefono(telefono);
          ges.setStruttura(nomeStruttura);

          this.gedt.doSave(ges);
          GestoreBean test = this.gedt.doRetrieveByKey(email);
          StrutturaBean testS = this.sdt.doRetrieveByKey(nomeStruttura);

          if ((test != null) && (testS != null)) {
            request.getSession().setAttribute("gestore", test);
            RequestDispatcher dispatcher = request
                .getRequestDispatcher(response.encodeRedirectURL("./RichiesteEventi.jsp"));
            dispatcher.forward(request, response);
          }

          else {
            request.setAttribute("errorReg", "errore");
            RequestDispatcher dispatcher = request
                .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
            dispatcher.forward(request, response);
          }

        } else {
          if (testEmail != null) {
            request.setAttribute("emailRe", "errorEmail");
          }
          if (testStruttura != null) {
            request.setAttribute("strutturaRe", "errorStruttura");
          }
          RequestDispatcher dispatcher = request
              .getRequestDispatcher(response.encodeRedirectURL("./Registrazione.jsp"));
          dispatcher.forward(request, response);
        }
      } catch (SQLException | NoSuchAlgorithmException e) {
        e.getStackTrace();
      }

    }

  }

  public void setGdt(GiocatoreDAO gdt) {
    this.gdt = gdt;
  }

  public void setGedt(GestoreDAO gedt) {
    this.gedt = gedt;
  }

  public void setSdt(StrutturaDAO sdt) {
    this.sdt = sdt;
  }
}
