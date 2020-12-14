package BVXK;


import BanVeXeKhach.NhanVien;
import BVXK.KiemTra;
import static BVXK.KiemTra.kiemTraEmail;
import static BVXK.KiemTra.kiemTraNgaySinh;
import static BVXK.KiemTra.kiemTraSdt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            String matKhau = rs.getString("matKhau");
            NhanVien nv = new NhanVien(tk,name,ngaySinh,diaChi,chucVu,sdt,email,matKhau);
            
            kq.add(nv);
        }
        return kq;
    }

    public static boolean themNhanVien(NhanVien nv) throws ParseException, SQLException
    {
        if(nv.getChucVu() != null && nv.getDiaChi() != null && nv.getEmail() != null && nv.getHoTen() != null &&nv.getMatKhau() != null &&nv.getNgaySinh() != null &&nv.getSdt() != null &&nv.getTaiKhoan() != null)
        {
            DateFormat sfm = new SimpleDateFormat("yyyy/MM/dd");
                  if(kiemTraEmail(nv.getEmail()) && kiemTraNgaySinh(sfm.format(nv.getNgaySinh())) && kiemTraSdt(nv.getSdt()) && getSoLuongNV(nv.getTaiKhoan())==0)
                  {
        String sql = "INSERT INTO nhanvien (idNV,tenNV, ngaySinh, diaChi, chucVu, sdt, email,matKhau) VALUES (?,?,?,?,?,?,?,?)";
        Connection cnt = JDBC.getConn();
        try {
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
        pStm.setString(8,nv.getMatKhau());
        pStm.executeUpdate();
        
        cnt.commit();
        return true;
        } catch (SQLException ex) {
            return false;
        }
        }}
        return false;
        
    }
    
    public static List<NhanVien> timKiemNv(String key) throws SQLException
    {
        
        Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        
        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien WHERE idNV like '%" + key + "%' OR tenNV like N'%" + key
        + "%' OR diaChi like N'%" + key+ "%' OR chucVu like N'%" + key+ "%' OR sdt like '%" + key+ "%' OR email like N'%" + key +"%' OR NgaySinh like '%" +key +"%'");
        List<NhanVien> dsnv = new ArrayList<>();
        while (rs.next()) {
            String tk = rs.getString("idNV");
            String name = rs.getString("tenNV");
            Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String chucVu = rs.getString("chucVu");
            String sdt = rs.getString("sdt");
            String email = rs.getString("email");
            String matKhau = rs.getString("matKhau");
            NhanVien nv = new NhanVien(tk,name,ngaySinh,diaChi,chucVu,sdt,email,matKhau);
            dsnv.add(nv);
        }
        return dsnv;
    }

   public static boolean capNhatNhanVien(NhanVien nv) throws SQLException
   {
       if(nv.getChucVu() != null && nv.getDiaChi() != null && nv.getEmail() != null && nv.getHoTen() != null && nv.getMatKhau() != null && nv.getNgaySinh() != null && nv.getSdt() != null && nv.getTaiKhoan()!=null){
           DateFormat sfm = new SimpleDateFormat("yyyy/MM/dd");
          
       if(kiemTraEmail(nv.getEmail()) && kiemTraNgaySinh(sfm.format(nv.getNgaySinh())) && kiemTraSdt(nv.getSdt()) && getSoLuongNV(nv.getTaiKhoan()) == 1)
       {
       String sql = "UPDATE nhanvien SET idNV = ?, tenNV = ?, ngaySinh = ?, diaChi = ?, chucVu = ?, sdt = ?, email = ?, matKhau = ? WHERE idNV = ?";
        Connection cnt = JDBC.getConn();
        try {
            cnt.setAutoCommit(false);
             PreparedStatement pStm = cnt.prepareStatement(sql);
        pStm.setString(1,nv.getTaiKhoan());
        pStm.setString(2, nv.getHoTen());
        
        java.util.Date date = nv.getNgaySinh();
       java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
        pStm.setDate(3, sqlDate);
        
        
        pStm.setString(4, nv.getDiaChi());
        pStm.setString(5, nv.getChucVu());
        pStm.setString(6, nv.getSdt());
        pStm.setString(7, nv.getEmail());
        pStm.setString(8, nv.getMatKhau());
        pStm.setString(9, nv.getTaiKhoan());
        pStm.executeUpdate();
        cnt.commit();
        return true;
        } catch (SQLException ex) {
            return false;
        }
       }
   }
        return false;
       
       
   }
   public static boolean xoaNhanVien(String taiKhoan) 
   {
       if(taiKhoan != null)
       { Connection cnt = JDBC.getConn();
            String sql = "DELETE FROM nhanvien WHERE idNV = N'" + taiKhoan +"'";
           int kq = 0;
             Statement stm;
           try {
               stm = cnt.createStatement();
               ResultSet rs = stm.executeQuery("SELECT count(*) FROM nhanvien WHERE idNV  = N'" + taiKhoan +"'");
               System.out.print(rs);
               while(rs.next())
               {
                   kq = rs.getInt(1);
               }
           } catch (SQLException ex) {
               Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
           }
             
            if(kq ==1)
            {
             try {
                 cnt.setAutoCommit(false);
                  PreparedStatement pStm = cnt.prepareStatement(sql);
                 pStm.executeUpdate();
                 cnt.commit();
              return true;
             } catch (SQLException ex) {
                 return false;
             }
            }
       }
       return false;
       
         //ok
   }
   public static NhanVien getNV(String maNV) throws SQLException
   {
       Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien WHERE idNV = '" +maNV+ "'");
        while (rs.next()) {
            String tk = rs.getString("idNV");
            String name = rs.getString("tenNV");
            Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String chucVu = rs.getString("chucVu");
            String sdt = rs.getString("sdt");
            String email = rs.getString("email");
            String matKhau = rs.getString("matKhau");
            NhanVien nv = new NhanVien(tk,name,ngaySinh,diaChi,chucVu,sdt,email,matKhau);
            return nv;
        }
        return null;
   }
   
   public static int getSoLuongNV(String MaNV) throws SQLException
   {
       Connection conn = JDBC.getConn();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT count(*) FROM nhanvien WHERE idNV = '"+MaNV+"'");
       int i = 0;
       while(rs.next())
       {
           i = rs.getInt(1);
       }
       return i;
   }
}
