package control.squadra;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.squadra.LogoSquadraBean;
import model.squadra.LogoSquadraDAO;
import model.squadra.SquadraBean;
import model.squadra.SquadraDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

@WebServlet("/squadraSpecifica")
public class SquadraSpecificaServlet extends HttpServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private SquadraDAO squadraDao;
    private LogoSquadraDAO logoDao;
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
        if (this.logoDao == null) {
            this.logoDao = new LogoSquadraDAO();
        }
        if (this.gioDao == null) {
            this.gioDao = new GiocatoreDAO();
        }

        int idSquadra = Integer.parseInt(request.getParameter("idSquadra"));

        try {
            SquadraBean squadra = this.squadraDao.doRetrieveByKey(idSquadra);
            LogoSquadraBean logo = this.logoDao.doRetrieveByKey(squadra.getLogo());
            ArrayList<GiocatoreBean> giocatori = this.gioDao.doRetrieveBySquadra(idSquadra);
            GiocatoreBean capitano = this.gioDao.doRetrieveByKey(squadra.getCapitano());

            request.setAttribute("squadra", squadra);
            request.setAttribute("logo", logo);
            request.setAttribute("giocatori", giocatori);
            request.setAttribute("capitano", capitano);

            RequestDispatcher dispatcher = request
                    .getRequestDispatcher(response.encodeRedirectURL("./Squadra.jsp"));
            dispatcher.forward(request, response);
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