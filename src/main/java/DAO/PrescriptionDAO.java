/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Prescription;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrescriptionDAO {
    private static final Logger logger = Logger.getLogger(PrescriptionDAO.class.getName());

    private static List<Prescription> prescriptions = new ArrayList<>();
    private static PatientDAO patientDAO = new PatientDAO();

    static {
        prescriptions.add(new Prescription("Pre1", patientDAO.getPatientById("P1"), "Piriton", "1 Per Day", "Night", 7 ));
    }

    public List<Prescription> getAllPrescriptions() {
        logger.log(Level.INFO, "Fetching all prescriptions");
        return prescriptions;
    }

    public Prescription getPrescriptionById(String id) {
        logger.log(Level.INFO, "Fetching prescription by ID: " + id);
        for (Prescription prescription : prescriptions) {
            if (prescription.getId().equals(id)) {
                logger.log(Level.INFO, "Prescription found with ID: " + id);
                return prescription;
            }
        }
        logger.log(Level.WARNING, "Prescription not found with ID: " + id);
        return null;
    }

    public void addPrescription(Prescription prescription) {
        logger.log(Level.INFO, "Adding new prescription: " + prescription);
        prescriptions.add(prescription);
    }

    public void updatePrescription(Prescription updatedPrescription) {
        logger.log(Level.INFO, "Updating prescription with ID: " + updatedPrescription.getId());
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            if (prescription.getId().equals(updatedPrescription.getId())) {
                prescriptions.set(i, updatedPrescription);
                logger.log(Level.INFO, "Prescription updated: " + updatedPrescription);
                return;
            }
        }
        logger.log(Level.WARNING, "Prescription not found with ID: " + updatedPrescription.getId() + ", update failed");
    }

    public void deletePrescription(String id) {
        logger.log(Level.INFO, "Deleting prescription with ID: " + id);
        prescriptions.removeIf(prescription -> prescription.getId().equals(id));
    }
}