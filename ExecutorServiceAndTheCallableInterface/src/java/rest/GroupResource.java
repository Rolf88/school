/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Group;
import facade.GroupFacade;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author RolfMoikjær
 */
@Path("group")
public class GroupResource {

    @Context
    private UriInfo context;

    private final Gson gson = new GsonBuilder().create();
    private GroupFacade groupFacade;

    public GroupResource() {

    }

    @GET
    @Produces("application/json")
    public Response search() throws InterruptedException {
        groupFacade = new GroupFacade();
        List<Group> groups = groupFacade.createGroups();
        CacheControl cc = new CacheControl();
        cc.setMaxAge(3600);
        cc.setPrivate(true);

        ResponseBuilder builder = Response.ok(gson.toJson(groups));
        builder.cacheControl(cc);
        return builder.build();
    }
}
