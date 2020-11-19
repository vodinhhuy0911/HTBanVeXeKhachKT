package BVXK;


import BanVeXeKhach.TuyenDuong;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
       ResultSet rs = stm.executeQuery("SELECT * FROM tuyenduong");
       List<TuyenDuong> kq = new ArrayList<>();
       while(rs.next())
       {
           String tuyenDi = rs.getString("tuyendi");
           String tuyenDen = rs.getString("tuyenden");
           TuyenDuong td = new TuyenDuong(tuyenDi,tuyenDen);
           kq.add(td);
       }
       return kq;
   }
}
