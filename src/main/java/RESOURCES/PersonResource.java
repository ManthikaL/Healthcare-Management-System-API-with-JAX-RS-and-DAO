/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RESOURCES;

/**
 *
 * @author chamo
 */
import DAO.PersonDAO;
import MODEL.Person;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/persons")
public class PersonResource {

    private static final Logger logger = Logger.getLogger(PersonResource.class.getName());
    private PersonDAO personDAO = new PersonDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
        logger.log(Level.INFO, "Fetching all persons");
        try {
            return personDAO.getAllPersons();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch persons: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch persons", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(@PathParam("personId") String personId) {
        logger.log(Level.INFO, "Fetching person by ID: " + personId);
        try {
            Person person = personDAO.getPersonById(personId);
            if (person != null) {
                return person;
            } else {
                logger.log(Level.WARNING, "Person not found with ID: " + personId);
                throw new WebApplicationException("Person not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch person with ID: " + personId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch person", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        logger.log(Level.INFO, "Adding new person: " + person);
        try {
            personDAO.addPerson(person);
            return Response.status(Response.Status.CREATED).entity("Person added successfully").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add person: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to add person", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("personId") String personId, Person updatedPerson) {
        logger.log(Level.INFO, "Updating person with ID: " + personId);
        try {
            Person existingPerson = personDAO.getPersonById(personId);
            if (existingPerson != null) {
                updatedPerson.setId(personId);
                personDAO.updatePerson(updatedPerson);
                return Response.ok().entity("Person updated successfully").build();
            } else {
                logger.log(Level.WARNING, "Person not found with ID: " + personId);
                throw new WebApplicationException("Person not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update person with ID: " + personId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to update person", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/{personId}")
    public Response deletePerson(@PathParam("personId") String personId) {
        logger.log(Level.INFO, "Deleting person with ID: " + personId);
        try {
            personDAO.deletePerson(personId);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete person with ID: " + personId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to delete person", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}