/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

/**
 *
 * @author John Ivan Puayap
 */
public class Staff extends Person implements LoginService{
    private String employeeid;
    private String password;
    private String role;
    private boolean isnew;
    
    public Staff() {
        
    }
    
    public Staff(String employeeid, String firstname, String middlename, String lastname, String password, String role, boolean isnew) {
        super(firstname, middlename, lastname);
        this.employeeid = employeeid;
        this.password = encryptPassword(password);
        this.role = role;
        this.isnew = isnew;
    }
    
    public Staff(String employeeid, String firstname, String middlename, String lastname, String password, String role) {
        super(firstname, middlename, lastname);
        this.employeeid = employeeid;
        this.password = encryptPassword(password);
        this.role = role;
    }

    public Staff(String employeeid, String password) {
        this.employeeid = employeeid;
        this.password = encryptPassword(password);
    }
    
    public Staff(String employeeid, String password, String role, Boolean isnew) {
        this.employeeid = employeeid;
        this.password = encryptPassword(password);
        this.role = role;
        this.isnew = isnew;
    }

    public boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(boolean isnew) {
        this.isnew = isnew;
    }

    public String getEmployeeID() {
        return employeeid;
    }

    public void setEmployeeID(String employeeid) {
        this.employeeid = employeeid;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
