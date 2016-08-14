/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.QuoteNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author RolfMoikjær
 */
@Path("quote")
public class Basic {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser parser = new JsonParser();

    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Basic
     */
    public Basic() {
    }

    /**
     * Retrieves representation of an instance of rest.Basic
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getJson(@PathParam("id") String id) throws QuoteNotFoundException {
        //TODO return proper representation object
        int newId = Integer.parseInt(id);
        if (quotes.get(newId) == null) {
            throw new IllegalArgumentException("Forkert id");
        }
        String quote = quotes.get(newId);
        JsonObject response = new JsonObject();
        response.addProperty("quote", quote);
        return gson.toJson(response);
    }

    @GET
    @Path("random")
    @Produces("application/json")
    public String getRandom() {
        Random rNumb = new Random();
        JsonObject json = new JsonObject();
        String quote = null;
        int randomIndex = rNumb.nextInt(quotes.size());
        int currentIndex = 0;

        for (Entry<Integer, String> quoteItem : quotes.entrySet()) {
            if (randomIndex == currentIndex) {
                quote = quoteItem.getValue();
                json.addProperty("quote", quote);
                break;
            }
            currentIndex += 1;
        }
        return gson.toJson(json);
    }

    @PUT
    //@Consumes("application/json")
    @Path("{id}")
    public String putJson(@PathParam("id") String id, String content) {
        //text = content;
        JsonObject request = parser.parse(content).getAsJsonObject();
        String quote = request.get("quote").getAsString();
        int newId = Integer.parseInt(id);

        quotes.put(newId, quote);
        JsonObject response = new JsonObject();
        response.addProperty("id", newId);
        response.addProperty("quote", quote);
        return gson.toJson(response);
    }

    @POST
    public String postJson(String content) {
        JsonObject request = parser.parse(content).getAsJsonObject();
        String quote = request.get("quote").getAsString();

        quotes.put(quotes.size() + 1, quote);
        JsonObject response = new JsonObject();
        response.addProperty("id", quotes.size());
        response.addProperty("quote", quote);
        return gson.toJson(response);
    }

    @DELETE
    @Path("{id}")
    public String deleteJson(@PathParam("id") String id) {
        int newId = Integer.parseInt(id);
        String quote = quotes.get(newId);

        JsonObject response = new JsonObject();
        response.addProperty("id", id);
        response.addProperty("quote", quote);

        quotes.remove(newId);
        return gson.toJson(response);
    }
}
