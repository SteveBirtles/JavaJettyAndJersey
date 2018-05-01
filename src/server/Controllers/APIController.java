package server.Controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Console;
import server.Model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;

@Path("api/message/")
@SuppressWarnings("unchecked")
public class APIController {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String apiTest() {
        JSONArray messageList = new JSONArray();
        for (Message m : Message.allMessages) {
            JSONObject nextMessage = new JSONObject();
            nextMessage.put("id", m.getId());
            nextMessage.put("name", m.getText());
            nextMessage.put("postdate", m.getPostDate());
            nextMessage.put("author", m.getAuthor());
            messageList.add(nextMessage);
        }
        return messageList.toString();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String addMessage(@FormParam("new_message") String newMessage, @FormParam("new_author") String newAuthor) {
        Console.log("POST Request received with new_message=" + newMessage + ", new_author=" + newAuthor);
        Message.allMessages.add(new Message(newMessage, newAuthor));
        return "OK";
    }

    @POST
    @Path("remove")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String removeMessage(@FormParam("old_message_id") int oldMessageId) {
        Console.log("POST Request received with old_message_id=" + oldMessageId);
        Message oldMessage = null;
        for (Message m: Message.allMessages) {
            if (oldMessageId == m.getId()) {
                oldMessage = m;
                break;
            }
        }
        if (oldMessage == null) {
            return "That message doesn't appear to exist anymore";
        } else {
            Message.allMessages.remove(oldMessage);
            return "OK";
        }
    }

}
