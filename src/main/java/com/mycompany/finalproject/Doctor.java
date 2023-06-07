/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class Doctor extends Staff{
    ArrayList<String> specialties;
    double rate;
    String specialty;
    
    public Doctor(String doctorID, String firstname, String middlename, String lastname, double rate, ArrayList<String> specialties) {
        super(doctorID, firstname, middlename, lastname, "", "doctor");
        this.specialties = specialties;
    }

    public Doctor(String doctorID, String firstname, String middlename, String lastname, double rate) {
        super(doctorID, firstname, middlename, lastname, "", "doctor");
        specialties = new ArrayList();
        this.rate = rate;
    }
    
    public Doctor(String doctorID, String firstname, String middlename, String lastname, double rate, String specialty) {
        super(doctorID, firstname, middlename, lastname, "", "doctor");
        this.specialty = specialty;
    } 
    
    public Doctor() {
        
    }
    
    public String getSpecialty() {
        return specialty;
    }
    
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public ArrayList<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(ArrayList<String> specialties) {
        this.specialties = specialties;
    }
    
    public void addSpecialties(String specialty) {
        specialties.add(specialty);
    }
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
