/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BVXK.ThongTinTaiKhoan;
import BanVeXeKhach.DangNhap;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    @FXML
    private Button btCancel;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwPassword;

    private  String USERNAME = "dk";
    private  String PASSWORD = "1";
    
    
    public void btLoginOnAction(ActionEvent event) throws SQLException{
      String username = txtUsername.getText();
      String password = pwPassword.getText();
      List <DangNhap> ds = ThongTinTaiKhoan.getThongTin();
      boolean flag = false;
      for(DangNhap dn : ds)
      {
          USERNAME = dn.getTaiKhoan();
          PASSWORD = dn.getMatKhau();
          if(password != "" && username != "")
            if(password.equals(PASSWORD) && username.equals(USERNAME)){
                flag = true;
               }

        }
      if(flag)
      {
          Alert alert = new Alert(AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Login success!");
                          alert.showAndWait();
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
