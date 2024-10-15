/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RESOURCES;

/**
 *
 * @author chamo 
 */

import DAO.PatientDAO;
import MODEL.Patient;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/patients")
public class PatientResource {

    private static final Logger logger = Logger.getLogger(PatientResource.class.getName());
    private PatientDAO patientDAO = new PatientDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getAllPatients() {
        logger.log(Level.INFO, "Fetching all patients");
        try {
            return patientDAO.getAllPatients();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch patients: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch patients", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("patientId") String patientId) {
        logger.log(Level.INFO, "Fetching patient by ID: " + patientId);
        try {
            Patient patient = patientDAO.getPatientById(patientId);
            if (patient != null) {
                return patient;
            } else {
                logger.log(Level.WARNING, "Patient not found with ID: " + patientId);
                throw new WebApplicationException("Patient not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch patient with ID: " + patientId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch patient", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        logger.log(Level.INFO, "Adding new patient: " + patient);
        try {
            patientDAO.addPatient(patient);
            return Response.status(Response.Status.CREATED).entity("Patient added successfully").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add patient: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to add patient", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("patientId") String patientId, Patient updatedPatient) {
        logger.log(Level.INFO, "Updating patient with ID: " + patientId);
        try {
            Patient existingPatient = patientDAO.getPatientById(patientId);
            if (existingPatient != null) {
                updatedPatient.setId(patientId);
                patientDAO.updatePatient(updatedPatient);
                return Response.ok().entity("Patient updated successfully").build();
            } else {
                logger.log(Level.WARNING, "Patient not found with ID: " + patientId);
                throw new WebApplicationException("Patient not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update patient with ID: " + patientId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to update patient", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/{patientId}")
    public Response deletePatient(@PathParam("patientId") String patientId) {
        logger.log(Level.INFO, "Deleting patient with ID: " + patientId);
        try {
            patientDAO.deletePatient(patientId);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete patient with ID: " + patientId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to delete patient", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}