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
public class LoaiPhongDTO {
    private int MaLoaiPhong;
    private String TenLoaiPhong;
    private double Gia;
    private int SoGiuongNgu;

    public LoaiPhongDTO() {
        
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
     * @return the TenLoaiPhong
     */
    public String getTenLoaiPhong() {
        return TenLoaiPhong;
    }

    /**
     * @param TenLoaiPhong the TenLoaiPhong to set
     */
    public void setTenLoaiPhong(String TenLoaiPhong) {
        this.TenLoaiPhong = TenLoaiPhong;
    }

    /**
     * @return the Gia
     */
    public double getGia() {
        return Gia;
    }

    /**
     * @param Gia the Gia to set
     */
    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    /**
     * @return the SoGiuongNgu
     */
    public int getSoGiuongNgu() {
        return SoGiuongNgu;
    }

    /**
     * @param SoGiuongNgu the SoGiuongNgu to set
     */
    public void setSoGiuongNgu(int SoGiuongNgu) {
        this.SoGiuongNgu = SoGiuongNgu;
    }
}
