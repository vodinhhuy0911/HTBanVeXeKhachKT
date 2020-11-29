/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<NhanVien,String> colTK;
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
    
    ObservableList<NhanVien> nvList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colChucVu.setCellValueFactory(new PropertyValueFactory<>("chucVu"));
        colSDT.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colTK.setCellValueFactory(new PropertyValueFactory<>("taiKhoan"));
        tvNV.setItems(nvList);
        
       
    }
    public void add(){
        btThem.setOnAction((ActionEvent e) -> {
            if(
                    txtID.getText().isEmpty()
                    || txtHoTen.getText().isEmpty()
                    || txtNgaySinh.getText().isEmpty()
                    || txtDiaChi.getText().isEmpty()
                    || txtChucVu.getText().isEmpty()
                    || txtSDT.getText().isEmpty()
                    || txtEmail.getText().isEmpty()
                    || txtTK.getText().isEmpty()
                    )
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Xin vui lòng nhập đầy đủ thông tin!");
                alert.showAndWait();
            }
            
            else
            {
                NhanVien nv = new NhanVien();
                nv.setID(txtID.getText());
                nv.setHoTen(txtHoTen.getText());
                nv.setNgaySinh(txtNgaySinh.getText());
                nv.setDiaChi(txtDiaChi.getText());
                nv.setChucVu(txtChucVu.getText());
                nv.setSdt(txtSDT.getText());
                nv.setEmail(txtEmail.getText());
                nv.setTaiKhoan(txtTK.getText());
                nvList.add(nv);
                
                txtID.clear();
                txtHoTen.clear();
                txtNgaySinh.clear();
                txtDiaChi.clear();
                txtChucVu.clear();
                txtSDT.clear();
                txtEmail.clear();
                txtTK.clear();
                

            }
        }
            );
    }
   public void delete (ActionEvent e){
        NhanVien selected = tvNV.getSelectionModel().getSelectedItem();
        nvList.remove(selected);

    }
}

      

