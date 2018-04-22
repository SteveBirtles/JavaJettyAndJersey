package server.Controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("cookie/")
public class CookieController {

    @GET
    @Path("set")
    @Produces(MediaType.TEXT_PLAIN)
    public Response setcookie(@QueryParam("name") String name, @QueryParam("value") String value) {
        System.out.println("Setting cookie.");
        NewCookie cookie = new NewCookie(name, value);
        return Response.ok("OK").cookie(cookie).build();
    }

    @GET
    @Path("get")
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
    @Path("clear")
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
    @Path("list")
    public Response list(@Context HttpHeaders headers){
        for (String name : headers.getCookies().keySet()) {
            Cookie cookie = headers.getCookies().get(name);
            System.out.println("Cookie: " + name + "=" + cookie.getValue());
        }
        return Response.ok().build();
    }

}