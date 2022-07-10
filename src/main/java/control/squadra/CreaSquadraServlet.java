package control.squadra;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log.LoggerSingleton;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import model.squadra.LogoSquadraBean;
import model.squadra.LogoSquadraDAO;
import model.squadra.SquadraBean;
import model.squadra.SquadraDAO;
import model.struttura.StrutturaBean;
import model.struttura.StrutturaDAO;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

@WebServlet("/creaSquadra")
public class CreaSquadraServlet extends HttpServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private SquadraDAO squadraDao;
    private GiocatoreDAO gioDao;

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
        doPost(request, response);
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
        GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
        if (giocatore == null) {
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher(response.encodeRedirectURL("./"));
            dispatcher.forward(request, response);
            return;
        }

        if (this.squadraDao == null) {
            this.squadraDao = new SquadraDAO();
        }

        String nome = request.getParameter("nomeSquadra");
        String nomeAbbr = request.getParameter("nomeAbbr");
        String citta = request.getParameter("citta");
        int idLogo = Integer.parseInt(request.getParameter("id_logo_scelto"));
        String descr = request.getParameter("descr");

        String messageError = "";
        if (nome.length() < 1) {
            messageError += "Lunghezza minima nome: 1,";
        } else if (nome.length() > 30) {
            messageError += "Lunghezza massima nome: 30,";
        }
        if (!nome.matches("^[A-Za-z \\']*$")) {
            messageError += "Formato nome non rispettato,";
        }
        SquadraBean squadraTemp = this.squadraDao.doRetrieveByName(nome);
        if (squadraTemp != null) {
            messageError += "Nome squadra già esistente,";
        }
        if (nomeAbbr.length() < 1) {
            messageError += "Lunghezza minima nome abbreviato: 1,";
        } else if (nomeAbbr.length() > 3) {
            messageError += "Lunghezza massima nome abbreviato: 3,";
        }
        if (!nomeAbbr.matches("^[A-Za-z]*$")) {
            messageError += "Formato nome abbreviato non rispettato,";
        }
        if (citta.length() < 1) {
            messageError += "Lunghezza minima città: 1,";
        } else if (citta.length() > 30) {
            messageError += "Lunghezza massima città: 30,";
        }
        if (!citta.matches("^[A-zÀ-ù '-]*$")) {
            messageError += "Formato città non rispettato,";
        }
        if (descr.length() < 30) {
            messageError += "Lunghezza minima descrizione: 30,";
        } else if (descr.length() > 300) {
            messageError += "Lunghezza massima descrizione: 300,";
        }
        if (messageError.length() > 0) {
            request.setAttribute("messageError", messageError);

            RequestDispatcher dispatcher = request
                    .getRequestDispatcher(response.encodeRedirectURL("./CreaSquadra.jsp"));
            dispatcher.forward(request, response);
            return;
        }

        SquadraBean squadra = new SquadraBean();
        squadra.setNome(nome);
        squadra.setNomeAbbreviato(nomeAbbr);
        squadra.setCitta(citta);
        squadra.setDescrizione(descr);
        if (idLogo != 0) {
            squadra.setLogo(idLogo);
        } else {
            squadra.setLogo(8); //no_image
        }
        squadra.setCapitano(giocatore.getEmail());
        try {
            this.squadraDao.doSave(squadra);
            SquadraBean sq = this.squadraDao.doRetrieveByName(squadra.getNome());
            giocatore.setIdSquadra(sq.getIdSquadra());
            squadra.setIdSquadra(sq.getIdSquadra());
            if (this.gioDao == null) {
                this.gioDao = new GiocatoreDAO();
            }
            this.gioDao.doUpdateTeam(giocatore);

            request.getSession().setAttribute("giocatore", giocatore);
            request.getSession().setAttribute("squadra", squadra);

            request.setAttribute("cu", "giocatore");
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher(response.encodeRedirectURL("./AreaUtente.jsp"));
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public SquadraDAO getSquadraDao() {
        return squadraDao;
    }

    public void setSquadraDao(SquadraDAO squadraDao) {
        this.squadraDao = squadraDao;
    }

    public GiocatoreDAO getGioDao() {
        return gioDao;
    }

    public void setGioDao(GiocatoreDAO gioDao) {
        this.gioDao = gioDao;
    }
}