/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import BVXK.QuanLyVeXe;
import BanVeXeKhach.VeXe;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
