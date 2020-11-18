package BVXK;


import BanVeXeKhach.NhanVien;
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
public class QuanLyNhanVien {
    public static List<NhanVien> getDsNhanVien () throws SQLException
    {
        Connection conn = JDBC.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien");
        List<NhanVien> kq = new ArrayList<>();
        while (rs.next()) {
            String tk = rs.getString("taikhoan");
            String name = rs.getString("hoten");
            Date ngaySinh = rs.getDate("ngaysinh");
            String diaChi = rs.getString("diachi");
            String chucVu = rs.getString("chucvu");
            String sdt = rs.getString("sdt");
            String email = rs.getString("email");
            NhanVien nv = new NhanVien(tk,name,ngaySinh,diaChi,chucVu,sdt,email);
            
            kq.add(nv);
        }
        return kq;
    }
}
