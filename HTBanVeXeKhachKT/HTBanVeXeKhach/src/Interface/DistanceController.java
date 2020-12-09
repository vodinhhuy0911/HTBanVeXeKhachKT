/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BVXK.QuanLyTuyenDi;
import BVXK.QuanLyXe;
import BanVeXeKhach.TuyenDuong;
import BanVeXeKhach.Xe;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
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
public class DistanceController implements Initializable {

    @FXML
    private TextField txtMaLoTrinh;
            
    @FXML
    private TextField txtTuyenDi;
    
    @FXML
    private TextField txtTuyenDen;
    
    @FXML
    private TableView <TuyenDuong> tvNoiDung;
    
    @FXML
    private TableColumn tcMaLoTrinh;
    
    @FXML
    private TableColumn tcLoTrinh;
    
    @FXML
    private TableColumn tcTuyenDi;
    
    @FXML
    private ComboBox cbXe;
    
    @FXML
    private TableColumn tcTuyenDen;
    
    @FXML
    private TableColumn tcXe;
    
    @FXML
    private DatePicker dpNgayKhoiHanh;
    
    @FXML
    private TextField txtGioKhoiHanh;
    
    @FXML
    private TableColumn tcNgayKhoiHanh;
    
    @FXML
    private TableColumn tcGioKhoiHanh;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            this.loadData();
        } catch (SQLException ex) {
            Logger.getLogger(DistanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
         this.tvNoiDung.setRowFactory((TableView<TuyenDuong> td) ->{
            TableRow row = new TableRow();
            row.setOnMouseClicked((MouseEvent r) -> {
                TuyenDuong t = tvNoiDung.getSelectionModel().getSelectedItem();
                this.txtMaLoTrinh.setText(t.getMaTuyenDuong());
                this.txtTuyenDi.setText(t.getTuyenDi());
               this.txtTuyenDen.setText(t.getTuyenDen());
               this.cbXe.getSelectionModel().select(t.getMaXe());
               
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  //convert String to LocalDate
  String date = String.valueOf(t.getThoiGianKhoiHanh());
  String s[] = date.split("-");
  
  //mdy
  date = s[2] + "/" + s[1] + "/" + s[0];
  LocalDate localDate = LocalDate.parse(date, formatter);
               
               this.dpNgayKhoiHanh.setValue(localDate);
               this.txtGioKhoiHanh.setText(t.getGioKhoiHanh());
            });
            return row;
        });
         List<String> str = new ArrayList<>();
        try {
            List<Xe> xe = QuanLyXe.getXe();
            for(Xe x : xe)
            {
                str.add(x.getBienSoXe());
                ObservableList <String> list = FXCollections.observableArrayList(str);
                    cbXe.setItems(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DistanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void loadData() throws SQLException
    {
        tcMaLoTrinh.setCellValueFactory(new PropertyValueFactory("maTuyenDuong"));
        
        tcTuyenDi.setCellValueFactory(new PropertyValueFactory("tuyenDi"));
        
        tcTuyenDen.setCellValueFactory(new PropertyValueFactory("tuyenDen"));
        tcXe.setCellValueFactory(new PropertyValueFactory("maXe"));
        tcNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory("thoiGianKhoiHanh"));
         tcGioKhoiHanh.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
         this.tvNoiDung.setItems(FXCollections.observableArrayList(QuanLyTuyenDi.getDsTuyenDuong()));
    }
    
    public void themLoTring() throws SQLException, ParseException
    {
        
        LocalDate ngayKhoiHanh = dpNgayKhoiHanh.getValue();
        String date = String.valueOf(ngayKhoiHanh);
  String s[] = date.split("-");
  date = s[2] + "/" + s[1] + "/" + s[0];
        DateFormat sfm = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sfm.parse(date);
        
        TuyenDuong td = new TuyenDuong(txtMaLoTrinh.getText(), txtTuyenDi.getText(), txtTuyenDen.getText(),cbXe.getSelectionModel().getSelectedItem().toString(),d,txtGioKhoiHanh.getText());
        QuanLyTuyenDi.themTuyenDuong(td);
        this.loadData();
    }
    public void capNhatTuyenDuong() throws SQLException, ParseException
    {
        LocalDate ngayKhoiHanh = dpNgayKhoiHanh.getValue();
        String date = String.valueOf(ngayKhoiHanh);
  String s[] = date.split("-");
  date = s[2] + "/" + s[1] + "/" + s[0];
        DateFormat sfm = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sfm.parse(date);
        
        
        QuanLyTuyenDi.capNhatTuyenDuong(txtTuyenDi.getText(), txtTuyenDen.getText(),txtMaLoTrinh.getText(),cbXe.getSelectionModel().getSelectedItem().toString(),
                d,txtGioKhoiHanh.getText());
        this.loadData();
    }
    
    public void xoaTuyenDuong() throws SQLException
    {
        QuanLyTuyenDi.xoaTuyenDuong(txtMaLoTrinh.getText());
        this.loadData();
    }
    public void btExitOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
