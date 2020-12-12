/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BVXK;

import BanVeXeKhach.NhanVien;
import static Interface.LoginController.USERNAME;
import static Interface.LoginController.maNV;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vohuy
 */
public class Login {
    
    public static String login(String taiKhoan, String matKhau) throws SQLException
    {
        List <NhanVien> ds = QuanLyNhanVien.getDsNhanVien();
        for(NhanVien dn : ds)
      {
          if(taiKhoan != "" && matKhau != "")
            if(taiKhoan.equals(dn.getTaiKhoan()) && matKhau.equals(dn.getMatKhau())){
                return dn.getHoTen();
               }

      }
        return null;
    }
}
