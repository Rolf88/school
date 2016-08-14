/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import entity.Person;
import facade.JsonConverter;
import facade.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author RolfMoikjær
 */
@Path("person")
public class GenericResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser parser = new JsonParser();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RESTwithJAXRSandCwhJQAJAXPU");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getJson(@PathParam("id") String id) {
        //TODO return proper representation object
        int newId = Integer.parseInt(id);
        Person newPerson;
        try (PersonFacade facade = new PersonFacade(emf)) {

            newPerson = facade.getPerson(newId);

            return JsonConverter.getJSONFromPerson(newPerson);
        }
    }

    @GET
    @Produces("application/json")
    public String getAllPersons() {

        try (PersonFacade facade = new PersonFacade(emf)) {

            return JsonConverter.getJSONFromPerson(facade.getPersons());
        }
    }

    @POST
    public void postJson(Person person) {
        try (PersonFacade facade = new PersonFacade(emf)) {
            facade.addPerson(person);

        }
    }

    @PUT
    @Path("{id}")
    public void putPerson(@PathParam("id") String id, Person person) {
        int newId = Integer.parseInt(id);
        try (PersonFacade facade = new PersonFacade(emf)) {
            facade.editPerson(person);

        }
    }

    @DELETE
    @Path("{id}")
    public void removePerson(@PathParam("id") String id) {
        int newId = Integer.parseInt(id);
        try (PersonFacade facade = new PersonFacade(emf)) {
            facade.deletePerson(newId);

        }
    }
}
