/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import Data.ConnectingDB;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    
    private int layMaLoaiPhong(String tenLoaiPhong) throws SQLException{
        PreparedStatement preparedStatement = null;
        String strSQL = "SELECT MaLoaiPhong FROM LoaiPhong WHERE TenLoaiPhong=?";
        preparedStatement = connection.prepareStatement(strSQL);
        preparedStatement.setString(1, tenLoaiPhong);
        rs = preparedStatement.executeQuery();
        rs.next();
        int iMaLoaiPhong = rs.getInt("MaLoaiPhong");
        return iMaLoaiPhong;
    }
    
    @FXML
    private void xoaPhong_Click(ActionEvent event) {
        PreparedStatement preparedStatement = null;
        String sql = "delete from Phong where MaPhong=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(txtMaPhong.getText()));
            int i = preparedStatement.executeUpdate();
            if(i == 1){
                JOptionPane.showMessageDialog(null, "Xóa thành công"); 
                tbPhong.getItems().clear();
                hienThiDuLieuPhong();
                txtMaPhong = null;
                txtTenPhong = null;
                chbTinhTrangPhong = null;
                cbMaLoaiPhong.getSelectionModel().select(1);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(FrmPhongController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void isCheckTenPhong(ActionEvent event) {
        if (rdbTenPhong.isSelected()== true){
        txtTimTenPhong.setDisable(false);
        cbTimMaLoaiPhong.setDisable(true);
        }
    }

    @FXML
    private void isCheckLoaiPhong(ActionEvent event) {
        if (rdbLoaiPhong.isSelected()== true){
        txtTimTenPhong.setDisable(true);
        cbTimMaLoaiPhong.setDisable(false);
        }
    }

    @FXML
    private void luuPhong_Click(ActionEvent event) throws SQLException {
        if(flag == true){
            PreparedStatement prepare = null;
            String sql = "INSERT INTO Phong(MaPhong, TenPhong, MaLoaiPhong, TinhTrangPhong) VALUES (?, ?, ?, ?)";
            Phong phong = new Phong();
            phong.setMaPhong(Integer.parseInt(txtMaPhong.getText()));
            phong.setTenPhong(txtTenPhong.getText());
//      String str=cbMaLoaiPhong.getSelectionModel().getSelectedItem().toString();
//      phong.setMaLoaiPhong(Integer.parseInt(cbMaLoaiPhong.getValue().toString()));
//int iMa=layMaLoaiPhong(cbMaLoaiPhong.getSelectionModel().getSelectedItem().toString());
            phong.setTenLoaiPhong(cbMaLoaiPhong.getValue().toString());
            if (chbTinhTrangPhong.isSelected() == true) {
                phong.setTinhTrangPhong("Đã Đặt");
            } else {
                phong.setTinhTrangPhong("Còn Trống");
            }
            try {

                prepare = connection.prepareStatement(sql);
                prepare.setInt(1, phong.getMaPhong());
                prepare.setString(2, phong.getTenPhong());
                prepare.setInt(3, layMaLoaiPhong(phong.getTenLoaiPhong()));
                String q = phong.getTinhTrangPhong();
                prepare.setString(4, q);
                int i = prepare.executeUpdate();
                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công 1 dòng");
                    tbPhong.getItems().clear();
                    hienThiDuLieuPhong();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmPhongController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                prepare.close();
            }
        }
        else{
            PreparedStatement preparedStatement = null;
            String sql = "Update Phong Set TenPhong = ?,MaLoaiPhong = ?, TinhTrangPhong = ? Where MaPhong = ?";
            Phong phong = new Phong();
            phong.setTenPhong(txtTenPhong.getText());
            phong.setTenLoaiPhong(cbMaLoaiPhong.getValue().toString());
            if (chbTinhTrangPhong.isSelected() == true) {
                phong.setTinhTrangPhong("Đã Đặt");
            } else {
                phong.setTinhTrangPhong("Còn Trống");
            }
            phong.setMaPhong(Integer.parseInt(txtMaPhong.getText()));
            try {

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, phong.getTenPhong());
                preparedStatement.setInt(2, layMaLoaiPhong(phong.getTenLoaiPhong()));
                preparedStatement.setString(3, phong.getTinhTrangPhong());
                preparedStatement.setInt(4, phong.getMaPhong());

                int i = preparedStatement.executeUpdate();
                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công 1 dòng");
                    tbPhong.getItems().clear();
                    hienThiDuLieuPhong();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmPhongController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void huyPhong_Click(ActionEvent event) {
        txtTenPhong.setDisable(true);
        cbMaLoaiPhong.setDisable(true);
        chbTinhTrangPhong.setDisable(true);
        btnThemPhong.setDisable(false);
        btnSuaPhong.setDisable(false);
        btnLuuPhong.setVisible(false);
        btnHuyPhong.setVisible(false);
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
    private void TimKiemLoaiPhong(){
        
    }
}
