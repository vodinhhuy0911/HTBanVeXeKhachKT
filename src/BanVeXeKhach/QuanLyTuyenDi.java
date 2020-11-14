package BanVeXeKhach;


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
    private List<TuyenDuong> dsTuyenDuong = new ArrayList<>();

    /**
     * @return the dsTuyenDuong
     */
    public List<TuyenDuong> getDsTuyenDuong() {
        return dsTuyenDuong;
    }

    /**
     * @param dsTuyenDuong the dsTuyenDuong to set
     */
    public void setDsTuyenDuong(List<TuyenDuong> dsTuyenDuong) {
        this.dsTuyenDuong = dsTuyenDuong;
    }
    public void themChuyenDiMoi(String chuyenDi,String chuyenDen)
    {
        TuyenDuong td = new TuyenDuong(chuyenDi, chuyenDen);
        this.dsTuyenDuong.add(td);
    }
    
    public void hienThiChuyenDi()
    {
        for(TuyenDuong td : this.dsTuyenDuong)
            System.out.print(td);
    }
    
    public void traCuuChuyenDi(TuyenDuong td)
    {
        if(this.dsTuyenDuong.indexOf(td)!= -1)
            System.out.print(td);
        else
            System.out.print("Không tìm thấy :)");
    }
}
