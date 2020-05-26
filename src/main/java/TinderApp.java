import filter.LoginFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.StaticServlet;
import servlet.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class TinderApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(Heroku.port());
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new HomeServlet()), "/*");
        handler.addServlet(new ServletHolder(new StaticServlet()), "/static/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        handler.addServlet(new ServletHolder(new LikeServlet()), "/like/*");
        handler.addServlet(new ServletHolder(new LikedServlet()), "/liked/*");
        handler.addServlet(new ServletHolder(new MessagesServlet()), "/messages/*");
        handler.addFilter(new FilterHolder(new LoginFilter()), "/like/*", EnumSet.of(DispatcherType.REQUEST));
        server.setHandler(handler);
        
        server.start();
        server.join();
    }
}
