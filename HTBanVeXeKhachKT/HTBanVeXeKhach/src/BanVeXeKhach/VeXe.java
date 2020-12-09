package BanVeXeKhach;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vohuy
 */
public class VeXe {

    /**
     * @return the giaVe
     */
    public double getGiaVe() {
        return giaVe;
    }

    /**
     * @param giaVe the giaVe to set
     */
    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    /**
     * @return the ngayKhoiHanh
     */
    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    /**
     * @param ngayKhoiHanh the ngayKhoiHanh to set
     */
    public void setNgayKhoiHanh(Date ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    /**
     * @return the gioKhoiHanh
     */
    public String getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    /**
     * @param gioKhoiHanh the gioKhoiHanh to set
     */
    public void setGioKhoiHanh(String gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }

    /**
     * @return the maVe
     */
    public String getMaVe() {
        return maVe;
    }

    /**
     * @param maVe the maVe to set
     */
    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

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
     * @return the tenNV
     */
    public String getMaNV() {
        return maNV;
    }

    /**
     * @param tenNV the tenNV to set
     */
    public void setMaNV(String MaNV) {
        this.maNV = MaNV;
    }

    /**
     * @return the hoTenKH
     */
    public String getHoTenKH() {
        return hoTenKH;
    }

    /**
     * @param hoTenKH the hoTenKH to set
     */
    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    /**
     * @return the sdtKH
     */
    public String getSdtKH() {
        return sdtKH;
    }

    /**
     * @param sdtKH the sdtKH to set
     */
    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    /**
     * @return the maGheNgoi
     */
    public String getMaGheNgoi() {
        return maGheNgoi;
    }

    /**
     * @param maGheNgoi the maGheNgoi to set
     */
    public void setMaGheNgoi(String maGheNgoi) {
        this.maGheNgoi = maGheNgoi;
    }

    /**
     * @return the thoiGianDatVe
     */
    public Date getThoiGianDatVe() {
        return thoiGianDatVe;
    }

    /**
     * @param thoiGianDatVe the thoiGianDatVe to set
     */
    public void setThoiGianDatVe(Date thoiGianDatVe) {
        this.thoiGianDatVe = thoiGianDatVe;
    }

    /**
     * @return the isThanhToan
     */
    public boolean isIsThanhToan() {
        return isThanhToan;
    }

    /**
     * @param isThanhToan the isThanhToan to set
     */
    public void setIsThanhToan(boolean isThanhToan) {
        this.isThanhToan = isThanhToan;
    }
    private String maVe;
    private String bienSoXe;
    private String maNV;
    private String hoTenKH;
    private String sdtKH;
    private String maGheNgoi;
    private Date thoiGianDatVe;
    private boolean isThanhToan;
    private Date ngayKhoiHanh;
    private String gioKhoiHanh;
    private double giaVe;
    
    public VeXe(String maVe, String bienSoXe, String maNV, String hoTenKH, String sdtKH, String maGheNgoi, Date thoiGianDatVe, boolean  isThanhToan, Date ngayKhoiHanh, String gioKhoiHanh, double giaVe)
    {
        this.maVe = maVe;
        this.bienSoXe = bienSoXe;
        this.maNV = maNV;
        this.hoTenKH = hoTenKH;
        this.sdtKH = sdtKH;
        this.maGheNgoi = maGheNgoi;
        this.thoiGianDatVe = thoiGianDatVe;
        this.isThanhToan = isThanhToan;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.gioKhoiHanh = gioKhoiHanh;
        this.giaVe = giaVe;
    }
}
