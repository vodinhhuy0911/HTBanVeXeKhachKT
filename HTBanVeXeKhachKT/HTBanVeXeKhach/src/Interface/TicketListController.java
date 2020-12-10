/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BVXK.QuanLyTuyenDi;
import BVXK.QuanLyVeXe;
import BanVeXeKhach.TuyenDuong;
import BanVeXeKhach.VeXe;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
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
public class TicketListController implements Initializable {

    
    @FXML
    private TextField txtGiaVe;

    @FXML
    private TableView<VeXe> tvThongTin;

    @FXML
    private TextField txtIDXe;

    @FXML
    private TextField txtIDVe;

    @FXML
    private ComboBox cbNgayKH;

    @FXML
    private TextField txtIDNV;

    @FXML
    private TextField txtViTriGhe;

    @FXML
    private TextField txtSDT;

    @FXML
    private RadioButton rdTT;

    @FXML
    private ComboBox cbIDLT;

    @FXML
    private TextField txtNgayBook;

    @FXML
    private ComboBox cbGioKH;

    @FXML
    private TextField txtKH;
    
    @FXML
    private TableColumn txtMaVe;
    
    @FXML
    private TableColumn clMaVe;
    
    @FXML
    private TableColumn clMaNV;
    
    @FXML
    private TableColumn clMaXe;
    
    @FXML
    private TableColumn clTKH;
    
    @FXML
    private TableColumn clSDT;
    
    @FXML
    private TableColumn clViTriGhe;
    
    @FXML
    private TableColumn clGiaVe;
    
    @FXML
    private TableColumn clNgayDat;
    
    @FXML
    private TableColumn clGioKH;
    
    @FXML
    private TableColumn clNgayKH;
    
    @FXML
    private TableColumn clTT;
    
    @FXML
    private TableColumn clMaLT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        ObservableList <String> list = FXCollections.observableArrayList("Xe giường nằm","Xe 40 chỗ ngồi","Xe limo house");
//                    cbLoaiXe.setItems(list);
//                try {
//                    this.loadData();
//                } catch (SQLException ex) {
//                    Logger.getLogger(TransportController.class.getName()).log(Level.SEVERE, null, ex);
//                }
                 this.tvThongTin.setRowFactory((TableView<VeXe> vx) ->{
            TableRow row = new TableRow();
            row.setOnMouseClicked((MouseEvent r) -> {
                VeXe x = tvThongTin.getSelectionModel().getSelectedItem();
                
                this.txtIDVe.setText(x.getMaVe());
                this.txtIDNV.setText(x.getMaNV());
                this.txtIDXe.setText(x.getBienSoXe());
                this.txtKH.setText(x.getHoTenKH());
                this.txtSDT.setText(x.getSdtKH());
                this.txtNgayBook.setText(String.valueOf(x.getThoiGianDatVe()));
                this.txtViTriGhe.setText(x.getMaGheNgoi());
                this.txtGiaVe.setText(String.valueOf(x.getGiaVe()));
                if(x.isIsThanhToan())
                    this.rdTT.setSelected(true);
                else
                    this.rdTT.setSelected(false);
                ObservableList <String> list;
                list = FXCollections.observableArrayList(String.valueOf(x.getNgayKhoiHanh()));
                cbNgayKH.setItems(list);
                list = FXCollections.observableArrayList(x.getGioKhoiHanh());
                cbGioKH.setItems(list);
              
                //thêm vào combobox mã lộ trình
                
                List<String> str = new ArrayList<>();
                try {
                    List <TuyenDuong> td = QuanLyTuyenDi.getDsTuyenDuong();
            int i = 0;
            for(TuyenDuong t : td)
            {
               str.add(t.getTuyenDi() + " - " + t.getTuyenDen());
            }
                    list = FXCollections.observableArrayList(str);
                    cbIDLT.setItems(list);
                } catch (SQLException ex) {
                    Logger.getLogger(TicketListController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    String sss = null;
                try {
                    sss = QuanLyTuyenDi.getTuyenDuong(x.getMaLoTrinh());
                } catch (SQLException ex) {
                    Logger.getLogger(TicketListController.class.getName()).log(Level.SEVERE, null, ex);
                }
               this.cbIDLT.getSelectionModel().select(sss);
            });
            return row;
        });
        
        
        
        try {
            // TODO

            this.loadData();
        } catch (SQLException ex) {
            Logger.getLogger(TicketListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
     public void btExitOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
     
     public void loadData() throws SQLException
     {
          clMaVe.setCellValueFactory(new PropertyValueFactory("maVe"));
        clMaNV.setCellValueFactory(new PropertyValueFactory("maNV"));
        clMaLT.setCellValueFactory(new PropertyValueFactory("maLoTrinh"));
        clMaXe.setCellValueFactory(new PropertyValueFactory("bienSoXe"));
        clTKH.setCellValueFactory(new PropertyValueFactory("hoTenKH"));
        clSDT.setCellValueFactory(new PropertyValueFactory("sdtKH"));
        clViTriGhe.setCellValueFactory(new PropertyValueFactory("maGheNgoi"));
        clGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        clNgayDat.setCellValueFactory(new PropertyValueFactory("thoiGianDatVe"));
        clGioKH.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
        clNgayKH.setCellValueFactory(new PropertyValueFactory("ngayKhoiHanh"));
        clTT.setCellValueFactory(new PropertyValueFactory("isThanhToan"));
             this.tvThongTin.setItems(FXCollections.observableArrayList(QuanLyVeXe.getDsVexe()));
     }
}
