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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
public class TicketListFunctionality {
    public static Connection conn;
    
    @BeforeClass
    public static void setUp() {
        conn = BVXK.JDBC.getConn();
        System.out.println("Clean data in xe, vexe, lotrinh in database for better test-result");
        LocalDateTime now = LocalDateTime.now();
        String date = now.getMonthValue() + "/" + now.getDayOfMonth() + "/" + now.getYear();

         String gioKH;
        if(now.getHour() + 4 <= 24)
           gioKH = String.valueOf(now.getHour() + 4) + ":00";
        else
        {
            gioKH = String.valueOf(now.getHour() + 4 - 24) + ":00";
            date = now.getMonthValue() + "/" + String.valueOf(now.getDayOfMonth() + 1) + "/" + now.getYear();
        }
        Date d = new java.util.Date(date);
        Xe xe = new Xe("35HA-0909", "Xe giường nằm");
        QuanLyXe.themXe(xe);
        TuyenDuong t = new TuyenDuong("4", "SaPa", "Lào Cai", xe.getBienSoXe(), d, gioKH);
        QuanLyTuyenDi.themTuyenDuong(t);
            
        Xe xe1 = new Xe("35HA-0969", "Xe giường nằm");
        QuanLyXe.themXe(xe1);
        TuyenDuong t1 = new TuyenDuong("5", "Mộc Châu", "Điện Biên", xe1.getBienSoXe(), d, gioKH);
        QuanLyTuyenDi.themTuyenDuong(t1);
    }
    
