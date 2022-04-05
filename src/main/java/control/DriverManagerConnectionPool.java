package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class DriverManagerConnectionPool.
 *
 * @author tanucc
 */
public class DriverManagerConnectionPool {

  /** The free db connections. */
  private static List<Connection> freeDbConnections;

  static {
    freeDbConnections = new LinkedList<>();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found:" + e.getMessage());
    }
  }

  /**
   * Creates the DB connection.
   *
   * @return the connection
   * @throws SQLException the SQL exception
   */
  private static synchronized Connection createDBConnection() throws SQLException {
    Connection newConnection = null;
    String ip = "localhost";
    String port = "3306";
    String db = "mybomber";
    //db = "mybomber_testing"; // da attivare per il testing
    String username = "root";
    String password = "tanucc"; // ognuno la propria di mysql

    newConnection = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift"
            + "=true&useLegacyDatetimeCode=false&serverTimezone=CET",
        username, password);

    newConnection.setAutoCommit(false);
    return newConnection;
  }

  /**
   * Gets the connection.
   *
   * @return the connection
   * @throws SQLException the SQL exception
   */
  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;

    if (!freeDbConnections.isEmpty()) {
      connection = freeDbConnections.get(0);
      freeDbConnections.remove(0);

      try {
        if (connection.isClosed()) {
          connection = getConnection();
        }
      } catch (SQLException e) {
        connection.close();
        connection = getConnection();
      }
    } else {
      connection = createDBConnection();
    }

    return connection;
  }

  /**
   * Release connection.
   *
   * @param connection the connection
   * @throws SQLException the SQL exception
   */
  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if (connection != null) {
      freeDbConnections.add(connection);
    }
  }

  /**
   * Clear connections.
   */
  public static synchronized void clearConnections() {
    for (Connection connection : DriverManagerConnectionPool.freeDbConnections) {
      try {
        if (!connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {

      }
    }
    DriverManagerConnectionPool.freeDbConnections.clear();
  }
}
