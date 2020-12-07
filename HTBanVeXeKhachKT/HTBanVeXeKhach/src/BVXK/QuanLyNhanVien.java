package BVXK;


import BanVeXeKhach.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vohuy
 */
public class QuanLyNhanVien {
    public static List<NhanVien> getDsNhanVien () throws SQLException
    {
        Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien");
        List<NhanVien> kq = new ArrayList<>();
        while (rs.next()) {
            String tk = rs.getString("idNV");
            String name = rs.getString("tenNV");
            Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String chucVu = rs.getString("chucVu");
            String sdt = rs.getString("sdt");
            String email = rs.getString("email");
            NhanVien nv = new NhanVien(tk,name,ngaySinh,diaChi,chucVu,sdt,email);
            
            kq.add(nv);
        }
        return kq;
    }

    public static void themNhanVien(NhanVien nv) throws SQLException, ParseException
    {
        String sql = "INSERT INTO nhanvien (idNV, tenNV, ngaySinh, diaChi, chucVu, sdt, email) VALUES (?,?,?,?,?,?,?)";
        Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, nv.getTaiKhoan());
        pStm.setString(2, nv.getHoTen());

        
      java.util.Date date = nv.getNgaySinh();
      java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 

        pStm.setDate(3, sqlDate);
        
        pStm.setString(4, nv.getDiaChi());
        pStm.setString(5, nv.getChucVu());
        pStm.setString(6, nv.getSdt());
        pStm.setString(7, nv.getEmail());
        pStm.executeUpdate();
        
        cnt.commit();
    }
    
    public static NhanVien timKiemNvID(String taiKhoan) throws SQLException
    {
        Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien WHERE idNV = " + taiKhoan);
        while (rs.next()) {
            String tk = rs.getString("idNV");
            String name = rs.getString("tenNV");
            Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String chucVu = rs.getString("chucVu");
            String sdt = rs.getString("sdt");
            String email = rs.getString("email");
            NhanVien nv = new NhanVien(tk,name,ngaySinh,diaChi,chucVu,sdt,email);
            return nv;
        }
        return null;
    }

   public static void capNhatNhanVien(NhanVien nv) throws SQLException
   {
       String sql = "UPDATE nhanvien SET tenNV = ?, ngaySinh = ?, diaChi = ?, chucVu = ?, sdt = ?, email = ? WHERE idNV = ?";
        Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, nv.getHoTen());
        
        java.util.Date date = nv.getNgaySinh();
       java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
        pStm.setDate(2, sqlDate);
        
        
        pStm.setString(3, nv.getDiaChi());
        pStm.setString(4, nv.getChucVu());
        pStm.setString(5, nv.getSdt());
        pStm.setString(6, nv.getEmail());
        pStm.setString(7, nv.getTaiKhoan());
        pStm.executeUpdate();
        cnt.commit();
   }
   public static void xoaNhanVien(String taiKhoan) throws SQLException
   {
       String sql = "DELETE FROM nhanvien WHERE idNV = " + taiKhoan;
        Connection cnt = JDBC.getConn();
        PreparedStatement pStm = cnt.prepareStatement(sql);
         pStm.executeUpdate();
         cnt.commit();
   }

}
