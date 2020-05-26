package libs;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

  private static final String url = "jdbc:postgresql://ec2-54-86-170-8.compute-1.amazonaws.com:5432/d109e5ptr6dgq6";
  private static final String username = "nmcohbgcrqlsmq";
  private static final String pass = "3031cc02c67cbe7864235c7b6eb76021abc5f14116b7a8ed816b5152eebec160";


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
//
//  public static void prepare(String uri, String user, String password){
//    prepare(uri, user, password, true);
//  }
//  static void prepare(String uri, String user, String password, boolean clear) {
//    FluentConfiguration config = new FluentConfiguration()
//            .dataSource(uri, user, password);
//    Flyway flyway = new Flyway(config);
//    if (clear) flyway.clean();
//    flyway.migrate();
//  }
}
