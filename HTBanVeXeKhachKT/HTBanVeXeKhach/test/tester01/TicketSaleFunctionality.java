/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import BanVeXeKhach.VeXe;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static tester01.LoginFunctionality.conn;

/**
 *
 * @author ASUS
 */
public class TicketSaleFunctionality {
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
//    // test add button
//    @Test
//    public void TC146() {
//        try {
//            System.out.println("* Check Add ticket button:");
//            System.out.println("TC146: Check the results on clicking the Add button after entering all fields with valid data.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";          
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "csd", "0123456789", "B1", d, false, sqlDate, "6", 8000, "3", false); 
//            
//            assertTrue(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//        @Test
//    public void TC147() {
//        try {
//            System.out.println("TC147: Check the results on clicking the Add button after entering the same route information, date, time and seat number but different customer's information.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "abc", "0123456789", "A3",d,  false, sqlDate, "6", 8000, "3", false);
//            BVXK.QuanLyVeXe.themVe(vx);
//            VeXe vx1 = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "def", "0123455559", "A3",d,  false, sqlDate, "6", 8000, "3", false);
//            assertFalse( BVXK.QuanLyVeXe.themVe(vx1));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//            @Test
//    public void TC148() {
//        try {
//            System.out.println("TC148: Check the results on clicking the Add button after entering all fields with the same data as an existing ticket in database.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "abc", "0123456789", "A4",d,  false, sqlDate, "6", 8000, "3", false);
//            BVXK.QuanLyVeXe.themVe(vx);
//           assertFalse(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//                @Test
//    public void TC149() {
//        try {
//            System.out.println("TC149: Check the results on clicking the Add button without giving any data.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), null, null, null, null, null,null,  false, null, null, 0, null, false);
//            assertFalse(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//        @Test
//    public void TC150() {
//        try {
//            System.out.println("TC150: Check the results on clicking the Add button entering all fields except the customer's name field.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", null, "0123456789", "A4", d,  false, sqlDate, "6", 8000, "3", false);
//            assertFalse(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//        @Test
//    public void TC151() {
//        try {
//            System.out.println("TC151: Check the results on clicking the Add button when entering a negative number in Price field.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "test151", "0123456789", "A5", d,  false, sqlDate, "6", -8000, "3", false);
//            assertFalse(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//            @Test
//    public void TC152() {
//        try {
//            System.out.println("TC152: Check the results on clicking the Add button when entering a phone number containing alphabetic or special characters in Phone number field.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "test151", "0123456as99", "A5",d,  false, sqlDate, "6", 8000, "3", false);
//            assertFalse(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//            @Test
//    public void TC153() {
//        try {
//            System.out.println("TC153: Check the results on clicking the Add button when entering the customer's name which length is longer than the limit length of customer's name field.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "asdfghjklaasdfghjklaasdfghjklaasdfghjklaasdfghjkla", "01234568899", "A5",d,  false, sqlDate, "6", 8000, "3", false);
//            assertFalse(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    //////////////////////////////////////////////////////////////////////////////
//                @Test
//    public void TC154() {
//        try {
//            System.out.println("TC154: Check the results when booking a ticket exactly 60 minutes before departure time.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "asdfghjklaasdfghjklaasdfghjklaasdfghjklaasdfghjkla", "0123456as99", "A5",d,  false, sqlDate, "6", 8000, "3", false);
//            assertFalse(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//                @Test
//    public void TC155() {
//        try {
//            System.out.println("TC155: Check the results when booking a ticket at least 60 minutes 01 seconds before departure time.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "etest155", "01234568899", "A6", d,  false, sqlDate, "6", 8000, "3", false);
//            
//            assertTrue(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//                @Test
//    public void TC156() {
//        try {
//            System.out.println("TC156: Check the results when booking a ticket no more than 59 minutes 59 seconds before departure time.");
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String ngaygio = "2020-12-14 23:30:00";         
//            Date d = formatter.parse(ngaygio);
//            Date d1 = new java.util.Date("2020/12/21");
//            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
//            Random rand = new Random();
//            
//            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0908", "1", "asdfghjklaasdfghjklaasdfghjklaasdfghjklaasdfghjkla", "0123456as99", "A5",d,  false, sqlDate, "6", 8000, "3", false);
//            assertFalse(BVXK.QuanLyVeXe.themVe(vx));
//        } catch (ParseException ex) {
//            Logger.getLogger(TicketSaleFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
