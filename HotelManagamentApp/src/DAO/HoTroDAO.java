/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.*;
import DAO.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author TTC
 */
public class HoTroDAO {
    public static String laySoCMND(int maKhachHang) throws SQLException {
        String strCMND = "";
        try {
            PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("SELECT SoCMND FROM KhachHang WHERE MaKhachHang = " + maKhachHang);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (!rs.isBeforeFirst())
                strCMND = rs.getString("SoCMND");
            ConnectingDB.closeConnection();
        }
        catch (Exception ex) {
            
        }
        return strCMND;
    }
    
    public static int layMaDatPhong() throws SQLException{
        PreparedStatement ps;
        int iMa = 0;
        ps = ConnectingDB.getConnection().prepareStatement("SELECT MAX(MaDatPhong) AS MaDatPhong FROM DatPhong");
        ResultSet rs = ps.executeQuery();
        rs.next();
        if (!rs.isBeforeFirst())
            iMa = rs.getInt("MaDatPhong");
        ConnectingDB.closeConnection();
        return iMa;
    }
    
    public static int layMaPhong() throws SQLException {
        int iMa = 0;
        PreparedStatement ps;
        ps = ConnectingDB.getConnection().prepareStatement("SELECT MAX(MaPhong) AS MaPhong FROM Phong");
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst())
            iMa = rs.getInt("MaPhong");
        ConnectingDB.closeConnection();
        return iMa;
    }
    
    public static int layMaPhong(String strSoPhong) throws SQLException {
        PreparedStatement ps;
        ps = ConnectingDB.getConnection().prepareStatement("SELECT MaPhong FROM Phong WHERE TenPhong=N'" + strSoPhong + "'");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int iMaPhong = rs.getInt("MaPhong");
        ConnectingDB.closeConnection();
        return iMaPhong;
    }
    
    public static int layMaKhachHang() throws SQLException{
        PreparedStatement ps;
        int iMa = 0;
        ps = ConnectingDB.getConnection().prepareStatement("SELECT MAX(MaKhachHang) AS MaKhachHang FROM KhachHang");
        ResultSet rs = ps.executeQuery();
        if (rs.isBeforeFirst()) {
            rs.next();
            iMa = rs.getInt("MaKhachHang");
        }
        ConnectingDB.closeConnection();
        return iMa;
    }
    
    public static int layMaHoaDon() throws SQLException{
        PreparedStatement ps;
        int iMa = 0;
        ps = ConnectingDB.getConnection().prepareStatement("SELECT MAX(MaHoaDon) AS MaHoaDon FROM HoaDon");
        ResultSet rs = ps.executeQuery();
        if (rs.isBeforeFirst()) {
            rs.next();
            iMa = rs.getInt("MaHoaDon");
        }
        ConnectingDB.closeConnection();
        return iMa;
    }
    
    public static int layMaHoaDon(int maKhachHang) throws SQLException{
        PreparedStatement ps;
        int iMa = 0;
        ps = ConnectingDB.getConnection().prepareStatement("SELECT HoaDon.MaHoaDon AS MaHD FROM DatPhong, HoaDon, KhachHang WHERE HoaDon.MaDatPhong = DatPhong.MaDatPhong AND KhachHang.MaKhachHang = DatPhong.MaKhachHang AND KhachHang.MaKhachHang = " + maKhachHang);
        ResultSet rs = ps.executeQuery();
        if (rs.isBeforeFirst()) {
            rs.next();
            iMa = rs.getInt("MaHD");
        }
        ConnectingDB.closeConnection();
        return iMa;
    }
    
    public static long tinhKhoangCachHaiNgay(LocalDate ngayMot, LocalDate ngayHai) {
        long lSoNgay = ChronoUnit.DAYS.between(ngayMot, ngayHai);
        return lSoNgay;
    }
    
    public static String layTenLoaiPhongChoKhachHang(int maKhachHang) throws SQLException {
        String strTenLoai = "";
        PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("SELECT LoaiPhong.TenLoaiPhong AS TenLoaiPhong FROM DatPhong, Phong, LoaiPhong WHERE DatPhong.MaPhong = Phong.MaPhong AND PHONG.MaLoaiPhong = LoaiPhong.MaLoaiPhong AND DatPhong.MaKhachHang =" + maKhachHang);
        ResultSet rs = ps.executeQuery();
        if (rs.isBeforeFirst()) {
            rs.next();
            strTenLoai = rs.getString("TenLoaiPhong");
        }
        ConnectingDB.closeConnection();
        return strTenLoai;
    }
    
    public static double layGiaPhongChoKhachHang(int maKhachHang) throws SQLException {
        double donGia = 0;
        PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("SELECT LoaiPhong.Gia AS Gia FROM DatPhong, Phong, LoaiPhong WHERE DatPhong.MaPhong = Phong.MaPhong AND PHONG.MaLoaiPhong = LoaiPhong.MaLoaiPhong AND DatPhong.MaKhachHang = " + maKhachHang);
        ResultSet rs = ps.executeQuery();
        if (rs.isBeforeFirst()) {
            rs.next();
            donGia = rs.getDouble("Gia");
        }
        ConnectingDB.closeConnection();
        return donGia;
    }
    
    public static int layMaNguoiDung(String strUsername) throws SQLException {
        PreparedStatement ps;
        ps = ConnectingDB.getConnection().prepareStatement("SELECT ID FROM TaiKhoan WHERE Username = '" + strUsername + "'");
        ResultSet rs = ps.executeQuery();
        ConnectingDB.closeConnection();
        return rs.getInt("ID");
    }
    
    public static void datPhong(HoaDonDTO hd, KhachHangDTO kh, PhongDTO p, DatPhongDTO phieudat) throws SQLException {
        // kiểm tra khách hàng đã tồn tại trong CSDL hay chưa
        PreparedStatement ps;
        ps = ConnectingDB.getConnection().prepareStatement("SELECT * FROM KhachHang WHERE SoCMND = '" + kh.getStrSoCMND() + "'");

        ResultSet rs = ps.executeQuery();
        if(!rs.isBeforeFirst()) { // nếu chưa có khách hàng trong CSDL
            // thêm mới 1 khách hàng
            ps = ConnectingDB.getConnection().prepareStatement("INSERT INTO KhachHang VALUES(?,?,?,?,?,?)");
            ps.setInt(1, kh.getiMaKhachHang());
            ps.setString(2, kh.getStrHoTen());
            ps.setString(3, kh.getStrDiaChi());
            ps.setString(4, kh.getStrSoCMND());
            ps.setString(5, kh.getStrNgaySinh());
            ps.setString(6, kh.getStrSoDienThoai());
            
            ps.executeUpdate();
        }
        
        // thêm vào bảng DatPhong
        phieudat.setMaPhong(layMaPhong());
        ps = ConnectingDB.getConnection().prepareStatement("INSERT INTO DatPhong VALUES(?,?,?,?,?)");
        ps.setInt(1, phieudat.getMaDatPhong());
        ps.setInt(2, phieudat.getMaKhachHang());
        ps.setString(3, phieudat.getNgayDatPhong());
        ps.setString(4, phieudat.getNgayTraPhong());
        ps.setInt(5, p.getMaPhong());
        ps.executeUpdate();
        
        // thêm vào bảng HoaDon
        ps = ConnectingDB.getConnection().prepareStatement("INSERT INTO HoaDon VALUES(?,?,?,?)");
        ps.setInt(1, hd.getMaHoaDon());
        ps.setInt(2, hd.getMaDatPhong());
        ps.setString(3, hd.getNgayThanhToan());
        ps.setDouble(4, hd.getTongTien());
        ps.executeUpdate();
        
        // cập nhật tình trạng phòng
        ps = ConnectingDB.getConnection().prepareStatement("UPDATE Phong SET TinhTrangPhong = N'Đã Đặt' WHERE TenPhong = '" + p.getSoPhong() + "'");
        ps.executeUpdate();
    }
    
    public static boolean datPhongForTesting(HoaDonDTO hd, KhachHangDTO kh, PhongDTO p, DatPhongDTO phieudat) throws SQLException {
        try {
            PreparedStatement ps;
//            ps = ConnectingDB.getConnection().prepareStatement("SELECT * FROM Phong WHERE TenPhong = N'" + p.getSoPhong() + "' AND TinhTrangPhong = N'Đã Đặt'");
//            ResultSet rs = ps.executeQuery();
//            if (rs.first())
//                return false;
            
            // thêm mới 1 khách hàng
            ps = ConnectingDB.getConnection().prepareStatement("INSERT INTO KhachHang VALUES(?,?,?,?,?,?)");
            ps.setInt(1, kh.getiMaKhachHang());
            ps.setString(2, kh.getStrHoTen());
            ps.setString(3, kh.getStrDiaChi());
            ps.setString(4, kh.getStrSoCMND());
            ps.setString(5, kh.getStrNgaySinh());
            ps.setString(6, kh.getStrSoDienThoai());
            ps.executeUpdate();
            
            // thêm vào bảng DatPhong
            phieudat.setMaPhong(layMaPhong());
            ps = ConnectingDB.getConnection().prepareStatement("INSERT INTO DatPhong VALUES(?,?,?,?,?)");
            ps.setInt(1, phieudat.getMaDatPhong());
            ps.setInt(2, phieudat.getMaKhachHang());
            ps.setString(3, phieudat.getNgayDatPhong());
            ps.setString(4, phieudat.getNgayTraPhong());
            ps.setInt(5, p.getMaPhong());
            ps.executeUpdate();

            // thêm vào bảng HoaDon
            ps = ConnectingDB.getConnection().prepareStatement("INSERT INTO HoaDon VALUES(?,?,?,?)");
            ps.setInt(1, hd.getMaHoaDon());
            ps.setInt(2, hd.getMaDatPhong());
            ps.setString(3, hd.getNgayThanhToan());
            ps.setDouble(4, hd.getTongTien());
            ps.executeUpdate();

            // cập nhật tình trạng phòng
            ps = ConnectingDB.getConnection().prepareStatement("UPDATE Phong SET TinhTrangPhong = N'Đã Đặt' WHERE TenPhong = '" + p.getSoPhong() + "'");
            ps.executeUpdate();
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }
    
    public boolean datPhongTest(HoaDonDTO hd, 
            KhachHangDTO kh, PhongDTO p, DatPhongDTO phieudat) throws SQLException {
        // kiểm tra khách hàng đã tồn tại trong CSDL hay chưa
        PreparedStatement ps;
        ps = ConnectingDB.getConnection().prepareStatement("SELECT * FROM "
                + "KhachHang WHERE SoCMND = '" + kh.getStrSoCMND() + "'");

        ResultSet rs = ps.executeQuery();
        if(!rs.isBeforeFirst()) { // nếu chưa có khách hàng trong CSDL
            // thêm mới 1 khách hàng
            ps = ConnectingDB.getConnection().prepareStatement("INSERT INTO "
                    + "KhachHang VALUES(?,?,?,?,?,?)");
            ps.setInt(1, kh.getiMaKhachHang());
            ps.setString(2, kh.getStrHoTen());
            ps.setString(3, kh.getStrDiaChi());
            ps.setString(4, kh.getStrSoCMND());
            ps.setString(5, kh.getStrNgaySinh());
            ps.setString(6, kh.getStrSoDienThoai());
            
            ps.executeUpdate();
        }
        
        // thêm vào bảng DatPhong
        phieudat.setMaPhong(layMaPhong());
        ps = ConnectingDB.getConnection().prepareStatement("INSERT INTO DatPhong"
                + " VALUES(?,?,?,?,?)");
        ps.setInt(1, phieudat.getMaDatPhong());
        ps.setInt(2, phieudat.getMaKhachHang());
        ps.setString(3, phieudat.getNgayDatPhong());
        ps.setString(4, phieudat.getNgayTraPhong());
        ps.setInt(5, p.getMaPhong());
        ps.executeUpdate();
        
        // thêm vào bảng HoaDon
        ps = ConnectingDB.getConnection().prepareStatement("INSERT INTO "
                + "HoaDon VALUES(?,?,?,?)");
        ps.setInt(1, hd.getMaHoaDon());
        ps.setInt(2, hd.getMaDatPhong());
        ps.setString(3, hd.getNgayThanhToan());
        ps.setDouble(4, hd.getTongTien());
        ps.executeUpdate();
        
        // cập nhật tình trạng phòng
        ps = ConnectingDB.getConnection().prepareStatement("UPDATE Phong "
                + "SET TinhTrangPhong = N'Đã Đặt' "
                + "WHERE MaPhong = '" + p.getMaPhong() + "'");
        ps.executeUpdate();
        return true;
    }
}
