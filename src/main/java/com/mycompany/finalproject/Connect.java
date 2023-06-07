/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Connect {
    Connection conn = null;

    public Connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Staff loginStaff(String employee_id) {
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM staff WHERE employee_id = '"+ employee_id +"' and is_active = true;";
            rs = stmt.executeQuery(sql);
            
            if(rs.next() == true) {
                Staff staff = new Staff(rs.getString("employee_id"), rs.getString("first_name"), rs.getString("middle_name"), rs.getString("last_name"), "", rs.getString("role"), rs.getBoolean("is_new"));
                staff.setPassword(rs.getString("password"));
                return staff;
            }
            else{
                JOptionPane.showMessageDialog(null, "Username does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public boolean addStaff(Staff staff) {
        Statement stmt;
        String sql;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            sql="select * from staff where employee_id='"+staff.getEmployeeID()+"'";
            rs =stmt.executeQuery(sql);
            if(rs.next()==false){
                sql="insert into staff(employee_id, first_name, middle_name, last_name, password, role) values('"+staff.getEmployeeID()+"','"+staff.getFirstname()+"','"+staff.getMiddlename()+"','"+staff.getLastname()+"','"+staff.getPassword()+"', '"+staff.getRole()+"')";
                stmt.executeUpdate(sql);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateStaff(Staff staff) {
        Statement stmt;
        String sql;
        
        try {
            stmt = conn.createStatement();
            sql="update staff set first_name = '"+staff.getFirstname()+"', middle_name = '"+staff.getMiddlename()+"', last_name = '"+staff.getLastname()+"' where employee_id='"+staff.getEmployeeID()+"'";
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean changePassword(Staff staff) {
        Statement stmt;
        String sql;
        try {
            stmt = conn.createStatement();
            sql = "UPDATE staff SET password = '"+ staff.getPassword() +"' WHERE employee_id = '"+ staff.getEmployeeID() +"'";
            int rs = stmt.executeUpdate(sql);
            
            if(rs == 1) {
              return true;   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void updateIsNew(Staff staff){
        Statement stmt;
        String sql;
        try {
            stmt = conn.createStatement();
            sql = "UPDATE staff SET is_new = false WHERE employee_id = '"+ staff.getEmployeeID() +"'";
            int rs = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateIsNewToTrue(Staff staff){
        Statement stmt;
        String sql;
        try {
            stmt = conn.createStatement();
            sql = "UPDATE staff SET is_new = true WHERE employee_id = '"+ staff.getEmployeeID() +"'";
            int rs = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean deleteStaff(String employeeID) {
        Statement stmt;
        String sql;
        try {
            stmt = conn.createStatement();
            sql = "UPDATE staff SET is_active = 0 WHERE employee_id = '"+ employeeID +"';";
            int result = stmt.executeUpdate(sql);
            
            if(result == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public ArrayList<Staff> getStaffs() {
        ArrayList<Staff> staff = new ArrayList();
        String sql ="select * from staff where (role = 'clerk' or role = 'admin') and is_active = true;";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Staff s = new Staff();
              s.setEmployeeID(rs.getString("employee_id"));
              s.setFirstname(rs.getString("first_name"));
              s.setMiddlename(rs.getString("middle_name"));
              s.setLastname(rs.getString("last_name"));
              s.setRole(rs.getString("role"));
              staff.add(s);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staff;
    }
    
    public boolean addDoctor(Doctor doctor) {
        Staff staff = new Staff(doctor.getEmployeeID(), doctor.getFirstname(), doctor.getMiddlename(), doctor.getLastname(), "", "doctor");
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            sql="select * from doctors where doctor_id='"+ doctor.getEmployeeID()+"'";
            rs =stmt.executeQuery(sql);
            
            if(rs.next()==false && addStaff(staff)){
                sql="insert into doctors values('"+doctor.getEmployeeID()+"',"+doctor.getRate()+", true)";
                stmt.executeUpdate(sql);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateDoctor(Doctor doctor) {
        Statement stmt;
        String sql1;
        String sql2;
        
        
        try {
            stmt = conn.createStatement();
            sql1 ="update staff set first_name = '"+doctor.getFirstname()+"', middle_name = '"+doctor.getMiddlename()+"', last_name = '"+doctor.getLastname()+"' where employee_id='"+doctor.getEmployeeID()+"'";
            sql2 ="update doctors set rate = "+doctor.getRate()+" where doctor_id='"+doctor.getEmployeeID()+"'";
            int result1 = stmt.executeUpdate(sql1);
            int result2 = stmt.executeUpdate(sql2);
            if(result1 == 1 && result2 == 1){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean checkDoctorAssigned(String employee_id) {
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM patient_doctor WHERE doctor_id = '"+ employee_id +"';";
            rs = stmt.executeQuery(sql);
            
            if(rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean deleteDoctor(String employee_id) {
        Statement stmt;
        String sql, sql2;
        try {
            stmt = conn.createStatement();
            sql = "UPDATE staff SET is_active = 0 WHERE employee_id = '"+ employee_id +"';";
            int result = stmt.executeUpdate(sql);
            
            sql2 = "UPDATE doctors SET is_active = 0 WHERE doctor_id = '"+ employee_id +"';";
            int result2 = stmt.executeUpdate(sql2);
            
            if(result == 1 && result2 == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public ArrayList<Doctor> getDoctors() {
        ArrayList<Doctor> doctors = new ArrayList();
        String sql = "select employee_id, first_name, middle_name, last_name, rate from staff as s, doctors as d where s.employee_id = d.doctor_id and s.is_active = true and d.is_active = true;";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Doctor d = new Doctor();
              d.setEmployeeID(rs.getString("employee_id"));
              d.setFirstname(rs.getString("first_name"));
              d.setMiddlename(rs.getString("middle_name"));
              d.setLastname(rs.getString("last_name"));
              d.setRate(rs.getDouble("rate"));
              
              doctors.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return doctors;
    }
    
    public boolean addRoom(Room room) {
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            sql="select * from rooms where room_number='"+room.getRoomNumber()+"'";
            rs =stmt.executeQuery(sql);
            
            if(rs.next()==false){
                sql="insert into rooms values("+room.getRoomNumber()+","+room.getCapacity()+", '"+room.getType()+"', "+room.getRate()+", true)";
                stmt.executeUpdate(sql);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Room Number Already Exists!", "Room Number Already Exists!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateRoom(Room room) {
        Statement stmt;
        String sql;
        try {
            stmt = conn.createStatement();
            sql="update rooms set capacity = "+room.getCapacity()+", type = '"+room.getType()+"', rate = "+room.getRate()+" where room_number="+room.getRoomNumber()+"";
            int result = stmt.executeUpdate(sql);
            if(result == 1){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteRoom(int roomNumber) {
        Statement stmt;
        String sql;
        try {
            stmt = conn.createStatement();
            sql = "UPDATE rooms SET is_active=false WHERE room_number = '"+ roomNumber +"';";
            int result = stmt.executeUpdate(sql);
            
            if(result == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public ArrayList<Room> getRooms(){
        ArrayList<Room> room = new ArrayList();
        String sql ="select * from rooms where is_active = 1;";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Room r = new Room(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getDouble(4)) ;
              room.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return room;
    }
    
    public ArrayList<Doctor> getSpecialties() {
        ArrayList<Doctor> doctors = new ArrayList();
        String sql = "SELECT s.first_name, s.middle_name, s.last_name, s.employee_id, ds.specialty FROM staff AS s, doctors as d, doctor_specialty as ds WHERE s.employee_id = d.doctor_id and d.doctor_id = ds.doctor_id and d.is_active = true and s.is_active = true ORDER BY first_name ASC;";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Doctor d = new Doctor();
              d.setEmployeeID(rs.getString("employee_id"));
              d.setFirstname(rs.getString("first_name"));
              d.setMiddlename(rs.getString("middle_name"));
              d.setLastname(rs.getString("last_name"));
              d.setSpecialty(rs.getString("specialty"));
              
              doctors.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return doctors;
    }
    
    public boolean addSpecialties(String employee_id, String specialty) {
        Statement stmt;
        String sql;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            sql="select * from doctors where doctor_id='"+employee_id+"'";
            rs =stmt.executeQuery(sql);
            if(rs.next()){
                sql="select * from doctor_specialty where doctor_id='"+employee_id+"' and specialty='"+specialty+"'";
                rs = stmt.executeQuery(sql);
                if(rs.next() == false) {
                    sql="insert into doctor_specialty values('"+employee_id+"','"+specialty+"')";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Doctor's Specialty Added");
                    return true;
                } else{
                    JOptionPane.showMessageDialog(null, "Doctor's Specialty Already Exists", "Specialty already exists", JOptionPane.ERROR_MESSAGE);
                }
                
                
            } else {
                JOptionPane.showMessageDialog(null, "Doctor Doesn't Exist", "Doctor Doesn't Exist", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteSpecialties(String employee_id, String specialty) {
        Statement stmt;
        String sql;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            sql="select * from doctors where doctor_id='"+employee_id+"'";
            rs =stmt.executeQuery(sql);
            if(rs.next()){
                sql="select * from doctor_specialty where doctor_id='"+employee_id+"' and specialty='"+specialty+"'";
                rs = stmt.executeQuery(sql);
                if(rs.next()) {
                    sql="delete from doctor_specialty where doctor_id='"+employee_id+"' and specialty ='"+specialty+"'";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Doctor's Specialty Deleted");
                    return true;
                } else{
                    JOptionPane.showMessageDialog(null, "Doctor's Specialty Doesn't Exists", "Specialty already exists", JOptionPane.ERROR_MESSAGE);
                }
                
                
            } else {
                JOptionPane.showMessageDialog(null, "Doctor Doesn't Exist", "Doctor Doesn't Exist", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
