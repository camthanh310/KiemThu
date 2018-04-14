/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.NhanVienDTO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TTC
 */
public class NhanVienDAO {
    public static ObservableList<NhanVienDTO> layDanhSachNV(ObservableList<NhanVienDTO> dsNV) {
        try {
            PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("SELECT * FROM NhanVien");
            ResultSet rs = ps.executeQuery();
            
            // lấy dữ liệu
            while (rs.next()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strNgaySinh = dateFormat.format(rs.getDate("NgaySinh"));

                dsNV.add(new NhanVienDTO(
                rs.getString("MaNhanVien").trim(),
                rs.getString("HoTen").trim(),
                strNgaySinh,
                rs.getString("DiaChi").trim(),
                rs.getString("GioiTinh"),
                rs.getString("QueQuan").trim()
                ));
            }
            
            return dsNV;
        }
        catch (Exception e) {
            dsNV = null;
            return dsNV;
        }
    }
    
    public static boolean xoaNhanVien(NhanVienDTO nv) throws Exception{
        try{
            PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("DELETE FROM NhanVien WHERE MaNhanVien=?");
            ps.setString(1, nv.getMaNhanVien());
            
            ps.executeUpdate();
            ConnectingDB.getConnection().close();
            return true;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public static boolean them_CapNhatNhanVien(NhanVienDTO nv) throws Exception{
       try {
           PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("SELECT * FROM NhanVien WHERE MaNhanVien=?");
           ps.setString(1, nv.getMaNhanVien());
           
           // nếu ko tìm thấy nv nào có mã truyền vào thì thêm mới
           if (!ps.executeQuery().isBeforeFirst()) { 
                ps = ConnectingDB.getConnection().prepareStatement("INSERT into NhanVien VALUES(?,?,?,?,?,?)");
                ps.setString(1, nv.getMaNhanVien());
                ps.setString(2, nv.getHoTen());
                ps.setString(3, nv.getNgaySinh());
                ps.setString(4, nv.getGioiTinh());
                ps.setString(5, nv.getQueQuan());
                ps.setString(6, nv.getDiaChi());
                
                ps.executeUpdate();
           }
           else { // nếu tìm thấy thì cập nhật dữ liệu
                ps = ConnectingDB.getConnection().prepareStatement("UPDATE NhanVien SET Hoten=?, NgaySinh=?, GioiTinh=?, QueQuan=?, DiaChi=? WHERE MaNhanVien =?");
                ps.setString(1, nv.getHoTen());
                ps.setString(2, nv.getNgaySinh());
                ps.setString(3, nv.getGioiTinh());
                ps.setString(4, nv.getQueQuan());
                ps.setString(5, nv.getDiaChi());
                ps.setString(6, nv.getMaNhanVien());

                ps.executeUpdate();
           }
            ConnectingDB.getConnection().close();
            return true;
       } catch (Exception e) {
           throw e;
       }
   }
    
//    public static boolean capNhatThongTin(NhanVienDTO nv){
//       try {
//            PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("UPDATE NhanVien VALUES(?,?,?,?,?) WHERE MaNhanVien =?");
//            ps.setString(1, nv.getHoTen());
//            ps.setString(2, nv.getNgaySinh());
//            ps.setString(3, nv.getGioiTinh());
//            ps.setString(4, nv.getQueQuan());
//            ps.setString(5, nv.getDiaChi());
//            ps.setString(6, nv.getMaNhanVien());
//
//           
//            ps.executeUpdate();
//            ConnectingDB.getConnection().close();
//            return true;
//       } catch (Exception e) {
//           return false;
//       }
//   }
}
