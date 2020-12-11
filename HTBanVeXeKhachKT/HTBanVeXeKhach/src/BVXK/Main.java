/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BVXK;

import BanVeXeKhach.DangNhap;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vohuy
 */
public class Main {
    public static void main(String args[]) throws SQLException
    {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now() ;  
   System.out.println(dtf.format(now));  
   String s =  now.getDayOfMonth() + "-" + now.getMonthValue()+ "-" + now.getYear() +" "+   now.getHour() + ":" + now.getMinute() + ":" + now.getSecond();
   String s1 = now.getDayOfMonth() + "-" + now.getMonthValue()+ "-" + now.getYear() +" ";
        s1 += (now.getHour() +0)+ ":" + now.getMinute() + ":" + now.getSecond();
   System.out.println(s1);
    }
    
}
