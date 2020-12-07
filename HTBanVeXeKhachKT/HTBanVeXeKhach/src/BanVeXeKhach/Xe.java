/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BanVeXeKhach;

/**
 *
 * @author vohuy
 */
public class Xe {

    /**
     * @return the bienSoXe
     */
    public String getBienSoXe() {
        return bienSoXe;
    }

    /**
     * @param bienSoXe the bienSoXe to set
     */
    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    /**
     * @return the loaiXe
     */
    public String getLoaiXe() {
        return loaiXe;
    }

    /**
     * @param loaiXe the loaiXe to set
     */
    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }
    private String bienSoXe;
    private String loaiXe;
    
    public Xe(String bienSoXe, String loaiXe)
    {
        this.bienSoXe = bienSoXe;
        this.loaiXe = loaiXe;
    }
}
