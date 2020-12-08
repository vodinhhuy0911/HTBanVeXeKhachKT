package BanVeXeKhach;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vohuy
 */
public class TuyenDuong {

    /**
     * @return the maXe
     */
    public String getMaXe() {
        return maXe;
    }

    /**
     * @param maXe the maXe to set
     */
    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    /**
     * @return the maTuyenDuong
     */
    public String getMaTuyenDuong() {
        return maTuyenDuong;
    }

    /**
     * @param maTuyenDuong the maTuyenDuong to set
     */
    public void setMaTuyenDuong(String maTuyenDuong) {
        this.maTuyenDuong = maTuyenDuong;
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
    private String maTuyenDuong;
    private String tuyenDi;
    private String tuyenDen;
    private String maXe;
    
    public TuyenDuong(String maTuyenDuong, String tuyenDi, String tuyenDen, String maXe)
    {
        this.maTuyenDuong = maTuyenDuong;
        this.tuyenDi = tuyenDi;
        this.tuyenDen = tuyenDen;
        this.maXe = maXe;
    }
}
