package model.utente.giocatore;

import control.DriverManagerConnectionPool;
import util.HashTool;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class GiocatoreDAO.
 */
public class GiocatoreDAO {

  /** The Constant TABLE_NAME. */
  private static final String TABLE_NAME = "giocatore";

  /**
   * Do save.
   *
   * @param e the e
   * @throws SQLException the SQL exception
   */
  public synchronized void doSave(GiocatoreBean e) throws SQLException, NoSuchAlgorithmException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSQL = "insert into " + TABLE_NAME
        + " (username, e_mail, nome, cognome, password_giocatore, telefono, data_nascita, nazione_residenza, provincia_residenza, citta_residenza, cap_residenza, valutazione) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setString(1, e.getUsername());
      preparedStatement.setString(2, e.getEmail());
      preparedStatement.setString(3, e.getNome());
      preparedStatement.setString(4, e.getCognome());
      preparedStatement.setString(5, e.getEncPassword());
      preparedStatement.setString(6, e.getTelefono());
      preparedStatement.setDate(7, e.getDataNascita());

      preparedStatement.setString(8, e.getNazioneResidenza());
      preparedStatement.setString(9, e.getProvinciaResidenza());
      preparedStatement.setString(10, e.getCittaResidenza());
      preparedStatement.setString(11, e.getCapResidenza());
      preparedStatement.setFloat(12, e.getValutazione());
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
   * @return the giocatore bean
   */
  public synchronized GiocatoreBean doRetrieveByKey(String email) {

    Connection conn = null;
    PreparedStatement ps = null;
    try {
      GiocatoreBean bean = new GiocatoreBean();
      conn = DriverManagerConnectionPool.getConnection();
      ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE e_mail = ?");
      ps.setString(1, email);

      ResultSet rs = ps.executeQuery();

      // 4. Prendi il risultato
      if (rs.next()) {
        bean.setUsername(rs.getString("username"));
        bean.setEmail(rs.getString("e_mail"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setEncPassword(rs.getString("password_giocatore"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setDataNascita(rs.getDate("data_nascita"));
        bean.setNazioneResidenza(rs.getString("nazione_residenza"));
        bean.setProvinciaResidenza(rs.getString("provincia_residenza"));
        bean.setCittaResidenza(rs.getString("citta_residenza"));
        bean.setCapResidenza(rs.getString("cap_residenza"));
        bean.setValutazione(rs.getFloat("valutazione"));
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
   * Do retrieve by username.
   *
   * @param username the username
   * @return the giocatore bean
   */
  public synchronized GiocatoreBean doRetrieveByUsername(String username) {

    Connection conn = null;
    PreparedStatement ps = null;
    try {
      GiocatoreBean bean = new GiocatoreBean();
      conn = DriverManagerConnectionPool.getConnection();
      ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE username = ?");
      ps.setString(1, username);

      ResultSet rs = ps.executeQuery();

      // 4. Prendi il risultato
      if (rs.next()) {
        bean.setUsername(rs.getString("username"));
        bean.setEmail(rs.getString("e_mail"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setEncPassword(rs.getString("password_giocatore"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setDataNascita(rs.getDate("data_nascita"));
        bean.setNazioneResidenza(rs.getString("nazione_residenza"));
        bean.setProvinciaResidenza(rs.getString("provincia_residenza"));
        bean.setCittaResidenza(rs.getString("citta_residenza"));
        bean.setCapResidenza(rs.getString("cap_residenza"));
        bean.setValutazione(rs.getFloat("valutazione"));
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
   * Do retrieve all.
   *
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<GiocatoreBean> doRetrieveAll() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<GiocatoreBean> giocatori = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        GiocatoreBean bean = new GiocatoreBean();
        bean.setUsername(rs.getString("username"));
        bean.setEmail(rs.getString("e_mail"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setEncPassword(rs.getString("password_giocatore"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setDataNascita(rs.getDate("data_nascita"));
        bean.setNazioneResidenza(rs.getString("nazione_residenza"));
        bean.setProvinciaResidenza(rs.getString("provincia_residenza"));
        bean.setCittaResidenza(rs.getString("citta_residenza"));
        bean.setCapResidenza(rs.getString("cap_residenza"));
        bean.setValutazione(rs.getFloat("valutazione"));
        giocatori.add(bean);

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
    return giocatori;
  }

  /**
   * Do update.
   *
   * @param e the e
   * @throws SQLException the SQL exception
   */
  public synchronized void doUpdate(GiocatoreBean e) throws SQLException, NoSuchAlgorithmException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String updateSQL = "UPDATE " + TABLE_NAME
        + " SET username = ?, e_mail = ?, nome = ? , cognome = ?, password_giocatore = ?, telefono = ?, data_nascita = ?, nazione_residenza = ?, provincia_residenza = ?, citta_residenza = ?, cap_residenza = ?, valutazione = ? WHERE e_mail = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(updateSQL);
      preparedStatement.setString(1, e.getUsername());
      preparedStatement.setString(2, e.getEmail());
      preparedStatement.setString(3, e.getNome());
      preparedStatement.setString(4, e.getCognome());
      preparedStatement.setString(5, e.getEncPassword());
      preparedStatement.setString(6, e.getTelefono());
      preparedStatement.setDate(7, e.getDataNascita());
      preparedStatement.setString(8, e.getNazioneResidenza());
      preparedStatement.setString(9, e.getProvinciaResidenza());
      preparedStatement.setString(10, e.getCittaResidenza());
      preparedStatement.setString(11, e.getCapResidenza());
      preparedStatement.setFloat(12, e.getValutazione());
      preparedStatement.setString(13, e.getEmail());
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
   * Check if exists the user with that specific email and password and return it.
   *
   * @param email
   * @param clearPassword
   * @return the gestore bean
   */
  public synchronized GiocatoreBean doRetrieveByAuth(String email, String clearPassword) throws NoSuchAlgorithmException {

    Connection conn = null;
    PreparedStatement preparedStatement = null;

    HashTool hash = new HashTool();
    String encPassword = hash.hashSHA256(clearPassword);

    try {
      GiocatoreBean bean = new GiocatoreBean();
      conn = DriverManagerConnectionPool.getConnection();
      preparedStatement = conn
              .prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE e_mail = ? AND password_giocatore = ?");
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, encPassword);

      ResultSet rs = preparedStatement.executeQuery();

      // 4. Prendi il risultato
      if (rs.next()) {
        bean.setUsername(rs.getString("username"));
        bean.setEmail(rs.getString("e_mail"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setEncPassword(rs.getString("password_giocatore"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setDataNascita(rs.getDate("data_nascita"));
        bean.setNazioneResidenza(rs.getString("nazione_residenza"));
        bean.setProvinciaResidenza(rs.getString("provincia_residenza"));
        bean.setCittaResidenza(rs.getString("citta_residenza"));
        bean.setCapResidenza(rs.getString("cap_residenza"));
        bean.setValutazione(rs.getFloat("valutazione"));

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
