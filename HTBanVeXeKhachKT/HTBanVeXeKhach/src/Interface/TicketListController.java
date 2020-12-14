/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BVXK.JDBC;
import BVXK.KiemTra;
import BVXK.QuanLyTuyenDi;
import BVXK.QuanLyVeXe;
import BanVeXeKhach.TuyenDuong;
import BanVeXeKhach.VeXe;
import static Interface.LoginController.chucVu;
import com.mysql.jdbc.Connection;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
 
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class TicketListController implements Initializable {

    
    @FXML
    private TextField txtGiaVe;
    @FXML
    private TextField txtIDVe;
    @FXML
    private TableView<VeXe> tvThongTin;

    @FXML
    private ComboBox cbIDXe;

    

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
    private RadioButton rdLayVe;
    @FXML
    private TableColumn clLayVe;
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
    @FXML
    private Button btCapNhat;
    @FXML
    private Button btXoa;
    @FXML
    private Button btHuy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
                if(x.isIsLayVe())
                    this.rdLayVe.setSelected(true);
                else
                    this.rdLayVe.setSelected(false);
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
//                   if(!cbNgayKH.getSelectionModel().isEmpty()&&!cbGioKH.getSelectionModel().isEmpty())
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
           LocalDateTime now = LocalDateTime.now();  
          String ngay = cbNgayKH.getSelectionModel().getSelectedItem().toString();
          String gio = cbGioKH.getSelectionModel().getSelectedItem().toString();
          String ngayGio = ngay + " " + gio;   
          String s1 = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth() +" " + (now.getHour() +1)+ ":" + (now.getMinute()) + ":" + now.getSecond();
          String s2 = now.getYear() + "-" + now.getMonthValue()+ "-" + now.getDayOfMonth() +" " + (now.getHour())+ ":" + (now.getMinute()+5) + ":" + now.getSecond();
          if(rdLayVe.isSelected() && rdTT.isSelected())
          {
            txtGiaVe.setDisable(true);
              txtIDNV.setDisable(true);
              txtIDVe.setDisable(true);
              txtKH.setDisable(true);
              txtNgayBook.setDisable(true);
              txtSDT.setDisable(true);
              txtViTriGhe.setDisable(true);
              cbGioKH.setDisable(true);
              cbIDLT.setDisable(true);
              cbIDXe.setDisable(true);
              cbNgayKH.setDisable(true);
              btHuy.setDisable(true);
              btXoa.setDisable(true);  
          }
          else
          {
          if(ngayGio.compareTo(s2) < 0)// nhỏ hơn 5p và mua vé
          {
              //&&((!rdLayVe.isSelected() &&!rdTT.isSelected()) ||(rdLayVe.isSelected() &&!rdTT.isSelected()) || (!rdLayVe.isSelected() &&rdTT.isSelected()) )
              txtGiaVe.setDisable(true);
              txtIDNV.setDisable(true);
              txtIDVe.setDisable(true);
              txtKH.setDisable(true);
              txtNgayBook.setDisable(true);
              txtSDT.setDisable(true);
              txtViTriGhe.setDisable(true);
              cbGioKH.setDisable(true);
              cbIDLT.setDisable(true);
              cbIDXe.setDisable(true);
              cbNgayKH.setDisable(true);
              btHuy.setDisable(true);
              btXoa.setDisable(true);
          }
          else if(ngayGio.compareTo(s1) < 0)//nho hon 60p
          {
               txtGiaVe.setDisable(true);
              txtIDNV.setDisable(true);
              txtIDVe.setDisable(true);
              txtKH.setDisable(true);
              txtNgayBook.setDisable(true);
              txtSDT.setDisable(true);
              txtViTriGhe.setDisable(true);
              cbGioKH.setDisable(true);
              cbIDLT.setDisable(true);
              cbIDXe.setDisable(true);
              cbNgayKH.setDisable(true);
              rdLayVe.setDisable(true);
              rdTT.setDisable(true);
              btCapNhat.setDisable(true);
              btXoa.setDisable(true);
          }
          else
          {
              
            txtGiaVe.setDisable(false);
              txtIDNV.setDisable(false);
              txtIDVe.setDisable(false);
              txtKH.setDisable(false);
              txtNgayBook.setDisable(false);
              txtSDT.setDisable(false);
              txtViTriGhe.setDisable(false);
              cbGioKH.setDisable(false);
              cbIDLT.setDisable(false);
              cbIDXe.setDisable(false);
              cbNgayKH.setDisable(false);
              rdLayVe.setDisable(false);
              rdTT.setDisable(false);
              btCapNhat.setDisable(false);
              btHuy.setDisable(false);
              btXoa.setDisable(false);
          }}
      
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
         if(chucVu.compareTo("Quản Trị Viên") == 0)
        {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        }
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("Employee2.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        }
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
        clLayVe.setCellValueFactory(new PropertyValueFactory("isLayVe"));
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
        
          if(!txtIDVe.getText().isEmpty() &&!txtIDNV.getText().isEmpty() &&!txtGiaVe.getText().isEmpty() &&!txtKH.getText().isEmpty() &&!txtNgayBook.getText().isEmpty() &&!txtSDT.getText().isEmpty() &&!txtViTriGhe.getText().isEmpty()&&!cbGioKH.getSelectionModel().isEmpty()&&!cbIDLT.getSelectionModel().isEmpty()&&!cbIDXe.getSelectionModel().isEmpty())
          {
              if(KiemTra.kiemTraSdt(txtSDT.getText()))
              {
                    boolean flag = false;
                    boolean flag1 = false;
                    if(rdTT.isSelected())
                        flag = true;

                    if(rdLayVe.isSelected())
                        flag1 = true;
                     List<String> gioKhoiHanh = new ArrayList<>();
                             String tuyenDuong = cbIDLT.getSelectionModel().getSelectedItem().toString();
                          String td[] = tuyenDuong.split(" - ");
                        String tuyenDi = td[0];
                          String tuyenDen = td[1];
                    if(QuanLyVeXe.capNhatVeXe(cbIDXe.getSelectionModel().getSelectedItem().toString(), 
                           txtIDNV.getText(),txtKH.getText(), txtSDT.getText(),
                           txtViTriGhe.getText(), txtNgayBook.getText(), flag,
                           cbNgayKH.getSelectionModel().getSelectedItem().toString(),
                           cbGioKH.getSelectionModel().getSelectedItem().toString(), 
                           Double.parseDouble(txtGiaVe.getText()),
                           QuanLyTuyenDi.getMaLoTrinh(tuyenDi, tuyenDen, 
                                   cbIDXe.getSelectionModel().getSelectedItem().toString(), cbNgayKH.getSelectionModel().getSelectedItem().toString(),
                           cbGioKH.getSelectionModel().getSelectedItem().toString())
                           , txtIDVe.getText(),flag1,txtNgayBook.getText()))
                    {
                         this.loadData();
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Login");
                        alert.setHeaderText(null);
                        alert.setContentText("Cập nhật thành công");
                        alert.showAndWait();
                        return;
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Login");
                        alert.setHeaderText(null);
                        alert.setContentText("Cập nhật không thành công. Vui lòng kiểm tra lại thông tin");
                        alert.showAndWait();
                        return;
                    }
                  } else
          {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Login");
              alert.setHeaderText(null);
              alert.setContentText("Số điện thoại không hợp lệ.");
              alert.showAndWait();
              return;
          }
          }
          else
          {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Login");
              alert.setHeaderText(null);
              alert.setContentText("Vui lòng điền đầy đủ thông tin");
              alert.showAndWait();
              return;
          }
      }
      
      public void xoaVe() throws SQLException
      {
          if(!rdTT.isSelected())// Chú ý các vé đã bán thì không được hoàn lại.
          {   
              Alert alertt = new Alert(Alert.AlertType.CONFIRMATION);
                                  alertt.setTitle("Information Login");
                                  alertt.setHeaderText(null);
                                  alertt.setContentText("Bạn có muốn xóa hay không");
                                  Optional<ButtonType> result =  alertt.showAndWait();
              if(result.get() == ButtonType.OK)
              {
                if(QuanLyVeXe.xoaVeXe(txtIDVe.getText()))
                {
                    this.loadData();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Login");
              alert.setHeaderText(null);
              alert.setContentText("Hủy vé thành công.");
              alert.showAndWait();
              return;
                
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Login");
              alert.setHeaderText(null);
              alert.setContentText("Hủy vé không thành công.");
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
              alert.setContentText("Vé đã mua không thể trả lại.");
              alert.showAndWait();
              return;
          }
      }
      
      public void huy() throws SQLException
      {
          txtGiaVe.setText("");
          txtIDVe.setText("");
          txtIDNV.setText("");
          txtKH.setText("");
          txtSDT.setText("");
          txtNgayBook.setText("");
          txtViTriGhe.setText("");
          rdTT.setSelected(false);
          cbNgayKH.setItems(null);
          cbNgayKH.getSelectionModel().select(null);
          cbIDXe.setItems(null);
          cbIDXe.getSelectionModel().select(null);
          cbIDLT.setItems(null);
          cbIDLT.getSelectionModel().select(null);
          cbGioKH.setItems(null);
          cbGioKH.getSelectionModel().select(null);
          rdLayVe.setSelected(false);
          this.loadData();
        }
      
      public void timKiem() throws SQLException, JRException, IOException
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
        clLayVe.setCellValueFactory(new PropertyValueFactory("isLayVe"));
        this.tvThongTin.setItems(FXCollections.observableArrayList(QuanLyVeXe.timKiemLoTrinh(txtKey.getText())));
     
          
          }
          else
              this.loadData();
        
      }
      
      
      //Tạo ve tren jasper

   private static JasperReport jasperReport;
   private static JasperViewer jasperViewer;
   private static JasperPrint jasperPrint;
   public void XuatVe() throws JRException, IOException {
       if(!txtKey.getText().isEmpty()){
        Hashtable map = new Hashtable();
        JasperReport jasperReport = JasperCompileManager.compileReport("src/Interface/VeXe.jrxml");
        map.put("HoTenKH",txtKey.getText());
        jasperPrint = JasperFillManager.fillReport(jasperReport,
               map, JDBC.getConn());
        jasperViewer = new JasperViewer(jasperPrint);
        jasperViewer.setVisible(true);

       }}}



      //Xuat ra report
      
      


