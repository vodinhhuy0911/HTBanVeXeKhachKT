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
import java.util.logging.Level;
import java.util.logging.Logger;

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
   
    public static boolean themTuyenDuong(TuyenDuong td)
    {
         String sql = "INSERT INTO lotrinh (MaLoTrinh, TuyenDi,TuyenDen,MaXe,NgayKhoiHanh,GioKhoiHanh) VALUES (?,?,?,?,?,?)";
         Connection cnt = JDBC.getConn();
       try {
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
        return true;
       } catch (SQLException ex) {
           return false;
       }
        
    }
    public static boolean capNhatTuyenDuong(String tuyenDi, String tuyenDen,String maLoTrinh, String maXe, Date thoiGianKhoiHanh, String GioKhoiHanh)
   {
       //cap nhat bang vexe
       String sql = "UPDATE lotrinh SET MaXe = ?, NgayKhoiHanh = ?, GioKhoiHanh = ? WHERE MaLoTrinh = ?";
        Connection cnt = JDBC.getConn();
       try {
           cnt.setAutoCommit(false);
           PreparedStatement pStm = cnt.prepareStatement(sql);
       pStm.setString(1, maXe);
        java.util.Date date = thoiGianKhoiHanh;
      java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 

        pStm.setDate(2, sqlDate);
        pStm.setString(3, GioKhoiHanh);
       pStm.setString(4, maLoTrinh);
             pStm.executeUpdate();
        cnt.commit();
        pStm.close();
       //cap nhat bang lo trinh
       sql = "UPDATE lotrinh SET TuyenDi = ?, TuyenDen = ?, MaXe = ?, NgayKhoiHanh = ?, GioKhoiHanh = ? WHERE MaLoTrinh = ?";
        
        cnt.setAutoCommit(false);
        pStm = cnt.prepareStatement(sql);
        pStm.setString(1, tuyenDi);
        pStm.setString(2, tuyenDen);
        pStm.setString(3, maXe);
        pStm.setDate(4, sqlDate);
        pStm.setString(5, GioKhoiHanh);
       pStm.setString(6, maLoTrinh);
        pStm.executeUpdate();
        cnt.commit();
        return true;
       } catch (SQLException ex) {
           return false;
       }
        
   }
   public static boolean xoaTuyenDuong(String maTuyenDuong)
   {
       //xoa tren bang vexe
       if(maTuyenDuong != null)
       {
       String sql = "DELETE FROM vexe WHERE MaLoTrinh = '" + maTuyenDuong+"'";
        Connection cnt = JDBC.getConn();
       try {
           cnt.setAutoCommit(false);
           PreparedStatement pStm = cnt.prepareStatement(sql);
         pStm.executeUpdate();
         cnt.commit();
       
       //xoa tren bang tuyenduong
       sql = "DELETE FROM lotrinh WHERE MaLoTrinh = '" + maTuyenDuong+"'";
        
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
       return false;
        
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
   public static String getTuyenDuong(String maLoTrinh) throws SQLException
   {
       Connection conn = JDBC.getConn();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT TuyenDi, TuyenDen FROM lotrinh WHERE MaLoTrinh = '" +maLoTrinh+"'" );
       while(rs.next())
       {
           return rs.getString("TuyenDi") + " - " + rs.getString("TuyenDen");
       }
       return null;
   }
       public static List<TuyenDuong> timKiemTuyenDuong(String key) throws SQLException
    {
        Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM lotrinh WHERE MaLoTrinh like '" + key + "' OR TuyenDi like N'" + key
        + "' OR TuyenDen like N'" + key+ "' OR MaXe like N'" + key+ "' OR NgayKhoiHanh like '" + key+ "' OR GioKhoiHanh like N'" + key +"'");
        List<TuyenDuong> dsTuyenDuong = new ArrayList<>();
        while (rs.next()) {
            String maTuyenDuong = rs.getString("MaLoTrinh");
            String tuyenDi = rs.getString("TuyenDi");
            String tuyenDen = rs.getString("TuyenDen");
            String maXe = rs.getString("MaXe");
            Date thoiGianKhoiHanh = rs.getDate("NgayKhoiHanh");
            String gioKhoiHanh = rs.getString("GioKhoiHanh");
            TuyenDuong tuyenDuong = new TuyenDuong(maTuyenDuong,tuyenDi,tuyenDen,maXe,thoiGianKhoiHanh,gioKhoiHanh);
            dsTuyenDuong.add(tuyenDuong);
        }
        return dsTuyenDuong;
    }
   public List<String> getMaLoTrinh(String key) throws SQLException
   {
       Connection conn = JDBC.getConn();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT MaLoTrinh FROM lotrinh WHERE TuyenDi like'"+key+"' OR TuyenVe like '"+key+"'");
       
       List<String> kq = new ArrayList<>();
       while(rs.next())
       {
           kq.add(rs.getString("MaLoTrinh"));
       }
       return kq;
   }
}
