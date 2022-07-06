package model.partecipazione;

import control.DriverManagerConnectionPool;
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
}
