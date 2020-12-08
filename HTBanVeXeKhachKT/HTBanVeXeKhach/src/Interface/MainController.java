/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class MainController implements Initializable {

    @FXML
    private Button btQuanLyTK;
    @FXML
    private Button btDangXuat;
    @FXML
    private Button btQuanLyVe;
    @FXML
    private Button btQuanLyLT;
    @FXML
    private Button btQuanLyXe;
    @FXML
    private Button btThoat;
    @FXML
    private Button btBanVe;
    @FXML
    private BorderPane main;
    @FXML
    private Pane load;
    @FXML
    private TextField txtNV;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btQuanLyTKOnAction(ActionEvent event) throws IOException {

    }

    @FXML
    private void btDangXuatOnAction(ActionEvent event) {
    }

    @FXML
    private void btQuanLyVeOnAction(ActionEvent event) {
    }

    @FXML
    private void btQuanLyLTOnAction(ActionEvent event) {
    }

    @FXML
    private void btQuanLyXeOnAction(ActionEvent event) {
    }

    @FXML
    private void btThoatOnAction(ActionEvent event) {
    }

    @FXML
    private void btBanVeOnAction(ActionEvent event) {
    }
    
}
