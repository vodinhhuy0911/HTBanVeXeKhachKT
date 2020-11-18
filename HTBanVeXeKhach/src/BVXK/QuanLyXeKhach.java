package BVXK;

import BanVeXeKhach.XeKhach;
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
public class QuanLyXeKhach {
    public static List<XeKhach> getDsXeKhach() throws SQLException
    {
        Connection cnt = JDBC.getConn();
        Statement stm = cnt.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM xekhach");
        List<XeKhach> kq = new ArrayList<>();
        return kq;
    }
}
