/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import BVXK.QuanLyNhanVien;

import BanVeXeKhach.NhanVien;
import static Interface.LoginController.chucVu;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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


public class EmployeeController implements Initializable {

    @FXML
    private TableView<NhanVien> tvNV;
    @FXML
    private TableColumn<NhanVien,String> colID;
    @FXML
    private TableColumn<NhanVien, String> colHoTen;
    @FXML
    private TableColumn<NhanVien, String> colNgaySinh;
    @FXML
    private TableColumn<NhanVien,String> colDiaChi;
    @FXML
    private TableColumn<NhanVien,String> colChucVu;
    @FXML
    private TableColumn<NhanVien,String> colSDT;
    @FXML
    private TableColumn<NhanVien,String> colEmail;
    
    @FXML
    private TableColumn clMatKhau;
    @FXML
    private TextField txtMatKhau;
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
    private ComboBox cbChucVu;
    @FXML
    private TextField txtSDT;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btThem;
    
    ObservableList<NhanVien> nvList = FXCollections.observableArrayList ();
    private String maNhanVien;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> list = FXCollections.observableArrayList("Quản Trị Viên","Nhân Viên");
                    cbChucVu.setItems(list);
        try {
            this.loadData();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.tvNV.setRowFactory((TableView<NhanVien> nv) ->{
            TableRow row = new TableRow();
            row.setOnMouseClicked((MouseEvent r) -> {
                NhanVien n = tvNV.getSelectionModel().getSelectedItem();
                maNhanVien = (n.getTaiKhoan());
                this.txtID.setText(n.getTaiKhoan());
                this.txtHoTen.setText(n.getHoTen());
                this.txtNgaySinh.setText(n.getNgaySinh().toString());
                this.txtDiaChi.setText(n.getDiaChi());
                this.cbChucVu.getSelectionModel().select(n.getChucVu());
                this.txtSDT.setText(n.getSdt());
                this.txtEmail.setText(n.getEmail());
                this.txtMatKhau.setText(n.getMatKhau());
            });
            return row;
        });
       
    }
    
