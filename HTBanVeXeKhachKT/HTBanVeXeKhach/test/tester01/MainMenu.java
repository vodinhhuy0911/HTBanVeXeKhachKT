/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
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
        System.out.println("* Check Add employee button");        
        System.out.println("TC13: Check the results on clicking the Add button after entering all fields with valid data.");
        
        
    }
    
     @Test
    public void TC14(){  
        System.out.println("TC14: Check the results on clicking Edit button after editing valid data in name, DOB, address, position, phone number, email fields.");
        
        
    }
    
     @Test
    public void TC15(){     
        System.out.println("TC15: Check the results on clicking Edit button after deleting information of the employee in all fields except the employee id field.");
        
        
    }
    
     @Test
    public void TC16(){      
        System.out.println("TC16: Check the results on clicking Edit button when entering wrong type of date formatting in DOB field.");
        
        
    }
    
     @Test
    public void TC17(){     
        System.out.println("TC17: Check the results on clicking Edit button when entering a random string in DOB field.");
        
        
    }
    
     @Test
    public void TC18(){  
        System.out.println("TC18: Check the results on clicking Edit button when entering a negative number in Phone number field.");
        
        
    }
    
     @Test
    public void TC19(){    
        System.out.println("TC19: Check the results on clicking Edit button when entering a phone number containing alphabetic or special characters in Phone number field.");
        
        
    }
    
     @Test
    public void TC20(){      
        System.out.println("TC20: Check the results on clicking Edit button when entering wrong type of email formatting in email field.");
        
        
    }
    
     @Test
    public void TC21(){        
        System.out.println("TC21: Check the results on clicking Edit button when updating an information which length is longer than the limit length of the field.");
        
        
    }
}
