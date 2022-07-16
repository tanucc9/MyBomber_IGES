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
public class PartecipazioneDAO {

  /** The Constant TABLE_NAME. */
  private static final String TABLE_NAME = "partecipazione";

  /**
   * Do save.
   *
   * @param e the e
   * @throws SQLException the SQL exception
   */
  public synchronized void doSave(PartecipazioneBean e) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSQL = "insert into " + TABLE_NAME + " (e_mail, code_evento) values (?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setString(1, e.getUtente());
      preparedStatement.setString(2, e.getCodeEvento());
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

  /**
   * Do retrieve by key.
   *
   * @param email  the email
   * @param evento the evento
   * @return the partecipazione bean
   */
  public synchronized PartecipazioneBean doRetrieveByKey(String email, String evento) {

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    try {
      PartecipazioneBean bean = new PartecipazioneBean();
      conn = DriverManagerConnectionPool.getConnection();
      preparedStatement = conn.prepareStatement(
          "SELECT * FROM " + TABLE_NAME + " WHERE e_mail = ? AND code_evento = ?");
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, evento);

      ResultSet rs = preparedStatement.executeQuery();

      // 4. Prendi il risultato
      if (rs.next()) {
        bean.setUtente(rs.getString("e_mail"));
        bean.setCodeEvento(rs.getString("code_evento"));
        return bean;
      }

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(conn);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * Do retrieve all.
   *
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<PartecipazioneBean> doRetrieveAll() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<PartecipazioneBean> partecipazioni = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        PartecipazioneBean bean = new PartecipazioneBean();
        bean.setUtente(rs.getString("e_mail"));
        bean.setCodeEvento(rs.getString("code_evento"));

        partecipazioni.add(bean);

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
    return partecipazioni;
  }

  /*
   * public synchronized void doUpdate(PartecipazioneBean e) throws SQLException {
   *
   * Connection connection = null; PreparedStatement preparedStatement = null;
   *
   * String updateSQL ="UPDATE " + TABLE_NAME +
   * " SET e_mail = ?, code_evento = ? WHERE e_mail = ? AND code_evento = ?"; try
   * { connection = DriverManagerConnectionPool.getConnection(); preparedStatement
   * = connection.prepareStatement(updateSQL); preparedStatement.setString(1,
   * e.getUtente()); preparedStatement.setString(2, e.getEvento());
   * preparedStatement.setString(3, e.getUtente()); preparedStatement.setString(4,
   * e.getEvento());
   *
   *
   *
   * connection.commit(); } finally { try { if (preparedStatement != null)
   * preparedStatement.close(); } finally { if (connection != null)
   * connection.close(); } } }
   */

  /**
   * Do delete.
   *
   * @param email  the email
   * @param evento the evento
   * @return true, if successful
   * @throws SQLException the SQL exception
   */
  public synchronized boolean doDelete(String email, String evento) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSQL = "delete from " + TABLE_NAME + " where e_mail = ? AND code_evento = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      connection.setAutoCommit(true);
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, evento);

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

  /**
   * Do retrieve by evento.
   *
   * @param evento the evento
   * @return the array list
   */
  public synchronized ArrayList<PartecipazioneBean> doRetrieveByEvento(String evento) {

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ArrayList<PartecipazioneBean> partecipanti = new ArrayList<>();
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE code_evento = ?";

    try {
      conn = DriverManagerConnectionPool.getConnection();
      preparedStatement = conn.prepareStatement(selectSQL);
      preparedStatement.setString(1, evento);

      ResultSet rs = preparedStatement.executeQuery();

      // 4. Prendi il risultato
      while (rs.next()) {
        PartecipazioneBean bean = new PartecipazioneBean();
        bean.setUtente(rs.getString("e_mail"));
        bean.setCodeEvento(rs.getString("code_evento"));
        partecipanti.add(bean);
      }

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(conn);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return partecipanti;
  }

  /**
   * Do get partecipanti.
   *
   * @param evento the evento
   * @return the array list
   */
  public synchronized ArrayList<String> doGetPartecipanti(String evento) {

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ArrayList<String> partecipanti = new ArrayList<>();
    String selectSQL = "SELECT e_mail FROM " + TABLE_NAME + " WHERE code_evento = ?";

    try {
      conn = DriverManagerConnectionPool.getConnection();
      preparedStatement = conn.prepareStatement(selectSQL);
      preparedStatement.setString(1, evento);

      ResultSet rs = preparedStatement.executeQuery();

      // 4. Prendi il risultato
      while (rs.next()) {
        String giocatore = rs.getString("e_mail");
        partecipanti.add(giocatore);
      }

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(conn);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return partecipanti;
  }

}
