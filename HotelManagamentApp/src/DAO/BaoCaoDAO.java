/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DAO.ConnectingDB;
import DTO.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TTC
 */
public class BaoCaoDAO {
    public static ObservableList<BaoCaoDTO> layDanhSachBaoCao(String strDieuKienLay) {
        ObservableList<BaoCaoDTO> dsBaoCao = FXCollections.observableArrayList();
        try {
            String strDieuKien = " WHERE ";
            switch (strDieuKienLay) {
                case "2017":
                    strDieuKien += "YEAR(NgayThanhToan) = 2017 ";
                    break;
                case "2018":
                    strDieuKien += "YEAR(NgayThanhToan) = 2018 ";
                    break;
                case "Quý 1":
                    strDieuKien += "MONTH(NgayThanhToan) > 1 AND MONTH(NgayThanhToan) < 4 ";
                    break;
                case "Quý 2":
                    strDieuKien += "MONTH(NgayThanhToan) > 4 AND MONTH(NgayThanhToan) < 7 ";
                    break;
                case "Quý 3":
                    strDieuKien += "MONTH(NgayThanhToan) > 7 AND MONTH(NgayThanhToan) < 10 ";
                    break;
                case "Quý 4":
                    strDieuKien += "MONTH(NgayThanhToan) > 10 AND MONTH(NgayThanhToan) < 13 ";
                    break;
                default:
                    strDieuKien = "";
            }
            PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("SELECT (CAST(MONTH(NgayThanhToan) AS VARCHAR(4)) + '-' + CAST(YEAR(NgayThanhToan) AS VARCHAR(4))) AS ThangNam, SUM(TongTien) AS TongTien FROM HoaDon " + strDieuKien + " GROUP BY CAST(MONTH(NgayThanhToan) AS VARCHAR(4)) + '-' + CAST(YEAR(NgayThanhToan) AS VARCHAR(4))");
            ResultSet rs = ps.executeQuery();
            
            // lấy dữ liệu
            while (rs.next()) {
                BaoCaoDTO baoCao = new BaoCaoDTO();
                baoCao.setThoiGian(rs.getString("ThangNam"));
                baoCao.setTongTien(rs.getString("TongTien"));
                dsBaoCao.add(baoCao);
            }
            ps.close();
            ConnectingDB.closeConnection();
        }
        catch (Exception e) {
        }
        return dsBaoCao;
    }
    
    public static ObservableList<BaoCaoDTO> layDanhSachBaoCao() {
        ObservableList<BaoCaoDTO> dsBaoCao = FXCollections.observableArrayList();
        try { 
            PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("SELECT (CAST(MONTH(NgayThanhToan) AS VARCHAR(4)) + '-' + CAST(YEAR(NgayThanhToan) AS VARCHAR(4))) AS ThangNam, SUM(TongTien) AS TongTien FROM HoaDon GROUP BY CAST(MONTH(NgayThanhToan) AS VARCHAR(4)) + '-' + CAST(YEAR(NgayThanhToan) AS VARCHAR(4))");
            ResultSet rs = ps.executeQuery();
            
            // lấy dữ liệu
            while (rs.next()) {
                BaoCaoDTO baoCao = new BaoCaoDTO();
                baoCao.setThoiGian(rs.getString("ThangNam"));
                baoCao.setTongTien(rs.getString("TongTien"));
                dsBaoCao.add(baoCao);
            }
            ps.close();
            ConnectingDB.closeConnection();
        }
        catch (Exception e) {
        }
        return dsBaoCao;
    }
    
    public static ObservableList<String> layCacNamLapBaoCao() {
        ObservableList<String> dsCacNamLapBaoCao = FXCollections.observableArrayList();
        try { 
            PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("SELECT DISTINCT YEAR(NgayThanhToan) AS Nam FROM HoaDon");
            ResultSet rs = ps.executeQuery();
            
            // lấy dữ liệu
            String strNam = "";
            while (rs.next()) {
                strNam = rs.getString("Nam");
                dsCacNamLapBaoCao.add(strNam);
            }
            ps.close();
            ConnectingDB.closeConnection();
        }
        catch (Exception e) {
        }
        return dsCacNamLapBaoCao;
    }
}
