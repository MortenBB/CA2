package Rest;

import Exceptions.NotFoundException;
import Entity.Person;
import Entity.PersonDTO;
import Facade.PersonFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
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

    /**
     * This method gets a person from id
     *
     * @param id
     * @return
     */
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

    /**
     * This method returns a person from phone(number).
     *
     * @param phone
     * @return
     */
    @GET
    @Path("/getPhone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findFromPhone(@PathParam("phone") int phone) throws NotFoundException {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));

        PersonDTO persDTO = pfacade.findFromPhone(phone);
        if (persDTO == null) {
            throw new NotFoundException("No Person With This Phone nr: " + phone);
        }
        return Response.ok().entity(gson.toJson(persDTO)).build();
    }

    /**
     * This method returns all persons with a given hobby
     *
     * @param hobby
     * @return
     * @throws NotFoundException
     */
    @GET
    @Path("/getHobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findFromHobby(@PathParam("hobby") String hobby) throws NotFoundException {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));

        PersonDTO persDTO = pfacade.findFromHobby(hobby);
        if (persDTO == null) {
            throw new NotFoundException("No Person With This Hobby: " + hobby);
        }
        return Response.ok().entity(gson.toJson(persDTO)).build();
    }

    /**
     * returns all persons with a given zipCode
     *
     * @param zip
     * @return
     * @throws NotFoundException
     */
    @GET
    @Path("/getCity/{zip}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findFromZip(@PathParam("zip") int zip) throws NotFoundException {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));

        ArrayList<PersonDTO> persDTO = pfacade.findFromCity(zip);
        if (persDTO == null) {
            throw new NotFoundException("No Person From This PostalCode: " + zip);
        }
        return Response.ok().entity(gson.toJson(persDTO)).build();
    }

    /**
     * This method takes an id and saves the new input to that person id. So it
     * overwrites the existing data.
     *
     * @param content
     * @param id
     * @return
     */
    @PUT
    @Path("edit/{id}")
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

    /**
     * Create a new Person given a JSON object
     *
     * @param content
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(String content) {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        Person newPerson = gson.fromJson(content, Person.class);
        pfacade.addPerson(newPerson);
        return Response.ok().entity(gson.toJson(newPerson)).build();
    }

    /**
     * Delete a person with a given id.
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id) {
        pfacade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        String jsonPerosn = gson.toJson(pfacade.deletePerson(id));
        return Response.ok().entity(gson.toJson(jsonPerosn)).build();
    }
}
