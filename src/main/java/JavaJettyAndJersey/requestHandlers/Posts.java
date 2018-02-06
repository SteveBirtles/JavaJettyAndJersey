package JavaJettyAndJersey.requestHandlers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Posts {

    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String postMethod(@FormParam("name") String name) {
        return "<h2>Message received from " + name + "</h2>";
    }

}
