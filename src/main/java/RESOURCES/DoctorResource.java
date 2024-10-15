/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RESOURCES;

/**
 *
 * @author chamo
 */

import DAO.DoctorDAO;
import MODEL.Doctor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/doctors")
public class DoctorResource {

    private static final Logger logger = Logger.getLogger(DoctorResource.class.getName());
    private DoctorDAO doctorDAO = new DoctorDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctors() {
        logger.log(Level.INFO, "Fetching all doctors");
        try {
            return doctorDAO.getAllDoctors();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch doctors: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch doctors", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor getDoctorById(@PathParam("doctorId") String doctorId) {
        logger.log(Level.INFO, "Fetching doctor by ID: " + doctorId);
        try {
            Doctor doctor = doctorDAO.getDoctorById(doctorId);
            if (doctor != null) {
                return doctor;
            } else {
                logger.log(Level.WARNING, "Doctor not found with ID: " + doctorId);
                throw new WebApplicationException("Doctor not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch doctor with ID: " + doctorId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch doctor", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        logger.log(Level.INFO, "Adding new doctor: " + doctor);
        try {
            doctorDAO.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity("Doctor added successfully").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add doctor: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to add doctor", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("doctorId") String doctorId, Doctor updatedDoctor) {
        logger.log(Level.INFO, "Updating doctor with ID: " + doctorId);
        try {
            Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);
            if (existingDoctor != null) {
                updatedDoctor.setId(doctorId);
                doctorDAO.updateDoctor(updatedDoctor);
                return Response.ok().entity("Doctor updated successfully").build();
            } else {
                logger.log(Level.WARNING, "Doctor not found with ID: " + doctorId);
                throw new WebApplicationException("Doctor not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update doctor with ID: " + doctorId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to update doctor", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/{doctorId}")
    public Response deleteDoctor(@PathParam("doctorId") String doctorId) {
        logger.log(Level.INFO, "Deleting doctor with ID: " + doctorId);
        try {
            doctorDAO.deleteDoctor(doctorId);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete doctor with ID: " + doctorId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to delete doctor", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}