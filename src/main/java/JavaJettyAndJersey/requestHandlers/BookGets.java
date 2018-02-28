package JavaJettyAndJersey.requestHandlers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/book")
public class BookGets {

    @GET
    @Path("list")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        System.out.println("Request to list all books");
        return "Lots of books!";
    }

}
