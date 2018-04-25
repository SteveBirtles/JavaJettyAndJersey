package server.Controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Console;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api/")
@SuppressWarnings("unchecked")
public class APIController {

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String apiTest() {

        Console.log("API GET Request received on path 'test'...");

        String[] fruits = new String[] {"Apple",
                                        "Apricot",
                                        "Banana",
                                        "Blackberry",
                                        "Blackcurrant",
                                        "Blueberry",
                                        "Cherry",
                                        "Clementine",
                                        "Cranberry",
                                        "Date",
                                        "Dragonfruit",
                                        "Elderberry",
                                        "Fig",
                                        "Gooseberry",
                                        "Grape",
                                        "Grapefruit",
                                        "Guava",
                                        "Kiwifruit",
                                        "Kumquat",
                                        "Lemon",
                                        "Lime",
                                        "Lychee",
                                        "Mandarine",
                                        "Mango",
                                        "Melon",
                                        "Nectarine",
                                        "Orange",
                                        "Papaya",
                                        "Passionfruit",
                                        "Peach",
                                        "Pear",
                                        "Plum",
                                        "Pineapple",
                                        "Pomegranate",
                                        "Raspberry",
                                        "Redcurrant",
                                        "Starfruit",
                                        "Strawberry",
                                        "Tangerine"};


        JSONArray fruitList = new JSONArray();

        for (String fruitName : fruits) {
            JSONObject nextFruit = new JSONObject();
            nextFruit.put("name", fruitName);
            fruitList.add(nextFruit);
        }

        return fruitList.toString();

    }

}
