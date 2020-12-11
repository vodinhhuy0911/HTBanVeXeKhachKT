package BVXK;


import BanVeXeKhach.NhanVien;
import BanVeXeKhach.TuyenDuong;
import BanVeXeKhach.VeXe;
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
public class QuanLyVeXe {
    public static List<VeXe> getDsVexe () throws SQLException
    {
        Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM vexe");
        List<VeXe> kq = new ArrayList<>();
        while (rs.next()) {
            String maVe = rs.getString("MaVe");
            String bienSoXe = rs.getString("BienSoXe");
            String maNV = rs.getString("MaNV");
            String hoTenKh = rs.getString("HoTenKH");
            String sdtKh = rs.getString("SDTKH");
            String maGheNgoi = rs.getString("MaGhe");
            
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss ");
            
//            java.util.Date date = rs.getDate("ThoiGianDat");
//      java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
//            System.out.println(rs.getDate("ThoiGianDat") + " " + rs.getTime("ThoiGianDat"));
//            Date thoiGianDat = sqlDate;
            
//             SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd hh:mm:ss ");

SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String dateInString = "2017-06-07 22:22:22";
String ngay = String.valueOf(rs.getDate("ThoiGianDat"));
String gio = String.valueOf(rs.getTime("ThoiGianDat"));
String dateInString = ngay + " " + gio;
        try {
            Date date = formatter.parse(dateInString);
//            System.out.println(date);
//            System.out.println(formatter.format(date));
             Date thoiGianDat;
             thoiGianDat = date; // System.out.println(formatter.format(date));
             Boolean isThanhToan = rs.getBoolean("ThanhToan");
             Date ngayKhoiHanh = rs.getDate("NgayKhoiHanh");
             String gioKhoiHanh = rs.getString("GioKhoiHanh");
             double giaVe = rs.getDouble("GiaVe");
             String maLoTrinh = rs.getString("MaLoTrinh");
             Boolean isLayVe = rs.getBoolean("LayVe");
             VeXe vx = new VeXe(maVe,bienSoXe,maNV, hoTenKh, sdtKh,maGheNgoi, thoiGianDat, isThanhToan,ngayKhoiHanh,gioKhoiHanh,giaVe,maLoTrinh,isLayVe);
             kq.add(vx);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//            String ngay = rs.getDate("ThoiGianDat").toString();
//            String gio = rs.getTime("ThoiGianDat").toString();
//            String date = ngay + " " + gio;
           
            
            
        }
        rs.close();
        return kq;
    }
    public static boolean themVe(VeXe ve)
    {
     Connection conn = JDBC.getConn();
        try {
            
        Statement stm = conn.createStatement();
        ResultSet rs;
            rs = stm.executeQuery("SELECT count(*) FROM vexe WHERE MaVe = '" + ve.getMaVe() + "'");
            int kq = 0;
        while(rs.next())
        {
            kq = rs.getInt(1);
            break;
        }
        
        if(kq == 0)
        {
                    String sql = "INSERT INTO vexe (MaVe,BienSoXe,MaNV,HoTenKH,SDTKH,MaGhe,ThoiGianDat,ThanhToan,NgayKhoiHanh,GioKhoiHanh,GiaVe,MaLoTrinh,LayVe) VALUES (?,?,?,?,?,?,now(),?,?,?,?,?,?)";
            Connection cnt = JDBC.getConn();
            cnt.setAutoCommit(false);
             PreparedStatement pStm = cnt.prepareStatement(sql);
             pStm.setString(1, ve.getMaVe());
             pStm.setString(2, ve.getBienSoXe());
             pStm.setString(3, ve.getMaNV());
             pStm.setString(4, ve.getHoTenKH());
             pStm.setString(5, ve.getSdtKH());
             pStm.setString(6, ve.getMaGheNgoi());

             java.util.Date date = ve.getThoiGianDatVe();
          java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
    //
    //         
    //         pStm.setDate(7, sqlDate);
             pStm.setBoolean(7, ve.isIsThanhToan());

             date = ve.getNgayKhoiHanh();

           sqlDate = new java.sql.Date(date.getTime()); 


             pStm.setDate(8,sqlDate);
             pStm.setString(9,ve.getGioKhoiHanh());
             pStm.setDouble(10, ve.getGiaVe());
             pStm.setString(11,ve.getMaLoTrinh());
             pStm.setBoolean(12, ve.isIsLayVe());
             pStm.executeUpdate();
            cnt.commit();
           
        }
         return true;
        } catch (SQLException ex) {
           return false;
        }
        
    }
    
    public static void capNhatVeXe(String bienSoXe, String maNV, String hoTenKH, String sdtKH, String maGhe,String thoiGianDat,boolean isThanhToan,String ngayKhoiHanh,String gioKhoiHanh,double giaVe, String maLoTrinh,String maVe, boolean isLayVe) throws SQLException
    {
        String sql = "UPDATE vexe SET BienSoXe = ?, MaNV = ?, HoTenKH = ?, SDTKH = ?, MaGhe = ?, ThoiGianDat = now(), ThanhToan = ?, NgayKhoiHanh = ?, GioKhoiHanh = ?, GiaVe = ?, MaLoTrinh = ?, LayVe = ? WHERE MaVe = ?";
        Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1, bienSoXe);
        pStm.setString(2, maNV);
        pStm.setString(3, hoTenKH);
        pStm.setString(4, sdtKH);
        pStm.setString(5, maGhe);
//        pStm.setString(6, thoiGianDat);
        pStm.setBoolean(6, isThanhToan);
        pStm.setString(7, ngayKhoiHanh);
        pStm.setString(8, gioKhoiHanh);
        pStm.setDouble(9, giaVe);
        pStm.setString(10, maLoTrinh);
        pStm.setBoolean(11, isLayVe);
        pStm.setString(12, maVe);
        pStm.executeUpdate();
        cnt.commit();
    }
    
