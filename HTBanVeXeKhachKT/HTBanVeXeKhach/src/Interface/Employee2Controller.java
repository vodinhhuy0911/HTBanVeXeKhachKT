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

    
    ObservableList<NhanVien> nvList = FXCollections.observableArrayList ();
        
    private String USERNAME;
    @FXML
    private TextField txttaiKhoan;
    @FXML
    private TextField txtMatKhau;
    @FXML
    private TableColumn<DangNhap,String> colTaiKhoan;
    @FXML
    private TableColumn<DangNhap,String> colMatKhau;
    @FXML
    private TextField txtTenNV;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load ten nhan vien tren form
            txtTenNV.setText(LoginController.USERNAME);
         
         
         
        try {
            this.loadData();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.tvNV.setRowFactory((TableView<NhanVien> nv) ->{
            TableRow row = new TableRow();
            row.setOnMouseClicked((MouseEvent r) -> {
                NhanVien n = tvNV.getSelectionModel().getSelectedItem();
                this.txtID.setText(n.getTaiKhoan());
                this.txtHoTen.setText(n.getHoTen());
                this.txtNgaySinh.setText(n.getNgaySinh().toString());
                this.txtDiaChi.setText(n.getDiaChi());
                this.txtChucVu.setText(n.getChucVu());
                txtChucVu.setEditable(false);
                this.txtSDT.setText(n.getSdt());
                this.txtEmail.setText(n.getEmail());
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
//        tvNV.getColumns().addAll(colID,colHoTen,colNgaySinh,colDiaChi,colChucVu,colSDT,colEmail);
        try {       
            this.tvNV.setItems(FXCollections.observableArrayList(QuanLyNhanVien.getDsNhanVien()));
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
     public void update() throws SQLException, ParseException 
     {
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
          String s = txtNgaySinh.getText();
          s = s.replace('-', '/');
//           String str[] = s.split("-");
//           String ns = str[2] + "/" +  str[1] + "/" + str[0];
                java.util.Date ngaySinh = formatter.parse(s);
         NhanVien nv = new NhanVien(txtID.getText(), txtHoTen.getText(), ngaySinh, txtDiaChi.getText(), txtChucVu.getText(),txtSDT.getText(), txtEmail.getText(),txtMatKhau.getText());
         QuanLyNhanVien.capNhatNhanVien(nv);
         this.loadData();
     }  
    @FXML
   public void delete (ActionEvent e) throws SQLException{
        QuanLyNhanVien.xoaNhanVien(txtID.getText());
        this.loadData();

    }
   //timkiem
    @FXML
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
    @FXML
   public void huy(ActionEvent e)
    {
           txtID.setText("");
           txtChucVu.setText("");
           txtDiaChi.setText("");
           txtEmail.setText("");
           txtHoTen.setText("");
           txtNgaySinh.setText("");
           txtSDT.setText("");
           
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
}


