package JavaJettyAndJersey.requestHandlers;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/")
public class Gets {

    @GET
    @Path("setcookie")
    @Produces(MediaType.TEXT_PLAIN)
    public Response setcookie(@QueryParam("name") String name, @QueryParam("value") String value) {
        System.out.println("Setting cookie.");
        NewCookie cookie = new NewCookie(name, value);
        return Response.ok("OK").cookie(cookie).build();
    }

    @GET
    @Path("getcookie")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getcookie(@CookieParam("test") Cookie cookie) {
        if (cookie == null) {
            System.out.println("Can't find requested cookie.");
            return Response.serverError().entity("ERROR").build();
        } else {
            System.out.println("Retrieving cookie.");
            return Response.ok(cookie.getValue()).build();
        }
    }

    @GET
    @Path("clearcookie")
    @Produces(MediaType.TEXT_PLAIN)
    public Response clearcookie(@CookieParam("test") Cookie cookie) {
        if (cookie != null) {
            System.out.println("Clearing cookie.");
            NewCookie newCookie = new NewCookie(cookie, null, 0, false);
            return Response.ok("OK").cookie(newCookie).build();
        }
        System.out.println("No cookie to clear.");
        return Response.ok("OK - No session").build();
    }

    @GET
    @Path("listcookies")
    public Response list(@Context HttpHeaders headers){
        for (String name : headers.getCookies().keySet()) {
            Cookie cookie = headers.getCookies().get(name);
            System.out.println("Cookie: " + name + "=" + cookie.getValue());
        }
        return Response.ok().build();
    }

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
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

    @GET
    @Path("boo/{var}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pathMethod(@PathParam("var") String name, @QueryParam("type") String type) {
        return type + " boo to " + name;
    }

}