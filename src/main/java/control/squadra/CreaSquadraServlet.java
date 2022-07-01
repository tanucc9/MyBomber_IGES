package control.squadra;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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

@WebServlet("/creaSquadra")
public class CreaSquadraServlet extends HttpServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private SquadraDAO squadraDao;

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
        String nome = request.getParameter("nomeSquadra");
        String nomeAbbr = request.getParameter("nomeAbbr");
        String citta = request.getParameter("citta");
        int idLogo = Integer.parseInt(request.getParameter("id_logo_scelto"));
        String descr = request.getParameter("descr");

        if (this.squadraDao == null) {
            this.squadraDao = new SquadraDAO();
        }

        SquadraBean squadra = new SquadraBean();
        squadra.setNome(nome);
        squadra.setNomeAbbreviato(nomeAbbr);
        squadra.setCitta(citta);
        squadra.setDescrizione(descr);
        squadra.setLogo(idLogo);
        try {
            this.squadraDao.doSave(squadra);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SquadraDAO getSquadraDao() {
        return squadraDao;
    }

    public void setSquadraDao(SquadraDAO squadraDao) {
        this.squadraDao = squadraDao;
    }
}