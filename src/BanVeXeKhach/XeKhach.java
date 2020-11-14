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
public class XeKhach {

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
     * @return the tenXe
     */
    public String getTenXe() {
        return tenXe;
    }

    /**
     * @param tenXe the tenXe to set
     */
    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    /**
     * @return the tuyenDi
     */
    public String getTuyenDi() {
        return tuyenDi;
    }

    /**
     * @param tuyenDi the tuyenDi to set
     */
    public void setTuyenDi(String tuyenDi) {
        this.tuyenDi = tuyenDi;
    }

    /**
     * @return the tuyenDen
     */
    public String getTuyenDen() {
        return tuyenDen;
    }

    /**
     * @param tuyenDen the tuyenDen to set
     */
    public void setTuyenDen(String tuyenDen) {
        this.tuyenDen = tuyenDen;
    }

    /**
     * @return the thoiGIanKhoiHanh
     */
    public String getThoiGianKhoiHanh() {
        return thoiGianKhoiHanh;
    }

    /**
     * @param thoiGIanKhoiHanh the thoiGIanKhoiHanh to set
     */
    public void setThoiGianKhoiHanh(String thoiGianKhoiHanh) {
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
    }
    private String bienSoXe;
    private String tenXe;
    private String tuyenDi;
    private String tuyenDen;
    private String thoiGianKhoiHanh;
    private List<GheXe> soLuongGheTrong = new ArrayList<>();
    
    public XeKhach(String bienSoXe, String tenXe, String tuyenDi, String tuyenDen, String thoiGianKhoiHanh, List<GheXe> sl )
    {
        this.bienSoXe = bienSoXe;
        this.tenXe = tenXe;
        this.tuyenDi = tuyenDi;
        this.tuyenDen = tuyenDen;
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
        this.soLuongGheTrong = sl;
    }
}
