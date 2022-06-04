package model.struttura;

import control.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class StrutturaDAO.
 */
public class StrutturaDAO {

  /** The Constant TABLE_NAME. */
  private static final String TABLE_NAME = "struttura";

  /**
   * Do save.
   *
   * @param bean the bean
   * @throws SQLException the SQL exception
   */
  public synchronized void doSave(StrutturaBean bean) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String insertSQL = "insert into " + TABLE_NAME
        + " (nome, indirizzo, nazione, citta, cap, provincia, telefono) values (?, ?, ?, ?, ?, ?, ?)";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setString(1, bean.getNome());
      preparedStatement.setString(2, bean.getIndirizzo());
      preparedStatement.setString(3, bean.getNazione());
      preparedStatement.setString(4, bean.getCitta());
      preparedStatement.setString(5, bean.getCap());
      preparedStatement.setString(6, bean.getProvincia());
      preparedStatement.setString(7, bean.getTelefono());
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
   * @param nome the nome
   * @return the struttura bean
   */
  public synchronized StrutturaBean doRetrieveByKey(String nome) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
      StrutturaBean bean = new StrutturaBean();
      conn = DriverManagerConnectionPool.getConnection();
      ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE nome = ?");
      ps.setString(1, nome);
      ResultSet rs = ps.executeQuery();
      // 4. Prendi il risultato
      if (rs.next()) {
        bean.setNome(rs.getString("nome"));
        bean.setIndirizzo(rs.getString("indirizzo"));
        bean.setNazione(rs.getString("nazione"));
        bean.setCitta(rs.getString("citta"));
        bean.setCap(rs.getString("cap"));
        bean.setProvincia(rs.getString("provincia"));
        bean.setTelefono(rs.getString("telefono"));
        return bean;
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        ps.close();
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
  public synchronized void doUpdate(StrutturaBean bean) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String updateSQL = "UPDATE " + TABLE_NAME
        + " SET nome = ?, indirizzo = ?, nazione = ?, citta = ?, cap = ?, provincia = ?, telefono = ? WHERE nome = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(updateSQL);
      preparedStatement.setString(1, bean.getNome());
      preparedStatement.setString(2, bean.getIndirizzo());
      preparedStatement.setString(3, bean.getNazione());
      preparedStatement.setString(4, bean.getCitta());
      preparedStatement.setString(5, bean.getCap());
      preparedStatement.setString(6, bean.getProvincia());
      preparedStatement.setString(7, bean.getTelefono());
      preparedStatement.setString(8, bean.getNome());
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
   * @param nome the nome
   * @return true, if successful
   * @throws SQLException the SQL exception
   */
  public synchronized boolean doDelete(String nome) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSQL = "delete from " + TABLE_NAME + " where nome = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      connection.setAutoCommit(true);
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setString(1, nome);

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
  public synchronized ArrayList<StrutturaBean> doRetrieveAll() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<StrutturaBean> strutture = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        StrutturaBean bean = new StrutturaBean();

        bean.setNome(rs.getString("nome"));
        bean.setIndirizzo(rs.getString("indirizzo"));
        bean.setNazione(rs.getString("nazione"));
        bean.setCitta(rs.getString("citta"));
        bean.setCap(rs.getString("cap"));
        bean.setProvincia(rs.getString("provincia"));
        bean.setTelefono(rs.getString("telefono"));
        strutture.add(bean);
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
    return strutture;
  }

  /**
   * Do retrieve all nomi.
   *
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<String> doRetrieveAllNomi() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<String> strutture = new ArrayList<>();

    String selectSQL = "SELECT nome FROM " + TABLE_NAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        String bean = new String();

        bean = rs.getString("nome");
        strutture.add(bean);
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
    return strutture;
  }

}
