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
import javax.xml.namespace.QName;

import model.squadra.LogoSquadraBean;
import model.squadra.LogoSquadraDAO;
import model.squadra.SquadraBean;
import model.squadra.SquadraDAO;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;

@WebServlet("/mostraSquadre")
public class MostraSquadreServlet extends HttpServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private SquadraDAO squadraDao;
    private LogoSquadraDAO logoDao;

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

        try {
            ArrayList<SquadraBean> squadre = this.squadraDao.doRetrieveAll();
            ArrayList<LogoSquadraBean> loghi = this.logoDao.doRetrieveAll();

            request.setAttribute("squadre", squadre);
            request.setAttribute("loghi", loghi);

            RequestDispatcher dispatcher = request
                    .getRequestDispatcher(response.encodeRedirectURL("./Squadre.jsp"));
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