package Rest;

import Facade.PersonFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Gonners
 */
@Path("Person")
public class PersonResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;
    
    PersonFacade pfacade = new PersonFacade();

    public PersonResource() {
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByID(@PathParam("id") int id) {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        return Response.ok().entity(gson.toJson(pfacade.getPerson(id))).build();
    }

}