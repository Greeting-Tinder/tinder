package libs;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

  private static final String url = "jdbc:postgresql://ec2-52-202-22-140.compute-1.amazonaws.com:5432/d5j53goiqufuts";
  private static final String username = "wtgcnsjmuhjwcu";
  private static final String pass = "63246260ebb4709c5fa444c4d6b356ce4d2dd0d964399a9dee00a1a3643e497d";


  private static Connection connection;

  private DbConnection() {}

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(url, username, pass);
      } catch (SQLException e) {
        throw new RuntimeException("Something went wrong during connection", e);
      }
    }
    return connection;
  }

  public static void prepare(String uri, String user, String password){
    prepare(uri, user, password, true);
  }
  static void prepare(String uri, String user, String password, boolean clear) {
    FluentConfiguration config = new FluentConfiguration()
            .dataSource(uri, user, password);
    Flyway flyway = new Flyway(config);
    if (clear) flyway.clean();
    flyway.migrate();
  }
}
