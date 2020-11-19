package BVXK;


import BanVeXeKhach.NhanVien;
import BanVeXeKhach.VeXe;
import java.sql.Connection;
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
            String maVe = rs.getString("mave");
            String bienSoXe = rs.getString("biensoxe");
            String maNV = rs.getString("manv");
            String hoTenKh = rs.getString("hotenkh");
            String sdtKh = rs.getString("sdtkh");
            String maGheNgoi = rs.getString("maghengoi");
            Date thoiGianDat = rs.getDate("thoigiandat");
            Boolean isThanhToan = rs.getBoolean("thanhtoan");
            VeXe vx = new VeXe(maVe,bienSoXe,maNV, hoTenKh, sdtKh,maGheNgoi, thoiGianDat, isThanhToan);
            
            kq.add(vx);
        }
        rs.close();
        return kq;
    }
}
