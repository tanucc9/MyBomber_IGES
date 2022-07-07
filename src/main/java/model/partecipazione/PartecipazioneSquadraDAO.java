package model.partecipazione;

import control.DriverManagerConnectionPool;
import model.squadra.SquadraBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class PartecipazioneDAO.
 */
public class PartecipazioneSquadraDAO {

    /** The Constant TABLE_NAME. */
    private static final String TABLE_NAME = "partecipazione_squadra";

    public synchronized void doSave(PartecipazioneSquadraBean p) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "insert into " + TABLE_NAME + " (id_squadra, id_evento) values (?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, p.getIdSquadra());
            preparedStatement.setString(2, p.getIdEvento());
            preparedStatement.executeUpdate();

            connection.commit();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    public synchronized boolean doDelete(PartecipazioneSquadraBean ps) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "delete from " + TABLE_NAME + " WHERE id_squadra = ? AND id_evento = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, ps.getIdSquadra());
            preparedStatement.setString(2, ps.getIdEvento());

            result = preparedStatement.executeUpdate();

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
        return (result != 0);
    }

    public synchronized PartecipazioneSquadraBean doRetrieveByKey(PartecipazioneSquadraBean ps) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PartecipazioneSquadraBean bean = new PartecipazioneSquadraBean();
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id_squadra = ? AND id_evento = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, ps.getIdSquadra());
            preparedStatement.setString(2, ps.getIdEvento());

            ResultSet rs = preparedStatement.executeQuery();
            // 4. Prendi il risultato
            if (rs.next()) {
                bean.setIdSquadra(rs.getInt("id_squadra"));
                bean.setIdEvento(rs.getString("id_evento"));

                return bean;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                DriverManagerConnectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
