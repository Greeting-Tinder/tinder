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

    private static final String url = "jdbc:postgresql://ec2-3-215-83-17.compute-1.amazonaws.com:5432/d6ktia2iehkj63;JDBC_USER=bchwpemrhwwufm";
    private static final String username = System.getenv("bchwpemrhwwufm");
    private static final String pass = System.getenv("75f038385dc4da2afbe7a4d269c30bebe5768f71bf2df7f4d90b8b0a326fb529");


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
