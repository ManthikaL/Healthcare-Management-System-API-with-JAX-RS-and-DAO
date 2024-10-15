/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Appointment;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDAO {
    private static final Logger logger = Logger.getLogger(AppointmentDAO.class.getName());

    private static List<Appointment> appointments = new ArrayList<>();
    private static PatientDAO patientDAO = new PatientDAO();
    private static DoctorDAO doctorDAO = new DoctorDAO();

    static {
        appointments.add(new Appointment("A1", "2023.4.7", "16.00", patientDAO.getPatientById("P1"), doctorDAO.getDoctorById("D2")));
    }

    public List<Appointment> getAllAppointments() {
        logger.log(Level.INFO, "Fetching all appointments");
        return appointments;
    }

    public Appointment getAppointmentById(String id) {
        logger.log(Level.INFO, "Fetching appointment by ID: " + id);
        for (Appointment appointment : appointments) {
            if (appointment.getId().equals(id)) {
                logger.log(Level.INFO, "Appointment found with ID: " + id);
                return appointment;
            }
        }
        logger.log(Level.WARNING, "Appointment not found with ID: " + id);
        return null;
    }

    public void addAppointment(Appointment appointment) {
        logger.log(Level.INFO, "Adding new appointment: " + appointment);
        appointments.add(appointment);
    }

    public void updateAppointment(Appointment updatedAppointment) {
        logger.log(Level.INFO, "Updating appointment with ID: " + updatedAppointment.getId());
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getId().equals(updatedAppointment.getId())) {
                logger.log(Level.INFO, "Appointment found with ID: " + updatedAppointment.getId() + ", updating...");
                appointments.set(i, updatedAppointment);
                logger.log(Level.INFO, "Appointment updated: " + updatedAppointment);
                return;
            }
        }
        logger.log(Level.WARNING, "Appointment not found with ID: " + updatedAppointment.getId() + ", update failed");
    }

    public void deleteAppointment(String id) {
        logger.log(Level.INFO, "Deleting appointment with ID: " + id);
        appointments.removeIf(appointment -> appointment.getId().equals(id));
    }
}