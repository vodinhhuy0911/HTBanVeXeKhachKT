/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import BanVeXeKhach.DangNhap;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ASUS
 */
public class LoginFunctionality {
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
    public void TC1(){     
        try {
            System.out.println("TC1: Check the results on entering correct Username & Password.");
            
            String tk = "1";
            String mk = "123";
            assertNotNull(BVXK.Login.login(tk, mk));
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC2(){     
        try {
            System.out.println("TC2: Check the results on entering correct Username & incorrect Password.");
            
            String tk = "1";
            String mk = "1234";
            assertNull(BVXK.Login.login(tk, mk));
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC3(){     
        try {
            System.out.println("TC3: Check the results on entering incorrect Username & correct Password.");
            
            String tk = "1 ";
            String mk = "123";
            assertNull(BVXK.Login.login(tk, mk));
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC4(){     
        try {
            System.out.println("TC4: Check the results on entering incorrect Username & Password.");
            
            String tk = "1  ";
            String mk = "123  ";
            assertNull(BVXK.Login.login(tk, mk));
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC5(){     
        try {
            System.out.println("TC5: Check the results on entering Username & Password starting with blank spaces.");
            
            String tk = " 1";
            String mk = " 123";
            assertNull(BVXK.Login.login(tk, mk));
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC6(){     
        try {
            System.out.println("TC6: Check the results on clicking the Login button without entering Username and Password.");
            
            String tk = "";
            String mk = "";
            assertNull(BVXK.Login.login(tk, mk));
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC7(){     
        try {
            System.out.println("TC7: Check the results on clicking the Login button without entering Username, but entering Password.");
            
            String tk = "";
            String mk = "123";
            assertNull(BVXK.Login.login(tk, mk));
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC8(){     
        try {
            System.out.println("TC8:Check the results on clicking the Login button without entering Password, but entering Username.");
            
            String tk = "1";
            String mk = "";
            assertNull(BVXK.Login.login(tk, mk));
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
