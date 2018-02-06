package JavaJettyAndJersey;

/* Original source https://www.acando.no/thedailypassion/200555/a-rest-service-with-jetty-and-jersey */

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class AppStart {
    public static void main(String[] args) {

        ResourceConfig config = new ResourceConfig();
        config.packages("JavaJettyAndJersey");
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

