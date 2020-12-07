/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import BVXK.QuanLyNhanVien;
import BanVeXeKhach.NhanVien;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private TextField txtTK;
    @FXML
    private Button btThem;
    
    ObservableList<NhanVien> nvList = FXCollections.observableArrayList ();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    public void add() throws SQLException, ParseException{
        if(!txtID.getText().isEmpty() && !txtHoTen.getText().isEmpty() && !txtNgaySinh.getText().isEmpty() && !txtDiaChi.getText().isEmpty() && !txtChucVu.getText().isEmpty() && !txtSDT.getText().isEmpty() && !txtEmail.getText().isEmpty())
        {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date ngaySinh = formatter.parse(txtNgaySinh.getText());
            NhanVien nv = new NhanVien(txtID.getText(), txtHoTen.getText(), ngaySinh, txtDiaChi.getText(), txtChucVu.getText(),txtSDT.getText(), txtEmail.getText());
            QuanLyNhanVien.themNhanVien(nv);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Thêm thành công.");
                          alert.showAndWait();
              this.loadData();
        }
    }
     public void update() throws SQLException, ParseException 
     {
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date ngaySinh = formatter.parse(txtNgaySinh.getText());
         NhanVien nv = new NhanVien(txtID.getText(), txtHoTen.getText(), ngaySinh, txtDiaChi.getText(), txtChucVu.getText(),txtSDT.getText(), txtEmail.getText());
         QuanLyNhanVien.capNhatNhanVien(nv);
         this.loadData();
     }  
   public void delete (ActionEvent e) throws SQLException{
        QuanLyNhanVien.xoaNhanVien(txtID.getText());
        this.loadData();

    }
   //timkiem
   public void search (ActionEvent e) throws SQLException{
        if(!txtID.getText().isEmpty())
        {
            
            NhanVien nv = QuanLyNhanVien.timKiemNvID(txtID.getText());
            colID.setCellValueFactory(new PropertyValueFactory("taiKhoan"));
            colHoTen.setCellValueFactory(new PropertyValueFactory("hoTen"));
            colNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
            colDiaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));
            colChucVu.setCellValueFactory(new PropertyValueFactory("chucVu"));
            colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));
            colEmail.setCellValueFactory(new PropertyValueFactory("email"));
            this.tvNV.setItems(FXCollections.observableArrayList(nv));
        }
        else
        {
            this.loadData();
        }
        

   }
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
      
}


