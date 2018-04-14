/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import DAO.*;
import DTO.NhanVienDTO;
import com.sun.jnlp.ApiDialog;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 * FXML Controller class
 *
 * @author TTC
 */
public class frmThemNhanVienController implements Initializable {
    @FXML TableView<NhanVienDTO> tvDSNV;
    @FXML TableColumn<NhanVienDTO, String> colMaNV;
    @FXML TableColumn<NhanVienDTO, String> colHoTen;
    @FXML TableColumn<NhanVienDTO, String> colNgaySinh;
    @FXML TableColumn<NhanVienDTO, Date> colGioiTinh;
    @FXML TableColumn<NhanVienDTO, String> colQueQuan;
    @FXML TableColumn<NhanVienDTO, String> colDiaChi;
    
    @FXML Button btnLuu;
    @FXML Button btnCapNhat;
    @FXML Button btnSaThai;
    @FXML TextField txtMaNV;
    @FXML TextField txtHoTen;
    @FXML TextField txtQueQuan;
    @FXML TextField txtDiaChi;
    @FXML DatePicker dpNgaySinh;
    @FXML ComboBox cboGioiTinh;
    
    ObservableList<NhanVienDTO> dataDSNV = FXCollections.observableArrayList()
            , dsNV = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colMaNV.setCellValueFactory(new PropertyValueFactory<>("MaNhanVien"));
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        colGioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        colQueQuan.setCellValueFactory(new PropertyValueFactory<>("QueQuan"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        
        // chọn 1 item có index > 0:
        // comboBox.getSelectionModel().select("Option B");
        cboGioiTinh.getItems().addAll("Nam", "Nữ");
        
        layDanhSachNhanVien();
    }    
    
    private void layDanhSachNhanVien(){
        tvDSNV.getItems().clear();
        dsNV = NhanVienDAO.layDanhSachNV(dataDSNV);
        tvDSNV.setItems(dsNV);
    }
    
    @FXML
    private void layDongDuLieu(MouseEvent event) {
        try {
            txtMaNV.setText(tvDSNV.getSelectionModel().getSelectedItem().getMaNhanVien());
            txtHoTen.setText(tvDSNV.getSelectionModel().getSelectedItem().getHoTen());
            cboGioiTinh.setValue(tvDSNV.getSelectionModel().getSelectedItem().getGioiTinh());
            txtQueQuan.setText(tvDSNV.getSelectionModel().getSelectedItem().getQueQuan());
            dpNgaySinh.setValue(ConvertorDAO.LOCAL_DATE(tvDSNV.getSelectionModel().getSelectedItem().getNgaySinh()));
            txtDiaChi.setText(tvDSNV.getSelectionModel().getSelectedItem().getDiaChi());
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }
    
    @FXML
    private void btnSaThai(ActionEvent event) throws Exception {
        try {
                if (JOptionPane.showConfirmDialog(null, "Sa thải nhân viên " + txtHoTen.getText() + "?", "Xác nhận hành động", 0, 2) == 0){
                // 0: error; 1: information; 2: warning

                NhanVienDTO nv = new NhanVienDTO(
                        txtMaNV.getText().trim()
                        , txtHoTen.getText().trim()
                        , ConvertorDAO.ngaySinh_Dto2DB(dpNgaySinh.getValue())
                        , txtDiaChi.getText().trim()
                        , cboGioiTinh.getValue().toString()
                        , txtQueQuan.getText().trim()
                );
                NhanVienDAO.xoaNhanVien(nv);
                JOptionPane.showMessageDialog(null, "Sa thải thành công.");
                txtMaNV.setText("");
                txtDiaChi.setText("");
                txtHoTen.setText("");
                txtQueQuan.setText("");
                dpNgaySinh.setValue(null);
                cboGioiTinh.setValue(null);
                layDanhSachNhanVien();
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Sa thải thất bại.\n\n" + ex);
        }
    }
    
    @FXML
    private void btnLuu(ActionEvent event) {
        // lấy dữ liệu từ DatePicker và định dạng lại theo chuẩn của SQL:
        if (dpNgaySinh.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Chưa nhập ngày sinh của nhân viên");
            return;
        }
        DateTimeFormatter formatterDB = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateDB =  dpNgaySinh.getValue();
        String strNgaySinhDB = formatterDB.format(dateDB);
        
        // tạo định dạng để hiển thị lên giao diện:
        DateTimeFormatter formatterUI = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateUI =  dpNgaySinh.getValue();
        String strNgaySinhUI = formatterUI.format(dateUI);
        
        if (cboGioiTinh.getSelectionModel().getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn giới tính cho nhân viên");
            return;
        }
        String strGioiTinh = cboGioiTinh.getSelectionModel().getSelectedItem().toString();
        
        if ("".equals(txtHoTen.getText())) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên cho nhân viên");
            return;
        }
        
        if ("".equals(txtDiaChi.getText())) {
            JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ cho nhân viên");
            return;
        }
        
        if ("".equals(txtQueQuan.getText())) {
            JOptionPane.showMessageDialog(null, "Chưa nhập quê quán cho nhân viên");
            return;
        }
        
        NhanVienDTO nv = new NhanVienDTO(
                txtMaNV.getText().trim()
                , txtHoTen.getText().trim()
                , strNgaySinhDB
                , txtDiaChi.getText().trim()
                , strGioiTinh
                , txtQueQuan.getText().trim()
        );
        // tạo bản sao của nhân viên mới để hiển thị lên giao diện:
        NhanVienDTO nvUI = new NhanVienDTO();
//        nvUI = nv;
//        nvUI.setNgaySinh(strNgaySinhUI);
        
        try{
            NhanVienDAO.them_CapNhatNhanVien(nv);
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công.");
            tvDSNV.getItems().clear();
            layDanhSachNhanVien();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại.\n\n" + ex);
        }
    }
}
