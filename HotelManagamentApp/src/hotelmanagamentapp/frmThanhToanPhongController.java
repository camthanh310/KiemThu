/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import DTO.PhieuThanhToanDTO;
import DAO.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author TTC
 */

public class frmThanhToanPhongController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    AnchorPane mainPane;
    @FXML
    TextField txtTimThongTinThanhToan;
    
    @FXML 
    TableView<PhieuThanhToanDTO> tbPhieuThanhToan;
    @FXML
    TableColumn<PhieuThanhToanDTO, String> colMaKhachHang;
    @FXML
    TableColumn<PhieuThanhToanDTO, String> colHoTen;
    @FXML
    TableColumn<PhieuThanhToanDTO, String> colSoPhong;
    @FXML
    TableColumn<PhieuThanhToanDTO, String> colNgayThue;
    @FXML
    TableColumn<PhieuThanhToanDTO, String> colNgayTra;
    @FXML
    TableColumn<PhieuThanhToanDTO, String> colTongTien;
    
    @FXML
    Label lblMaKhachHang;
    @FXML
    Label lblSoCMND;
    @FXML
    Label lblHoTen;
    @FXML
    Label lblMaHoaDon;
    @FXML
    Label lblLoaiPhong;
    @FXML
    Label lblNgayDatPhong;
    @FXML
    Label lblSoNgayO;
    @FXML
    Label lblSoPhong;
    @FXML
    Label lblNgayTraPhong;
    @FXML
    Label lblDonGia;
    @FXML
    Label lblThanhTien;
    
    @FXML
    Button btnThanhToan;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colMaKhachHang.setCellValueFactory(new PropertyValueFactory<>("MaKhachHang"));
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        colSoPhong.setCellValueFactory(new PropertyValueFactory<>("SoPhong"));
        colNgayThue.setCellValueFactory(new PropertyValueFactory<>("NgayThue"));
        colNgayTra.setCellValueFactory(new PropertyValueFactory<>("NgayTra"));
        colTongTien.setCellValueFactory(new PropertyValueFactory<>("TongTien"));
    }    
    
    private void layThongTinThanhToan(String strThongTinThanhToan) {
        try {
            tbPhieuThanhToan.getItems().clear();
            txtTimThongTinThanhToan.setText(txtTimThongTinThanhToan.getText().toUpperCase());
            tbPhieuThanhToan.getItems().clear();
            ObservableList<PhieuThanhToanDTO> dsPhieuThanhToan = PhieuThanhToanDAO.layPhieuThanhToan(strThongTinThanhToan);
            if (dsPhieuThanhToan == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin thanh toán phù hợp cho phòng '" + txtTimThongTinThanhToan.getText().toUpperCase() + "'");
                return;
            }
            tbPhieuThanhToan.setItems(dsPhieuThanhToan);
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi.\n\n" + ex);
        }
    }
    
    @FXML
    private void btnVeMenuChinh_Click(ActionEvent event) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        
        try {
            frmTrangChuController.strLoaiNguoiDung = "nhanvien";
            Parent root = FXMLLoader.load(getClass().getResource("/hotelmanagamentapp/frmTrangChu.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(frmTiepNhanKhachController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi đăng xuất.\n\n" + ex);
        }
    }
    
    @FXML
    private void btnThoat_Click(ActionEvent event) {
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát chương trình?", "Xác nhận hành động", 0, 2) == 0) {
            // 0: error; 1: information; 2: warning
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
        }
    }
    
    @FXML
    private void btnDangXuat_Click(ActionEvent event) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hotelmanagamentapp/HotelManagament.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Đăng nhập");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(frmTiepNhanKhachController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi đăng xuất.\n\n" + ex);
        }
    }
    
    @FXML
    private void btnTimThongTinThanhToan_Click(ActionEvent event) {
        layThongTinThanhToan(txtTimThongTinThanhToan.getText());
    }
    
    @FXML
    private void tbPhieuThanhToan_SelectedIndexChanged(MouseEvent event) throws SQLException {
        btnThanhToan.setDisable(false);
        lblMaKhachHang.setText(tbPhieuThanhToan.getSelectionModel().getSelectedItem().getMaKhachHang() + "");
        lblHoTen.setText(tbPhieuThanhToan.getSelectionModel().getSelectedItem().getHoTen());
        lblSoPhong.setText(tbPhieuThanhToan.getSelectionModel().getSelectedItem().getSoPhong());
        lblNgayDatPhong.setText(tbPhieuThanhToan.getSelectionModel().getSelectedItem().getNgayThue());
        lblNgayTraPhong.setText(tbPhieuThanhToan.getSelectionModel().getSelectedItem().getNgayTra());
        lblThanhTien.setText(tbPhieuThanhToan.getSelectionModel().getSelectedItem().getTongTien());
        lblSoCMND.setText(HoTroDAO.laySoCMND(Integer.parseInt(lblMaKhachHang.getText())));
        lblMaHoaDon.setText(HoTroDAO.layMaHoaDon(Integer.parseInt(lblMaKhachHang.getText())) + "");
        
        LocalDate ldNgayTra = ConvertorDAO.LOCAL_DATE(lblNgayTraPhong.getText())
                 , ldNgayDat = ConvertorDAO.LOCAL_DATE(lblNgayDatPhong.getText());
        lblSoNgayO.setText(HoTroDAO.tinhKhoangCachHaiNgay(ldNgayDat, ldNgayTra) + "");
        lblDonGia.setText(HoTroDAO.layGiaPhongChoKhachHang(Integer.parseInt(lblMaKhachHang.getText())) + "");
        lblLoaiPhong.setText(HoTroDAO.layTenLoaiPhongChoKhachHang(Integer.parseInt(lblMaKhachHang.getText())));
    }
    
    @FXML
    private void btnThanhToan_Click(ActionEvent event) {
        if (JOptionPane.showConfirmDialog(null, "Thanh toán phòng cho '" + lblHoTen.getText() + "'?", "Xác nhận hành động", 0, 2) == 0) {
            // 0: error; 1: information; 2: warning
            try {
                PhieuThanhToanDAO.thanhToanPhong(lblSoPhong.getText());
                JOptionPane.showMessageDialog(null, "Thanh toán thành công.");
                btnThanhToan.setDisable(true);
                
                lblMaKhachHang.setText("");
                lblHoTen.setText("");
                lblSoPhong.setText("");
                lblNgayDatPhong.setText("");
                lblNgayTraPhong.setText("");
                lblThanhTien.setText("");
                lblSoCMND.setText("");
                lblMaHoaDon.setText("");
                lblSoNgayO.setText("");
                lblDonGia.setText("");
                lblLoaiPhong.setText("");
                txtTimThongTinThanhToan.setText("");
                tbPhieuThanhToan.getItems().clear();
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Thanh toán thất bại.\n\n" + ex);
            }
        }
    }
    
    public static int testThanhToanPhong(String soPhong) throws SQLException {
        try {
            PreparedStatement ps = ConnectingDB.getConnection().prepareStatement("UPDATE Phong SET TinhTrangPhong = N'Còn Trống' WHERE TenPhong=N'" + soPhong + "'");
            int n = ps.executeUpdate();
            ConnectingDB.closeConnection();
            return n;
        }
        catch(Exception ex) {
            return -1;
        }
    }
    
    public int testLayThongTinThanhToan(String strThongTinThanhToan) {
        try {
            tbPhieuThanhToan.getItems().clear();
            txtTimThongTinThanhToan.setText(txtTimThongTinThanhToan.getText().toUpperCase());
            tbPhieuThanhToan.getItems().clear();
            ObservableList<PhieuThanhToanDTO> dsPhieuThanhToan = PhieuThanhToanDAO.layPhieuThanhToan(strThongTinThanhToan);
            if (dsPhieuThanhToan == null) {
                return 0;
            }
            tbPhieuThanhToan.setItems(dsPhieuThanhToan);
            return 1;
        }
        catch (SQLException ex) {
            return 0;
        }
    }
}
