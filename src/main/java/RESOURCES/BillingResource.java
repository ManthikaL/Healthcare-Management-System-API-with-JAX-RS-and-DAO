/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RESOURCES;

import DAO.BillingDAO;
import MODEL.Billing;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/billings")
public class BillingResource {

    private static final Logger logger = Logger.getLogger(BillingResource.class.getName());
    private BillingDAO billingDAO = new BillingDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billing> getAllBillings() {
        logger.log(Level.INFO, "Fetching all billings");
        try {
            return billingDAO.getAllBillings();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch billings: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch billings", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Billing getBillingById(@PathParam("billingId") String billingId) {
        logger.log(Level.INFO, "Fetching billing by ID: " + billingId);
        try {
            Billing billing = billingDAO.getBillingById(billingId);
            if (billing != null) {
                return billing;
            } else {
                logger.log(Level.WARNING, "Billing not found with ID: " + billingId);
                throw new WebApplicationException("Billing not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch billing with ID: " + billingId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch billing", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        logger.log(Level.INFO, "Adding new billing: " + billing);
        try {
            billingDAO.addBilling(billing);
            return Response.status(Response.Status.CREATED).entity("Billing added successfully").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add billing: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to add billing", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("/{billingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("billingId") String billingId, Billing updatedBilling) {
        logger.log(Level.INFO, "Updating billing with ID: " + billingId);
        try {
            Billing existingBilling = billingDAO.getBillingById(billingId);
            if (existingBilling != null) {
                updatedBilling.setId(billingId);
                billingDAO.updateBilling(updatedBilling);
                return Response.ok().entity("Billing updated successfully").build();
            } else {
                logger.log(Level.WARNING, "Billing not found with ID: " + billingId);
                throw new WebApplicationException("Billing not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update billing with ID: " + billingId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to update billing", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/{billingId}")
    public Response deleteBilling(@PathParam("billingId") String billingId) {
        logger.log(Level.INFO, "Deleting billing with ID: " + billingId);
        try {
            billingDAO.deleteBilling(billingId);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete billing with ID: " + billingId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to delete billing", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}