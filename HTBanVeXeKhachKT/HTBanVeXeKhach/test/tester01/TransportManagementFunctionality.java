/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import BVXK.QuanLyTuyenDi;
import BVXK.QuanLyXe;
import BVXK.QuanLyXeKhach;
import BanVeXeKhach.TuyenDuong;
import BanVeXeKhach.Xe;
import BanVeXeKhach.XeKhach;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static tester01.LoginFunctionality.conn;

/**
 *
 * @author ASUS
 */
public class TransportManagementFunctionality {
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
    
     // test add button
    @Test
    public void TC89(){
        System.out.println("* Check Add employee button:");        
        System.out.println("TC89: Check the results on clicking the Add button after entering all fields with valid data.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, "Xe giường nằm");
        assertTrue(QuanLyXe.themXe(xe));
    }
    
        @Test
    public void TC90(){    
        System.out.println("TC90: Check the results on clicking the Add button after entering the same vehicle id as an existing vehicle id in database.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, "Xe giường nằm");
        QuanLyXe.themXe(xe);
        assertFalse(QuanLyXe.themXe(xe));
    }
    
            @Test
    public void TC91(){    
        System.out.println("TC91: Check the results on clicking the Add button without giving any data.");
        
        Xe xe = new Xe(null, null);
        assertFalse(QuanLyXe.themXe(xe));
    }
    
            @Test
    public void TC92(){    
        System.out.println("TC92: Check the results on clicking the Add button without entering vehicle id but selecting vehicle type.");
        
        Xe xe = new Xe(null, "Xe giường nằm");
        assertFalse(QuanLyXe.themXe(xe));
    }
    
            @Test
    public void TC93(){    
        System.out.println("TC93: Check the results on clicking the Add button without selecting vehicle type but entering vehicle id.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, null);
        assertFalse(QuanLyXe.themXe(xe));
    }
            @Test
    public void TC94(){    
        System.out.println("TC94: Check the results on clicking the Add button when entering the vehicle id which length is longer than the limit length of vehicle id field.");
        Xe xe = new Xe("test89asdfrtg75", "Xe giường nằm");
        assertFalse(QuanLyXe.themXe(xe));
       
    }
//           test Update button
    @Test
    public void TC95(){
        System.out.println("* Check Update button:");        
        System.out.println("TC95: Check the results on clicking the Update button after selecting a vehicle in the transport table and editing vehicle type.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, "Xe giường nằm");
        QuanLyXe.themXe(xe);
        xe.setLoaiXe("Xe limo house");
        assertTrue(QuanLyXe.capNhatXe(xe, id));
    }
        @Test
    public void TC96(){        
        System.out.println("TC96: Check the results on clicking the Update button after selecting a vehicle in the transport table and editing vehicle id.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, "Xe giường nằm");
        QuanLyXe.themXe(xe);
        xe.setBienSoXe(id + "se");
        assertTrue(QuanLyXe.capNhatXe(xe, id));
    }
            @Test
    public void TC97(){        
        System.out.println("TC97: Check the results on clicking the Update button without selecting any vehicle in the transport table.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, "Xe giường nằm");
        assertFalse(QuanLyXe.capNhatXe(xe, null));
    }
            @Test
    public void TC98(){        
        System.out.println("TC98: Check the results on clicking the Update button after deleting vehicle id in vehicle id field.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, "Xe giường nằm");
        QuanLyXe.themXe(xe);
        xe.setBienSoXe("");
        assertFalse(QuanLyXe.capNhatXe(xe, id));
    }
            @Test
    public void TC99(){        
        System.out.println("TC99: Check the results on clicking the Update button when entering the vehicle id which length is longer than the limit length of vehicle id field.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, "Xe giường nằm");
        QuanLyXe.themXe(xe);
        xe.setBienSoXe("asfefefsafsdfefaesfaeafasdfsfe");
        assertFalse(QuanLyXe.capNhatXe(xe, id));
        
    }
    // test delete button
    @Test
    public void TC100(){
        System.out.println("* Check Delete button:");        
        System.out.println("TC100: Check the results by clicking the Delete button after selecting a vehicle in the vehicle table that is not on any route.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, "Xe giường nằm");
        QuanLyXe.themXe(xe);  
        
        assertTrue(QuanLyXe.xoaXe(id));
    }
    
        @Test
    public void TC101(){       
        System.out.println("TC101: Check the results by clicking the Delete button after selecting a vehicle in the vehicle table that is already in a route.");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 6);
        Xe xe = new Xe(id, "Xe giường nằm");
        QuanLyXe.themXe(xe); 
        
        Date d = new java.util.Date("12/18/2020");
        String id1 = uuid.toString().substring(0, 5);          
        TuyenDuong t = new TuyenDuong(id, "a", "b", xe.getBienSoXe(), d, "8");
        QuanLyTuyenDi.themTuyenDuong(t);
       
        assertTrue(QuanLyXe.xoaXe(id));
    }
    
        @Test
    public void TC102(){       
        System.out.println("TC102: Check the results on clicking the Delete button without selecting any vehicle in the transport table.");

        String vId = null;     
        boolean kq = QuanLyXe.xoaXe(vId);
        assertFalse(kq);
    }
    

     // 
    @Test
    public void TCx() {
        System.out.println("*");        
        System.out.println("TCx: Check if app can get all vehicles in the database.");
        
        try {
            List<Xe> ds = QuanLyXe.getXe();
            assertTrue(ds.size() >= 2);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
