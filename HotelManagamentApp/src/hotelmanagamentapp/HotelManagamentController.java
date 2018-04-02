/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import Data.ConnectingDB;
import Data.Login;

/**
 *
 * @author TTC
 */
public class HotelManagamentController implements Initializable {
    
    @FXML private Label label;
    @FXML private TextField txtTenDangNhap;
    @FXML private TextField txtMatKhau;
    @FXML private Button btnDangNhap;
    @FXML private Button btnLamMoi;
    @FXML private Button btnThoat;
    @FXML private Pane paneDangNhap;
    @FXML private GridPane gridMainPan;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML private void btnLamMoi(ActionEvent event){
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
        paneDangNhap.setVisible(false);
    }
    
    @FXML private void btnDangNhap(ActionEvent event) {
        try{
            String a;
            a="aa";
            String strTenDangNhap = txtTenDangNhap.getText()
               ,strMatKhau = txtMatKhau.getText();
            
            if ("".equals(strTenDangNhap) || "".equals(strMatKhau)) {
                JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không được để trống.");
                return;
            }
            
            
            PreparedStatement ps = Login.startLogin(strTenDangNhap, strMatKhau);

            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");               
//
                Stage stage = (Stage) gridMainPan.getScene().getWindow();
                stage.close();
                
                Parent root = null;
                if ("quanly".equals(strTenDangNhap)) {
                    root = FXMLLoader.load(getClass().getResource("/hotelmanagamentapp/Phong.fxml"));
                }
                else {
                    
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
                JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu");
        }
        catch (Exception | ExceptionInInitializerError e){

            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi!\n\n" + e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
