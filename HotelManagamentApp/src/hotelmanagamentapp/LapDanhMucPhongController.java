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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    @FXML private Label errorMaLoaiPhong;
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
    @FXML
    private ToggleGroup timkiem;
    private boolean flag = false;
    @FXML
    private Button btnLuuLoaiPhong;
    @FXML
    private Button btnHuyLoaiPhong;
    
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
    private void hienthiMaLenTextField(){
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
    private boolean kiemTraGiaTriTrongTextField(TextField tF){
        boolean bl = false;
        if(tF.getText().length() != 0 || !tF.getText().isEmpty()){
            bl = true;
        }
        return bl;
    }
    private boolean kiemTraloiGiaTriTrongTextField(TextField tF, Label lB, String error){
        boolean bl = true;
        String msg = null;
        if(!kiemTraGiaTriTrongTextField(tF)){
            bl = false;
            msg = error;
        }
        lB.setText(msg);
        
        return bl;
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
    
    @FXML
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
        PreparedStatement preparedStatement = null;
        String sql = "delete from LoaiPhong where MaLoaiPhong=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(txtMaLoaiPhong.getText()));
            int i = preparedStatement.executeUpdate();
            if(i == 1){
                JOptionPane.showMessageDialog(null, "Xóa thành công"); 
                tbLoaiPhong.getItems().clear();
                hienThiDuLieuLoaiPhong();
                txtGiaTien = null;
                txtMaLoaiPhong = null;
                txtGiaTien = null;
                cbSoGiuongNgu.getSelectionModel().select("1");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LapDanhMucPhongController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void isCheckTenLoaiPhong(ActionEvent event) {
        if (rdbTenLoaiPhong.isSelected()== true){
        txtTimTenLoaiPhong.setDisable(false);
        txtTimGiaPhong.setDisable(true);
        }
    }

    @FXML
    private void isCheckGiaTien(ActionEvent event) {
        if (rdbGiaTien.isSelected()== true){
        txtTimTenLoaiPhong.setDisable(true);
        txtTimGiaPhong.setDisable(false);
        }
    }

    @FXML
    private void luuLoaiPhong_Click(ActionEvent event) throws SQLException {
        if(flag == true){
            PreparedStatement preparedStatement = null;
            String sql = "Insert into LoaiPhong(MaLoaiPhong,TenLoaiPhong,Gia,SoGiuongNgu) Values(?,?,?,?)";
            LoaiPhong loaiPhong = new LoaiPhong();
            loaiPhong.setMaLoaiPhong(Integer.parseInt(txtMaLoaiPhong.getText()));
            loaiPhong.setTenLoaiPhong(txtTenLoaiPhong.getText());
            loaiPhong.setGia(Float.parseFloat(txtGiaTien.getText()));
            loaiPhong.setSoGiuongNgu(Integer.parseInt(cbSoGiuongNgu.getValue().toString()));

            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, loaiPhong.getMaLoaiPhong());
                preparedStatement.setString(2, loaiPhong.getTenLoaiPhong());
                preparedStatement.setFloat(3, loaiPhong.getGia());
                preparedStatement.setInt(4, loaiPhong.getSoGiuongNgu());
                int i = preparedStatement.executeUpdate();
                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công 1 dòng");
                    tbLoaiPhong.getItems().clear();
                    hienThiDuLieuLoaiPhong();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LapDanhMucPhongController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                preparedStatement.close();
            }
        }
        else{
            PreparedStatement preparedStatement = null;
            String sql = "Update LoaiPhong Set TenLoaiPhong = ?,Gia = ?, SoGiuongNgu = ? Where MaLoaiPhong = ?";
            LoaiPhong loaiPhong = new LoaiPhong();
            loaiPhong.setMaLoaiPhong(Integer.parseInt(txtMaLoaiPhong.getText()));
            loaiPhong.setTenLoaiPhong(txtTenLoaiPhong.getText());
            loaiPhong.setGia(Float.parseFloat(txtGiaTien.getText()));
            loaiPhong.setSoGiuongNgu(Integer.parseInt(cbSoGiuongNgu.getValue().toString()));
            try {

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, loaiPhong.getTenLoaiPhong());
                preparedStatement.setFloat(2, loaiPhong.getGia());
                preparedStatement.setInt(3, loaiPhong.getSoGiuongNgu());
                preparedStatement.setInt(4, loaiPhong.getMaLoaiPhong());

                int i = preparedStatement.executeUpdate();
                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công 1 dòng");
                    tbLoaiPhong.getItems().clear();
                    hienThiDuLieuLoaiPhong();
                }

            } catch (SQLException ex) {
                Logger.getLogger(LapDanhMucPhongController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        txtGiaTien.setOnKeyReleased(e->{
            if(txtGiaTien.getText().equals("")){
                tbLoaiPhong.getItems().clear();
                hienThiDuLieuLoaiPhong();
            }
            else{
               PreparedStatement preparedStatement = null;
               data.clear();
               String sql = "Select * from LoaiPhong where Gia = '"+txtTimTenLoaiPhong.getText()+"'";
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

}
