/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import BVXK.QuanLyTuyenDi;
import BVXK.QuanLyXe;
import BanVeXeKhach.TuyenDuong;
import BanVeXeKhach.Xe;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static tester01.LoginFunctionality.conn;

/**
 *
 * @author ASUS
 */
public class DistanceFunctionality {
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
    
//          test add button
    @Test
    public void TC60(){
        System.out.println("* Check Add button:");        
        System.out.println("TC60: Check the results on clicking the Add button after entering all fields with valid data.");
        
        Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        Xe xe = new Xe("1412", "Xe giường nằm");
        QuanLyXe.themXe(xe);
        TuyenDuong t = new TuyenDuong(id, "a", "b", xe.getBienSoXe(), d, "8");

        assertTrue(QuanLyTuyenDi.themTuyenDuong(t));
    }
    
        @Test
    public void TC61(){      
        System.out.println("TC61: Check the results on clicking the Add button after entering all fields with the same data as an existing route in database.");
        
        Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, "a", "b", "1412", d, "8");
        QuanLyTuyenDi.themTuyenDuong(t);       
        assertFalse(QuanLyTuyenDi.themTuyenDuong(t));
    }
        @Test
    public void TC62(){       
        System.out.println("TC62: Check the results on clicking the Add button without giving any data.");
        
        Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(null, null, null, null, null, null);      
        assertFalse(QuanLyTuyenDi.themTuyenDuong(t));
    }
        @Test
    public void TC63(){
        System.out.println("TC63: Check the results on clicking the Add button only entering date and time.");
        
        Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, null, null, null, d, "8");
        QuanLyTuyenDi.themTuyenDuong(t);       
        assertFalse(QuanLyTuyenDi.themTuyenDuong(t));
    }
        @Test
    public void TC64(){    
        System.out.println("TC64: Check the results on clicking the Add button when entering a random string in the time field.");
        
        Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, "a", "b", "1412", d, "as84");     
        assertFalse(QuanLyTuyenDi.themTuyenDuong(t));
    }
        @Test
    public void TC65(){      
        System.out.println("TC65: Check the results on clicking the Add button without entering route id, but entering departure point and destination.");
        
       Date d = new java.util.Date("12/18/2020");
            
        TuyenDuong t = new TuyenDuong(null, "a", "b", "1412", d, "8");     
        assertFalse(QuanLyTuyenDi.themTuyenDuong(t));
    }
        @Test
    public void TC66(){    
        System.out.println("TC66: Check the results on clicking the Add button when entering an information which length is longer than the limit length of the field.");
        
        Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, "asdfghjklaasdfghjklaasdfghjklaasdfghjklaasdfghjkla", "b", "1412", d, "8");     
        assertFalse(QuanLyTuyenDi.themTuyenDuong(t));
    }
       // test Update button
    @Test
    public void TC67(){
        System.out.println("* Check Update button:");        
        System.out.println("TC67: Check the results on clicking the Update button after entering departure point, destination, date and time fields with valid data.");

        Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, "a", "b", "1412", d, "8");       
        assertTrue(QuanLyTuyenDi.themTuyenDuong(t));
        Date d1 = new java.util.Date("12/18/2020");

        assertTrue(QuanLyTuyenDi.capNhatTuyenDuong(id, "d", "e", "1412", d1, "7"));
    }
        @Test
    public void TC68(){    
        System.out.println("TC68: Check the results on clicking the Update button after editing route id field .");
        
            Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, "a", "b", "1412", d, "8");       
        QuanLyTuyenDi.themTuyenDuong(t);

        assertFalse(QuanLyTuyenDi.capNhatTuyenDuong("ad1548497", "a", "b", "1412", d, "8"));
    }
        @Test
    public void TC69(){      
        System.out.println("TC69: Check the results on clicking the Update button without choosing any route in the route table.");

        assertFalse(QuanLyTuyenDi.capNhatTuyenDuong(null, null, null, null, null,null));
    }
        @Test
    public void TC70(){       
        System.out.println("TC70: Check the results on clicking the Update button after deleting all information of the route in departure point, destination, date or time fields.");

                Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, "a", "b", "1412", d, "8");       
        QuanLyTuyenDi.themTuyenDuong(t);

         assertFalse(QuanLyTuyenDi.capNhatTuyenDuong(id, "a", null, "1412", d, "8"));
    }
        @Test
    public void TC71(){     
        System.out.println("TC71: Check the results on clicking the Update button after deleting information of the route in all fields.");

        Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, "a", "b", "1412", d, "8");       
        QuanLyTuyenDi.themTuyenDuong(t);

         assertFalse(QuanLyTuyenDi.capNhatTuyenDuong("", "", "", "", null,""));
    }
        @Test
    public void TC72(){      
        System.out.println("TC72: Check the results on clicking the Update button after deleting all information of the route in all fields except the id field.");

        Date d = new java.util.Date("12/18/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, "a", "b", "1412", d, "8");       
        QuanLyTuyenDi.themTuyenDuong(t);

        assertFalse(QuanLyTuyenDi.capNhatTuyenDuong(id, "", "", "", null,""));
    }
        @Test
    public void TC73(){    
        System.out.println("TC73: Check the results on clicking the Update button when entering an information which length is longer than the limit length of the field.");
        
        Date d = new java.util.Date("12/11/2020");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 5);
            
        TuyenDuong t = new TuyenDuong(id, "q", "w", "1412", d, "8");       
        QuanLyTuyenDi.themTuyenDuong(t);

         assertFalse(QuanLyTuyenDi.capNhatTuyenDuong(id, "asdfghjklaasdfghjklaasdfghjklaasdfghjklaasdfghjkla", "w", "1412", d, "8"));
    }
    // test delete button
      @Test
    public void TC76(){
        System.out.println("* Check Delete button:");        
        System.out.println("TC76: Check the results on clicking the Delete button after selecting a route in the route table.");
        
        String eId = "4";     
        boolean kq = QuanLyTuyenDi.xoaTuyenDuong(eId);
        assertTrue(kq);
    }
    
    @Test
    public void TC77(){       
        System.out.println("TC77: Check the results on clicking the Delete button without selecting any route in the route table.");
        
        String eId = null;  
        boolean kq = QuanLyTuyenDi.xoaTuyenDuong(eId);
        assertFalse(kq);
    }
    
    // test find button
    @Test
    public void TC80() {
        try {
            System.out.println("* Check Find button:");
            System.out.println("TC80: Check the results on clicking the Find button when entering a route id that exists in the database.");
            
            List<TuyenDuong> ds = QuanLyTuyenDi.timKiemTuyenDuong("1");
            List<TuyenDuong> kq = new ArrayList<>();
            assertNotEquals(ds, kq);
        } catch (SQLException ex) {
            Logger.getLogger(DistanceFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        @Test
    public void TC81() {       
        try {
            System.out.println("TC81: Check the results on clicking the Find button when entering a destination that exists in the database.");
            Date d = new java.util.Date("12/18/2020");
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);

            TuyenDuong t = new TuyenDuong(id, "a", "Nha Trang", "1412", d, "8");
            QuanLyTuyenDi.themTuyenDuong(t);       
            List<TuyenDuong> ds = QuanLyTuyenDi.timKiemTuyenDuong("Nha Trang");
            List<TuyenDuong> kq = new ArrayList<>();
            assertNotEquals(ds, kq);
        } catch (SQLException ex) {
            Logger.getLogger(DistanceFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
            @Test
    public void TC82() {       
        try {
            System.out.println("TC82: Check the results on clicking the Find button when entering a departure date that exists in the database.");
            Date d = new java.util.Date("12/19/2020");
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);

            TuyenDuong t = new TuyenDuong(id, "a", "b", "1412", d, "8");
            QuanLyTuyenDi.themTuyenDuong(t);       
            List<TuyenDuong> ds = QuanLyTuyenDi.timKiemTuyenDuong("2020-12-19");
            List<TuyenDuong> kq = new ArrayList<>();
            assertNotEquals(ds, kq);
        } catch (SQLException ex) {
            Logger.getLogger(DistanceFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
            @Test
    public void TC83() {       
        try {
            System.out.println("TC83: Check the results on clicking the Find button when entering a departure point that exists in the database.");
            Date d = new java.util.Date("12/18/2020");
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);

            TuyenDuong t = new TuyenDuong(id, "TP.HCM", "b", "1412", d, "8");
            QuanLyTuyenDi.themTuyenDuong(t);       
            List<TuyenDuong> ds = QuanLyTuyenDi.timKiemTuyenDuong("TP.HCM");
            List<TuyenDuong> kq = new ArrayList<>();
            assertNotEquals(ds, kq);
        } catch (SQLException ex) {
            Logger.getLogger(DistanceFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
            @Test
    public void TC84() {       
        try {
            System.out.println("TC84: Check the results on clicking the Find button when entering an information does not exist in the route database.");
            
            List<TuyenDuong> ds = QuanLyTuyenDi.timKiemTuyenDuong("afafwefwafea");
            List<TuyenDuong> kq = new ArrayList<>();
            assertEquals(ds, kq);
        } catch (SQLException ex) {
            Logger.getLogger(DistanceFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     // 
    @Test
    public void TCx() {
        System.out.println("*");        
        System.out.println("TCx: Check if app can get all routes in the database.");
        
        try {
            List<TuyenDuong> ds = QuanLyTuyenDi.getDsTuyenDuong();
            assertTrue(ds.size() >= 3);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
