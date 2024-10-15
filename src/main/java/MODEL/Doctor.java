/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author chamo
 */
public class Doctor extends Person {
    private String specialization;

    public Doctor(){}
    
    // Constructor
    public Doctor(String id, String name, String contactInfo, String address, String specialization) {
        super(id, name, contactInfo, address);
        this.specialization = specialization;
    }

    // Getter and setter
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}