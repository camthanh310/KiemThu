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
public class BaoCaoDTO {

    /**
     * @return the ThoiGian
     */
    public String getThoiGian() {
        return ThoiGian;
    }

    /**
     * @param ThoiGian the ThoiGian to set
     */
    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
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
    private String ThoiGian;
    private String TongTien;
}
