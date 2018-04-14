/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PhieuThanhToanDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TTC
 */
public class PhieuThanhToanDAO {
    public static ObservableList<PhieuThanhToanDTO> layPhieuThanhToan(String strSoPhong) throws SQLException {
        ObservableList<PhieuThanhToanDTO> phieuThanhToan = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            switch (strSoPhong){
                case "":
                    ps = ConnectingDB.getConnection().prepareStatement("SELECT TinhTrangPhong FROM Phong WHERE TinhTrangPhong=N'Đã Đặt'");
                    break;
                default:
                    ps = ConnectingDB.getConnection().prepareStatement("SELECT TinhTrangPhong FROM Phong WHERE TinhTrangPhong=N'Đã Đặt' AND TenPhong = '" + strSoPhong + "'");
            }
            ResultSet rs = ps.executeQuery();
            rs.next();
            if ("Còn Trống".equals(rs.getString("TinhTrangPhong"))) {
                return null;
            }
            
            ps = ConnectingDB.getConnection().prepareStatement("SELECT KhachHang.MaKhachHang, HoTen FROM KhachHang, Phong, DatPhong WHERE Phong.MaPhong = DatPhong.MaPhong AND DatPhong.MaKhachHang = KhachHang.MaKhachHang AND TenPhong = '" + strSoPhong + "'");
            rs = ps.executeQuery();
            rs.next();
            
            PhieuThanhToanDTO phieuTT = new PhieuThanhToanDTO();
            phieuTT.setMaKhachHang(rs.getInt("MaKhachHang"));
            phieuTT.setHoTen(rs.getString("HoTen").trim());
            
//            ps = ConnectingDB.getConnection().prepareStatement("select Phong.TenPhong AS TenPhong from phong, DatPhong where phong.MaPhong = DatPhong.MaPhong and DatPhong.MaKhachHang = " + phieuTT.getMaKhachHang());
//            rs = ps.executeQuery();
//            rs.next();
            phieuTT.setSoPhong(strSoPhong);
            
            ps = ConnectingDB.getConnection().prepareStatement("SELECT NgayDatPhong, NgayTraPhong FROM DatPhong WHERE MaKhachHang = " + phieuTT.getMaKhachHang());
            rs = ps.executeQuery();
            rs.next();
            phieuTT.setNgayThue(ConvertorDAO.ngayThang_DB2Dto(rs.getString("NgayDatPhong")));
            phieuTT.setNgayTra(ConvertorDAO.ngayThang_DB2Dto(rs.getString("NgayTraPhong")));
            
            ps = ConnectingDB.getConnection().prepareStatement("SELECT TongTien FROM HoaDon, DatPhong WHERE HoaDon.MaDatPhong = DatPhong.MaDatPhong AND MaKhachHang = " + phieuTT.getMaKhachHang());
            rs = ps.executeQuery();
            rs.next();
            phieuTT.setTongTien(rs.getFloat("TongTien") + "");
            
            phieuThanhToan.add(phieuTT);
            ConnectingDB.closeConnection();
            rs.close();
            return phieuThanhToan;
        }
        catch (SQLException ex) {
            ConnectingDB.closeConnection();
            return null;
        }
    }
    
    public static void thanhToanPhong(String soPhong) throws SQLException {
        PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("UPDATE Phong SET TinhTrangPhong = N'Còn Trống' WHERE TenPhong=N'" + soPhong + "'");
        ps.executeUpdate();
        ConnectingDB.closeConnection();
    }
}
