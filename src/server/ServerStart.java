package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import server.Model.DatabaseConnection;

import java.util.ArrayList;
import java.util.Arrays;

public class ServerStart {

    public static DatabaseConnection database;

    public static void main(String[] args) {

        database = new DatabaseConnection("src/server/Messages.db");

        ResourceConfig config = new ResourceConfig();
        config.packages("server");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}

