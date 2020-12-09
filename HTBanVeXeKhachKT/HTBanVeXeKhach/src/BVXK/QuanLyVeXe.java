package BVXK;


import BanVeXeKhach.NhanVien;
import BanVeXeKhach.VeXe;
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
            Date thoiGianDat = rs.getDate("ThoiGianDat");
            Boolean isThanhToan = rs.getBoolean("ThanhToan");
            Date ngayKhoiHanh = rs.getDate("NgayKhoiHanh");
            String gioKhoiHanh = rs.getString("GioKhoiHanh");
            VeXe vx = new VeXe(maVe,bienSoXe,maNV, hoTenKh, sdtKh,maGheNgoi, thoiGianDat, isThanhToan,ngayKhoiHanh,gioKhoiHanh);
            
            kq.add(vx);
        }
        rs.close();
        return kq;
    }
    public static boolean themVe(VeXe ve) throws SQLException
    {
//        String sql = "INSERT INTO xe (MaXe, LoaiXe) VALUES (?,?)";
//         Connection cnt = JDBC.getConn();
//        cnt.setAutoCommit(false);
       
//        pStm.setString(1, xe.getBienSoXe());
//        pStm.setString(2, xe.getLoaiXe());
        
        

//        
        Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT count(*) FROM vexe WHERE MaVe = '" + ve.getMaVe() + "'");
        int kq = 0;
        while(rs.next())
        {
            kq = rs.getInt(1);
            break;
        }
        
        if(kq == 0)
        {
                    String sql = "INSERT INTO vexe (MaVe,BienSoXe,MaNV,HoTenKH,SDTKH,MaGhe,ThoiGianDat,ThanhToan,NgayKhoiHanh,GioKhoiHanh) VALUES (?,?,?,?,?,?,?,?,?,?)";
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

         
         pStm.setDate(7, sqlDate);
         pStm.setBoolean(8, ve.isIsThanhToan());
         
         date = ve.getNgayKhoiHanh();
 
       sqlDate = new java.sql.Date(date.getTime()); 
       
         
         pStm.setDate(9,sqlDate);
         pStm.setString(10,ve.getGioKhoiHanh());
         
         
         pStm.executeUpdate();
        cnt.commit();
        return true;
        }
        
        return false;
    }
}
