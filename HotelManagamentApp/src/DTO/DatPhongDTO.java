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
public class DatPhongDTO {

    /**
     * @return the MaDatPhong
     */
    public int getMaDatPhong() {
        return MaDatPhong;
    }

    /**
     * @param MaDatPhong the MaDatPhong to set
     */
    public void setMaDatPhong(int MaDatPhong) {
        this.MaDatPhong = MaDatPhong;
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
     * @return the NgayDatPhong
     */
    public String getNgayDatPhong() {
        return NgayDatPhong;
    }

    /**
     * @param NgayDatPhong the NgayDatPhong to set
     */
    public void setNgayDatPhong(String NgayDatPhong) {
        this.NgayDatPhong = NgayDatPhong;
    }

    /**
     * @return the NgayTraPhong
     */
    public String getNgayTraPhong() {
        return NgayTraPhong;
    }

    /**
     * @param NgayTraPhong the NgayTraPhong to set
     */
    public void setNgayTraPhong(String NgayTraPhong) {
        this.NgayTraPhong = NgayTraPhong;
    }

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
    private int MaDatPhong;
    private int MaKhachHang;
    private String NgayDatPhong;
    private String NgayTraPhong;
    private int MaPhong;
    
    public DatPhongDTO() {
        
    }
}
