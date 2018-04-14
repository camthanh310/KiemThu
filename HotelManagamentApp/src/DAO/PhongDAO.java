/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.LoaiPhongDTO;
import DTO.NhanVienDTO;
import DTO.PhongDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author TTC
 */
public class PhongDAO {
    public static ObservableList<PhongDTO> layDSPhong(String strKieuXemDanhSach, ObservableList<PhongDTO> dsPhong) {
        
        try { 
            PreparedStatement ps;
            if ("Tất Cả".equals(strKieuXemDanhSach))
                ps = ConnectingDB.getConnection().prepareStatement("SELECT * FROM Phong");
            else {
                ps = ConnectingDB.getConnection().prepareStatement("SELECT * FROM Phong WHERE TinhTrangPhong=?");
                ps.setString(1, strKieuXemDanhSach);
            }
            
            ResultSet rs = ps.executeQuery();
            
            // lấy dữ liệu
            while (rs.next()) {
                PhongDTO phong = new PhongDTO();
                phong.setMaLoaiPhong(rs.getInt("MaLoaiPhong"));
                phong.setMaPhong(rs.getInt("MaPhong"));
                phong.setTinhTrang(rs.getString("TinhTrangPhong").trim());
                phong.setSoPhong(rs.getString("TenPhong").trim());
                
                dsPhong.add(phong);
            }
            rs.close();
            ConnectingDB.getConnection().close();
            return dsPhong;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static ObservableList<PhongDTO> layDSSoPhong(String strTenLoaiPhong, ObservableList<PhongDTO> dsSoPhong) {
        try { 
            PreparedStatement ps;
            ps = ConnectingDB.getConnection().prepareStatement("SELECT DISTINCT Phong.TenPhong as SoPhong FROM Phong, LoaiPhong WHERE Phong.MaLoaiPhong = LoaiPhong.MaLoaiPhong AND TenLoaiPhong=N'" + strTenLoaiPhong + "'");
//            ps.setString(1, strTenLoaiPhong);
            
            ResultSet rs = ps.executeQuery();
            dsSoPhong = FXCollections.observableArrayList();
            // lấy dữ liệu
            while (rs.next()) {
                PhongDTO phong = new PhongDTO();
                phong.setSoPhong(rs.getString(1));
                dsSoPhong.add(phong);
            }
            rs.close();
            ConnectingDB.getConnection().close();
            return dsSoPhong;
        }
        catch (Exception e) {
            return null;
        }
    }
}
