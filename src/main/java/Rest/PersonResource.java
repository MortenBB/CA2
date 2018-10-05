package Rest;

import Exceptions.NotFoundException;
import Entity.Person;
import Entity.PersonDTO;
import Facade.PersonFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    public Response getPersonByID(@PathParam("id") int id) throws NotFoundException {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));

        PersonDTO persDTO = pfacade.getPersonByID(id);
        if (persDTO == null) {
            throw new NotFoundException("No Person with this ID: " + id);
        }
        return Response.ok().entity(gson.toJson(persDTO)).build();
    }

    @GET
    @Path("/getPhone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findFromPhone(@PathParam("phone") int phone) throws NotFoundException {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));

        PersonDTO persDTO = pfacade.getPersonByPhone(phone);
        if (persDTO == null) {
            throw new NotFoundException("No Person With This Phone nr: " + phone);
        }
            return Response.ok().entity(gson.toJson(persDTO)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(String content, @PathParam("id") int id) {
        Person newPerson = gson.fromJson(content, Person.class);
        PersonDTO savedPers = pfacade.getPersonByID(id);
        if (newPerson.getEmail() != null) {
            savedPers.setEmail(newPerson.getEmail());
        }
        if (newPerson.getFirstname() != null) {
            savedPers.setFirstname(newPerson.getFirstname());
        }
        if (newPerson.getLastname() != null) {
            savedPers.setLastname(newPerson.getLastname());
        }
        pfacade.editPerson(newPerson);
        return Response.ok().entity(gson.toJson(savedPers)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(String content) {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        Person newPerson = gson.fromJson(content, Person.class);
        pfacade.addPerson(newPerson);
        return Response.ok().entity(gson.toJson(newPerson)).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id) {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        String jsonPerosn = gson.toJson(pfacade.deletePerson(id));
        return Response.ok().entity(gson.toJson(jsonPerosn)).build();
    }
}
