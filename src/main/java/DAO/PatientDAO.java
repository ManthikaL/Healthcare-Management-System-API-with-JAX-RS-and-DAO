/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author chamo
 */

import MODEL.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientDAO {
    private static final Logger logger = Logger.getLogger(PatientDAO.class.getName());

    private static List<Patient> patients = new ArrayList<>();

    static {
        patients.add(new Patient("P1", "Nethmi", "0382545745", "5, Bandaragama", "Headache", "Good"));
        patients.add(new Patient("P2", "Shany", "077894521", "49, Panadura", "Fatty Liver", "Improving"));
        // Add more patients as needed
    }

    public List<Patient> getAllPatients() {
        logger.log(Level.INFO, "Fetching all patients");
        return patients;
    }

    public Patient getPatientById(String id) {
        logger.log(Level.INFO, "Fetching patient by ID: " + id);
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                logger.log(Level.INFO, "Patient found with ID: " + id);
                return patient;
            }
        }
        logger.log(Level.WARNING, "Patient not found with ID: " + id);
        return null;
    }

    public void addPatient(Patient patient) {
        logger.log(Level.INFO, "Adding new patient: " + patient);
        patients.add(patient);
    }

    public void updatePatient(Patient updatedPatient) {
        logger.log(Level.INFO, "Updating patient with ID: " + updatedPatient.getId());
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            if (patient.getId().equals(updatedPatient.getId())) {
                logger.log(Level.INFO, "Patient found with ID: " + updatedPatient.getId() + ", updating...");
                patients.set(i, updatedPatient);
                logger.log(Level.INFO, "Patient updated: " + updatedPatient);
                return;
            }
        }
        logger.log(Level.WARNING, "Patient not found with ID: " + updatedPatient.getId() + ", update failed");
    }

    public void deletePatient(String id) {
        logger.log(Level.INFO, "Deleting patient with ID: " + id);
        patients.removeIf(patient -> patient.getId().equals(id));
    }
}