import filter.LoginFilter;
import libs.DbConnection;
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

        final String url = "jdbc:postgresql://ec2-52-202-22-140.compute-1.amazonaws.com:5432/d5j53goiqufuts";
        final String username = "wtgcnsjmuhjwcu";
        final String pass = "63246260ebb4709c5fa444c4d6b356ce4d2dd0d964399a9dee00a1a3643e497d";

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

  //      DbConnection.prepare(url, username, pass);


        server.start();
        server.join();
    }
}
