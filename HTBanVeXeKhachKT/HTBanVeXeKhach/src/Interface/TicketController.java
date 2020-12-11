/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BVXK.QuanLyTuyenDi;
import BVXK.QuanLyVeXe;
import BVXK.QuanLyXe;
import BanVeXeKhach.TuyenDuong;
import BanVeXeKhach.VeXe;
import BanVeXeKhach.Xe;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class TicketController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     */
    
    @FXML
    private Button bt;
    
    @FXML
    private ComboBox cbLoTrinh;
    
    @FXML
    private ComboBox cbXe;
    
    @FXML
    private TextField txtLoaiXe;
    
    @FXML 
    private TextField txtTenKH;
    
    @FXML
    private TextField txtSdtKH;
    
    @FXML
    private TextField txtMaGhe;
    
    @FXML
    private RadioButton rdTT;
    
    @FXML 
    private ComboBox cbNgayKhoiHanh;
    
    @FXML
    private ComboBox cbGioKhoiHanh;
    
    @FXML
    private TextField txtGiaVe;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> str = new ArrayList<>();
        
        
        //thêm tuyến đi vào combobox tuyến đi
        try {
            // TODO

            List <TuyenDuong> td = QuanLyTuyenDi.getDsTuyenDuong();
            int i = 0;
            for(TuyenDuong t : td)
            {
               str.add(t.getTuyenDi() + " - " + t.getTuyenDen());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
         ObservableList <String> list = FXCollections.observableArrayList(str);
                    cbLoTrinh.setItems(list);
        
          //thêm xe đi vào combobox xe  
          
          str.clear();
       
                    
        
         
    }   
    
    public void chonLoTrinh() throws SQLException
    {
        List <String> s = new ArrayList<>();
        String tuyenDuong = cbLoTrinh.getSelectionModel().getSelectedItem().toString();
        String td[] = tuyenDuong.split(" - ");
        String tuyenDi = td[0];
        String tuyenDen = td[1];
        
        s = QuanLyTuyenDi.getMaXe(tuyenDi, tuyenDen);
        ObservableList <String> list = FXCollections.observableArrayList(s);
                    cbXe.setItems(list);
                    cbXe.getSelectionModel().select(0);
    
    }
    public void chonXe()
    {
        try {
            if(cbXe.getSelectionModel().getSelectedItem().toString() != null)
            {
                txtLoaiXe.setText(QuanLyXe.getLoaiXe(cbXe.getSelectionModel().getSelectedItem().toString()));
                
                ///////////Them ngày khởi hành vào combobox
                List<String> ngayKhoiHanh = new ArrayList<>();
                   String tuyenDuong = cbLoTrinh.getSelectionModel().getSelectedItem().toString();
                String td[] = tuyenDuong.split(" - ");
              String tuyenDi = td[0];
                String tuyenDen = td[1];
                /////////////
                
                ngayKhoiHanh = QuanLyTuyenDi.getNgayKhoiHanh(tuyenDi, tuyenDen, 
                        cbXe.getSelectionModel().getSelectedItem().toString());
                ObservableList <String> list = FXCollections.observableArrayList(ngayKhoiHanh);
                    cbNgayKhoiHanh.setItems(list);
                    cbGioKhoiHanh.setItems(null);
                
            }   
        } catch (SQLException ex ) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NullPointerException ex)
        {
            
        }
    }
    
    public void themVe() throws SQLException, ParseException
    {
        long millis=System.currentTimeMillis();  
        java.util.Date date=new java.util.Date(millis);  
        boolean tt = false;
        if(rdTT.isSelected())
            tt = true;
        
        UUID uuid = UUID.randomUUID();
       String id = uuid.toString().substring(0, 5);
       String d = cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString();
       
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       
       String s[] = d.split("-");
  
  //mdy
             d = s[2] + "/" + s[1] + "/" + s[0];
       
        Date ngayKhoiHanh = formatter.parse(d);

       String giaVe = txtGiaVe.getText();
       double gia = Double.parseDouble(giaVe);
       
      
       
       List <String> str = new ArrayList<>();
        String tuyenDuong = cbLoTrinh.getSelectionModel().getSelectedItem().toString();
        String td[] = tuyenDuong.split(" - ");
        String tuyenDi = td[0];
        String tuyenDen = td[1];
       
         String maLT = QuanLyTuyenDi.getMaLoTrinh(tuyenDi, tuyenDen, cbXe.getSelectionModel().getSelectedItem().toString(),
                 cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString(), cbGioKhoiHanh.getSelectionModel().getSelectedItem().toString());
       
        VeXe ve = new VeXe(id,cbXe.getSelectionModel().getSelectedItem().toString(),"1",txtTenKH.getText(),txtSdtKH.getText(),
        txtMaGhe.getText(),date,tt,ngayKhoiHanh,
        cbGioKhoiHanh.getSelectionModel().getSelectedItem().toString(),gia,maLT);
        QuanLyVeXe.themVe(ve);
    }
    
    public void chonNgay()
    {
         try {
            if(cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString() != null)
            {
               
                
                ///////////Them Giờ khởi hành vào combobox
                List<String> gioKhoiHanh = new ArrayList<>();
                   String tuyenDuong = cbLoTrinh.getSelectionModel().getSelectedItem().toString();
                String td[] = tuyenDuong.split(" - ");
              String tuyenDi = td[0];
                String tuyenDen = td[1];
                /////////////
                //tuyenDi, tuyenden,maxe,ngaykhoihanh
                gioKhoiHanh = QuanLyTuyenDi.getGioKhoiHanh(tuyenDi, tuyenDen,
                        cbXe.getSelectionModel().getSelectedItem().toString(),
                        cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString());
                ObservableList <String> list = FXCollections.observableArrayList(gioKhoiHanh);
                    cbGioKhoiHanh.setItems(list);
                
            }   
        } catch (SQLException ex ) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NullPointerException ex)
        {
            
        }
    }
    
    public void chonGhe(ActionEvent ae)
    {
       
        txtMaGhe.setText(((Button)ae.getSource()).getText());
    }
      public void btExitOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
