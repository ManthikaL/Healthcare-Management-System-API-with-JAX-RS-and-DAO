package DAO;

import MODEL.Billing;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillingDAO {
    private static final Logger logger = Logger.getLogger(BillingDAO.class.getName());

    private static List<Billing> billings = new ArrayList<>();
    private static PatientDAO patientDAO = new PatientDAO();

    static {
        billings.add(new Billing("B1", patientDAO.getPatientById("P1"), 45500, 45550, "2021.12.21"));
    }

    public List<Billing> getAllBillings() {
        logger.log(Level.INFO, "Fetching all billings");
        return billings;
    }

    public Billing getBillingById(String id) {
        logger.log(Level.INFO, "Fetching billing by ID: " + id);
        for (Billing billing : billings) {
            if (billing.getId().equals(id)) {
                logger.log(Level.INFO, "Billing found with ID: " + id);
                return billing;
            }
        }
        logger.log(Level.WARNING, "Billing not found with ID: " + id);
        return null;
    }

    public void addBilling(Billing billing) {
        logger.log(Level.INFO, "Adding new billing: " + billing);
        billings.add(billing);
    }

    public void updateBilling(Billing updatedBilling) {
        logger.log(Level.INFO, "Updating billing with ID: " + updatedBilling.getId());
        for (int i = 0; i < billings.size(); i++) {
            Billing billing = billings.get(i);
            if (billing.getId().equals(updatedBilling.getId())) {
                billings.set(i, updatedBilling);
                logger.log(Level.INFO, "Billing updated: " + updatedBilling);
                return;
            }
        }
        logger.log(Level.WARNING, "Billing not found with ID: " + updatedBilling.getId() + ", update failed");
    }

    public void deleteBilling(String id) {
        logger.log(Level.INFO, "Deleting billing with ID: " + id);
        billings.removeIf(billing -> billing.getId().equals(id));
    }
}