/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaiPhongDTO;
import DTO.PhongDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author TTC
 */
public class LoaiPhongDAO {
    public static ObservableList<LoaiPhongDTO> layDSLoaiPhong(ObservableList<LoaiPhongDTO> dsLoaiPhong) {
        try { 
            PreparedStatement ps;
            ps = ConnectingDB.getConnection().prepareStatement("SELECT TenLoaiPhong FROM LoaiPhong");
            
            ResultSet rs = ps.executeQuery();
            
            // lấy dữ liệu
            while (rs.next()) {
                LoaiPhongDTO loaiPhong = new LoaiPhongDTO();
                loaiPhong.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
                dsLoaiPhong.add(loaiPhong);
            }
            ps.close();
            ConnectingDB.closeConnection();
            return dsLoaiPhong;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static double layDonGiaLoaiPhong(String strLoaiPhong) {
        try {
            PreparedStatement ps;
            ps = ConnectingDB.getConnection().prepareStatement("SELECT Gia FROM LoaiPhong WHERE TenLoaiPhong=N'" + strLoaiPhong + "'");
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            LoaiPhongDTO loaiPhong = new LoaiPhongDTO();
            // lấy dữ liệu
            loaiPhong.setGia(rs.getDouble("Gia"));
            
            ps.close();
            ConnectingDB.closeConnection();
            
            return loaiPhong.getGia();
        } catch (SQLException ex) {
            return 0;
        }
    }
}
