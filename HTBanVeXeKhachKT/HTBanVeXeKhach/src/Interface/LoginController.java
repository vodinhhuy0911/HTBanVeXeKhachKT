/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BVXK.QuanLyNhanVien;

import BanVeXeKhach.DangNhap;
import BVXK.Login;
import BanVeXeKhach.NhanVien;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class LoginController implements Initializable {

    static String USERNAME() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private Button btCancel;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwPassword;

    public static  String maNV;
    public static String chucVu;
    public static String user;
    private  String PASSWORD;
    
    
    public void btLoginOnAction(ActionEvent event) throws SQLException, IOException{
        user = Login.login(txtUsername.getText(), pwPassword.getText());
      if(user != null)
      {
          NhanVien nv = QuanLyNhanVien.getNV(txtUsername.getText());
          chucVu = nv.getChucVu();
          maNV = txtUsername.getText();
          Alert alert = new Alert(AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Login success!");
                          alert.showAndWait();
        //Login xong xuất ra main
        
        if(chucVu.compareTo("Quản Trị Viên") == 0)
        {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        }
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("Employee2.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        }
      }
      else
      {
          Alert notiAll = new Alert(AlertType.ERROR);
                      notiAll.setTitle("Notification!");
                      notiAll.setHeaderText(null);
                      notiAll.setContentText("Try again please!!!");
                      notiAll.showAndWait();
      }

    }


   
   public void btCancelOnAction(ActionEvent event){
       Stage stage =(Stage)btCancel.getScene().getWindow();
       stage.close();
   }
   
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
