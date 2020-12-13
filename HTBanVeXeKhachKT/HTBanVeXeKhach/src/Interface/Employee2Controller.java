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
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */


public class Employee2Controller implements Initializable {

    
    @FXML
    private TextField txtKey;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtNgaySinh;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private TextField txtChucVu;
    @FXML
    private TextField txtSDT;
    @FXML
    private TextField txtEmail;

    
    
        
    private String USERNAME;
    @FXML
    private TextField txttaiKhoan;
    @FXML
    private TextField txtMatKhau;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //Load ten nhan vien tren form
            this.loadData();
        } catch (SQLException ex) {
            Logger.getLogger(Employee2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
       
    }
    
    public void loadData() throws SQLException
    {
        NhanVien nv = QuanLyNhanVien.getNV(LoginController.maNV);
//        NhanVien nv = QuanLyNhanVien.getNV("1");
     
        txtID.setText(nv.getTaiKhoan());
        txtHoTen.setText(nv.getHoTen());
        txtChucVu.setText(nv.getChucVu());
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        txtMatKhau.setText(nv.getMatKhau());
        txtNgaySinh.setText(nv.getNgaySinh().toString());
        txtSDT.setText(nv.getSdt());
    }
    @FXML
     public void update() throws SQLException, ParseException 
     {
            if(!txtID.getText().isEmpty() && !txtHoTen.getText().isEmpty() && !txtNgaySinh.getText().isEmpty() && !txtDiaChi.getText().isEmpty() && !txtChucVu.getText().isEmpty() && !txtSDT.getText().isEmpty() && !txtEmail.getText().isEmpty()&& !txtMatKhau.getText().isEmpty())
          {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
          String d = txtNgaySinh.getText();
          for(int i = 0; i < d.length(); i++)
                if(Character.isLetter(d.charAt(i)))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                  alert.setTitle("Information Login");
                                  alert.setHeaderText(null);
                                  alert.setContentText("Lỗi nhập ngày tháng. Vui lòng kiểm tra lại!");
                                  alert.showAndWait();
                                  return;
                }
            d = d.replace('-', '/');
                String str[] = d.split("/");
             if(str[0].length() == 2)
                d = str[2] + "/" + str[1] + "/" + str[0];
            
              try {
            
                java.util.Date ngaySinh;
                  ngaySinh = formatter.parse(d);
                   for(int i = 0; i < txtSDT.getText().length(); i++)
                    if((!Character.isDigit(txtSDT.getText().charAt(i))) || (txtSDT.getText().length() < 10 || txtSDT.getText().length() > 11))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                      alert.setTitle("Information Login");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Nhập số điện thoại không hợp lệ. Vui lòng kiểm tra lại!");
                                      alert.showAndWait();
                                      return;
                    }
                   
                   if(!kiemTraEmail(txtEmail.getText()))
                   {
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                  alert.setTitle("Information Login");
                                  alert.setHeaderText(null);
                                  alert.setContentText("Lỗi định dạng email.");
                                  alert.showAndWait();
                                  return;
                   }
             
                  NhanVien nv = new NhanVien(txtID.getText(), txtHoTen.getText(), ngaySinh, txtDiaChi.getText(), txtChucVu.getText(),txtSDT.getText(), txtEmail.getText(),txtMatKhau.getText());
         if(QuanLyNhanVien.capNhatNhanVien(nv))
         {
                this.loadData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Cập nhật thành công.");
                          alert.showAndWait();
         }
         else
         {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Cập nhật không thành công. Vui lòng kiểm tra lại thông tin.");
                          alert.showAndWait();
         }
              } catch (ParseException ex) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Vui lòng nhập thông tin ngày tháng hợp lệ.");
                          alert.showAndWait();
              }
              catch(ArrayIndexOutOfBoundsException a)
            {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Vui lòng nhập thông tin ngày tháng hợp lệ.");
                          alert.showAndWait();
            }
         
          }
          else{
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Vui lòng điền đầy đủ thông tin!");
                          alert.showAndWait();
          }
     }  
    @FXML
   public void delete (ActionEvent e) throws SQLException{
       

    }
   //timkiem
    @FXML
   public void search (ActionEvent e) throws SQLException{
        
        

   }
    @FXML
    public void btExit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    private void btBanVeOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    private boolean kiemTraEmail(String email)
    {
         if(email.indexOf("@")== -1)
                {
                    return false;
                }
                else if (email.indexOf("@")!= -1 && (email.indexOf("@") + 1) == email.length())
                {
                    return false;
                }
         return true;
    }
}


