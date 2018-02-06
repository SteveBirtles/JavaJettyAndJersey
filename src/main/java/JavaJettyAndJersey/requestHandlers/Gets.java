package JavaJettyAndJersey.requestHandlers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Gets {

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello, world!";
    }

    @GET
    @Path("hi")
    @Produces(MediaType.TEXT_PLAIN)
    public String paramMethod(@QueryParam("name") String name) {
        return "Hi, " + name;
    }

    @GET
    @Path("greet/{var}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pathMethod(@PathParam("var") String name) {
        return "Greetings, " + name;
    }

}