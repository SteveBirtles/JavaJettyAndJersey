package server.Controllers;

import server.Console;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class RootController {

    @POST
    @Path("form1")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String postMethod(@FormParam("name") String name) {
        Console.log("POST Request received with form data name=" + name);
        return "<h2>Message received from " + name + "</h2>";
    }

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_HTML)
    public String helloWorld() {
        Console.log("GET Request received on path 'hello'...");
        return "<p>Hello, world!</p>";
    }

    @GET
    @Path("hi")
    @Produces(MediaType.TEXT_PLAIN)
    public String paramMethod(@QueryParam("name") String name) {
        Console.log("GET Request received on path 'hi' with query string name=" + name);
        return "Hi, " + name;
    }

    @GET
    @Path("greet/{var}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pathMethod(@PathParam("var") String name) {
        Console.log("GET Request received on path 'greet' with resource identifier " + name);
        return "Greetings, " + name;
    }

    @GET
    @Path("boo/{var}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pathMethod(@PathParam("var") String name, @QueryParam("type") String type) {
        return type + " boo to " + name;
    }

}
