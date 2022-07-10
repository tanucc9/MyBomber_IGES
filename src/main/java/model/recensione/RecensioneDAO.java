package model.recensione;

import control.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class RecensioneDAO.
 */
public class RecensioneDAO {

  /** The Constant TABLE_NAME. */
  private static final String TABLE_NAME = "recensione";

  /**
   * Do save.
   *
   * @param e the e
   * @throws SQLException the SQL exception
   */
  public synchronized void doSave(RecensioneBean e) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSQL = "insert into " + TABLE_NAME
        + " (e_mail_recensore, e_mail_recensito, nome_evento, recensione, descrizione) values (?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setString(1, e.getRecensore());
      preparedStatement.setString(2, e.getRecensito());
      preparedStatement.setString(3, e.getNomeEvento());
      preparedStatement.setFloat(4, e.getRecensione());
      preparedStatement.setString(5, e.getDescrizione());

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
   * @param recensore the recensore
   * @param recensito the recensito
   * @param evento    the evento
   * @return the recensione bean
   */
  public synchronized RecensioneBean doRetrieveByKey(String recensore, String recensito,
      String evento) {

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    try {
      RecensioneBean bean = new RecensioneBean();
      conn = DriverManagerConnectionPool.getConnection();
      preparedStatement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME
          + " WHERE e_mail_recensore = ? AND e_mail_recensito = ? AND nome_evento = ?");
      preparedStatement.setString(1, recensore);
      preparedStatement.setString(2, recensito);
      preparedStatement.setString(3, evento);
      ResultSet rs = preparedStatement.executeQuery();

      // 4. Prendi il risultato
      if (rs.next()) {
        bean.setRecensore(rs.getString("e_mail_recensore"));
        bean.setRecensito(rs.getString("e_mail_recensito"));
        bean.setNomeEvento(rs.getString("nome_evento"));
        bean.setRecensione(rs.getFloat("recensione"));
        bean.setDescrizione(rs.getString("descrizione"));
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
  public synchronized ArrayList<RecensioneBean> doRetrieveAll() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<RecensioneBean> recensioni = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        RecensioneBean bean = new RecensioneBean();
        bean.setRecensore(rs.getString("e_mail_recensore"));
        bean.setRecensito(rs.getString("e_mail_recensito"));
        bean.setNomeEvento(rs.getString("nome_evento"));
        bean.setRecensione(rs.getFloat("recensione"));
        bean.setDescrizione(rs.getString("descrizione"));
        recensioni.add(bean);

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
    return recensioni;
  }

  /**
   * Do retrieve media.
   *
   * @param giocatore the giocatore
   * @return the float
   * @throws SQLException the SQL exception
   */
  public synchronized float doRetrieveMedia(String giocatore) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    float media = 0;

    String selectSQL = "SELECT AVG(recensione) AS media FROM " + TABLE_NAME
        + " WHERE e_mail_recensito = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, giocatore);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {

        media = rs.getFloat("media");
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
    return media;
  }

  /**
   * Do retrieve da recensire.
   *
   * @param recensore the recensore
   * @param evento    the evento
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<String> doRetrieveDaRecensire(String recensore, String evento)
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<String> daRecensire = new ArrayList<>();

    String selectSQL = "SELECT e_mail FROM partecipazione WHERE nome_evento = ? AND e_mail != ALL "
        + "(SELECT e_mail_recensito FROM " + TABLE_NAME
        + " WHERE nome_evento = ? AND e_mail_recensore = ?) AND e_mail != ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      preparedStatement.setString(1, evento);
      preparedStatement.setString(2, evento);
      preparedStatement.setString(3, recensore);
      preparedStatement.setString(4, recensore);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        String utente = rs.getString("e_mail");
        daRecensire.add(utente);

      }

    } finally {
      try {
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return daRecensire;
  }

  /**
   * Do retrieve recensiti.
   *
   * @param recensore the recensore
   * @param evento    the evento
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<RecensioneBean> doRetrieveRecensiti(String recensore, String evento)
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<RecensioneBean> recensiti = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME
        + " WHERE nome_evento = ? AND e_mail_recensore = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      preparedStatement.setString(1, evento);
      preparedStatement.setString(2, recensore);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        RecensioneBean bean = new RecensioneBean();
        bean.setRecensore(rs.getString("e_mail_recensore"));
        bean.setRecensito(rs.getString("e_mail_recensito"));
        bean.setNomeEvento(rs.getString("nome_evento"));
        bean.setRecensione(rs.getFloat("recensione"));
        bean.setDescrizione(rs.getString("descrizione"));
        recensiti.add(bean);

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
    return recensiti;
  }

  /**
   * Do update.
   *
   * @param e the e
   * @throws SQLException the SQL exception
   */
  public synchronized void doUpdate(RecensioneBean e) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String updateSQL = "UPDATE " + TABLE_NAME
        + " SET e_mail_recensore = ?, e_mail_recensito = ?, nome_evento = ? , recensione = ? , descrizione = ? WHERE e_mail_recensore = ? AND e_mail_recensito = ? AND nome_evento = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(updateSQL);
      preparedStatement.setString(1, e.getRecensore());
      preparedStatement.setString(2, e.getRecensito());
      preparedStatement.setString(3, e.getNomeEvento());
      preparedStatement.setString(4, e.getDescrizione());
      preparedStatement.setFloat(5, e.getRecensione());
      preparedStatement.setString(6, e.getRecensore());
      preparedStatement.setString(7, e.getRecensito());
      preparedStatement.setString(8, e.getNomeEvento());

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
   * @param recensore the recensore
   * @param recensito the recensito
   * @param evento    the evento
   * @return true, if successful
   * @throws SQLException the SQL exception
   */
  public synchronized boolean doDelete(String recensore, String recensito, String evento)
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSQL = "delete from " + TABLE_NAME
        + " where e_mail_recensore = ? AND e_mail_recensito = ? AND nome_evento = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      connection.setAutoCommit(true);
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setString(1, recensore);
      preparedStatement.setString(2, recensito);
      preparedStatement.setString(3, evento);
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
}
