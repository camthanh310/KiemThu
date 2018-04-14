/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Data.ConnectingDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
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
public class LapDanhMucPhongController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection connection;
    ObservableList<LoaiPhong> data ;
    //PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    @FXML private TextField txtMaLoaiPhong;
    @FXML private TextField txtTenLoaiPhong;
    @FXML private TextField txtGiaTien;
    @FXML private ComboBox cbSoGiuongNgu;
    @FXML private TableView<LoaiPhong> tbLoaiPhong;
    @FXML private  TableColumn<?,?> colMaLoaiPhong;
    @FXML private  TableColumn<?,?> colTenLoaiPhong;
    @FXML private  TableColumn<?,?> colGiaTien;
    @FXML private  TableColumn<?,?> colSoGiuongNgu;
    @FXML private Label errorTenLoaiPhong;
    @FXML private Label errorGiaTien;
    @FXML
    private Button btnThemLoaiPhong;
    @FXML
    private Button btnSuaLoaiPhong;
    @FXML
    private Button btnXoaLoaiPhong;
    @FXML
    private TextField txtTimTenLoaiPhong;
    @FXML
    private TextField txtTimGiaPhong;
    @FXML
    private RadioButton rdbTenLoaiPhong;
    @FXML
    private RadioButton rdbGiaTien;
    private boolean flag = false;
    @FXML
    private Button btnLuuLoaiPhong;
    @FXML
    private Button btnHuyLoaiPhong;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ToggleGroup timkiemloaiphong;
    @FXML
    private RadioButton rdbTatCaLoaiPhong;
    @FXML
    private Label errorLaSo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbSoGiuongNgu.getItems().removeAll(cbSoGiuongNgu.getItems());
        cbSoGiuongNgu.getItems().addAll("1","2","3");
        cbSoGiuongNgu.getSelectionModel().select("1");
        
        connection = ConnectingDB.getConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        hienThiDuLieuLoaiPhong();
        hienThiGiaTriTuTableView();
        TimKiemTenLoaiPhong();
        TimKiemGiaTien();
        
    }
    
    private void setCellTable(){
        colMaLoaiPhong.setCellValueFactory(new PropertyValueFactory<>("maLoaiPhong"));
        colTenLoaiPhong.setCellValueFactory(new PropertyValueFactory<>("tenLoaiPhong"));
        colGiaTien.setCellValueFactory(new PropertyValueFactory<>("gia"));
        colSoGiuongNgu.setCellValueFactory(new PropertyValueFactory<>("soGiuongNgu"));
    }
    
    public boolean luuXoaLoaiPhong(int maLoaiPhong) {
        try {
            PreparedStatement preparedStatement;
            String sql = "delete from LoaiPhong where MaLoaiPhong=?";
            connection = ConnectingDB.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maLoaiPhong);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    private void hienThiDuLieuLoaiPhong(){
        PreparedStatement preparedStatement = null;
        String query = "select * from LoaiPhong";
        try{
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                data.add(new LoaiPhong(
                        rs.getInt(1),
                        rs.getString(2),                                                                 
                        rs.getFloat(3),
                        rs.getInt(4)
                        ));               
            }                                                
        }
        catch(Exception ex){
            System.err.println(ex);
        }
         tbLoaiPhong.setItems(data);  
    }
    public void hienthiMaLenTextField(){
        PreparedStatement preparedStatement = null;
        int iMaLoaiPhong = 0;
        String strSQL = "SELECT MAX(MaLoaiPhong) as max FROM LoaiPhong";
        try {
            preparedStatement = connection.prepareStatement(strSQL);
            rs = preparedStatement.executeQuery();
            rs.next();
            iMaLoaiPhong = rs.getInt("max");
        } catch (SQLException ex) {
            Logger.getLogger(LapDanhMucPhongController.class.getName()).log(Level.SEVERE, null, ex);
        }     
        txtMaLoaiPhong.setText(iMaLoaiPhong+1 + "");
    }
    private void hienThiGiaTriTuTableView(){
        tbLoaiPhong.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                LoaiPhong lP = tbLoaiPhong.getItems().get(tbLoaiPhong.getSelectionModel().getSelectedIndex());
                txtMaLoaiPhong.setText(String.valueOf(lP.getMaLoaiPhong()));
                txtTenLoaiPhong.setText(lP.getTenLoaiPhong());
                txtGiaTien.setText(String.valueOf(lP.getGia()));
                cbSoGiuongNgu.setValue(String.valueOf(lP.getSoGiuongNgu()));
                
            }           
        });   
    }

    @FXML 
    private void themLoaiPhong_Click(ActionEvent event){
      hienthiMaLenTextField();
      txtTenLoaiPhong.setDisable(false);
      txtGiaTien.setDisable(false);
      cbSoGiuongNgu.setDisable(false);
      btnLuuLoaiPhong.setVisible(true);
      btnHuyLoaiPhong.setVisible(true);
      btnSuaLoaiPhong.setDisable(true);
      btnThemLoaiPhong.setDisable(true);
      flag = true;
    }
    
    private void suaLoaiPhong_Click(ActionEvent event) {
      txtTenLoaiPhong.setDisable(false);
      txtGiaTien.setDisable(false);
      cbSoGiuongNgu.setDisable(false);
      flag = false;
      btnLuuLoaiPhong.setVisible(true);
      btnHuyLoaiPhong.setVisible(true);
      btnSuaLoaiPhong.setDisable(true);
      btnThemLoaiPhong.setDisable(true);
    }

    @FXML
    private void xoaLoaiPhong_Click(ActionEvent event) {
        int maLoaiPhong = Integer.parseInt(txtMaLoaiPhong.getText());
        if (luuXoaLoaiPhong(maLoaiPhong)) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            tbLoaiPhong.getItems().clear();
            hienThiDuLieuLoaiPhong();
            txtGiaTien = null;
            txtMaLoaiPhong = null;
            txtGiaTien = null;
            cbSoGiuongNgu.getSelectionModel().select("1");
        }
    }

    @FXML
    private void luuLoaiPhong_Click(ActionEvent event) throws SQLException {
        boolean isGiaTrong = validation.KiemTraGiaTriTrong.kiemTraloiGiaTriTrongTextField(txtGiaTien.getText(), errorGiaTien, "Giá không trống và là số");
        boolean isGiaLaSo = validation.KiemTraLaSo.kiemTraLoiGiaTriLaSoTextField(txtGiaTien.getText(), errorGiaTien, "Giá không trống và là số");
        boolean isTenLoaiPhongTrong = validation.KiemTraGiaTriTrong.kiemTraloiGiaTriTrongTextField(txtTenLoaiPhong.getText(), errorTenLoaiPhong, "Tên không trống và có ký tự đặc biệt");
        boolean isTenLoaiPhongKyTuDacBiet = validation.KiemTraKyTuDacBiet.KiemTraKyTuDacBietTextField(txtTenLoaiPhong.getText(), errorTenLoaiPhong, "Tên không trống và có ký tự đặc biệt");
        
        if (flag == true) {
            if (isGiaTrong && isTenLoaiPhongTrong && isTenLoaiPhongKyTuDacBiet) {
                if (isGiaLaSo) {
                    int maLoaiPhong = Integer.parseInt(txtMaLoaiPhong.getText());
                    String tenLoaiPhong = txtTenLoaiPhong.getText();
                    float gia = Float.parseFloat(txtGiaTien.getText());
                    int soGiuong = Integer.parseInt(cbSoGiuongNgu.getValue().toString());
                    if (luuThemLoaiPhong(maLoaiPhong, tenLoaiPhong, gia, soGiuong)) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công 1 dòng");
                        tbLoaiPhong.getItems().clear();
                        hienThiDuLieuLoaiPhong();
                    }
                }
            }
        } 
        else {
            if (isGiaTrong && isTenLoaiPhongTrong && isGiaLaSo && isTenLoaiPhongKyTuDacBiet) {
                int maLoaiPhong = Integer.parseInt(txtMaLoaiPhong.getText());
                String tenLoaiPhong = txtTenLoaiPhong.getText();
                float gia = Float.parseFloat(txtGiaTien.getText());
                int soGiuong = Integer.parseInt(cbSoGiuongNgu.getValue().toString());
                if (luuSuaLoaiPhong(maLoaiPhong, tenLoaiPhong, gia, soGiuong)) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công 1 dòng");
                    tbLoaiPhong.getItems().clear();
                    hienThiDuLieuLoaiPhong();
                }
            }
        }
    }

    public boolean luuThemLoaiPhong(int maLoaiPhong, String tenLoaiPhong, float giaTien, int soGiuong) {
        try {
            String sql = "Insert into LoaiPhong(MaLoaiPhong,TenLoaiPhong,Gia,SoGiuongNgu) Values(?,?,?,?)";
            LoaiPhong loaiPhong = new LoaiPhong();
            loaiPhong.setMaLoaiPhong(maLoaiPhong);
            loaiPhong.setTenLoaiPhong(tenLoaiPhong);
            loaiPhong.setGia(giaTien);
            loaiPhong.setSoGiuongNgu(soGiuong);

            connection = ConnectingDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, loaiPhong.getMaLoaiPhong());
            preparedStatement.setString(2, loaiPhong.getTenLoaiPhong());
            preparedStatement.setFloat(3, loaiPhong.getGia());
            preparedStatement.setInt(4, loaiPhong.getSoGiuongNgu());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean luuSuaLoaiPhong(int maLoaiPhong, String tenLoaiPhong, float giaTien, int soGiuong) {
        try {
            PreparedStatement preparedStatement = null;
            String sql = "Update LoaiPhong Set TenLoaiPhong = ?,Gia = ?, SoGiuongNgu = ? Where MaLoaiPhong = ?";
            LoaiPhong loaiPhong = new LoaiPhong();
            loaiPhong.setMaLoaiPhong(maLoaiPhong);
            loaiPhong.setTenLoaiPhong(tenLoaiPhong);
            loaiPhong.setGia(giaTien);
            loaiPhong.setSoGiuongNgu(soGiuong);

            connection = ConnectingDB.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loaiPhong.getTenLoaiPhong());
            preparedStatement.setFloat(2, loaiPhong.getGia());
            preparedStatement.setInt(3, loaiPhong.getSoGiuongNgu());
            preparedStatement.setInt(4, loaiPhong.getMaLoaiPhong());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    @FXML
    private void huyLoaiPhong_Click(ActionEvent event) {
        txtTenLoaiPhong.setDisable(false);
        txtGiaTien.setDisable(false);
        cbSoGiuongNgu.setDisable(false);
        btnThemLoaiPhong.setDisable(false);
        btnSuaLoaiPhong.setDisable(false);
        btnLuuLoaiPhong.setVisible(false);
        btnHuyLoaiPhong.setVisible(false);
    }
    
    private void TimKiemTenLoaiPhong(){
        txtTimTenLoaiPhong.setOnKeyReleased(e->{
            if(txtTimTenLoaiPhong.getText().equals("")){
                tbLoaiPhong.getItems().clear();
                hienThiDuLieuLoaiPhong();
            }
            else{
               PreparedStatement preparedStatement = null;
               data.clear();
               String sql = "Select * from LoaiPhong where TenLoaiPhong LIKE N'%"+txtTimTenLoaiPhong.getText()+"%'";
                try {               
                preparedStatement = connection.prepareStatement(sql);
                rs = preparedStatement.executeQuery();
                while(rs.next()){
                    data.add(new LoaiPhong(
                        rs.getInt(1),
                        rs.getString(2),                                                                 
                        rs.getFloat(3),
                        rs.getInt(4)
                        ));   
                }
                tbLoaiPhong.setItems(data);
                } catch (SQLException ex) {
                Logger.getLogger(LapDanhMucPhongController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }        
        });
    }
    private void TimKiemGiaTien(){
        txtTimGiaPhong.setOnKeyReleased(e->{
            if(txtTimGiaPhong.getText().equals("")){
                tbLoaiPhong.getItems().clear();
                hienThiDuLieuLoaiPhong();
            }
            else{
               PreparedStatement preparedStatement = null;
               data.clear();
               String sql = "Select * from LoaiPhong where Gia = '"+txtTimGiaPhong.getText()+"'";
                try {               
                preparedStatement = connection.prepareStatement(sql);
                rs = preparedStatement.executeQuery();
                while(rs.next()){
                    data.add(new LoaiPhong(
                        rs.getInt(1),
                        rs.getString(2),                                                                 
                        rs.getFloat(3),
                        rs.getInt(4)
                        ));   
                }
                tbLoaiPhong.setItems(data);
                } catch (SQLException ex) {
                Logger.getLogger(LapDanhMucPhongController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }        
        });
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
    
    private void LamMoi() {
        txtTenLoaiPhong.setDisable(true);
        cbSoGiuongNgu.setDisable(true);
        txtGiaTien.setDisable(true);
        btnThemLoaiPhong.setDisable(false);
        btnSuaLoaiPhong.setDisable(false);
        btnLuuLoaiPhong.setVisible(false);
        btnHuyLoaiPhong.setVisible(false);
        btnXoaLoaiPhong.setDisable(false);
    }
    
    @FXML
    private void isCheckTenLoaiPhong(ActionEvent event) {
        if (rdbTenLoaiPhong.isSelected() == true) {
            txtTimTenLoaiPhong.setDisable(false);
            txtTimGiaPhong.setDisable(true);
        }
    }

    @FXML
    private void isCheckGiaTien(ActionEvent event) {
        if (rdbGiaTien.isSelected() == true) {
            txtTimTenLoaiPhong.setDisable(true);
            txtTimGiaPhong.setDisable(false);
        }
    }

    @FXML
    private void isCheckTatCa(ActionEvent event) {
        if (rdbTatCaLoaiPhong.isSelected() == true) {
            txtTimTenLoaiPhong.setDisable(true);
            txtTimGiaPhong.setDisable(true);
        }
        hienThiDuLieuLoaiPhong();
    }
}
