package BVXK;


import BanVeXeKhach.TuyenDuong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
           String maXe = rs.getString("MaXe");
           Date thoiGianKhoiHanh = rs.getDate("NgayKhoiHanh");
           String gioKhoiHanh = rs.getString("GioKhoiHanh");
           TuyenDuong td = new TuyenDuong(maTuyenDuong,tuyenDi,tuyenDen,maXe,thoiGianKhoiHanh,gioKhoiHanh);
           kq.add(td);
       }
       return kq;
   }
   
    public static void themTuyenDuong(TuyenDuong td) throws SQLException 
    {
         String sql = "INSERT INTO lotrinh (MaLoTrinh, TuyenDi,TuyenDen,MaXe,NgayKhoiHanh,GioKhoiHanh) VALUES (?,?,?,?,?,?)";
         Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, td.getMaTuyenDuong());
        pStm.setString(2, td.getTuyenDi());
        pStm.setString(3,td.getTuyenDen());
        pStm.setString(4,td.getMaXe());

        java.util.Date date = td.getThoiGianKhoiHanh();
 
       java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
       
        pStm.setDate(5, sqlDate);
        pStm.setString(6, td.getGioKhoiHanh());
        pStm.executeUpdate();
        
        cnt.commit();
    }
    public static void capNhatTuyenDuong(String tuyenDi, String tuyenDen,String maLoTrinh, String maXe, Date thoiGianKhoiHanh, String GioKhoiHanh) throws SQLException
   {
       String sql = "UPDATE lotrinh SET TuyenDi = ?, TuyenDen = ?, MaXe = ?, NgayKhoiHanh = ?, GioKhoiHanh = ? WHERE MaLoTrinh = ?";
        Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, tuyenDi);
        pStm.setString(2, tuyenDen);
        pStm.setString(3, maXe);
        java.util.Date date = thoiGianKhoiHanh;
      java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 

        pStm.setDate(4, sqlDate);
        pStm.setString(5, GioKhoiHanh);
       pStm.setString(6, maLoTrinh);
       
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
   
   public static List<String> getMaXe(String tuyenDi, String tuyenDen) throws SQLException
   {
       Connection conn = JDBC.getConn();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT MaXe FROM lotrinh WHERE TuyenDi like N'"+ tuyenDi + "' AND TuyenDen like N'"+tuyenDen+"'" );
       List<String> kq = new ArrayList<>();
       while(rs.next())
       {
           
           String maXe = rs.getString("MaXe");
           
           kq.add(maXe);
       }
       return kq;
   }
   
   public static List<String> getNgayKhoiHanh(String tuyenDi,String tuyenDen, String maXe) throws SQLException
   {
       Connection conn = JDBC.getConn();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT NgayKhoiHanh FROM lotrinh WHERE TuyenDi like N'"+ tuyenDi + "' AND TuyenDen like N'"+tuyenDen+"' AND MaXe = '"+maXe+"'");
       List<String> kq = new ArrayList<>();
       while(rs.next())
       {
           kq.add(rs.getDate("NgayKhoiHanh").toString());
       }
       return kq;
   }
   
   public static List<String> getGioKhoiHanh(String tuyenDi,String tuyenDen, String maXe, String ngayKhoiHanh) throws SQLException
   {
       Connection conn = JDBC.getConn();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT GioKhoiHanh FROM lotrinh WHERE TuyenDi like N'"+ tuyenDi + "' AND TuyenDen like N'"+tuyenDen+"' AND MaXe = '"+maXe+"' AND NgayKhoiHanh = '"+ngayKhoiHanh+"'");
       List<String> kq = new ArrayList<>();
       while(rs.next())
       {
           kq.add(rs.getString("GioKhoiHanh").toString());
       }
       return kq;
   }
   
   
   public static String getMaLoTrinh(String tuyenDi, String tuyenDen, String maXe, String ngayKhoiHanh, String gioKhoiHanh) throws SQLException
   {
       Connection conn = JDBC.getConn();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT MaLoTrinh FROM lotrinh WHERE TuyenDi like N'"+ tuyenDi + "' AND TuyenDen like N'"+tuyenDen+"' AND NgayKhoiHanh = '" + ngayKhoiHanh + "' AND GioKhoiHanh = '"+ gioKhoiHanh+"'");
       while(rs.next())
       {
           return rs.getString("MaLoTrinh");
       }
       return null;
   }
}
