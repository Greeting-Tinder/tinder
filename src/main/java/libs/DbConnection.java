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

    private static final String url = System.getenv("jdbc:postgresql://ec2-34-194-198-176.compute-1.amazonaws.com:5432/d3ptkqd1pikd7a");
    private static final String username = System.getenv("kpzzwsvipkolxa");
    private static final String pass = System.getenv("9266dd2802769122e2c224c777ae7fc930f14190e57c7855dad4d6c145060695");

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
