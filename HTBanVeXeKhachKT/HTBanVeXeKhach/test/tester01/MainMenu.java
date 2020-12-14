/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import BVXK.QuanLyNhanVien;
import BanVeXeKhach.NhanVien;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ASUS
 */
public class MainMenu {
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

     @Test
    public void TC13(){  
        try {
            System.out.println("* Check Add employee button");
            System.out.println("TC13: Check the results on clicking Edit button after editing valid data in name, DOB, address, position, phone number, email fields.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            nv.setChucVu("Nhân Viên");
            nv.setHoTen("etest13");
            assertTrue(QuanLyNhanVien.capNhatNhanVien(nv));
            
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void TC14(){     
        try {
            System.out.println("TC14: Check the results on clicking Edit button after deleting information of the employee in all fields except the employee id field.");
            
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
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void TC15(){      
        try {
            System.out.println("TC15: Check the results on clicking Edit button when entering wrong type of date formatting in DOB field.");
            
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
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @Test(expected = IllegalArgumentException.class)
    public void TC16(){     
        try {
            System.out.println("TC16: Check the results on clicking Edit button when entering a random string in DOB field.");
            
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
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
     @Test
    public void TC17(){  
        try {
            System.out.println("TC17: Check the results on clicking Edit button when entering a negative number in Phone number field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
           nv.setSdt("-0123456789");
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void TC18(){    
        try {
            System.out.println("TC18: Check the results on clicking Edit button when entering a phone number containing alphabetic or special characters in Phone number field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            nv.setSdt("012345as789");
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void TC19(){      
        try {
            System.out.println("TC19: Check the results on clicking Edit button when entering wrong type of email formatting in email field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            nv.setEmail("asfasasf");
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void TC20(){        
        try {
            System.out.println("TC20: Check the results on clicking Edit button when updating an information which length is longer than the limit length of the field.");
            
            Date d = new java.util.Date("04/18/2000");
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().substring(0, 5);
            NhanVien nv = new NhanVien(id, "dk1",sqlDate , "abc", "NV", "01234567894", "asdf@gh", "123");
            QuanLyNhanVien.themNhanVien(nv);
            
            nv.setHoTen("asdfghjklaasdfghjklaasdfghjklaasdfghjklaasdfghjklaklaasdfghjk");
            assertFalse(QuanLyNhanVien.capNhatNhanVien(nv));
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
