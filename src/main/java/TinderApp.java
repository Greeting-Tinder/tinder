import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.StaticServlet;
import servlet.*;

public class TinderApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9000);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new HomeServlet()), "/*");
        handler.addServlet(new ServletHolder(new StaticServlet()), "/static/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        handler.addServlet(new ServletHolder(new LikeServlet()), "/like/*");
        handler.addServlet(new ServletHolder(new LikedServlet()), "/liked/*");
        handler.addServlet(new ServletHolder(new MessagesServlet()), "/messages/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
