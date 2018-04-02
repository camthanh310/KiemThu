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
public class Phong {
    private int maPhong;
    private String tenPhong;
    private String tenLoaiPhong;
    private String tinhTrangPhong;

    public Phong(int ma, String ten,String tenloai,String tinhtrang){
        super();
        this.maPhong = ma;
        this.tenPhong = ten;
        this.tenLoaiPhong = tenloai;
        this.tinhTrangPhong = tinhtrang;
        
    }
    public Phong(){
        
    }
    /**
     * @return the maPhong
     */
    public int getMaPhong() {
        return maPhong;
    }

    /**
     * @param maPhong the maPhong to set
     */
    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    /**
     * @return the tenPhong
     */
    public String getTenPhong() {
        return tenPhong;
    }

    /**
     * @param tenPhong the tenPhong to set
     */
    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    /**
     * @return the tenLoaiPhong
     */
    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    /**
     * @param tenLoaiPhong the maLoaiPhong to set
     */
    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    /**
     * @return the tinhTrangPhong
     */
    public String getTinhTrangPhong() {
        return tinhTrangPhong;
    }
    
 
    
    

    /**
     * @param tinhTrangPhong the tinhTrangPhong to set
     */
    public void setTinhTrangPhong(String tinhTrangPhong) {
        this.tinhTrangPhong = tinhTrangPhong;
    }
    
}
