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
public class PhongDTO {

    private int MaPhong;
    private String SoPhong; // <-- là TenPhong trong CSDL.
    private int MaLoaiPhong;
    private String TinhTrang;
    
    public PhongDTO() {
        
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
     * @return the MaLoaiPhong
     */
    public int getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    /**
     * @param MaLoaiPhong the MaLoaiPhong to set
     */
    public void setMaLoaiPhong(int MaLoaiPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
    }

    /**
     * @return the TinhTrang
     */
    public String getTinhTrang() {
        return TinhTrang;
    }

    /**
     * @param TinhTrang the TinhTrang to set
     */
    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
  
}
