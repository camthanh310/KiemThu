/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

/**
 *
 * @author Hau
 */
public class LoaiPhong {
    public int maLoaiPhong;
    private String tenLoaiPhong;
    private int soGiuongNgu;
    private float gia;

    public LoaiPhong(int ma, String ten, float gia,int sogiuongngu){
        super();
        this.maLoaiPhong = ma;
        this.tenLoaiPhong = ten;
        this.gia = gia;
        this.soGiuongNgu = sogiuongngu;
        
    }
    public LoaiPhong(){
        
    }
    /**
     * @return the maLoaiPhong
     */
    public int getMaLoaiPhong() {
        return maLoaiPhong;
    }

    /**
     * @param maLoaiPhong the maLoaiPhong to set
     */
    public void setMaLoaiPhong(int maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    /**
     * @return the tenLoaiPhong
     */
    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    /**
     * @param tenLoaiPhong the tenLoaiPhong to set
     */
    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    /**
     * @return the soGiuong
     */
    public int getSoGiuongNgu() {
        return soGiuongNgu;
    }

    /**
     * @param soGiuong the soGiuong to set
     */
    public void setSoGiuongNgu(int soGiuongNgu) {
        this.soGiuongNgu = soGiuongNgu;
    }

    /**
     * @return the giaTien
     */
    public float getGia() {
        return gia;
    }

    /**
     * @param gia the giaTien to set
     */
    public void setGia(float gia) {
        this.gia = gia;
    }
    
}
