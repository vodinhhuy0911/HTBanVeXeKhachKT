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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Alert;
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
    private ComboBox cbGheNgoi;
    
    @FXML
    private TextField txtLoaiXe;
    
    @FXML
    private Button btThem;
    @FXML 
    private TextField txtTenKH;
    @FXML
    private RadioButton rdLayVe;
    @FXML
    private TextField txtSdtKH;
    
    
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
    
    public void themVe() throws ParseException
    {
        if(!txtTenKH.getText().isEmpty()|| !txtSdtKH.getText().isEmpty() || !txtGiaVe.getText().isEmpty())
        {
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
           LocalDateTime now = LocalDateTime.now();  
          String ngay = cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString();
          String gio = cbGioKhoiHanh.getSelectionModel().getSelectedItem().toString();
          String s1 = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth() +" ";
        s1 += (now.getHour() +1)+ ":" + now.getMinute() + ":" + now.getSecond();
          String ngayGio = ngay + " " + gio;    
          if(ngayGio.compareTo(s1) >= 0)
          {
        
        long millis=System.currentTimeMillis();  
        java.util.Date date=new java.util.Date(millis);  
        boolean tt = false;
        boolean layVe = false;
        if(rdTT.isSelected())
            tt = true;
        if(rdLayVe.isSelected())
            layVe = true;
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
       
         String maLT;
             try {
                 maLT = QuanLyTuyenDi.getMaLoTrinh(tuyenDi, tuyenDen, cbXe.getSelectionModel().getSelectedItem().toString(),
                         cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString(), cbGioKhoiHanh.getSelectionModel().getSelectedItem().toString());
                  for(int i = 0; i < txtSdtKH.getText().length(); i++)
                    if((!Character.isDigit(txtSdtKH.getText().charAt(i))) || (txtSdtKH.getText().length() < 10 || txtSdtKH.getText().length() > 11))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                      alert.setTitle("Information Login");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Nhập số điện thoại không hợp lệ. Vui lòng kiểm tra lại!");
                                      alert.showAndWait();
                                      return;
                    }
                  for(int i = 0; i < txtGiaVe.getText().length(); i++)
                    if((!Character.isDigit(txtGiaVe.getText().charAt(i))))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                      alert.setTitle("Information Login");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Nhập giá vé không hợp lệ. Vui lòng kiểm tra lại!");
                                      alert.showAndWait();
                                      return;
                    }
        VeXe ve = new VeXe(id,cbXe.getSelectionModel().getSelectedItem().toString(),"1",txtTenKH.getText(),txtSdtKH.getText(),
        cbGheNgoi.getSelectionModel().getSelectedItem().toString(),date,tt,ngayKhoiHanh,
        cbGioKhoiHanh.getSelectionModel().getSelectedItem().toString(),gia,maLT,layVe);
        if(QuanLyVeXe.themVe(ve))
        {
                                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                      alert.setTitle("Information Login");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Thêm vé thành công");
                                      alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                      alert.setTitle("Information Login");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Thêm vé không thành công. Vui lòng kiểm tra lại thông tin.");
                                      alert.showAndWait();
        }
             } catch (SQLException ex) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                      alert.setTitle("Information Login");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Thêm vé không thành công. Vui lòng kiểm tra lại thông tin.");
                                      alert.showAndWait();
             }
             catch(NullPointerException npe)
             {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                      alert.setTitle("Information Login");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Thêm vé không thành công. Vui lòng kiểm tra lại thông tin.");
                                      alert.showAndWait();
             }
       
         
        
          }
          else
          {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                      alert.setTitle("Information Login");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Xe sắp khởi hành không thể thêm vé.");
                                      alert.showAndWait();
                                      return;
          }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Information Login");
                          alert.setHeaderText(null);
                          alert.setContentText("Vui lòng điền đầy đủ thông tin!");
                          alert.showAndWait();
        }
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
    
    public void chonGhe(ActionEvent ae) throws SQLException
    {
        String tuyenDuong = cbLoTrinh.getSelectionModel().getSelectedItem().toString();
        String td[] = tuyenDuong.split(" - ");
        String tuyenDi = td[0];
        String tuyenDen = td[1];
       
         String maLT = QuanLyTuyenDi.getMaLoTrinh(tuyenDi, tuyenDen, cbXe.getSelectionModel().getSelectedItem().toString(),
                         cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString(), cbGioKhoiHanh.getSelectionModel().getSelectedItem().toString());
          

        List<String> dsMaGhe = QuanLyVeXe.getMaGhe(cbXe.getSelectionModel().getSelectedItem().toString(),
                        cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString(),
                        cbGioKhoiHanh.getSelectionModel().getSelectedItem().toString(),maLT);
          
          List<String> initMaGhe = new ArrayList<>();
          initMaGhe.add("A1");
          initMaGhe.add("A2");
          initMaGhe.add("A3");
          initMaGhe.add("A4");
          initMaGhe.add("A5");
          initMaGhe.add("A6");
          initMaGhe.add("A7");
          initMaGhe.add("A8");
          initMaGhe.add("A9");
          initMaGhe.add("A10");
          initMaGhe.add("A11");
          initMaGhe.add("A12");
          initMaGhe.add("B1");
          initMaGhe.add("B2");
          initMaGhe.add("B3");
          initMaGhe.add("B4");
          initMaGhe.add("B5");
          initMaGhe.add("B6");
          initMaGhe.add("B7");
          initMaGhe.add("B8");
          initMaGhe.add("B9");
          initMaGhe.add("B10");
          initMaGhe.add("B11");
          initMaGhe.add("B12");
           for(String s : dsMaGhe)
          {
              initMaGhe.remove(s);
          }
          for(String str : initMaGhe)
              if(str.compareTo((((Button)ae.getSource()).getText())) == 0)
              {
                  cbGheNgoi.getSelectionModel().select((((Button)ae.getSource()).getText()));
                  break;
              }
       
    }
      public void btExitOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
      public void chonGio() throws SQLException
      {
String tuyenDuong = cbLoTrinh.getSelectionModel().getSelectedItem().toString();
        String td[] = tuyenDuong.split(" - ");
        String tuyenDi = td[0];
        String tuyenDen = td[1];
       
         String maLT = QuanLyTuyenDi.getMaLoTrinh(tuyenDi, tuyenDen, cbXe.getSelectionModel().getSelectedItem().toString(),
                         cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString(), cbGioKhoiHanh.getSelectionModel().getSelectedItem().toString());
          
          
          
          
          List<String> dsMaGhe = QuanLyVeXe.getMaGhe(cbXe.getSelectionModel().getSelectedItem().toString(),
                        cbNgayKhoiHanh.getSelectionModel().getSelectedItem().toString(),
                        cbGioKhoiHanh.getSelectionModel().getSelectedItem().toString(),maLT);
          
          List<String> initMaGhe = new ArrayList<>();
          initMaGhe.add("A1");
          initMaGhe.add("A2");
          initMaGhe.add("A3");
          initMaGhe.add("A4");
          initMaGhe.add("A5");
          initMaGhe.add("A6");
          initMaGhe.add("A7");
          initMaGhe.add("A8");
          initMaGhe.add("A9");
          initMaGhe.add("A10");
          initMaGhe.add("A11");
          initMaGhe.add("A12");
          initMaGhe.add("B1");
          initMaGhe.add("B2");
          initMaGhe.add("B3");
          initMaGhe.add("B4");
          initMaGhe.add("B5");
          initMaGhe.add("B6");
          initMaGhe.add("B7");
          initMaGhe.add("B8");
          initMaGhe.add("B9");
          initMaGhe.add("B10");
          initMaGhe.add("B11");
          initMaGhe.add("B12");
          for(String s : dsMaGhe)
          {
              initMaGhe.remove(s);
          }
          
           ObservableList <String> list = FXCollections.observableArrayList(initMaGhe);
                    cbGheNgoi.setItems(list);
                    
      }
}
