/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagamentapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author TTC
 */
public class HotelManagamentApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("frmPhong.fxml"));
        
        Scene scene = new Scene(root);
//        AnchorPane.setBottomAnchor(root, 20.0);
//        AnchorPane.setLeftAnchor(root, 60.0);
//        AnchorPane.setRightAnchor(root, 20.0);
//        AnchorPane.setTopAnchor(root, 20.0);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
