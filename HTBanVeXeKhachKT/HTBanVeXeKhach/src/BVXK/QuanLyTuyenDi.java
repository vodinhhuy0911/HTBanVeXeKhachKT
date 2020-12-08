package BVXK;


import BanVeXeKhach.TuyenDuong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vohuy
 */
public class QuanLyTuyenDi {
   public static List<TuyenDuong> getDsTuyenDuong() throws SQLException
   {
       Connection conn = JDBC.getConn();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT * FROM lotrinh");
       List<TuyenDuong> kq = new ArrayList<>();
       while(rs.next())
       {
           String maTuyenDuong = rs.getString("MaLoTrinh");
           String tuyenDi = rs.getString("TuyenDi");
           String tuyenDen = rs.getString("TuyenDen");
           TuyenDuong td = new TuyenDuong(maTuyenDuong,tuyenDi,tuyenDen);
           kq.add(td);
       }
       return kq;
   }
   
    public static void themTuyenDuong(TuyenDuong td) throws SQLException 
    {
         String sql = "INSERT INTO lotrinh (MaLoTrinh, TuyenDi,TuyenDen) VALUES (?,?,?)";
         Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, td.getMaTuyenDuong());
        pStm.setString(2, td.getTuyenDi());
        pStm.setString(3,td.getTuyenDen());
        pStm.executeUpdate();
        
        cnt.commit();
    }
    public static void capNhatTuyenDuong(String tuyenDi, String tuyenDen,String maLoTrinh) throws SQLException
   {
       String sql = "UPDATE lotrinh SET TuyenDi = ?, TuyenDen = ? WHERE MaLoTrinh = ?";
        Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, tuyenDi);
        pStm.setString(2, tuyenDen);
       pStm.setString(3, maLoTrinh);
        pStm.executeUpdate();
        cnt.commit();
   }
   public static void xoaTuyenDuong(String maTuyenDuong) throws SQLException
   {
       String sql = "DELETE FROM lotrinh WHERE MaLoTrinh = '" + maTuyenDuong+"'";
      
        Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
         pStm.executeUpdate();
         cnt.commit();
         //ok
   }
}
