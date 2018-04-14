/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import DTO.*;
import DAO.*;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author TTC
 */
public class frmLapBaoCaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ComboBox<String> cboKieuXem;
    @FXML
    private ComboBox<String> cboKieuXemChiTiet;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            loadData();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        cboKieuXem.getItems().add("Tất cả");
        cboKieuXem.getItems().add("Theo năm");
        cboKieuXem.getItems().add("Theo quý");
        cboKieuXem.getSelectionModel().select(0);
        cboKieuXemChiTiet.setVisible(false);
    }    
    @FXML 
    private Pane paneView;
    
    @FXML
    private void cboKieuXem_SelectedChange(ActionEvent event) {
        if(cboKieuXem.getSelectionModel().getSelectedIndex() > 0) {
            cboKieuXemChiTiet.setVisible(true);
            
            if(cboKieuXem.getSelectionModel().getSelectedIndex() == 1) {
                cboKieuXemChiTiet.getItems().clear();
                ObservableList<String> dsNam = BaoCaoDAO.layCacNamLapBaoCao();
                cboKieuXemChiTiet.getItems().addAll(dsNam);
                cboKieuXemChiTiet.getSelectionModel().select(0);
            }
            else {
                cboKieuXemChiTiet.getItems().clear();
                ObservableList<String> dsNam = BaoCaoDAO.layCacNamLapBaoCao();
                cboKieuXemChiTiet.getItems().addAll("Quý 1", "Quý 2", "Quý 3", "Quý 4");
                cboKieuXemChiTiet.getSelectionModel().select(0);
            }
        }
        else {
            cboKieuXemChiTiet.setVisible(false);
            loadData();
        }
    }
    
    @FXML
    private void cboKieuXemChiTiet_SelectedChange(ActionEvent event) {
        loadData(cboKieuXemChiTiet.getValue().toString());
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
    
    private void loadData(){
        try {
            paneView.getChildren().clear();
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Tháng-năm");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Tổng tiền");
            BarChart tongTienChart = new BarChart(xAxis, yAxis);
            //tongTienChart.setTitle("Tong Tien Hoa Don");
            XYChart.Series series = new XYChart.Series();
            series.setName("Doanh thu");

            ObservableList<BaoCaoDTO> dsBaoCao = BaoCaoDAO.layDanhSachBaoCao();
            for (BaoCaoDTO baoCao : dsBaoCao) {
                series.getData().add(new XYChart.Data<>(baoCao.getThoiGian(), Double.parseDouble(baoCao.getTongTien())));
            }
            tongTienChart.getData().add(series);
            paneView.getChildren().add(tongTienChart);  
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private void loadData(String strDieuKien){
        try {
            paneView.getChildren().clear();
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Tháng-năm");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Tổng tiền");
            BarChart tongTienChart = new BarChart(xAxis, yAxis);
            //tongTienChart.setTitle("Tong Tien Hoa Don");
            XYChart.Series series = new XYChart.Series();
            series.setName("Doanh thu");

            ObservableList<BaoCaoDTO> dsBaoCao = BaoCaoDAO.layDanhSachBaoCao(strDieuKien);
            for (BaoCaoDTO baoCao : dsBaoCao) {
                series.getData().add(new XYChart.Data<>(baoCao.getThoiGian(), Double.parseDouble(baoCao.getTongTien())));
            }
            tongTienChart.getData().add(series);
            paneView.getChildren().add(tongTienChart);  
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
