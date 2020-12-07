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
    
    public static void themXe(Xe xe) throws SQLException 
    {
         String sql = "INSERT INTO xe (MaXe, LoaiXe) VALUES (?,?)";
         Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, xe.getBienSoXe());
        pStm.setString(2, xe.getLoaiXe());
        pStm.executeUpdate();
        
        cnt.commit();
    }
    public static void capNhatXe(Xe xe, String maXeCu) throws SQLException
   {
       String sql = "UPDATE xe SET MaXe = ?, LoaiXe = ? WHERE MaXe = ?";
        Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, xe.getBienSoXe());
        
        
        
        pStm.setString(2, xe.getLoaiXe());
        pStm.setString(3, maXeCu);
       
        pStm.executeUpdate();
        cnt.commit();
   }
   public static void xoaXe(String bienSoXe) throws SQLException
   {
       String sql = "DELETE FROM xe WHERE MaXe = '" + bienSoXe+"'";
      
        Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
         pStm.executeUpdate();
         cnt.commit();
         //ok
   }

}
