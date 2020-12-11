/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BVXK.QuanLyNhanVien;
import BanVeXeKhach.DangNhap;
import BanVeXeKhach.NhanVien;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainController implements Initializable {

    @FXML
    private TextField txtNV;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            txtNV.setText(LoginController.USERNAME);
        
       
    }    
    @FXML
    private void btQuanLyTKOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    
    }
    @FXML
    private void btDangXuatOnAction(ActionEvent e) throws IOException, Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    @FXML
    private void btQuanLyVeOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TicketList.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
         stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void btQuanLyLTOnAction(ActionEvent event) throws IOException {        
        Parent root = FXMLLoader.load(getClass().getResource("DistanceBetw.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
         stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void btQuanLyXeOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Transport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
         stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void btThoatOnAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void btBanVeOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
         stage.centerOnScreen();
        stage.show();
    }
    
}
