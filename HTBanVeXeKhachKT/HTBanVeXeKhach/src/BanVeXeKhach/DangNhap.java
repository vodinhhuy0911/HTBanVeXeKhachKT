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
public class DangNhap {

    /**
     * @return the taiKhoan
     */
    public String getTaiKhoan() {
        return taiKhoan;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    private String taiKhoan;
    private String matKhau;
    public DangNhap(String taiKhoan, String matKhau)
    {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        StringBuilder db = new StringBuilder();
        db.append(this.taiKhoan).append(" ").append(this.matKhau).append("\n");
        return db.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
