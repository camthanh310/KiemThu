/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import DAO.Login;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sun.plugin.javascript.navig.Anchor;

/**
 * FXML Controller class
 *
 * @author TTC
 */
public class NhanVienController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnTiepNhanKhach;
    @FXML
    private Button btnThanhToan;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            stg.setTitle("Tiếp nhận khách");
            stg.show();
        } catch (IOException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "" + ex);
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
}
