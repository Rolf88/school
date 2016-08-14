/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import exceptions.QuoteNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author RolfMoikjær
 */
@Provider
public class QuoteNotFoundExMapper implements ExceptionMapper<QuoteNotFoundException> {

    Gson gson = new Gson();

    @Override
    public Response toResponse(QuoteNotFoundException e) {
        JsonObject jObj = new JsonObject();
        jObj.addProperty("code", "404");
        jObj.addProperty("msg", "No quote found at this index");
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(gson.toJson(jObj))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    
}