    public static void xoaVeXe(String maVe) throws SQLException
    {
        String sql = "DELETE FROM vexe WHERE MaLoTrinh = '" + maVe+"'";
      
        Connection cnt = JDBC.getConn();
        cnt.setAutoCommit(false);
        PreparedStatement pStm = cnt.prepareStatement(sql);
         pStm.executeUpdate();
         cnt.commit();
    }
    
    public static List<VeXe> timKiemLoTrinh(String key) throws SQLException
    {
        Connection conn = JDBC.getConn();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT v.MaVe, v.BienSoXe, v.MaNV, v.HoTenKH, v.SDTKH, v.MaGhe, v.ThoiGianDat, v.ThanhToan, v.NgayKhoiHanh, v.GioKhoiHanh, v.GiaVe, v.MaLoTrinh, v.LayVe FROM lotrinh l join vexe v on l.MaLoTrinh = v.MaLoTrinh WHERE TuyenDi like N'%"+key+"%' OR TuyenDen LIKE N'%" +key+"%'");
       List<VeXe> kq = new ArrayList<>();
       while(rs.next())
       {
           String maVe = rs.getString("MaVe");
            String bienSoXe = rs.getString("BienSoXe");
            String maNV = rs.getString("MaNV");
            String hoTenKh = rs.getString("HoTenKH");
            String sdtKh = rs.getString("SDTKH");
            String maGheNgoi = rs.getString("MaGhe");
            
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss ");
            
//            java.util.Date date = rs.getDate("ThoiGianDat");
//      java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
//            System.out.println(rs.getDate("ThoiGianDat") + " " + rs.getTime("ThoiGianDat"));
//            Date thoiGianDat = sqlDate;
            
//             SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd hh:mm:ss ");

SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String dateInString = "2017-06-07 22:22:22";
String ngay = String.valueOf(rs.getDate("ThoiGianDat"));
String gio = String.valueOf(rs.getTime("ThoiGianDat"));
String dateInString = ngay + " " + gio;
        try {
            Date date = formatter.parse(dateInString);
//            System.out.println(date);
//            System.out.println(formatter.format(date));
             Date thoiGianDat;
             thoiGianDat = date; // System.out.println(formatter.format(date));
             Boolean isThanhToan = rs.getBoolean("ThanhToan");
             Date ngayKhoiHanh = rs.getDate("NgayKhoiHanh");
             String gioKhoiHanh = rs.getString("GioKhoiHanh");
             double giaVe = rs.getDouble("GiaVe");
             String maLoTrinh = rs.getString("MaLoTrinh");
             boolean isLayVe = rs.getBoolean("LayVe");
             VeXe vx = new VeXe(maVe,bienSoXe,maNV, hoTenKh, sdtKh,maGheNgoi, thoiGianDat, isThanhToan,ngayKhoiHanh,gioKhoiHanh,giaVe,maLoTrinh,isLayVe);
             kq.add(vx);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//            String ngay = rs.getDate("ThoiGianDat").toString();
//            String gio = rs.getTime("ThoiGianDat").toString();
//            String date = ngay + " " + gio;
       }
       return kq;
    }
    
    public static List<String> getMaGhe(String bienSoXe,String ngayKhoiHanh,String gioKhoiHanh, String maLoTrinh) throws SQLException
    {
         Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT MaGhe FROM vexe WHERE BienSoXe = '" + bienSoXe + "' AND NgayKhoiHanh = '" +ngayKhoiHanh + "' AND GioKhoiHanh = '" + gioKhoiHanh + "' AND MaLoTrinh = '" +maLoTrinh+"'");
        List<String> kq = new ArrayList<>();
        while(rs.next())
        {
            kq.add(rs.getString("MaGhe"));
        }
        return kq;
    }
}
