package rest;

import deploy.DeploymentConfiguration;
import entity.Person;
import exceptions.PersonNotFoundException;
import facade.JSONConverter;
import facade.PersonFacade;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("person")
public class PersonResource {
  

  @Context
  private UriInfo context;

  @Context
  ServletContext servletContext;

  PersonFacade facade = new PersonFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));

  /**
   * Creates a new instance of GenericResource
   */
  public PersonResource() {}


  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getPerson(@PathParam("id") int id) throws PersonNotFoundException {
     return JSONConverter.getJSONFromPerson(facade.getPerson(id));
  }
    
  @GET
  @Produces("application/json")
  public String getPerson() {
    return JSONConverter.getJSONFromPerson(facade.getPersons());
  }

  @POST
  @Produces("application/json")
  @Consumes("application/json")
  public String savePerson(String person) {
    Person p = JSONConverter.getPersonFromJson(person);
    p = facade.addPerson(p);
    return JSONConverter.getJSONFromPerson(p);
  }

  @PUT
  @Produces("application/json")
  @Consumes("application/json")
  public String editPerson(String person) throws PersonNotFoundException {
    Person p = JSONConverter.getPersonFromJson(person);
    p = facade.editPerson(p);
    return JSONConverter.getJSONFromPerson(p);
  }

  @DELETE
  @Path("{id}")
  @Produces("application/json")
  public String deletePerson(@PathParam("id") int id) throws PersonNotFoundException {
    Person p = facade.deletePerson(id);
    return JSONConverter.getJSONFromPerson(p);
  }

}