    public void loadData() throws SQLException
    {
       colID.setCellValueFactory(new PropertyValueFactory("taiKhoan"));
        colHoTen.setCellValueFactory(new PropertyValueFactory("hoTen"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));
        colChucVu.setCellValueFactory(new PropertyValueFactory("chucVu"));
        colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        clMatKhau.setCellValueFactory(new PropertyValueFactory("matKhau"));
//        tvNV.getColumns().addAll(colID,colHoTen,colNgaySinh,colDiaChi,colChucVu,colSDT,colEmail);
        try {       
            this.tvNV.setItems(FXCollections.observableArrayList(QuanLyNhanVien.getDsNhanVien()));
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void add() throws SQLException{
        if(!txtID.getText().isEmpty() && !txtHoTen.getText().isEmpty() && !txtNgaySinh.getText().isEmpty() && !txtDiaChi.getText().isEmpty() && !cbChucVu.getSelectionModel().isEmpty() && !txtSDT.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtMatKhau.getText().isEmpty())
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
            try {
                d = d.replace('-', '/');
                String str[] = d.split("/");
             if(str[0].length() == 2)
                d = str[2] + "/" + str[1] + "/" + str[0];
            
                java.util.Date ngaySinh;
                ngaySinh = formatter.parse(d);
                //kiểm tra sdt
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
                //kiểm tra email
                if(!kiemTraEmail(txtEmail.getText()))
                   {
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                  alert.setTitle("Information Login");
                                  alert.setHeaderText(null);
                                  alert.setContentText("Lỗi định dạng email.");
                                  alert.showAndWait();
                                  return;
                   }
    
                NhanVien nv = new NhanVien(txtID.getText(), txtHoTen.getText(), ngaySinh, txtDiaChi.getText(), cbChucVu.getSelectionModel().getSelectedItem().toString(),txtSDT.getText(), txtEmail.getText(),txtMatKhau.getText());
                if(QuanLyNhanVien.getSoLuongNV(txtID.getText())==0)
                {
                    if(QuanLyNhanVien.themNhanVien(nv))
                    {
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                  alert.setTitle("Information Login");
                                  alert.setHeaderText(null);
                                  alert.setContentText("Thêm thành công.");
                                  alert.showAndWait();
                    }
                        else
                       {
                           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                     alert.setTitle("Information Login");
                                     alert.setHeaderText(null);
                                     alert.setContentText("Thêm thất bại. Vui lòng kiểm tra lại thông tin!");
                                     alert.showAndWait();
                       }
                }
                else
                {
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                     alert.setTitle("Information Login");
                                     alert.setHeaderText(null);
                                     alert.setContentText("Id đã tồn tại. Vui lòng chọn id khác");
                                     alert.showAndWait();
                                     return;
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
              this.loadData();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Vui lòng điền đầy đủ thông tin!");
                          alert.showAndWait();
        }
    }
     public void update() throws SQLException
     {
         
          if(!txtID.getText().isEmpty() && !txtHoTen.getText().isEmpty() && !txtNgaySinh.getText().isEmpty() && !txtDiaChi.getText().isEmpty() && !cbChucVu.getSelectionModel().isEmpty() && !txtSDT.getText().isEmpty() && !txtEmail.getText().isEmpty()&& !txtMatKhau.getText().isEmpty())
          {
              if(txtID.getText().equals(maNhanVien))
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
                  NhanVien nv = new NhanVien(txtID.getText(), txtHoTen.getText(), ngaySinh, txtDiaChi.getText(), cbChucVu.getSelectionModel().getSelectedItem().toString(),txtSDT.getText(), txtEmail.getText(),txtMatKhau.getText());
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
                          alert.setContentText("Không thể cập nhật mã nhân viên.");
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
   public void delete (ActionEvent e) throws SQLException{
       if(!txtID.getText().isEmpty())
       {
       Alert alertt = new Alert(Alert.AlertType.CONFIRMATION);
                                  alertt.setTitle("Information Login");
                                  alertt.setHeaderText(null);
                                  alertt.setContentText("Bạn có muốn xóa hay không");
                                  Optional<ButtonType> result =  alertt.showAndWait();
        if(result.get() == ButtonType.OK)
        {
                if(QuanLyNhanVien.xoaNhanVien(txtID.getText()))
                {
                        this.loadData();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                          alert.setTitle("Information Login");
                                          alert.setHeaderText(null);
                                          alert.setContentText("Xóa thành công");
                                          alert.showAndWait();
                                          huy();
                                          return;
                                         
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                          alert.setTitle("Information Login");
                                          alert.setHeaderText(null);
                                          alert.setContentText("Xóa không thành công. Vui lòng kiểm tra lại thông tin!");
                                          alert.showAndWait();
                                          return;
                }
        }
       }
       else
       {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                          alert.setTitle("Information Login");
                                          alert.setHeaderText(null);
                                          alert.setContentText("Vui lòng nhập id cần xóa");
                                          alert.showAndWait();
                                          return;
       }
    }
   //timkiem
   public void search (ActionEvent e) throws SQLException{
        if(!txtKey.getText().isEmpty())
        {
            List<NhanVien> dsnv = new ArrayList<>();
            dsnv = QuanLyNhanVien.timKiemNv(txtKey.getText());
            colID.setCellValueFactory(new PropertyValueFactory("taiKhoan"));
            colHoTen.setCellValueFactory(new PropertyValueFactory("hoTen"));
            colNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
            colDiaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));
            colChucVu.setCellValueFactory(new PropertyValueFactory("chucVu"));
            colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));
            colEmail.setCellValueFactory(new PropertyValueFactory("email"));
            this.tvNV.setItems(FXCollections.observableArrayList(dsnv));
        }
        else
        {
            this.loadData();
        }
        

   }
   public void huy()
    {
           txtID.setText("");
          cbChucVu.getSelectionModel().select(null);
           txtDiaChi.setText("");
           txtEmail.setText("");
           txtHoTen.setText("");
           txtNgaySinh.setText("");
           txtSDT.setText("");
           txtMatKhau.setText("");
           
    }
    public void btExitOnAction(ActionEvent event) throws IOException {
         
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
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


