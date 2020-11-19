/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BVXK;

import BanVeXeKhach.DangNhap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vohuy
 */
public class Main {
    public static void main(String args[]) throws SQLException
    {
                List<DangNhap> dn = new ArrayList<>();
                dn = ThongTinTaiKhoan.getThongTin();
                for(DangNhap d : dn)
                {
                    System.out.print(d);   
                }
    }
}
