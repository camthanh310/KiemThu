/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import Data.ConnectingDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hau
 */
public class FrmPhongController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection connection;
    ObservableList<Phong> data ;
    //PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    @FXML private TextField txtMaPhong;
    @FXML private TextField txtTenPhong;
    @FXML private CheckBox chbTinhTrangPhong;
    @FXML private ComboBox cbMaLoaiPhong;
    @FXML private TableView<Phong> tbPhong;
    @FXML private  TableColumn<?,?> colMaPhong;
    @FXML private  TableColumn<?,?> colTenPhong;
    @FXML private  TableColumn<?,?> colMaLoaiPhong;
    @FXML private  TableColumn<?,?> colTinhTrangPhong;
    @FXML
    private Button btnSuaPhong;
    @FXML
    private Button btnXoaPhong;
    @FXML
    private ToggleGroup timkiemphong;
    @FXML
    private ComboBox cbTimMaLoaiPhong;
    @FXML
    private RadioButton rdbTenPhong;
    @FXML
    private RadioButton rdbLoaiPhong;
    @FXML
    private TextField txtTimTenPhong;
    private boolean flag = false;
    @FXML
    private Button btnLuuPhong;
    @FXML
    private Button btnHuyPhong;
    @FXML
    private Button btnThemPhong;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label errorTenPhong;
    @FXML
    private RadioButton rdbTatCaPhong;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         connection = ConnectingDB.getConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        hienThiDuLieuPhong();
        hienThiDuLieuComBoBoxLoaiPhong();
        hienThiGiaTriTuTableView();
        TimKiemTenPhong();
    }  
    
    private void setCellTable(){
        colMaPhong.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        colTenPhong.setCellValueFactory(new PropertyValueFactory<>("tenPhong"));
        colMaLoaiPhong.setCellValueFactory(new PropertyValueFactory<>("tenLoaiPhong"));
        colTinhTrangPhong.setCellValueFactory(new PropertyValueFactory<>("tinhTrangPhong"));
    }
    
    private void hienThiGiaTriTuTableView(){
        tbPhong.setOnMouseClicked((MouseEvent event) -> {
            Phong p = tbPhong.getItems().get(tbPhong.getSelectionModel().getSelectedIndex());
            txtMaPhong.setText(String.valueOf(p.getMaPhong()));
            txtTenPhong.setText(p.getTenPhong());
            if("Đã Đặt".equals(p.getTinhTrangPhong())){
                chbTinhTrangPhong.setSelected(true);
            }
            else{
                chbTinhTrangPhong.setSelected(false);
            }
            
            cbMaLoaiPhong.setValue(String.valueOf(p.getTenLoaiPhong()));
        });   
    }
    
    private void hienthiMaLenTextField(){
        PreparedStatement preparedStatement = null;
        int iMaLoaiPhong = 0;
        String strSQL = "SELECT MAX(MaPhong) as max FROM Phong";
        try {
            preparedStatement = connection.prepareStatement(strSQL);
            rs = preparedStatement.executeQuery();
            rs.next();
            iMaLoaiPhong = rs.getInt("max");
        } catch (SQLException ex) {
            Logger.getLogger(LapDanhMucPhongController.class.getName()).log(Level.SEVERE, null, ex);
        }     
        txtMaPhong.setText(iMaLoaiPhong+1 + "");
    }
    
    private void hienThiDuLieuPhong(){
        String query = "select MaPhong,TenPhong,TenLoaiPhong,TinhTrangPhong from Phong,LoaiPhong Where Phong.MaLoaiPhong= LoaiPhong.MaLoaiPhong";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                data.add(new Phong(
                        rs.getInt(1),
                        rs.getString(2),                                                                 
                        rs.getString(3),
                        rs.getString(4)
                        ));               
            }                                                
        }
        catch(SQLException ex){
            System.err.println(ex);
        }
         tbPhong.setItems(data);  
    }
    
     private void hienThiDuLieuComBoBoxLoaiPhong(){
        String query = "select * from LoaiPhong";
        try{
            PreparedStatement preparedStatement  = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                 cbMaLoaiPhong.getItems().add(rs.getString(2));
                 cbMaLoaiPhong.getSelectionModel().select(1);
                 cbTimMaLoaiPhong.getItems().add(rs.getString(2));
                 cbTimMaLoaiPhong.getSelectionModel().select(1);
               }                                               
        }
        catch(SQLException ex){
            System.err.println(ex);
        }
         tbPhong.setItems(data);  
    }

    @FXML
    private void themPhong_Click(ActionEvent event) {
      hienthiMaLenTextField();
      txtTenPhong.setDisable(false);
      cbMaLoaiPhong.setDisable(false);
      chbTinhTrangPhong.setDisable(false);
      btnLuuPhong.setVisible(true);
      btnHuyPhong.setVisible(true);
      btnSuaPhong.setDisable(true);
      btnThemPhong.setDisable(true);
      btnXoaPhong.setDisable(true);
      flag = true;
    }

    @FXML
    private void suaPhong_Click(ActionEvent event) {
        txtTenPhong.setDisable(false);
        cbMaLoaiPhong.setDisable(false);
        chbTinhTrangPhong.setDisable(false);
        flag = false;
        btnLuuPhong.setVisible(true);
        btnHuyPhong.setVisible(true);
        btnSuaPhong.setDisable(true);
        btnThemPhong.setDisable(true);
    }
    
    public int layMaLoaiPhong(String tenLoaiPhong) throws SQLException {
        PreparedStatement preparedStatement;
        String strSQL = "SELECT MaLoaiPhong FROM LoaiPhong WHERE TenLoaiPhong=?";
        connection = ConnectingDB.getConnection();
        preparedStatement = connection.prepareStatement(strSQL);
        preparedStatement.setString(1, tenLoaiPhong);
        rs = preparedStatement.executeQuery();
        rs.next();
        int iMaLoaiPhong = rs.getInt("MaLoaiPhong");
        return iMaLoaiPhong;
    }
    
    @FXML
    private void xoaPhong_Click(ActionEvent event) {
        int maPhong = Integer.parseInt(txtMaPhong.getText());
        if (luuXoaPhong(maPhong)) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            tbPhong.getItems().clear();
            hienThiDuLieuPhong();
            txtMaPhong = null;
            txtTenPhong = null;
            chbTinhTrangPhong = null;
            cbMaLoaiPhong.getSelectionModel().select(1);
        }
    }

    public boolean luuXoaPhong(int maPhong) {
        try {
            PreparedStatement preparedStatement;
            String sql = "delete from Phong where MaPhong=?";
            connection = ConnectingDB.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maPhong);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }  
    
    public boolean luuSuaPhong(int maPhong, String tenPhong, String loaiPhong,String tinhTrangPhong) {
        try {           
            String sql = "Update Phong Set TenPhong = ?,MaLoaiPhong = ?, TinhTrangPhong = ? Where MaPhong = ?";
            Phong phong = new Phong();
            phong.setMaPhong(maPhong);
            phong.setTenPhong(tenPhong);
            phong.setTenLoaiPhong(loaiPhong);
            phong.setTinhTrangPhong(tinhTrangPhong);
            connection = ConnectingDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);          
            preparedStatement.setString(1, phong.getTenPhong());
            preparedStatement.setInt(2, layMaLoaiPhong(phong.getTenLoaiPhong()));
            preparedStatement.setString(3, phong.getTinhTrangPhong());
             preparedStatement.setInt(4, phong.getMaPhong());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean luuThemPhong(int maPhong, String tenPhong, String loaiPhong,String tinhTrangPhong) {
        try {           
            String sql = "INSERT INTO Phong(MaPhong, TenPhong, MaLoaiPhong, TinhTrangPhong) VALUES (?, ?, ?, ?)";
            Phong phong = new Phong();
            phong.setMaPhong(maPhong);
            phong.setTenPhong(tenPhong);
            phong.setTenLoaiPhong(loaiPhong);
            phong.setTinhTrangPhong(tinhTrangPhong);
            connection = ConnectingDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, phong.getMaPhong());
            preparedStatement.setString(2, phong.getTenPhong());
            preparedStatement.setInt(3, layMaLoaiPhong(phong.getTenLoaiPhong()));
            preparedStatement.setString(4, phong.getTinhTrangPhong());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
   @FXML
    private void luuPhong_Click(ActionEvent event) throws SQLException {
        boolean isTenPhongTrong = validation.KiemTraGiaTriTrong.kiemTraloiGiaTriTrongTextField(txtTenPhong.getText(), errorTenPhong, "Tên không được trống");
        boolean isTenPhongKyTuDacBiet = validation.KiemTraKyTuDacBiet.KiemTraKyTuDacBietTextField(txtTenPhong.getText(), errorTenPhong, "Tên không trống và có ký tự đặc biệt");
        if (flag == true) {
            if (isTenPhongTrong && isTenPhongKyTuDacBiet) {
                int maPhong = Integer.parseInt(txtMaPhong.getText());
                String tenPhong = txtTenPhong.getText();
                String loaiPhong = cbMaLoaiPhong.getValue().toString();
                String tinhTrangPhong = null;
                if (chbTinhTrangPhong.isSelected() == true) {
                    tinhTrangPhong = "Đã Đặt";
                } else {
                    tinhTrangPhong = "Còn Trống";
                }
                if (luuThemPhong(maPhong, tenPhong, loaiPhong, tinhTrangPhong)) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công 1 dòng");
                    tbPhong.getItems().clear();
                    hienThiDuLieuPhong();
                    LamMoi();
                }
            }
        } else {
            if (isTenPhongTrong && isTenPhongTrong) {
                int maPhong = Integer.parseInt(txtMaPhong.getText());
                String tenPhong = txtTenPhong.getText();
                String loaiPhong = cbMaLoaiPhong.getValue().toString();
                String tinhTrangPhong = null;
                if (chbTinhTrangPhong.isSelected() == true) {
                    tinhTrangPhong = "Đã Đặt";
                } else {
                    tinhTrangPhong = "Còn Trống";
                }
                if (luuSuaPhong(maPhong, tenPhong, loaiPhong, tinhTrangPhong)) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công 1 dòng");
                    tbPhong.getItems().clear();
                    hienThiDuLieuPhong();
                    LamMoi();
                }
            }
        }
    }

    @FXML
    private void huyPhong_Click(ActionEvent event) {
        LamMoi();
    }
    private void LamMoi(){
        txtTenPhong.setDisable(true);
        cbMaLoaiPhong.setDisable(true);
        chbTinhTrangPhong.setDisable(true);
        btnThemPhong.setDisable(false);
        btnSuaPhong.setDisable(false);
        btnLuuPhong.setVisible(false);
        btnHuyPhong.setVisible(false);
        btnXoaPhong.setDisable(false);
    }
    private void TimKiemTenPhong(){
        txtTimTenPhong.setOnKeyReleased(e->{
            if(txtTimTenPhong.getText().equals("")){
                tbPhong.getItems().clear();
                hienThiDuLieuPhong();
            }
            else{
               PreparedStatement preparedStatement = null;
               data.clear();
               String sql = "select MaPhong,TenPhong,TenLoaiPhong,TinhTrangPhong from Phong,LoaiPhong Where Phong.MaLoaiPhong= LoaiPhong.MaLoaiPhong and TenPhong LIKE N'%"+txtTimTenPhong.getText()+"%'";
                try {               
                preparedStatement = connection.prepareStatement(sql);
                rs = preparedStatement.executeQuery();
               while(rs.next()){
                data.add(new Phong(
                        rs.getInt(1),
                        rs.getString(2),                                                                 
                        rs.getString(3),
                        rs.getString(4)
                        ));               
                } 
                tbPhong.setItems(data);
                } catch (SQLException ex) {
                Logger.getLogger(FrmPhongController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }        
        });
    }
   @FXML
    private void timKiemLoaiPhong(ActionEvent event) {
        PreparedStatement preparedStatement = null;
        data.clear();
        String sql = "select MaPhong,TenPhong,TenLoaiPhong,TinhTrangPhong from Phong,LoaiPhong Where Phong.MaLoaiPhong= LoaiPhong.MaLoaiPhong and TenLoaiPhong = '"+cbTimMaLoaiPhong.getValue()+"'";
        try {               
        preparedStatement = connection.prepareStatement(sql);
        rs = preparedStatement.executeQuery();
       while(rs.next()){
        data.add(new Phong(
            rs.getInt(1),
            rs.getString(2),                                                                 
            rs.getString(3),
            rs.getString(4)
            ));               
        } 
        tbPhong.setItems(data);
        } catch (SQLException ex) {
           Logger.getLogger(FrmPhongController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void btnVeMenuChinh_Click(ActionEvent event) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        
        try {
            frmTrangChuController.strLoaiNguoiDung = "quanly";
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
    private void isCheckTenPhong(ActionEvent event) {
        if (rdbTenPhong.isSelected() == true) {
            txtTimTenPhong.setDisable(false);
            cbTimMaLoaiPhong.setDisable(true);
        }
    }

    @FXML
    private void isCheckLoaiPhong(ActionEvent event) {
        if (rdbLoaiPhong.isSelected() == true) {
            txtTimTenPhong.setDisable(true);
            cbTimMaLoaiPhong.setDisable(false);
        }
    }

    @FXML
    private void isCheckTatCa(ActionEvent event) {
        if (rdbTatCaPhong.isSelected() == true) {
            txtTimTenPhong.setDisable(true);
            cbTimMaLoaiPhong.setDisable(true);
        }
        hienThiDuLieuPhong();
    }
}
