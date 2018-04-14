/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author TTC
 */
public class NhanVienDTO {
    private String MaNhanVien;
    private String HoTen;
    private String NgaySinh;
    private String GioiTinh;
    private String QueQuan;
    private String DiaChi;

    public NhanVienDTO(String MaNhanVien, String HoTen, String NgaySinh, String DiaChi, String GioiTinh,  String QueQuan){
        this.MaNhanVien = MaNhanVien;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.QueQuan = QueQuan;
        this.DiaChi = DiaChi;
    }
    
    public NhanVienDTO() {
        
    }
    
    /**
     * @return the MaNhanVien
     */
    public String getMaNhanVien() {
        return MaNhanVien;
    }

    /**
     * @param MaNhanVien the MaNhanVien to set
     */
    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
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

    public String getDiaChi() {
        return DiaChi;
    }

    /**
     * @param DiaChi the DiaChi to set
     */
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
    
    /**
     * @return the NgaySinh
     */
    public String getNgaySinh() {
        return NgaySinh;
    }

    /**
     * @param NgaySinh the NgaySinh to set
     */
    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    /**
     * @return the GioiTinh
     */
    public String getGioiTinh() {
        return GioiTinh;
    }

    /**
     * @param GioiTinh the GioiTinh to set
     */
    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    /**
     * @return the QueQuan
     */
    public String getQueQuan() {
        return QueQuan;
    }

    /**
     * @param QueQuan the QueQuan to set
     */
    public void setQueQuan(String QueQuan) {
        this.QueQuan = QueQuan;
    }
}
