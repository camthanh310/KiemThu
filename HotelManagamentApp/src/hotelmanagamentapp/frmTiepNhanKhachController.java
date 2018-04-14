/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import DAO.ConvertorDAO;
import DAO.HoTroDAO;
import DAO.LoaiPhongDAO;
import DAO.PhongDAO;
import DTO.DatPhongDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.LoaiPhongDTO;
import DTO.NhanVienDTO;
import DTO.PhongDTO;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sun.plugin.javascript.navig.Anchor;

/**
 * FXML Controller class
 *
 * @author TTC
 */
public class frmTiepNhanKhachController implements Initializable {
    public frmTiepNhanKhachController() {
        
    }
    /**
     * Initializes the controller class.
     */
    @FXML Button btnTiepNhanKhach;
    @FXML Button btnDatPhong;
    @FXML Button btnHuy;
    @FXML TextField txtHoTenKH;
    @FXML TextField txtSoDienThoaiKH;
    @FXML TextField txtSoCMND;
    @FXML TextField txtDiaChi;
    @FXML DatePicker dpNgaySinh;
    @FXML Label lblMaKhachHang;
    @FXML Label lblMaHoaDon;
    @FXML DatePicker dpNgayDatPhong;
    @FXML ComboBox cboSoNgayO;
    @FXML Label lblNgayTraPhong;
    @FXML Label lblThanhTien;
    @FXML ComboBox cboLoaiPhong;
    @FXML ComboBox cboSoPhong;
    @FXML Label lblDonGia;
    @FXML Label lblSoLuongKQTimTinhTrangPhong;
    
    @FXML ComboBox cbokieuXemTinhTrangPhong;
    
    @FXML TableView<PhongDTO> tbDSPhong;
    @FXML TableColumn<PhongDTO, String> colSoPhong;
    @FXML TableColumn<PhongDTO, String> colTinhTrang;
    
    @FXML AnchorPane mainPane;
    
