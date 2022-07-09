package model.evento;

import control.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Class EventoDAO.
 */
public class EventoDAO {

  /** The Constant TABLE_NAME. */
  private static final String TABLE_NAME = "evento";

  /**
   * Do save.
   *
   * @param e the e
   * @throws SQLException the SQL exception
   */
  public synchronized void doSave(EventoBean e) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String insertSQL = "insert into " + TABLE_NAME
        + " (nome, descrizione, struttura, data_evento, ora, e_mail_gestore, e_mail_utente, stato, valutazione, numero_partecipanti, tipologia) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setString(1, e.getNome());
      preparedStatement.setString(2, e.getDescrizione());
      preparedStatement.setString(3, e.getStruttura());
      preparedStatement.setDate(4, e.getData());
      preparedStatement.setInt(5, e.getOra());
      preparedStatement.setString(6, e.getGestore());
      preparedStatement.setString(7, e.getOrganizzatore());
      preparedStatement.setString(8, e.getStato());
      preparedStatement.setFloat(9, e.getValutazione());
      preparedStatement.setInt(10, e.getNumPartecipanti());
      preparedStatement.setString(11, e.getTipologia());
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
   * @return the evento bean
   */
  public synchronized EventoBean doRetrieveByKey(String nome) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    EventoBean bean = new EventoBean();
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE nome = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, nome);
      ResultSet rs = preparedStatement.executeQuery();
      // 4. Prendi il risultato
      if (rs.next()) {
        bean.setNome(rs.getString("nome"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setStruttura(rs.getString("struttura"));
        bean.setData(rs.getDate("data_evento"));
        bean.setOra(rs.getInt("ora"));
        bean.setGestore(rs.getString("e_mail_gestore"));
        bean.setOrganizzatore(rs.getString("e_mail_utente"));
        bean.setStato(rs.getString("stato"));
        bean.setValutazione(rs.getFloat("valutazione"));
        bean.setNumPartecipanti(rs.getInt("numero_partecipanti"));
        bean.setTipologia(rs.getString("tipologia"));
        return bean;
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
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
  public synchronized ArrayList<EventoBean> doRetrieveAll() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<EventoBean> eventi = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        EventoBean bean = new EventoBean();
        bean.setNome(rs.getString("nome"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setStruttura(rs.getString("struttura"));
        bean.setData(rs.getDate("data_evento"));
        bean.setOra(rs.getInt("ora"));
        bean.setGestore(rs.getString("e_mail_gestore"));
        bean.setOrganizzatore(rs.getString("e_mail_utente"));
        bean.setStato(rs.getString("stato"));
        bean.setValutazione(rs.getFloat("valutazione"));
        bean.setNumPartecipanti(rs.getInt("numero_partecipanti"));
        bean.setTipologia(rs.getString("tipologia"));
        eventi.add(bean);
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
    return eventi;
  }

  /**
   * Do update.
   *
   * @param e the e
   * @throws SQLException the SQL exception
   */
  public synchronized void doUpdate(EventoBean e) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String updateSQL = "UPDATE " + TABLE_NAME
        + " SET nome = ?, descrizione = ?, struttura = ? , data_evento = ?, ora = ?, e_mail_gestore = ?, e_mail_utente = ?, stato = ?, valutazione = ?, numero_partecipanti = ? WHERE nome = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(updateSQL);
      preparedStatement.setString(1, e.getNome());
      preparedStatement.setString(2, e.getDescrizione());
      preparedStatement.setString(3, e.getStruttura());
      preparedStatement.setDate(4, e.getData());
      preparedStatement.setInt(5, e.getOra());
      preparedStatement.setString(6, e.getGestore());
      preparedStatement.setString(7, e.getOrganizzatore());
      preparedStatement.setString(8, e.getStato());
      preparedStatement.setFloat(9, e.getValutazione());
      preparedStatement.setInt(10, e.getNumPartecipanti());
      preparedStatement.setString(11, e.getNome());
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

    String deleteSQL = "delete from " + TABLE_NAME + " WHERE nome = ?";

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
   * Do retrieve eventi attivi.
   *
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<EventoBean> doRetrieveEventiAttivi() throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<EventoBean> eventi = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE stato = 'attivo'";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        EventoBean bean = new EventoBean();
        bean.setNome(rs.getString("nome"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setStruttura(rs.getString("struttura"));
        bean.setData(rs.getDate("data_evento"));
        bean.setOra(rs.getInt("ora"));
        bean.setGestore(rs.getString("e_mail_gestore"));
        bean.setOrganizzatore(rs.getString("e_mail_utente"));
        bean.setStato(rs.getString("stato"));
        bean.setValutazione(rs.getFloat("valutazione"));
        bean.setNumPartecipanti(rs.getInt("numero_partecipanti"));
        bean.setTipologia(rs.getString("tipologia"));
        eventi.add(bean);
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
    return eventi;
  }

  /**
   * Do retrieve richieste.
   *
   * @param gestore the gestore
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<EventoBean> doRetrieveRichieste(String gestore)
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<EventoBean> eventi = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME
        + " WHERE e_mail_gestore = ? AND stato = 'richiesta'";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, gestore);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        EventoBean bean = new EventoBean();
        bean.setNome(rs.getString("nome"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setStruttura(rs.getString("struttura"));
        bean.setData(rs.getDate("data_evento"));
        bean.setOra(rs.getInt("ora"));
        bean.setGestore(rs.getString("e_mail_gestore"));
        bean.setOrganizzatore(rs.getString("e_mail_utente"));
        bean.setStato(rs.getString("stato"));
        bean.setValutazione(rs.getFloat("valutazione"));
        bean.setNumPartecipanti(rs.getInt("numero_partecipanti"));
        bean.setTipologia(rs.getString("tipologia"));
        eventi.add(bean);
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
    return eventi;
  }

  /**
   * Do retrieve eventi gestore.
   *
   * @param gestore the gestore
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<EventoBean> doRetrieveEventiGestore(String gestore)
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<EventoBean> eventi = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME
        + " WHERE e_mail_gestore = ? AND stato != 'richiesta' "
        + "ORDER BY data_evento desc, ora desc";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, gestore);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        EventoBean bean = new EventoBean();
        bean.setNome(rs.getString("nome"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setStruttura(rs.getString("struttura"));
        bean.setData(rs.getDate("data_evento"));
        bean.setOra(rs.getInt("ora"));
        bean.setGestore(rs.getString("e_mail_gestore"));
        bean.setOrganizzatore(rs.getString("e_mail_utente"));
        bean.setStato(rs.getString("stato"));
        bean.setValutazione(rs.getFloat("valutazione"));
        bean.setNumPartecipanti(rs.getInt("numero_partecipanti"));
        bean.setTipologia(rs.getString("tipologia"));

        eventi.add(bean);
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
    return eventi;
  }

  /**
   * Do retrieve cronologia.
   *
   * @param giocatore the giocatore
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<EventoBean> doRetrieveCronologia(String giocatore)
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<EventoBean> eventi = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME
        + " WHERE e_mail_utente = ? AND stato = 'completato'";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, giocatore);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        EventoBean bean = new EventoBean();
        bean.setNome(rs.getString("nome"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setStruttura(rs.getString("struttura"));
        bean.setData(rs.getDate("data_evento"));
        bean.setOra(rs.getInt("ora"));
        bean.setGestore(rs.getString("e_mail_gestore"));
        bean.setOrganizzatore(rs.getString("e_mail_utente"));
        bean.setStato(rs.getString("stato"));
        bean.setValutazione(rs.getFloat("valutazione"));
        bean.setNumPartecipanti(rs.getInt("numero_partecipanti"));
        bean.setTipologia(rs.getString("tipologia"));

        eventi.add(bean);
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
    return eventi;
  }

  /**
   * Do retrieve eventi.
   *
   * @param giocatore the giocatore
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<EventoBean> doRetrieveEventi(String giocatore) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<EventoBean> eventi = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE stato = 'attivo' AND nome != ALL "
        + "(SELECT nome_evento FROM partecipazione WHERE e_mail = ?) ORDER BY data_evento ASC";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, giocatore);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        EventoBean bean = new EventoBean();
        bean.setNome(rs.getString("nome"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setStruttura(rs.getString("struttura"));
        bean.setData(rs.getDate("data_evento"));
        bean.setOra(rs.getInt("ora"));
        bean.setGestore(rs.getString("e_mail_gestore"));
        bean.setOrganizzatore(rs.getString("e_mail_utente"));
        bean.setStato(rs.getString("stato"));
        bean.setValutazione(rs.getFloat("valutazione"));
        bean.setNumPartecipanti(rs.getInt("numero_partecipanti"));
        bean.setTipologia(rs.getString("tipologia"));
        eventi.add(bean);
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
    return eventi;
  }

  /**
   * Do retrieve eventi recenti.
   *
   * @param giocatore the giocatore
   * @return the array list
   * @throws SQLException the SQL exception
   */
  public synchronized ArrayList<EventoBean> doRetrieveEventiRecenti(String giocatore)
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<EventoBean> eventi = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME
        + " JOIN partecipazione P on nome = P.nome_evento "
        + "WHERE stato != 'richiesta' AND P.e_mail = ? ORDER BY data_evento desc";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, giocatore);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        EventoBean bean = new EventoBean();
        bean.setNome(rs.getString("nome"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setStruttura(rs.getString("struttura"));
        bean.setData(rs.getDate("data_evento"));
        bean.setOra(rs.getInt("ora"));
        bean.setGestore(rs.getString("e_mail_gestore"));
        bean.setOrganizzatore(rs.getString("e_mail_utente"));
        bean.setStato(rs.getString("stato"));
        bean.setValutazione(rs.getFloat("valutazione"));
        bean.setNumPartecipanti(rs.getInt("numero_partecipanti"));
        bean.setTipologia(rs.getString("tipologia"));
        eventi.add(bean);
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
    return eventi;
  }

  public synchronized ArrayList<EventoBean> doRetrieveEventiRecentiSquadra(int idSquadra)
          throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<EventoBean> eventi = new ArrayList<>();

    String selectSQL = "SELECT * FROM " + TABLE_NAME
            + " JOIN partecipazione_squadra P on nome = P.id_evento "
            + "WHERE stato != 'richiesta' AND P.id_squadra = ? ORDER BY data_evento desc";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setInt(1, idSquadra);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        EventoBean bean = new EventoBean();
        bean.setNome(rs.getString("nome"));
        bean.setDescrizione(rs.getString("descrizione"));
        bean.setStruttura(rs.getString("struttura"));
        bean.setData(rs.getDate("data_evento"));
        bean.setOra(rs.getInt("ora"));
        bean.setGestore(rs.getString("e_mail_gestore"));
        bean.setOrganizzatore(rs.getString("e_mail_utente"));
        bean.setStato(rs.getString("stato"));
        bean.setValutazione(rs.getFloat("valutazione"));
        bean.setNumPartecipanti(rs.getInt("numero_partecipanti"));
        bean.setTipologia(rs.getString("tipologia"));
        eventi.add(bean);
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
    return eventi;
  }
}