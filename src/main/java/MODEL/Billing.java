/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author chamo
 */
public class Billing {
    private String id;
    private Patient patient;
    private double amountDue;
    private double amountPaid;
    private String invoiceDate;

    public Billing(){}
    
    // Constructor
    public Billing(String id, Patient patient, double amountDue, double amountPaid, String invoiceDate) {
        this.id = id;
        this.patient = patient;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.invoiceDate = invoiceDate;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}