    ObservableList<PhongDTO> dsPhong = FXCollections.observableArrayList();
    ObservableList<LoaiPhongDTO> dsLoaiPhong = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        dpNgayDatPhong.setValue(LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        dpNgayDatPhong.setValue(LocalDate.now());
        // gán value mặc định cho cbokieuXemTinhTrangPhong
        cbokieuXemTinhTrangPhong.getItems().addAll("Tất Cả", "Còn Trống", "Đã Đặt");
        cbokieuXemTinhTrangPhong.getSelectionModel().select("Còn Trống");
        
        colSoPhong.setCellValueFactory(new PropertyValueFactory<>("SoPhong"));
        colTinhTrang.setCellValueFactory(new PropertyValueFactory<>("TinhTrang"));
        dpNgayDatPhong.setValue(LocalDate.now());
        
        for (int i=1; i<31; i++) {
            cboSoNgayO.getItems().addAll(i);
        }
        cboSoNgayO.getSelectionModel().select(0);
        
        layDSPhong();
        layDSLoaiPhong();
        layDanhSachSoPhong(cboLoaiPhong.getValue().toString());
        layDonGiaPhong(cboLoaiPhong.getValue().toString());
        layNgayTraPhong();
        layThanhTien();
        
        try {
            int iMaKhachHang = HoTroDAO.layMaKhachHang();
            iMaKhachHang++;
            lblMaKhachHang.setText(iMaKhachHang + "");

            int iMaHoaDon = HoTroDAO.layMaHoaDon();
            iMaHoaDon++;
            lblMaHoaDon.setText(iMaHoaDon + "");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lấy mã khách hàng hoặc mã hóa đơn." + ex);
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
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(frmTiepNhanKhachController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi đăng xuất.\n\n" + ex);
        }
    }
    
    private void layDanhSachSoPhong(String strTenLoaiPhong) {
        cboSoPhong.getItems().clear();
        ObservableList<PhongDTO> dsSoPhong = PhongDAO.layDSSoPhong(strTenLoaiPhong, dsPhong);
        for (PhongDTO item : dsSoPhong) {
            cboSoPhong.getItems().addAll(item.getSoPhong());
        }
        cboSoPhong.getSelectionModel().select(0);
    }
    
    private void layDSPhong() {
        tbDSPhong.getItems().clear();
        tbDSPhong.setItems(PhongDAO.layDSPhong(cbokieuXemTinhTrangPhong.getValue().toString(), dsPhong));
        lblSoLuongKQTimTinhTrangPhong.setText("Tìm thấy: " + tbDSPhong.getItems().size() + " phòng.");
    }
    
    private void layDSLoaiPhong() {
        cboLoaiPhong.getItems().clear();
        ObservableList<LoaiPhongDTO> dsKQ = LoaiPhongDAO.layDSLoaiPhong(dsLoaiPhong);
        for (LoaiPhongDTO item : dsKQ) {
            cboLoaiPhong.getItems().addAll(item.getTenLoaiPhong());
        }
        cboLoaiPhong.getSelectionModel().selectFirst();
    }
    
    private void layDonGiaPhong(String strTenLoaiPhong) {
//        txtDonGia.setText(String.format("{0:#,##0}", LoaiPhongDAO.layDonGiaLoaiPhong(strTenLoaiPhong)));
//          txtDonGia.setText(ConvertorDAO.currency_DB2Dto(LoaiPhongDAO.layDonGiaLoaiPhong(strTenLoaiPhong)));
          lblDonGia.setText(LoaiPhongDAO.layDonGiaLoaiPhong(strTenLoaiPhong) + "");
    }
    
    private void layNgayTraPhong() {
        int iSoNgayO = Integer.parseInt(cboSoNgayO.getValue().toString());
        LocalDate ldNgayDat = dpNgayDatPhong.getValue();
        LocalDate ldNgayTra = ldNgayDat.plusDays(iSoNgayO);
        lblNgayTraPhong.setText(ldNgayTra.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
    
    private void layThanhTien() {
        int iSoNgayO = Integer.parseInt(cboSoNgayO.getValue().toString());
        double dDonGia = Double.parseDouble(lblDonGia.getText());
        double dTongTien = dDonGia * iSoNgayO;
        lblThanhTien.setText(dTongTien + "");
    }
    
    @FXML
    public void cbokieuXemTinhTrangPhong_SelectedChange(ActionEvent event) {
        layDSPhong();
    }
    
    @FXML
    public void cboLoaiPhong_SelectedChange(ActionEvent event) {
        layDonGiaPhong(cboLoaiPhong.getValue().toString());
        layDanhSachSoPhong(cboLoaiPhong.getValue().toString());
        layThanhTien();
    }
    
    @FXML
    public void dpNgayDatPhong_ValueChange(ActionEvent event) {
        layNgayTraPhong();
    }
    
    @FXML
    public void cboSoNgayO__SelectedChange(ActionEvent event) {
        layNgayTraPhong();
        layThanhTien();
    }
    
    @FXML
    public void btnDatPhong_Click(ActionEvent event) throws SQLException {      
        if ("".equals(txtHoTenKH.getText())) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên của khách hàng.");
            return;
        }
        if ("".equals(txtSoDienThoaiKH.getText())) {
            JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại của khách hàng.");
            return;
        }
        if ("".equals(txtSoCMND.getText())) {
            JOptionPane.showMessageDialog(null, "Chưa nhập số CMND của khách hàng.");
            return;
        }
        if ("".equals(txtDiaChi.getText())) {
            JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ của khách hàng.");
            return;
        }
        if(dpNgaySinh.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Chưa chọn ngày sinh của khách hàng.");
            return;
        }
        
        if ("".equals(lblMaHoaDon.getText())) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã hóa đơn.\nNhấn nút 'Tiếp nhận khách' để phát sinh mã hóa đơn.");
            return;
        }
        if ("".equals(lblMaKhachHang.getText())) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã khách hàng.\nNhấn nút 'Tiếp nhận khách' để phát sinh mã hóa đơn.");
            return;
        }
        
        // kiểm tra số điện thoại có phải toàn số hay ko
        if(!kiemTraLaSo(txtSoDienThoaiKH.getText())) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ (có chứa ký tự không phải là số).");
            return;
        }
        
        // kiểm tra số CMND có phải toàn số hay ko
        if(!kiemTraLaSo(txtSoCMND.getText())) {
            JOptionPane.showMessageDialog(null, "Số CMND không hợp lệ (có chứa ký tự không phải là số).");
            return;
        }
        
        KhachHangDTO khachhang = new KhachHangDTO();
        khachhang.setiMaKhachHang(Integer.parseInt(lblMaKhachHang.getText()));
        khachhang.setStrHoTen(txtHoTenKH.getText());
        khachhang.setStrDiaChi(txtDiaChi.getText());
        khachhang.setStrNgaySinh(dpNgaySinh.getValue().toString());
        khachhang.setStrSoCMND(txtSoCMND.getText());
        khachhang.setStrSoDienThoai(txtSoDienThoaiKH.getText());
        
