/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.net.URL;
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

    private final String USERNAME = "dk";
    private final String PASSWORD = "1";
    
    
    public void btLoginOnAction(ActionEvent event){
      String username = txtUsername.getText();
      String password = pwPassword.getText();
      
      if(username != null && username.equals(USERNAME)){
          if (password != null && password.equals(PASSWORD)){
           
               Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Login");
                    alert.setHeaderText(null);
                    alert.setContentText("Login success!");
                    alert.showAndWait();
          }
           else
          {
   
            Alert notiPass = new Alert(AlertType.ERROR);
                notiPass.setTitle("Notification!");
                notiPass.setHeaderText(null);
                notiPass.setContentText("Wrong password!!!");
                notiPass.showAndWait();
            
          }}
      else {
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
