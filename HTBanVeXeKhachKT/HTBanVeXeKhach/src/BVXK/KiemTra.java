/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BVXK;

import java.text.SimpleDateFormat;

/**
 *
 * @author vohuy
 */
public class KiemTra {
    
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
