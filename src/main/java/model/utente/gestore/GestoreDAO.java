package model.utente.gestore;

import control.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class GestoreDAO.
 */
public class GestoreDAO {

  /** The Constant TABLE_NAME. */
  private static final String TABLE_NAME = "gestore";

  /**
   * Do save.
   *
   * @param bean the bean
   * @throws SQLException the SQL exception
   */
  public synchronized void doSave(GestoreBean bean) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSQL = "insert into " + TABLE_NAME
        + " (e_mail, nome, cognome, password_gestore, telefono, struttura) values (?, ?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setString(1, bean.getEmail());
      preparedStatement.setString(2, bean.getNome());
      preparedStatement.setString(3, bean.getCognome());
      preparedStatement.setString(4, bean.getPassword());
      preparedStatement.setString(5, bean.getTelefono());
      preparedStatement.setString(6, bean.getStruttura());
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
   * @param email the email
   * @return the gestore bean
   */
  public synchronized GestoreBean doRetrieveByKey(String email) {

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    try {
      GestoreBean bean = new GestoreBean();
      conn = DriverManagerConnectionPool.getConnection();
      preparedStatement = conn
          .prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE e_mail = ?");
      preparedStatement.setString(1, email);

      ResultSet rs = preparedStatement.executeQuery();

      // 4. Prendi il risultato
      if (rs.next()) {
        bean.setEmail(rs.getString("e_mail"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setPassword(rs.getString("password_gestore"));

        bean.setTelefono(rs.getString("telefono"));
        bean.setStruttura(rs.getString("struttura"));
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
   * Do update.
   *
   * @param bean the bean
   * @throws SQLException the SQL exception
   */
  public synchronized void doUpdate(GestoreBean bean) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String updateSQL = "UPDATE " + TABLE_NAME
        + " SET e_mail = ?, nome = ?, cognome = ?, password_gestore = ? , telefono = ?, struttura = ? WHERE e_mail = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(updateSQL);
      preparedStatement.setString(1, bean.getEmail());
      preparedStatement.setString(2, bean.getNome());
      preparedStatement.setString(3, bean.getCognome());
      preparedStatement.setString(4, bean.getPassword());
      preparedStatement.setString(5, bean.getTelefono());
      preparedStatement.setString(6, bean.getStruttura());
      preparedStatement.setString(7, bean.getEmail());
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
   * Do delete.
   *
   * @param email the email
   * @return true, if successful
   * @throws SQLException the SQL exception
   */
  public synchronized boolean doDelete(String email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSQL = "delete from " + TABLE_NAME + " where e_mail = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      connection.setAutoCommit(true);
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setString(1, email);

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
   * Do retrieve all.
   *
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<GestoreBean> doRetrieveAll() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<GestoreBean> gestori = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        GestoreBean bean = new GestoreBean();

        bean.setEmail(rs.getString("e_mail"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setPassword(rs.getString("password_gestore"));

        bean.setTelefono(rs.getString("telefono"));
        bean.setStruttura(rs.getString("struttura"));
        gestori.add(bean);
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
    return gestori;
  }

  /**
   * Do retrieve by struttura.
   *
   * @param struttura the struttura
   * @return the gestore bean
   */
  public synchronized GestoreBean doRetrieveByStruttura(String struttura) {

    Connection conn = null;
    PreparedStatement preparedStatement = null;

    try {
      GestoreBean bean = new GestoreBean();
      conn = DriverManagerConnectionPool.getConnection();
      preparedStatement = conn
          .prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE struttura = ?");
      preparedStatement.setString(1, struttura);

      ResultSet rs = preparedStatement.executeQuery();

      // 4. Prendi il risultato
      if (rs.next()) {
        bean.setEmail(rs.getString("e_mail"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setPassword(rs.getString("password_gestore"));

        bean.setTelefono(rs.getString("telefono"));
        bean.setStruttura(rs.getString("struttura"));
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

}
