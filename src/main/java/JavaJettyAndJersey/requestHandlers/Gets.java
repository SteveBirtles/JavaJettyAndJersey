package JavaJettyAndJersey.requestHandlers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Gets {

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        System.out.println("GET Request received on path 'hello'");
        return "Hello, world!";
    }

    @GET
    @Path("hi")
    @Produces(MediaType.TEXT_PLAIN)
    public String paramMethod(@QueryParam("name") String name) {
        System.out.println("GET Request received on path 'hi' with query string name=" + name);
        return "Hi, " + name;
    }

    @GET
    @Path("greet/{var}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pathMethod(@PathParam("var") String name) {
        System.out.println("GET Request received on path 'greet' with resource identifier " + name);
        return "Greetings, " + name;
    }

}