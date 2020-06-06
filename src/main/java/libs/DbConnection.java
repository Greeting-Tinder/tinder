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

    private static final String url = "jdbc:postgresql://ec2-34-194-198-176.compute-1.amazonaws.com:5432/dcvoocgd20ti7v";

    private static final String username = "hifexmldvumeij";
    private static final String pass = "f88ff3edb228909aa2e02cc080e2012b918f781c87b9cb9eb2bd2acbf24ce1ed";


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
