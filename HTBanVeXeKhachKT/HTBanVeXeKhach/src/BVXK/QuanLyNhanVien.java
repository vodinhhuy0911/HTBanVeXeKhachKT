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

    public static boolean themNhanVien(NhanVien nv) throws ParseException
    {
        if(nv.getChucVu() != null && nv.getDiaChi() != null && nv.getEmail() != null && nv.getHoTen() != null &&nv.getMatKhau() != null &&nv.getNgaySinh() != null &&nv.getSdt() != null &&nv.getTaiKhoan() != null)
        {
                  if(kiemTraEmail(nv.getEmail()) && kiemTraNgaySinh(nv.getNgaySinh().toString()) && kiemTraSdt(nv.getSdt()))
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
        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien WHERE idNV like '" + key + "' OR tenNV like N'" + key
        + "' OR diaChi like N'" + key+ "' OR chucVu like N'" + key+ "' OR sdt like '" + key+ "' OR email like N'" + key +"'");
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

   public static boolean capNhatNhanVien(NhanVien nv)
   {
       if(nv.getChucVu() != null && nv.getDiaChi() != null && nv.getEmail() != null && nv.getHoTen() != null && nv.getMatKhau() != null && nv.getNgaySinh() != null && nv.getSdt() != null && nv.getTaiKhoan()!=null){
       if(kiemTraEmail(nv.getEmail()) && kiemTraNgaySinh(nv.getNgaySinh().toString()) && kiemTraSdt(nv.getSdt()))
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

   
   
   public static boolean kiemTraEmail(String email)
    {
         if(email.indexOf("@")== -1)
                {
                    return false;
                }
                else if (email.indexOf("@")!= -1 && (email.indexOf("@") + 1) == email.length())
                {
                    return false;
                }
         return true;
    }
   
    public  static boolean kiemTraNgaySinh(String ngaySinh) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        for (int i = 0; i < ngaySinh.length(); i++) {
            if (Character.isLetter(ngaySinh.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean kiemTraSdt(String sdt)
    {
        for (int i = 0; i < sdt.length(); i++) {
                        if ((!Character.isDigit(sdt.charAt(i))) || (sdt.length() < 10 || sdt.length() > 11)) {
                            return false;
                        }
        }
                    return true;    
    }
}
