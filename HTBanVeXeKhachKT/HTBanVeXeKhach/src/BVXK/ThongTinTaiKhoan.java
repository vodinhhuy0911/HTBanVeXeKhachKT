/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BVXK;

import BanVeXeKhach.DangNhap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author vohuy
 */
public class ThongTinTaiKhoan {
    public static List<DangNhap> getThongTin() throws SQLException
    {
        Connection cnt = JDBC.getConn();
        Statement stm = cnt.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM dangnhap");
        List<DangNhap> kq = new ArrayList<>();
        while(rs.next())
        {
            String taiKhoan = rs.getString("taikhoan");
            String matKhau = rs.getString("matkhau");
            DangNhap dn = new DangNhap(taiKhoan,matKhau);
            kq.add(dn);
        }
        return kq;
    }
}
