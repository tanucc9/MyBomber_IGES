package model.squadra;

import control.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogoSquadraDAO {

    private static final String TABLE_NAME = "logo_squadra";

    public synchronized void doSave(LogoSquadraBean l) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "insert into " + TABLE_NAME
                + " (nome, url) values (?, ?)";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, l.getNome());
            preparedStatement.setString(2, l.getUrl());
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

    public synchronized ArrayList<LogoSquadraBean> doRetrieveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<LogoSquadraBean> loghi = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                LogoSquadraBean bean = new LogoSquadraBean();
                bean.setIdLogoSquadra(rs.getInt("id_logo_squadra"));
                bean.setNome(rs.getString("nome"));
                bean.setUrl(rs.getString("url"));
                loghi.add(bean);
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
        return loghi;
    }


}