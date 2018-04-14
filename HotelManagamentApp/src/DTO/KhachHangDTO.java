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
public class KhachHangDTO {

    /**
     * @return the iMaKhachHang
     */
    public int getiMaKhachHang() {
        return iMaKhachHang;
    }

    /**
     * @param iMaKhachHang the iMaKhachHang to set
     */
    public void setiMaKhachHang(int iMaKhachHang) {
        this.iMaKhachHang = iMaKhachHang;
    }

    /**
     * @return the strMaKhachHang
     */

    private int iMaKhachHang;
    private String strHoTen;
    private String strDiaChi;
    private String strSoCMND;
    private String strNgaySinh;
    private String strSoDienThoai;
    
    /**
     * @return the strHoTen
     */
    public String getStrHoTen() {
        return strHoTen;
    }

    /**
     * @param strHoTen the strHoTen to set
     */
    public void setStrHoTen(String strHoTen) {
        this.strHoTen = strHoTen;
    }

    /**
     * @return the strDiaChi
     */
    public String getStrDiaChi() {
        return strDiaChi;
    }

    /**
     * @param strDiaChi the strDiaChi to set
     */
    public void setStrDiaChi(String strDiaChi) {
        this.strDiaChi = strDiaChi;
    }

    /**
     * @return the strSoCMND
     */
    public String getStrSoCMND() {
        return strSoCMND;
    }

    /**
     * @param strSoCMND the strSoCMND to set
     */
    public void setStrSoCMND(String strSoCMND) {
        this.strSoCMND = strSoCMND;
    }

    /**
     * @return the strNgaySinh
     */
    public String getStrNgaySinh() {
        return strNgaySinh;
    }

    /**
     * @param strNgaySinh the strNgaySinh to set
     */
    public void setStrNgaySinh(String strNgaySinh) {
        this.strNgaySinh = strNgaySinh;
    }

    /**
     * @return the strSoDienThoai
     */
    public String getStrSoDienThoai() {
        return strSoDienThoai;
    }

    /**
     * @param strSoDienThoai the strSoDienThoai to set
     */
    public void setStrSoDienThoai(String strSoDienThoai) {
        this.strSoDienThoai = strSoDienThoai;
    }
   
}
