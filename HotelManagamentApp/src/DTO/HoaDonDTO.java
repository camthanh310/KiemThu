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
public class HoaDonDTO {

    /**
     * @return the MaHoaDon
     */
    public int getMaHoaDon() {
        return MaHoaDon;
    }

    /**
     * @param MaHoaDon the MaHoaDon to set
     */
    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }
    private int MaHoaDon;
    private int MaDatPhong;
    private String NgayThanhToan;
    private double  TongTien;
    private int MaNguoiDung;

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
     * @return the NgayThanhToan
     */
    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    /**
     * @param NgayThanhToan the NgayThanhToan to set
     */
    public void setNgayThanhToan(String NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    /**
     * @return the TongTien
     */
    public double getTongTien() {
        return TongTien;
    }

    /**
     * @param TongTien the TongTien to set
     */
    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    /**
     * @return the MaNguoiDung
     */
    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    /**
     * @param MaNguoiDung the MaNguoiDung to set
     */
    public void setMaNguoiDung(int MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }
}