     @AfterClass
    public static void tearDown() {
        try {
//            QuanLyXe.xoaXe("35HA-0909");
//            QuanLyXe.xoaXe("35HA-0969");
            BVXK.JDBC.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void TC110() {
        try {
            System.out.println("* Check Update");
            System.out.println("TC110: Check the results on clicking the Update button when changing information of the ticket that has already been sold .");
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4) + ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24) + ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String giod;
            if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2);
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24);
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }

            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A1", d, true, sqlDate, gioKH, 8000, "4", true);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            assertTrue(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "fail", "0123456789", "A1", dt, true, d1, gioKH, 8000, "4", id, true, dt));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TC111() {    
        try {
            System.out.println("TC111: Check the results on clicking the Update button when updating customer's name, phone number earlier than departure time at least 60 minutes 01 second.");
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH ;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24) + ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String giod;
            if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":00:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24 + ":00:00");
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A2", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
            if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":50:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":50:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
 
            dt = ngayd + " " + giod;
            assertTrue(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "csdq111", "0123456789", "A2", dt, false, d1, gioKH, 8000, "4", id, false, dt));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC112() {    
        try {
            System.out.println("TC112: Check the results on clicking the Update button when updating customer's name, phone number exactly 60 minutes before the departure time .");
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4) + ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24) + ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":59:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":59:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A3", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":00:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":00:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            dt = ngayd + " " + giod; 
            assertFalse(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "csdq112", "0123456789", "A3", dt, false, d1, gioKH, 8000, "4", id, false, dt));
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC113() {    
        try {
            System.out.println("TC113: Check the results on clicking the Update button when updating customer's name, phone number earlier than departure time no more than 59 minutes 59 second .");
            
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4) + ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24) + ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":59:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":59:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A4", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
            if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":01:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":01:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertFalse(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "csdq113", "0123456789", "A4", dt, false, d1, gioKH, 8000, "4", id, false, dt));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC114() {    
        try {
            System.out.println("TC114: Check the results on clicking the Update button when changing to another empty seat in the same route earlier than departure time at least 60 minutes 01 second.");
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod = String.valueOf(now.getHour() + 2) + ":" + String.valueOf(59) + ":00";
           
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4) + ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24) + ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":59:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":59:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A5", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":50:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":50:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }

             dt = ngayd + " " + giod; 
            assertTrue(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "csdq", "0123456789", "A6", dt, false, d1, gioKH, 8000, "4", id, false, dt));
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC115() {    
        try {
            System.out.println("TC115: Check the results on clicking the Update button when changing to another empty seat in the same route exactly 60 minutes before the departure time .");
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4) + ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24) + ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":59:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":59:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A7", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
                          if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":50:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":50:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertFalse(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "csdq", "0123456789", "A8", dt, false, d1, gioKH, 8000, "4", id, false, dt));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC116() {    
        try {
            System.out.println("TC116: Check the results on clicking the Update button when changing to another empty seat in the same route earlier than departure time no more than 59 minutes 59 seconds.");
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
             if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4) + ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24) + ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":59:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":59:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd + " " +   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A8", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
             if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":50:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":50:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertTrue(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "csdq", "0123456789", "A9", dt, false, d1, gioKH, 8000, "4", id, false, dt));
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC117() {    
        try {
            System.out.println("TC117: Check the results on clicking the Update button when changing the route earlier than departure time at least 60 minutes 01 second.");
            
            
            LocalDateTime now = LocalDateTime.now();
            
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
              if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4) + ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24) + ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":59:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":59:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A9", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
            if(now.getHour() + 1 <= 24)
               giod = String.valueOf(now.getHour() + 1) + ":1:00";
            else
            {
                giod = String.valueOf(now.getHour() + 1 - 24) + ":1:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertTrue(QuanLyVeXe.capNhatVeXe("35HA-0969", "1", "csdq", "0123456789", "A10", dt, false, d1, gioKH, 8000, "5", id, false, dt));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC118() {    
        try {
            System.out.println("TC118: Check the results on clicking the Update button when changing the route exactly 60 minutes before the departure time .");
            
            LocalDateTime now = LocalDateTime.now();
            
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
             if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4) + ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":00:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":00:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A11", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
                          if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":01:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":01:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertFalse(QuanLyVeXe.capNhatVeXe("35HA-0969", "1", "csdq", "0123456789", "A12", dt, false, d1, gioKH, 8000, "5", id, false, dt));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC119() {    
        try {
            System.out.println("TC119: Check the results on clicking the Update button when changing the route earlier than departure time no more than 59 minutes 59 second.");
            
            LocalDateTime now = LocalDateTime.now();
            
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 2 <= 24)
               giod = String.valueOf(now.getHour() + 2) + ":59:00";
            else
            {
                giod = String.valueOf(now.getHour() + 2 - 24) + ":59:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A12", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
                         if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":1:00";
            else
            {
                giod = String.valueOf(now.getHour() +3 - 24) + ":1:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertFalse(QuanLyVeXe.capNhatVeXe("35HA-0969", "1", "csdq", "0123456789", "B9", dt, false, d1, gioKH, 8000, "5", id, false, dt));
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC120() {    
        try {
            System.out.println("TC120: Check the results on clicking the Update button when changing the payment status of a ticket that has not been sold earlier than departure time no more than 4 minutes 59 seconds .");
            
            LocalDateTime now = LocalDateTime.now();
            
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":56:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":56:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "B10", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
                          if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":1:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":1:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertFalse(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "csdq", "0123456789", "B10", dt, true, d1, gioKH, 8000, "4", id, false, dt));
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
        @Test
    public void TC123() {    
        try {
            System.out.println("TC123: Check the results on clicking the Update button when changing the payment status of a ticket that already been sold.");
            
            LocalDateTime now = LocalDateTime.now();
            
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
              if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":56:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":56:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "B11", d, true, sqlDate, gioKH, 8000, "4", true);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
                       if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":1:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":1:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertTrue(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "csdq", "0123456789", "B10", dt, false, d1, gioKH, 8000, "4", id, true, dt));
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC124() {    
        try {
            System.out.println("TC124: Check the results on clicking the Update button when selecting a ticket that has not been sold and clicking the get ticket radio button .");
            
            LocalDateTime now = LocalDateTime.now();
            
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":56:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":56:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csdq", "0123456789", "A5", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
                          if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":1:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":1:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertTrue(QuanLyVeXe.capNhatVeXe("35HA-0909", "1", "csdq", "0123456789", "A5", dt, true, d1, gioKH, 8000, "4", id, true, dt));
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC125() {    
        try {
            System.out.println("TC125: Check the results on clicking the Update button when a ticket is collected at least 30 minutes 01 seconds earlier than departure time.");
            
            LocalDateTime now = LocalDateTime.now();
            
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":31:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":31:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0969", "1", "csdq", "0123456789", "A1", d, true, sqlDate, gioKH, 8000, "5", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
                          if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":1:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":1:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertTrue(QuanLyVeXe.capNhatVeXe("35HA-0969", "1", "csdq", "0123456789", "A1", dt, true, d1, gioKH, 8000, "5", id, true, dt));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Test
    public void TC126() {    
        try {
            System.out.println("TC126: Check the results on clicking the Update button when a ticket is collected exactly 30 minutes before the departure time.");
            
            LocalDateTime now = LocalDateTime.now();
            
            String gioKH;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth();
            String giod;
            
             if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
              if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":30:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":30:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
            
            String dt = ngayd +" "+   giod;
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0969", "1", "csdq", "0123456789", "A2", d, true, sqlDate, gioKH, 8000, "5", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
                          if(now.getHour() + 3 <= 24)
               giod = String.valueOf(now.getHour() + 3) + ":1:00";
            else
            {
                giod = String.valueOf(now.getHour() + 3 - 24) + ":1:00";
                ngayd = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1);
            }
             dt = ngayd + " " + giod; 
            assertFalse(QuanLyVeXe.capNhatVeXe("35HA-0969", "1", "csdq", "0123456789", "A2", dt, false, d1, gioKH, 8000, "5", id, true, dt));
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // delete
            @Test
    public void TC127() {    
        try {
            System.out.println("TC127: Check the results on clicking the Delete button when selecting the ticket that has already been sold .");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngaygio = "2020-12-14 23:30:00";
            Date d = formatter.parse(ngaygio);
            Date d1 = new java.util.Date("2020/12/21");
            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
            Random rand = new Random();
            LocalDateTime now = LocalDateTime.now();
            String gioKH;
            if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
            }
                    
            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0909", "1", "csd", "0123456789", "B3", d, true, sqlDate, gioKH, 8000, "4", true);
            BVXK.QuanLyVeXe.themVe(vx);

            assertFalse(QuanLyVeXe.xoaVeXe(vx.getMaVe(), true));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            @Test
    public void TC128() {    
            System.out.println("TC128: Check the results on clicking the Delete button without selecting any ticket in the ticket table");
            
            assertFalse(QuanLyVeXe.xoaVeXe(null, false));        
    }
    @Test
    public void TC129() {    
        try {
            System.out.println("TC129: Check the results on clicking the Delete button when selecting a ticket that has not been sold and deleting it earlier than departure time at least 30 minutes 01 second.");
            LocalDateTime now = LocalDateTime.now();

            String gioKH;
                        if(now.getHour() + 4 <= 24)
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");     
            String dt = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth() +" "+   String.valueOf(now.getHour() + 3) + ":" + String.valueOf(29) + ":00";
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csd5", "0123479989", "B1", d, false, sqlDate, gioKH, 8000, "4", false);  
            BVXK.QuanLyVeXe.themVe(vx);      
            QuanLyVeXe.doiGioDatVe(dt, id);
           assertTrue(QuanLyVeXe.xoaVeXe(vx.getMaVe(), false));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            @Test
    public void TC130() {    
        try {
            System.out.println("TC130: Check the results on clicking the Delete button when selecting a ticket that has not been sold and deleting it exactly 30 minutes before departure time.");
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH;
            String dt;  
            Date d;
            String d1;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                     
            if(now.getHour() + 4 <= 24){
               gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            
                dt = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth() +" "+   String.valueOf(now.getHour() + 3) + ":" + String.valueOf(30) + ":00";
                d = formatter.parse(dt);
                d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            }   
            else
            {
                gioKH = String.valueOf(now.getHour() + 4 - 24)+ ":00";
                dt = now.getYear() + "-" + now.getMonthValue()+ "-" + String.valueOf(now.getDayOfMonth() + 1) +" "+   String.valueOf(now.getHour() + 3) + ":" + String.valueOf(30) + ":00";
                d = formatter.parse(dt);
                d1 = now.getYear() + "/" + now.getMonthValue() + "/" + String.valueOf(now.getDayOfMonth() + 1);    
            }
            
            Date date1 = new java.util.Date(d1);
                java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csd", "0123456789", "B2", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
            assertTrue(QuanLyVeXe.xoaVeXe(vx.getMaVe(), false));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            @Test
    public void TC131() {    
        try {
            System.out.println("TC131: Check the results on clicking the Delete button when selecting a ticket that has not been sold and deleting it exactly 5 minutes before departure time .");
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dt = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth() +" "+   String.valueOf(now.getHour() + 3) + ":" + String.valueOf(55) + ":00";
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csd", "0123456789", "B5", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
            assertTrue(QuanLyVeXe.xoaVeXe(vx.getMaVe(), false));
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            @Test
    public void TC132() {    
        try {
            System.out.println("TC132: Check the results on clicking the Delete button when selecting a ticket that has not been sold and deleting it earlier than departure time no more than 4 minutes 59 seconds.");
            
            System.out.println("TC131: Check the results on clicking the Delete button when selecting a ticket that has not been sold and deleting it exactly 5 minutes before departure time .");
            
            LocalDateTime now = LocalDateTime.now();
            String gioKH = String.valueOf(now.getHour() + 4)+ ":00";
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dt = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth() +" "+   String.valueOf(now.getHour() + 3) + ":" + String.valueOf(56) + ":00";
            Date d = formatter.parse(dt);
            String d1 = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            Date date1 = new java.util.Date(d1);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            
            Random rand = new Random();
            String id = String.valueOf(rand.nextInt(99999));
            
            VeXe vx = new VeXe(id, "35HA-0909", "1", "csd", "0123456789", "B6", d, false, sqlDate, gioKH, 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            QuanLyVeXe.doiGioDatVe(dt, id);
            
            assertTrue(QuanLyVeXe.xoaVeXe(vx.getMaVe(), false));
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            
             List<VeXe> ds = QuanLyVeXe.timKiemLoTrinh("Lào Cai");
            List<VeXe> kq = new ArrayList<>();
            assertNotEquals(ds, kq);
            
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                @Test
    public void TC134() {    
        try {
            System.out.println("TC134: Check the results on clicking the Find button when entering a route departure point that exists in the database.");
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");          
            String ngaygio = "2020-12-14 23:30:00";
            Date d = formatter.parse(ngaygio);
            Date d1 = new java.util.Date("2020/12/21");
            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
            Random rand = new Random();
            
            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0909", "1", "csd", "0123456789", "B7", d, false, sqlDate, "6", 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);    
            
            List<VeXe> ds = QuanLyVeXe.timKiemLoTrinh("SaPa");
            List<VeXe> kq = new ArrayList<>();
            assertNotEquals(ds, kq);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                @Test
    public void TC135() {    
        try {
            System.out.println("TC135: Check the results on clicking the Find button when entering a route departure point does not exist in the database.");
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngaygio = "2020-12-14 23:30:00";
            Date d = formatter.parse(ngaygio);
            Date d1 = new java.util.Date("2020/12/21");
            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
            Random rand = new Random();
            
            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0909", "1", "csd", "0123456789", "B8", d, false, sqlDate, "6", 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);
            
            List<VeXe> ds = QuanLyVeXe.timKiemLoTrinh("Hihihihihi");
            List<VeXe> kq = new ArrayList<>();
            assertEquals(ds, kq);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                @Test
    public void TC136() {    
        try {
            System.out.println("TC136: Check the results on clicking the Find button when entering a customer's information exist in the ticket table.");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");          
            String ngaygio = "2020-12-14 23:30:00";
            Date d = formatter.parse(ngaygio);
            Date d1 = new java.util.Date("2020/12/21");
            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
            Random rand = new Random();
            
            VeXe vx = new VeXe(String.valueOf(rand.nextInt(99999)), "35HA-0909", "1", "csd", "0123456789", "B4", d, false, sqlDate, "6", 8000, "4", false);
            BVXK.QuanLyVeXe.themVe(vx);    
            
            List<VeXe> ds = QuanLyVeXe.timKiemLoTrinh("csd");
            List<VeXe> kq = new ArrayList<>();
            assertEquals(ds, kq);
        } catch (ParseException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketListFunctionality.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
