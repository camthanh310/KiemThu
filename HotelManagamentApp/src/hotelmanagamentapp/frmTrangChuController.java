/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author TTC
 */


public class frmTrangChuController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnTiepNhanKhach;
    @FXML
    private Button btnThanhToan;
    @FXML
    private Button btnQuanLyPhong;
    @FXML
    private Button btnQuanLyLoaiPhong;
    @FXML
    private Button btnInBaoCao;
    @FXML
    private Label lblTieuDeXinChao;
    
    public static String strLoaiNguoiDung;
    public static String lblXinChao;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if ("quanly".equals(strLoaiNguoiDung)) {
            btnQuanLyLoaiPhong.setVisible(true);
            btnQuanLyPhong.setVisible(true);
            btnThanhToan.setVisible(false);
            btnTiepNhanKhach.setVisible(false);
            btnInBaoCao.setVisible(false);
        }
        else {
            btnQuanLyLoaiPhong.setVisible(false);
            btnQuanLyPhong.setVisible(false);
            btnThanhToan.setVisible(true);
            btnTiepNhanKhach.setVisible(true);
            btnInBaoCao.setVisible(true);
        }
        
        if("quanly".equals(lblXinChao)) {
            lblTieuDeXinChao.setText("XIN CHÀO QUẢN LÝ");
        }
        else
            lblTieuDeXinChao.setText("XIN CHÀO NHÂN VIÊN");
    }
    
    @FXML
    private void btnInBaoCao_Click(ActionEvent event) {
        Parent root = null;
        Stage stg = new Stage();
        try {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
            
            root = FXMLLoader.load(getClass().getResource("/hotelmanagamentapp/frmLapBaoCao.fxml"));
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.setResizable(false);
            stg.setTitle("Lập báo cáo");
            stg.show();
        } catch (IOException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "" + ex);
        }
    }
    
    @FXML
    private void btnThanhToanPhong_Click(ActionEvent event) {
        Parent root = null;
        Stage stg = new Stage();
        try {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
            
            root = FXMLLoader.load(getClass().getResource("/hotelmanagamentapp/frmThanhToanPhong.fxml"));
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.setResizable(false);
            stg.setTitle("Tiếp nhận khách");
            stg.show();
        } catch (IOException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "" + ex);
        }  
    }
    
    @FXML
    private void btnTiepNhanKhach_Click(ActionEvent event) {
        Parent root = null;
        Stage stg = new Stage();
        try {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
            
            root = FXMLLoader.load(getClass().getResource("/hotelmanagamentapp/frmTiepNhanKhach.fxml"));
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.setResizable(false);
            stg.setTitle("Tiếp nhận khách");
            stg.show();
        } catch (IOException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "" + ex);
        }    
    }
    
    @FXML
    private void hienThiThongTinPhong(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/hotelmanagamentapp/frmPhong.fxml"));
        
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Quản lý Phòng");
        stage.show();
    }

    @FXML
    private void hienThiThongTinLoaiPhong(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        Stage demoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/hotelmanagamentapp/LoaiPhong.fxml"));
        Scene scene = new Scene(root); 
        demoStage.setScene(scene); 
        demoStage.setResizable(false);
        demoStage.setTitle("Danh Mục Phòng");
        demoStage.show(); 
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
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(frmTiepNhanKhachController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi đăng xuất.\n\n" + ex);
        }
    }
}
