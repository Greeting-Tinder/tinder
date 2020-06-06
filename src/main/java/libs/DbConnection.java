package libs;

import dao.LikesDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    private static final Logger LOG = LogManager.getFormatterLogger(LikesDAO.class);

    private static final String url = System.getenv("JDBC_URL");
    private static final String username = System.getenv("JDBC_USER");
    private static final String pass = System.getenv("JDBC_PASS");


    private static Connection connection;


    private DbConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, pass);
            } catch (SQLException e) {
                LOG.error("Something went wrong during connection " + e);
            }
        }
        return connection;
    }

    public static void prepare() {
        prepare(url, username, pass, true);
    }

    static void prepare(String uri, String user, String password, boolean clear) {
        FluentConfiguration config = new FluentConfiguration()
                .dataSource(uri, user, password);
        Flyway flyway = new Flyway(config);
        if (clear) flyway.clean();
        flyway.migrate();
    }
}
