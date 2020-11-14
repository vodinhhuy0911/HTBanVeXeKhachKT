/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class BTLController implements Initializable {

     @FXML
    private Label lblTitle;
    @FXML
    private TextField txtUser;  
    @FXML
    private PasswordField pwdPass;  
    @FXML
    private Button btnLogin;
    
    private final String USERNAME = "administrators";
    private final String PASSWORD = "abc123";
    private final String CSSERRFIELD = "txtFieldError";


    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String username = txtUser.getText();
        String password = pwdPass.getText();
        
        if (username != null && username .equals(USERNAME)){
            if (password != null && password.equals(PASSWORD))
            {
                validationStyle(true, true);
                Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông tin đăng nhập");
                    alert.setHeaderText(null);
                    alert.setContentText("Đăng nhập thành công!");
                    alert.showAndWait();
            } 
            else 
            {
                validationStyle(true, false);
            }
        } 
        else 
        {
            validationStyle(false, false);
        }
    }
    
    public void validationStyle(boolean user, boolean pass){
        if (user){
            txtUser.getStyleClass().removeAll("txtFieldError");
            txtUser.getStyleClass().add("txtField");
        }
        else{
            txtUser.getStyleClass().add("txtFieldError");
        }
        
        if (pass){
            pwdPass.getStyleClass().removeAll("txtFieldError");
            pwdPass.getStyleClass().add("txtField");
        }else{
            pwdPass.getStyleClass().add("txtFieldError");
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
