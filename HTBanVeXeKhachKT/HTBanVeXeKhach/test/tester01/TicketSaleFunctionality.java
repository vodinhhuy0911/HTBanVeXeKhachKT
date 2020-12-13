/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester01;

import BanVeXeKhach.VeXe;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
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
    
    @Test
    public void TC69() {
         Date d = new java.sql.Date(14/12/2020);
         Date d1 = new java.sql.Date(21/12/2020);
        VeXe vx = new VeXe(UUID.randomUUID().toString(), "35HA-0908", "1", "abc", "0123456789", "A2", d, false, d1, "6", 8000, "3", false);
        BVXK.QuanLyVeXe.themVe(vx);
        VeXe vx1 = new VeXe(UUID.randomUUID().toString(), "35HA-0908", "1", "abc", "0123456789", "A2", d, false, d1, "6", 8000, "3", false);
        Assert.assertFalse( BVXK.QuanLyVeXe.themVe(vx1));
    }
}
