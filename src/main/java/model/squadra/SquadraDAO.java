package model.squadra;

import control.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SquadraDAO {

    /** The Constant TABLE_NAME. */
    private static final String TABLE_NAME = "squadra";

    public synchronized void doSave(SquadraBean s) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "insert into " + TABLE_NAME
                + " (nome, nome_abbreviato, citta, descrizione, logo, capitano) values (?, ?, ?, ?, ?, ?)";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, s.getNome());
            preparedStatement.setString(2, s.getNomeAbbreviato());
            preparedStatement.setString(3, s.getCitta());
            preparedStatement.setString(4, s.getDescrizione());
            preparedStatement.setInt(5, s.getLogo());
            preparedStatement.setString(6, s.getCapitano());
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

    public synchronized void doUpdate(SquadraBean s) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE " + TABLE_NAME
                + " SET nome = ?, nome_abbreviato = ?, citta = ? , descrizione = ?, logo = ?, capitano = ? WHERE id_squadra = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, s.getNome());
            preparedStatement.setString(2, s.getNomeAbbreviato());
            preparedStatement.setString(3, s.getCitta());
            preparedStatement.setString(4, s.getDescrizione());
            preparedStatement.setInt(5, s.getLogo());
            preparedStatement.setString(6, s.getCapitano());
            preparedStatement.setInt(7, s.getIdSquadra());
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

    public synchronized boolean doDelete(int idSquadra) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "delete from " + TABLE_NAME + " WHERE id_squadra = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, idSquadra);

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

    public synchronized SquadraBean doRetrieveByKey(int idSquadra) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        SquadraBean bean = new SquadraBean();
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id_squadra = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idSquadra);
            ResultSet rs = preparedStatement.executeQuery();
            // 4. Prendi il risultato
            if (rs.next()) {
                bean.setNome(rs.getString("nome"));
                bean.setNomeAbbreviato(rs.getString("nome_abbreviato"));
                bean.setCitta(rs.getString("citta"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setLogo(rs.getInt("logo"));
                bean.setCapitano(rs.getString("capitano"));

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

    public synchronized ArrayList<SquadraBean> doRetrieveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<SquadraBean> squadre = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                SquadraBean bean = new SquadraBean();
                bean.setNome(rs.getString("nome"));
                bean.setNomeAbbreviato(rs.getString("nome_abbreviato"));
                bean.setCitta(rs.getString("citta"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setLogo(rs.getInt("logo"));
                bean.setCapitano(rs.getString("capitano"));
                squadre.add(bean);
            }

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
        return squadre;
    }

}