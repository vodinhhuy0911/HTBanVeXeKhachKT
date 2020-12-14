/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import BVXK.QuanLyNhanVien;
import BanVeXeKhach.NhanVien;
import BanVeXeKhach.VeXe;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static tester01.LoginFunctionality.conn;

/**
 *
 * @author ASUS
 */
public class EmployeeManagementFunctionality {
    public static Connection conn;
    
    @BeforeClass
    public static void setUp() {
        conn = BVXK.JDBC.getConn();
    }
    
     @AfterClass
    public static void tearDown() {
        try {
            BVXK.JDBC.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
 //    test add button
    @Test
    public void TC28(){
        System.out.println("* Check Add employee button:");        
        System.out.println("TC28: Check the results on clicking the Add button after entering all fields with valid data.");
        
        try {
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());

            NhanVien nv = new NhanVien("4", "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh","123");
            
            assertTrue(QuanLyNhanVien.themNhanVien(nv));
    
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC29(){
        System.out.println("TC29: Check the results on clicking the Add button after entering all fields with the same data as an existing employee in database.");
        
        try {
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asd@fgh","123");
            QuanLyNhanVien.themNhanVien(nv);
            NhanVien nv1 = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asd@fgh","123");
    
            assertFalse(QuanLyNhanVien.themNhanVien(nv1));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    @Test
    public void TC30() {
        System.out.println("TC30: Check the results on clicking the Add button only entering DOB field.");
       
        try {
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            NhanVien nv = new NhanVien(null, null,sqlDate , null, null, null, null,null);          
            
            assertFalse(QuanLyNhanVien.themNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC31() {
        try {
            System.out.println("TC31: Check the results on clicking the Add button when entering a wrong type of date formatting in DOB field.");
            
            Date d = new java.util.Date("31/04/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",d , "abc", "NV", "01234567894", "asdfgh","123");
            
            assertFalse(QuanLyNhanVien.themNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void TC32(){
        try {
            System.out.println("TC32: Check the results on clicking the Add button when entering a random string in DOB field");
            
            Date d = new java.util.Date("sfsafw");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdfgh","123");
            
            QuanLyNhanVien.themNhanVien(nv);
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void TC33(){
        try {
            System.out.println("TC33: Check the results on clicking the Add button when entering a negative number in Phone number field.");

            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "-0123456789", "asdfgh","123");
            
            assertFalse(QuanLyNhanVien.themNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC34(){
        try {
            System.out.println("TC34: Check the results on clicking the Add button when entering a phone number containing alphabetic or special characters in Phone number field.");

            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "0123as6789", "asdfgh","123");
            
            assertFalse(QuanLyNhanVien.themNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC35(){
        try {
            System.out.println("TC35: Check the results on clicking the Add button when entering wrong type of email formatting in email field.");

            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "0123as6789", "asdfgh","123");
            assertFalse(QuanLyNhanVien.themNhanVien(nv));
             
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC36(){
        try {
            System.out.println("TC36: Check the results on clicking the Add button when entering an information which length is longer than the limit length of the field.");

            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "0123456789", "asdfghjkloasdfghjkloasdfghjkloasdf@ghjklo","123");
            assertFalse(QuanLyNhanVien.themNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    test edit button
    @Test
    public void TC37(){
        System.out.println("* Check Edit employee button:");        
        System.out.println("TC37: Check the results on clicking Edit button without selecting any employee in the employee table.");

        Date d = new java.util.Date("04/18/2000");
        java.sql.Date sqlDate = new java.sql.Date(d.getTime());
        NhanVien nv = new NhanVien(null, "dk1",sqlDate , "abc", "NV", "01234567894", "asdfgh","123");
       
        assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
    }
    
       @Test
    public void TC38(){     
        try {
            System.out.println("TC38: Check the results on clicking Edit button after selecting an employee in the employee table and editing valid data in name, DOB, address, position, phone number, email fields.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            Date d1 = new java.util.Date("04/20/2000");
            java.sql.Date sqlDate1 = new java.sql.Date(d1.getTime());
            nv.setHoTen("test38");
            nv.setNgaySinh(sqlDate1);
            nv.setDiaChi("asdfg");
            nv.setChucVu("Nhân Viên");
            nv.setSdt("01236547895");
            nv.setEmail("afa@agds");
            nv.setMatKhau("123");
            assertTrue(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @Test
    public void TC39(){
        try {
            System.out.println("TC39: Check the results on clicking Edit button after deleting information of the employee in all fields except the employee id field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            nv.setHoTen("");
            nv.setNgaySinh(null);
            nv.setDiaChi("");
            nv.setChucVu("");
            nv.setSdt("");
            nv.setEmail("");
            nv.setMatKhau("");
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @Test
    public void TC40(){     
        try {
            System.out.println("TC40: Check the results on clicking Edit button when entering wrong type of date formatting in DOB field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            Date d1 = new java.util.Date("04/31/2000");
            java.sql.Date sqlDate1 = new java.sql.Date(d1.getTime());
            nv.setNgaySinh(sqlDate1);
            
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       @Test(expected = IllegalArgumentException.class)
    public void TC41(){    
        try {
            System.out.println("TC41: Check the results on clicking Edit button when entering a random string in DOB field.");
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            Date d1 = new java.util.Date("asdqwfaf");
            java.sql.Date sqlDate1 = new java.sql.Date(d.getTime());
            nv.setNgaySinh(sqlDate1);
            
            QuanLyNhanVien.capNhatNhanVien(nv);
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
       @Test
    public void TC42(){     
        try {
            System.out.println("TC42: Check the results on clicking Edit button when entering a negative number in Phone number field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            nv.setSdt("-0123456789");
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @Test
    public void TC43(){     
        try {
            System.out.println("TC43: Check the results on clicking Edit button when entering a phone number containing alphabetic or special characters in Phone number field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            nv.setSdt("012345as789");
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @Test
    public void TC44(){      
        try {
            System.out.println("TC44: CCheck the results on clicking Edit button when entering wrong type of email formatting in email field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            nv.setEmail("asfasasf");
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @Test
    public void TC45(){     
        try {
            System.out.println("TC45: Check the results on clicking Edit button when updating an information which length is longer than the limit length of the field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            nv.setHoTen("asdfghjklaasdfghjklaasdfghjklaasdfghjklaasdfghjklaklaasdfghjk");
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
       // test delete button
      @Test
    public void TC48(){
        System.out.println("* Check Delete employee button:");        
        System.out.println("TC48: Check the results on clicking the Delete button after selecting an employee in the employee table.");
        
        String eId = "4";     
        boolean kq = QuanLyNhanVien.xoaNhanVien(eId);
        assertTrue(kq);
    }
    
    @Test
    public void TC49(){       
        System.out.println("TC49: Check the results on clicking the Delete button without selecting any employee in the employee table.");
        
        String eId = null;  
        boolean kq = QuanLyNhanVien.xoaNhanVien(eId);
        assertFalse(kq);
    }
    
    // test find button
    @Test
    public void TC51() {
        System.out.println("* Check Find button:");        
        System.out.println("TC51: Check the results on clicking the Find button when entering an employee id that exists in the database.");
        
        try {
            List<NhanVien> ds = QuanLyNhanVien.timKiemNv("1");
            List<NhanVien> kq = new ArrayList<>();
            assertNotEquals(ds, kq);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Test
    public void TC52() {       
        System.out.println("TC52: Check the results on clicking the Find button when entering an employee name that exists in the database.");
        
        try {
            List<NhanVien> ds = QuanLyNhanVien.timKiemNv("etest4");
            List<NhanVien> kq = new ArrayList<>();
            assertNotEquals(ds, kq);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Test
    public void TC53() {   
        System.out.println("TC53: Check the results on clicking the Find button when entering an employee phone number that exists in the database.");
        
        try {
            List<NhanVien> ds = QuanLyNhanVien.timKiemNv("1234567899");
            List<NhanVien> kq = new ArrayList<>();
            assertNotEquals(ds, kq);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Test
    public void TC54() { 
        System.out.println("TC54: Check the results on clicking the Find button when entering an employee DOB that exists in the database.");
        
        try {
            List<NhanVien> ds = QuanLyNhanVien.timKiemNv("18/04/2000");
            List<NhanVien> kq = new ArrayList<>();
            assertNotEquals(ds, kq);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Test
    public void TC55() {      
        System.out.println("TC55: Check the results on clicking the Find button when entering an information does not exist in the employee database.");
        
        try {
            List<NhanVien> ds = QuanLyNhanVien.timKiemNv("asdfasfa");
            List<NhanVien> kq = new ArrayList<>();
            assertEquals(ds, kq);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // 
    @Test
    public void TCx() {
        System.out.println("*");        
        System.out.println("TCx: Check if app can get all employees in the database.");
        
        try {
            List<NhanVien> ds = QuanLyNhanVien.getDsNhanVien();
            assertTrue(ds.size() >= 4);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
