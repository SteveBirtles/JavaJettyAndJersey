package server.Controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Console;
import server.Model.Message;
import server.Model.MessageService;
import server.ServerStart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Path("api/message/")
@SuppressWarnings("unchecked")
public class APIController {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listMessages() {
        JSONArray messageList = new JSONArray();

        ArrayList<Message> allMessages = new ArrayList<>();

        MessageService.selectAll(allMessages, ServerStart.database);

        for (Message m : allMessages) {
            JSONObject nextMessage = new JSONObject();
            nextMessage.put("id", m.getId());
            nextMessage.put("text", m.getText());
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
        MessageService.save(new Message(0, newMessage, null, newAuthor), ServerStart.database);
        return "OK";
    }

    @POST
    @Path("remove")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String removeMessage(@FormParam("old_message_id") int oldMessageId) {
        Console.log("POST Request received with old_message_id=" + oldMessageId);
        Message oldMessage = MessageService.selectById(oldMessageId, ServerStart.database);

        if (oldMessage == null) {
            return "That message doesn't appear to exist anymore";
        } else {
            MessageService.deleteById(oldMessageId, ServerStart.database);
            return "OK";
        }
    }

}
