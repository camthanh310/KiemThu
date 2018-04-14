/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author TTC
 */
public class PhieuThanhToanDTO {

    /**
     * @return the MaPhong
     */
    public int getMaPhong() {
        return MaPhong;
    }

    /**
     * @param MaPhong the MaPhong to set
     */
    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }

    /**
     * @return the SoPhong
     */
    public String getSoPhong() {
        return SoPhong;
    }

    /**
     * @param SoPhong the SoPhong to set
     */
    public void setSoPhong(String SoPhong) {
        this.SoPhong = SoPhong;
    }



    /**
     * @return the MaKhachHang
     */
    public int getMaKhachHang() {
        return MaKhachHang;
    }

    /**
     * @param MaKhachHang the MaKhachHang to set
     */
    public void setMaKhachHang(int MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }
    
    /**
     * @return the HoTen
     */
    public String getHoTen() {
        return HoTen;
    }

    /**
     * @param HoTen the HoTen to set
     */
    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }


    /**
     * @return the NgayThue
     */
    public String getNgayThue() {
        return NgayThue;
    }

    /**
     * @param NgayThue the NgayThue to set
     */
    public void setNgayThue(String NgayThue) {
        this.NgayThue = NgayThue;
    }

    /**
     * @return the NgayTra
     */
    public String getNgayTra() {
        return NgayTra;
    }

    /**
     * @param NgayTra the NgayTra to set
     */
    public void setNgayTra(String NgayTra) {
        this.NgayTra = NgayTra;
    }

    /**
     * @return the TongTien
     */
    public String getTongTien() {
        return TongTien;
    }

    /**
     * @param TongTien the TongTien to set
     */
    public void setTongTien(String TongTien) {
        this.TongTien = TongTien;
    }
    private int MaKhachHang;
    private String HoTen;
    private String SoPhong;
    private String NgayThue;
    private String NgayTra;
    private String TongTien;
    private int MaPhong;
}
