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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    private ComboBox cbIDXe;

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
    private TextField txtKey;

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
                
                ObservableList <String> list;
                this.txtIDVe.setText(x.getMaVe());
                this.txtIDNV.setText(x.getMaNV());
               
                list = FXCollections.observableArrayList(String.valueOf(x.getBienSoXe()));
                cbIDXe.setItems(list);
                this.txtKH.setText(x.getHoTenKH());
                this.txtSDT.setText(x.getSdtKH());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                this.txtNgayBook.setText(String.valueOf(formatter.format(x.getThoiGianDatVe())));
                this.txtViTriGhe.setText(x.getMaGheNgoi());
                this.txtGiaVe.setText(String.valueOf(x.getGiaVe()));
                if(x.isIsThanhToan())
                    this.rdTT.setSelected(true);
                else
                    this.rdTT.setSelected(false);
            
                list = FXCollections.observableArrayList(String.valueOf(x.getNgayKhoiHanh()));
                cbNgayKH.setItems(list);
                cbNgayKH.getSelectionModel().select(x.getNgayKhoiHanh());
                list = FXCollections.observableArrayList(x.getGioKhoiHanh());
                cbGioKH.setItems(list);
              cbGioKH.getSelectionModel().select(x.getGioKhoiHanh());
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
        
        //themcombobox maxe
        
        
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
     public void chonLoTrinh() throws SQLException
    {
        try{
        List <String> s = new ArrayList<>();
        String tuyenDuong = cbIDLT.getSelectionModel().getSelectedItem().toString();
        String td[] = tuyenDuong.split(" - ");
        String tuyenDi = td[0];
        String tuyenDen = td[1];
        VeXe x = tvThongTin.getSelectionModel().getSelectedItem();
        s = QuanLyTuyenDi.getMaXe(tuyenDi, tuyenDen);
        ObservableList <String> list = FXCollections.observableArrayList(s);
        cbIDXe.setItems(list);
        cbIDXe.getSelectionModel().select(x.getBienSoXe());
//        cbNgayKH.setItems(null);
//        cbGioKH.setItems(null);
        } catch(Exception ex)
        {
            
        }
    }
     
     public void chonXe()
    {
        try {
            if(cbIDXe.getSelectionModel().getSelectedItem().toString() != null)
            {
                
                
                ///////////Them ngày khởi hành vào combobox
                List<String> ngayKhoiHanh = new ArrayList<>();
                   String tuyenDuong = cbIDLT.getSelectionModel().getSelectedItem().toString();
                String td[] = tuyenDuong.split(" - ");
              String tuyenDi = td[0];
                String tuyenDen = td[1];
                /////////////
                
                ngayKhoiHanh = QuanLyTuyenDi.getNgayKhoiHanh(tuyenDi, tuyenDen, 
                        cbIDXe.getSelectionModel().getSelectedItem().toString());
                ObservableList <String> list = FXCollections.observableArrayList(ngayKhoiHanh);
                    cbNgayKH.setItems(list);
//                    cbGioKH.setItems(null);
                
            }   
        } catch (SQLException ex ) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NullPointerException ex)
        {
            
        }
    }
     
     public void chonNgay()
    {
         try {
            if(cbNgayKH.getSelectionModel().getSelectedItem().toString() != null)
            {
               
                
                ///////////Them Giờ khởi hành vào combobox
                List<String> gioKhoiHanh = new ArrayList<>();
                   String tuyenDuong = cbIDLT.getSelectionModel().getSelectedItem().toString();
                String td[] = tuyenDuong.split(" - ");
              String tuyenDi = td[0];
                String tuyenDen = td[1];
                /////////////
                //tuyenDi, tuyenden,maxe,ngaykhoihanh
                gioKhoiHanh = QuanLyTuyenDi.getGioKhoiHanh(tuyenDi, tuyenDen,
                        cbIDXe.getSelectionModel().getSelectedItem().toString(),
                        cbNgayKH.getSelectionModel().getSelectedItem().toString());
                ObservableList <String> list = FXCollections.observableArrayList(gioKhoiHanh);
                    cbGioKH.setItems(list);
                
            }   
        } catch (SQLException ex ) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NullPointerException ex)
        {
            
        }
    }
     
      public void capNhat() throws SQLException
      {
          boolean flag = false;
          if(rdTT.isSelected())
              flag = true;
           List<String> gioKhoiHanh = new ArrayList<>();
                   String tuyenDuong = cbIDLT.getSelectionModel().getSelectedItem().toString();
                String td[] = tuyenDuong.split(" - ");
              String tuyenDi = td[0];
                String tuyenDen = td[1];
          QuanLyVeXe.capNhatVeXe(cbIDXe.getSelectionModel().getSelectedItem().toString(), 
                 txtIDNV.getText(),txtKH.getText(), txtSDT.getText(),
                 txtViTriGhe.getText(), txtNgayBook.getText(), true,
                 cbNgayKH.getSelectionModel().getSelectedItem().toString(),
                 cbGioKH.getSelectionModel().getSelectedItem().toString(), 
                 Double.parseDouble(txtGiaVe.getText()),
                 QuanLyTuyenDi.getMaLoTrinh(tuyenDi, tuyenDen, 
                         cbIDXe.getSelectionModel().getSelectedItem().toString(), cbNgayKH.getSelectionModel().getSelectedItem().toString(),
                 cbGioKH.getSelectionModel().getSelectedItem().toString())
                 , txtIDVe.getText());
          
          this.loadData();
      }
      
      public void xoaVe() throws SQLException
      {
          QuanLyVeXe.xoaVeXe(txtIDVe.getText());
          this.loadData();
      }
      
      public void huy() throws SQLException
      {
          txtGiaVe.setText(null);
          txtIDVe.setText(null);
          txtIDNV.setText(null);
          txtKH.setText(null);
          txtSDT.setText(null);
          txtNgayBook.setText(null);
          txtViTriGhe.setText(null);
          rdTT.setSelected(false);
          cbNgayKH.setItems(null);
          cbNgayKH.getSelectionModel().select(null);
          cbIDXe.setItems(null);
          cbIDXe.getSelectionModel().select(null);
          cbIDLT.setItems(null);
          cbIDLT.getSelectionModel().select(null);
          cbGioKH.setItems(null);
          cbGioKH.getSelectionModel().select(null);
          this.loadData();
        }
      
      public void timKiem() throws SQLException
      {
          if(txtKey.getText() != null ||txtKey.getText() != "" )
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
             this.tvThongTin.setItems(FXCollections.observableArrayList(QuanLyVeXe.timKiemLoTrinh(txtKey.getText())));
          }
          else
              this.loadData();
      }
}
