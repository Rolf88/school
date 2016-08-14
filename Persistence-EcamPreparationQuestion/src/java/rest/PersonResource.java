/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Person;
import facade.EntityFactory;
import facade.Facade;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author RolfMoikjær
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    @GET
    @Produces("application/json")
    @Path("{id}")
    public Response getJson(@PathParam("id") String id) {
        EntityManager emf = EntityFactory.getInstance().createEntityManager();
        Facade facade = new Facade(emf);

        return Response.ok(convertToJson(facade.findPerson(id))).build();
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {

    }

    private String convertToJson(Person person) {
        JsonObject obj = new JsonObject();
        obj.addProperty("fName", person.getfName());
        obj.addProperty("lName", person.getlName());
        obj.addProperty("age", person.getAge());

        return new Gson().toJson(obj);

    }
}