        HoaDonDTO hoadon = new HoaDonDTO();
        hoadon.setMaHoaDon(Integer.parseInt(lblMaHoaDon.getText()));
        hoadon.setMaDatPhong(HoTroDAO.layMaDatPhong() + 1);
        LocalDate ldTemp = ConvertorDAO.LOCAL_DATE(lblNgayTraPhong.getText());
        hoadon.setNgayThanhToan(ConvertorDAO.ngayThang_Dto2DB(ldTemp));
        hoadon.setTongTien(Double.parseDouble(lblThanhTien.getText()));
        
        DatPhongDTO phieudat = new DatPhongDTO();
        int iMaDatPhong = HoTroDAO.layMaDatPhong();
        iMaDatPhong += 1;
        phieudat.setMaDatPhong(iMaDatPhong);
        phieudat.setMaKhachHang(Integer.parseInt(lblMaKhachHang.getText()));
        phieudat.setNgayDatPhong(ConvertorDAO.ngayThang_Dto2DB(dpNgayDatPhong.getValue()));
        phieudat.setNgayTraPhong(ConvertorDAO.ngayThang_Dto2DB(ldTemp));
        phieudat.setMaPhong(HoTroDAO.layMaPhong(cboSoPhong.getValue().toString()));
        
        PhongDTO p = new PhongDTO();
        p.setMaPhong(HoTroDAO.layMaPhong(cboSoPhong.getValue().toString()));
        p.setSoPhong(cboSoPhong.getValue().toString());
        
        try {
            HoTroDAO.datPhong(hoadon, khachhang, p, phieudat);
            JOptionPane.showMessageDialog(null, "Đặt phòng thành công.");
            layDSPhong();
            layDSLoaiPhong();
            layDanhSachSoPhong(cboLoaiPhong.getValue().toString());
            layDonGiaPhong(cboLoaiPhong.getValue().toString());
            layNgayTraPhong();
            layThanhTien();
            
            txtHoTenKH.setText("");
            txtSoDienThoaiKH.setText("");
            txtSoCMND.setText("");
            txtDiaChi.setText("");
            dpNgaySinh.setValue(null);
            
            try {
                int iMaKhachHang = HoTroDAO.layMaKhachHang();
                iMaKhachHang++;
                lblMaKhachHang.setText(iMaKhachHang + "");

                int iMaHoaDon = HoTroDAO.layMaHoaDon();
                iMaHoaDon++;
                lblMaHoaDon.setText(iMaHoaDon + "");
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lấy mã khách hàng hoặc mã hóa đơn." + ex);
            }
            
            cboLoaiPhong.getSelectionModel().select(0);
            dpNgayDatPhong.setValue(LocalDate.now());
            cboSoNgayO.getSelectionModel().select(0);
            cboSoPhong.getSelectionModel().select(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi.\n\n" + ex);
        }
    }
    
    @FXML
    public void btnHuy_Click(ActionEvent event) {
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc hủy mọi thao tác dang dở?", "Xác nhận hành động", 0, 2) == 0){
            // 0: error; 1: information; 2: warning
            txtHoTenKH.setText("");
            txtSoDienThoaiKH.setText("");
            txtSoCMND.setText("");
            txtDiaChi.setText("");
            dpNgaySinh.setValue(null);
            lblMaHoaDon.setText("");
            lblMaKhachHang.setText("");
            
            cboLoaiPhong.getSelectionModel().select(0);
            dpNgayDatPhong.setValue(LocalDate.now());
            cboSoNgayO.getSelectionModel().select(0);
            cboSoPhong.getSelectionModel().select(0);
        }
    }
    
    @FXML
    private void txtsoDienThoai_KeyPress(KeyEvent event) {
        char enter = event.getKeyChar();
        if(!(Character.isDigit(enter))){
            event.consume();
        }
    }
    
    public boolean kiemTraLaSo(String strChuoiCanKiemTra) {
        try {
            double dSDT = Double.parseDouble(strChuoiCanKiemTra);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public  boolean testLayDanhSachSoPhong(String strTenLoaiPhong) {
        try {
            cboSoPhong.getItems().clear();
            ObservableList<PhongDTO> dsSoPhong = PhongDAO.layDSSoPhong(strTenLoaiPhong, dsPhong);
            for (PhongDTO item : dsSoPhong) {
                cboSoPhong.getItems().addAll(item.getSoPhong());
            }
            cboSoPhong.getSelectionModel().select(0);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
