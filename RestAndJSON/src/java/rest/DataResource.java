/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import converters.DataSerializer;
import entity.DataGenerator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author RolfMoikjær
 */
@Path("adresses")
public class DataResource {

    @Context
    private UriInfo context;

    public DataResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{amount}/{properties}")
    public Response get(@PathParam("amount") int amount, @PathParam("properties") String properties) {

        return Response.ok(DataSerializer.dataToJson(DataGenerator.getData(amount, properties))).header("Access-Control-Allow-Origin", "*").build();
    }

}
