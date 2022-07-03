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

@WebServlet("/eliminaSquadra")
public class EliminaSquadraServlet extends HttpServlet {

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
        GiocatoreBean giocatore = (GiocatoreBean) request.getSession().getAttribute("giocatore");
        SquadraBean squadra = (SquadraBean) request.getSession().getAttribute("squadra");
        boolean isCaptain = squadra.getCapitano().equals(giocatore.getEmail());

        if (giocatore == null || !isCaptain) {
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher(response.encodeRedirectURL("./"));
            dispatcher.forward(request, response);
            return;
        }

        if (this.squadraDao == null) {
            this.squadraDao = new SquadraDAO();
        }

        try {
            this.squadraDao.doDelete(squadra.getIdSquadra());

            RequestDispatcher dispatcher = request
                    .getRequestDispatcher(response.encodeRedirectURL("./Squadre.jsp"));
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        doGet(request, response);
    }
}