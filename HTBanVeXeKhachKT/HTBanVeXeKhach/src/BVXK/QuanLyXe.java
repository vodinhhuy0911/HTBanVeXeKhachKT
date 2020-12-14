/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BVXK;

import BanVeXeKhach.Xe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vohuy
 */
public class QuanLyXe {
    List<Xe> dsXe = new ArrayList<>();
    
    public static List<Xe> getXe() throws SQLException
    {
        Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM xe");
        List<Xe> kq = new ArrayList<>();
        while (rs.next()) {
            String bienSoXe = rs.getString("MaXe");
            String loaiXe = rs.getString("LoaiXe");
            Xe xe = new Xe(bienSoXe,loaiXe);
            kq.add(xe);
        }
        return kq;
    }
    
    public static boolean themXe(Xe xe) 
    {
        if(xe.getBienSoXe() != null &&xe.getLoaiXe()!= null)
        {
         String sql = "INSERT INTO xe (MaXe, LoaiXe) VALUES (?,?)";
         Connection cnt = JDBC.getConn();
        try {
            cnt.setAutoCommit(false);
            PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, xe.getBienSoXe());
        pStm.setString(2, xe.getLoaiXe());
        pStm.executeUpdate();
        
        cnt.commit();
        return true;
        } catch (SQLException ex) {
            return false;
        }
        }
        return false;
        
    }
    public static boolean capNhatXe(Xe xe, String maXeCu)
   {
       //capnhat lotrinh
       if(xe.getBienSoXe() != null &&xe.getLoaiXe() != null &&maXeCu != null && xe.getBienSoXe() != "")
       {
       String sql = "UPDATE lotrinh SET MaXe = ? WHERE MaXe = ?";
       Connection cnt = JDBC.getConn();
        try {
            cnt.setAutoCommit(false);
            PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, xe.getBienSoXe());
         pStm.setString(2, xe.getBienSoXe());
         pStm.executeUpdate();
        cnt.commit();
        pStm.close();
        //capnhatvexe
        sql = "UPDATE vexe SET BienSoXe = ? WHERE BienSoXe = ?";
       
        
        pStm = cnt.prepareStatement(sql);
        pStm.setString(1, xe.getBienSoXe());
         pStm.setString(2, xe.getBienSoXe());
         pStm.executeUpdate();
        cnt.commit();
        pStm.close();
       // 
       sql = "UPDATE xe SET MaXe = ?, LoaiXe = ? WHERE MaXe = ?";
          pStm = cnt.prepareStatement(sql);
       
        pStm.setString(1, xe.getBienSoXe());
        pStm.setString(2, xe.getLoaiXe());
        pStm.setString(3, maXeCu);
        pStm.executeUpdate();
        cnt.commit();
        return true;
        } catch (SQLException ex) {
            return false;
        }
       }
       return false;
        
   }
   public static boolean xoaXe(String bienSoXe)
   {
       //xoa tren bang vexe
       if(bienSoXe != null)
       {
       String sql = "DELETE FROM vexe WHERE BienSoXe = '" + bienSoXe+"'";
      
        Connection cnt = JDBC.getConn();
        
          int kq = 0;
             Statement stm;
           try {
               stm = cnt.createStatement();
               ResultSet rs = stm.executeQuery("SELECT count(*) FROM xe WHERE MaXe  = N'" + bienSoXe+"'");
               while(rs.next())
               {
                   kq = rs.getInt(1);
               }
           } catch (SQLException ex) {
               Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
           }
        if(kq == 1)
        {
        try {
            cnt.setAutoCommit(false);
            PreparedStatement pStm = cnt.prepareStatement(sql);
         pStm.executeUpdate();
         cnt.commit();
         pStm.close();
       //xoa tren bang lotrinh
       sql = "DELETE FROM lotrinh WHERE MaXe = '" + bienSoXe+"'";
      
       
        cnt.setAutoCommit(false);
        pStm = cnt.prepareStatement(sql);
         pStm.executeUpdate();
         cnt.commit();
         pStm.close();
       //xoa tren bang xe
       sql = "DELETE FROM xe WHERE MaXe = '" + bienSoXe+"'";
        cnt.setAutoCommit(false);
        pStm = cnt.prepareStatement(sql);
         pStm.executeUpdate();
         cnt.commit();
         //ok
         return true;
        } catch (SQLException ex) {
            return false;
        }
       }
       else
           return false;}return false;
   }

   public static String getLoaiXe(String id) throws SQLException
   {
       Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT LoaiXe FROM xe WHERE MaXe = '" + id +"'");
        String kq;
        while(rs.next())
        {
            return rs.getString("LoaiXe");
        }
       return null;
        
   }
}
