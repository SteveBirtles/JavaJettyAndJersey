package server.Controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Console;
import server.Model.Fruit;
import server.ServerStart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;

@Path("api/")
@SuppressWarnings("unchecked")
public class APIController {


    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String apiTest() {

        Console.log("API GET Request received on path 'test'...");

        JSONArray fruitList = new JSONArray();

        for (String fruitName : Fruit.allFruits) {
            JSONObject nextFruit = new JSONObject();
            nextFruit.put("name", fruitName);
            fruitList.add(nextFruit);
        }

        return fruitList.toString();

    }

    @POST
    @Path("fruitform")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String postMethod(@FormParam("new_fruit") String newfruit) {
        System.out.println("POST Request received with form data new_fruit=" + newfruit);
        Fruit.allFruits.add(newfruit);
        return "OK";
    }

}
