/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;
import java.util.Random;
/**
 *
 * @author Ivan
 */

public class EmployeeIDGenerator {
    private static final String PREFIX = "HSP";
    private static final int UNIQUE_ID_LENGTH = 6;
    
    //generates an ID WITH HSP as Prefix followed by 6 random number
    public static String generateEmployeeID() {
        // Generate a random unique ID of length UNIQUE_ID_LENGTH
        Random random = new Random();
        int uniqueID = random.nextInt((int) Math.pow(10, UNIQUE_ID_LENGTH));
        String paddedUniqueID = String.format("%0" + UNIQUE_ID_LENGTH + "d", uniqueID);
        
        // Construct the final employee ID
        String employeeID = PREFIX + paddedUniqueID;
        return employeeID;
    }
    
}
