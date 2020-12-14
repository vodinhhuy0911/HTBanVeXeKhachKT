/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import BVXK.QuanLyTuyenDi;
import BVXK.QuanLyVeXe;
import BVXK.QuanLyXe;
import BanVeXeKhach.TuyenDuong;
import BanVeXeKhach.VeXe;
import BanVeXeKhach.Xe;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static tester01.LoginFunctionality.conn;

/**
 *
 * @author ASUS
 */
public class TicketListFunctionality {
    public static Connection conn;
    
    @BeforeClass
    public static void setUp() {
        conn = BVXK.JDBC.getConn();
        
        UUID uuid = UUID.randomUUID();
        Date d = new java.util.Date("12/18/2020");
        String id = uuid.toString().substring(0, 5);
        String id1 = uuid.toString().substring(0, 15);
        String id2 = uuid.toString().substring(0, 15);
        Xe xe = new Xe("35HA-0909", "Xe giường nằm");
        QuanLyXe.themXe(xe);
        TuyenDuong t = new TuyenDuong("4", id1, id2, xe.getBienSoXe(), d, "6");

        QuanLyTuyenDi.themTuyenDuong(t);
    }
    
     @AfterClass
    public static void tearDown() {
        try {
            BVXK.JDBC.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        // test Update 
    @Test
    public void TC110() {
        System.out.println("* Check Update");        
        System.out.println("TC110: Check the results on clicking the Update button when changing information of the ticket that has already been sold .");
        
       
    }
    
    @Test
    public void TC111() {    
        System.out.println("TC111: Check the results on clicking the Update button when updating customer's name, phone number earlier than departure time at least 60 minutes 01 second.");
        
       
    }
        @Test
    public void TC112() {    
        System.out.println("TC112: Check the results on clicking the Update button when updating customer's name, phone number exactly 60 minutes before the departure time .");
        
       
    }
        @Test
    public void TC113() {    
        System.out.println("TC113: Check the results on clicking the Update button when updating customer's name, phone number earlier than departure time no more than 59 minutes 59 second .");
        
       
    }
        @Test
    public void TC114() {    
        System.out.println("TC114: Check the results on clicking the Update button when changing seat number in the same route earlier than departure time at least 60 minutes 01 second.");
        
       
    }
        @Test
    public void TC115() {    
        System.out.println("TC115: Check the results on clicking the Update button when changing seat number in the same route exactly 60 minutes before the departure time.");
        
       
    }
        @Test
    public void TC116() {    
        System.out.println("TC116: Check the results on clicking the Update button when changing seat number in the same route earlier than departure time no more than 59 minutes 59 seconds.");
        
       
    }
        @Test
    public void TC117() {    
        System.out.println("TC117: Check the results on clicking the Update button when changing the route earlier than departure time at least 60 minutes 01 second.");
        
       
    }
        @Test
    public void TC118() {    
        System.out.println("TC118: Check the results on clicking the Update button when changing the route exactly 60 minutes before the departure time .");
        
       
    }
        @Test
    public void TC119() {    
        System.out.println("TC119: Check the results on clicking the Update button when changing the route earlier than departure time no more than 59 minutes 59 second.");
        
       
    }
        @Test
    public void TC120() {    
        System.out.println("TC120: Check the results on clicking the Update button when changing the payment status of a ticket that has not been sold earlier than departure time no more than 4 minutes 59 seconds .");
        
       
    }
        @Test
    public void TC121() {    
        System.out.println("TC121: Check the results on clicking the Update button when changing the payment status of a ticket that has not been sold exactly 5 minutes before the departure time .");
        
       
    }
        @Test
    public void TC122() {    
        System.out.println("TC122: Check the results on clicking the Update button when changing the payment status of a ticket that has not been sold earlier than departure time at least 5 minutes 01 seconds .");
        
       
    }
        @Test
    public void TC123() {    
        System.out.println("TC123: Check the results on clicking the Update button when changing the payment status of a ticket that already been sold.");
        
       
    }
        @Test
    public void TC124() {    
        System.out.println("TC124: Check the results on clicking the Update button when selecting a ticket that has not been sold and clicking the get ticket radio button .");
        
       
    }
        @Test
    public void TC125() {    
        System.out.println("TC125: Check the results on clicking the Update button when a ticket is collected at least 30 minutes 01 seconds earlier than departure time.");
        
       
    }
        @Test
    public void TC126() {    
        System.out.println("TC126: Check the results on clicking the Update button when a ticket is collected exactly 30 minutes before the departure time.");
        
       
    }
    
    // delete
            @Test
    public void TC127() {    
        System.out.println("TC127: Check the results on clicking the Delete button when selecting the ticket that has already been sold .");
        
       
    }
            @Test
    public void TC128() {    
        System.out.println("TC128: Check the results on clicking the Delete button without selecting any ticket in the ticket table");
        
       
    }
            @Test
    public void TC129() {    
        System.out.println("TC129: Check the results on clicking the Delete button when selecting a ticket that has not been sold and deleting it earlier than departure time at least 30 minutes 01 second.");
        
       
    }
            @Test
    public void TC130() {    
        System.out.println("TC130: Check the results on clicking the Delete button when selecting a ticket that has not been sold and deleting it exactly 30 minutes before departure time.");
        
       
    }
            @Test
    public void TC131() {    
        System.out.println("TC131: Check the results on clicking the Delete button when selecting a ticket that has not been sold and deleting it exactly 5 minutes before departure time .");
        
       
    }
            @Test
    public void TC132() {    
        System.out.println("TC132: Check the results on clicking the Delete button when selecting a ticket that has not been sold and deleting it earlier than departure time no more than 4 minutes 59 seconds.");
        
       
    }
    
    // test find 
                @Test
    public void TC133() {    
        try {
            System.out.println("TC133: Check the results on clicking the Find button when entering a route destination that exists in the database.");
                                          
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngaygio = "2020-12-14 23:30:00";          
            Date d = formatter.parse(ngaygio);
            Date d1 = new java.util.Date("2020/12/21");
            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
            Random rand = new Random();
            
            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0909", "1", "csd", "0123456789", "B1", d, false, sqlDate, "6", 8000, "4", false); 
            BVXK.QuanLyVeXe.themVe(vx);    
            
            
            
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                @Test
    public void TC134() {    
        System.out.println("TC134: Check the results on clicking the Find button when entering a route departure point that exists in the database.");
        
       
    }
                @Test
    public void TC135() {    
        System.out.println("TC135: Check the results on clicking the Find button when entering a route departure point does not exist in the database.");
        
       
    }
                @Test
    public void TC136() {    
        System.out.println("TC136: Check the results on clicking the Find button when entering a customer's information exist in the ticket table.");
        
       
    }
//    // 
//    @Test
//    public void TCx() {
//        System.out.println("*");        
//        System.out.println("TCx: Check if app can get all employees in the database.");
//        
//        try {
//            List<VeXe> ds = QuanLyVeXe.getDsVexe();
//            assertTrue(ds.size() >= 1);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeManagementFunctionality.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
