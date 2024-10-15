/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.MedicalRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicalRecordDAO {
    private static final Logger logger = Logger.getLogger(MedicalRecordDAO.class.getName());

    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    private static PatientDAO patientDAO = new PatientDAO();

    static {
        medicalRecords.add(new MedicalRecord("M1", patientDAO.getPatientById("P2"), "fever", "paracetamol"));
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        logger.log(Level.INFO, "Fetching all medical records");
        return medicalRecords;
    }

    public MedicalRecord getMedicalRecordById(String id) {
        logger.log(Level.INFO, "Fetching medical record by ID: " + id);
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getId().equals(id)) {
                logger.log(Level.INFO, "Medical record found with ID: " + id);
                return medicalRecord;
            }
        }
        logger.log(Level.WARNING, "Medical record not found with ID: " + id);
        return null;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        logger.log(Level.INFO, "Adding new medical record: " + medicalRecord);
        medicalRecords.add(medicalRecord);
    }

    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        logger.log(Level.INFO, "Updating medical record with ID: " + updatedMedicalRecord.getId());
        for (int i = 0; i < medicalRecords.size(); i++) {
            MedicalRecord medicalRecord = medicalRecords.get(i);
            if (medicalRecord.getId().equals(updatedMedicalRecord.getId())) {
                medicalRecords.set(i, updatedMedicalRecord);
                logger.log(Level.INFO, "Medical record updated: " + updatedMedicalRecord);
                return;
            }
        }
        logger.log(Level.WARNING, "Medical record not found with ID: " + updatedMedicalRecord.getId() + ", update failed");
    }

    public void deleteMedicalRecord(String id) {
        logger.log(Level.INFO, "Deleting medical record with ID: " + id);
        medicalRecords.removeIf(medicalRecord -> medicalRecord.getId().equals(id));
    }
}