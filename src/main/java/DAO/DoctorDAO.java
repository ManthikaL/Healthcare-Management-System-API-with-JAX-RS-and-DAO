/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
/**
 *
 * @author chamo
 */
import MODEL.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDAO {
    private static final Logger logger = Logger.getLogger(DoctorDAO.class.getName());

    private static List<Doctor> doctors = new ArrayList<>();

    static {
        doctors.add(new Doctor("D1", "Hansaja", "0387956412", "75, Pitigala", "Cardiology"));
        doctors.add(new Doctor("D2", "Sidu", "0114586789", "8/1, Colombo", "Pediatrics"));
        // Add more doctors as needed
    }

    public List<Doctor> getAllDoctors() {
        logger.log(Level.INFO, "Fetching all doctors");
        return doctors;
    }

    public Doctor getDoctorById(String id) {
        logger.log(Level.INFO, "Fetching doctor by ID: " + id);
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                logger.log(Level.INFO, "Doctor found with ID: " + id);
                return doctor;
            }
        }
        logger.log(Level.WARNING, "Doctor not found with ID: " + id);
        return null;
    }

    public void addDoctor(Doctor doctor) {
        logger.log(Level.INFO, "Adding new doctor: " + doctor);
        doctors.add(doctor);
    }

    public void updateDoctor(Doctor updatedDoctor) {
        logger.log(Level.INFO, "Updating doctor with ID: " + updatedDoctor.getId());
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.getId().equals(updatedDoctor.getId())) {
                logger.log(Level.INFO, "Doctor found with ID: " + updatedDoctor.getId() + ", updating...");
                doctors.set(i, updatedDoctor);
                logger.log(Level.INFO, "Doctor updated: " + updatedDoctor);
                return;
            }
        }
        logger.log(Level.WARNING, "Doctor not found with ID: " + updatedDoctor.getId() + ", update failed");
    }

    public void deleteDoctor(String id) {
        logger.log(Level.INFO, "Deleting doctor with ID: " + id);
        doctors.removeIf(doctor -> doctor.getId().equals(id));
    }
}