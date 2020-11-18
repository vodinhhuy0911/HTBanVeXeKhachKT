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
    private String tuyenDi;
    private String tuyenDen;
    
    public TuyenDuong(String tuyenDi, String tuyenDen)
    {
        this.tuyenDi = tuyenDi;
        this.tuyenDen = tuyenDen;
    }
}
