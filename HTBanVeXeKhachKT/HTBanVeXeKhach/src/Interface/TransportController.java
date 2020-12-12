/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BVXK.QuanLyXe;
import BanVeXeKhach.NhanVien;
import BanVeXeKhach.Xe;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

public class TransportController implements Initializable {
@FXML
private ComboBox cbLoaiXe;

@FXML
private TableColumn colMaXe;

@FXML 
private TableColumn colLoaiXe;

@FXML
private TableView<Xe> tvXe;

@FXML
  private TextField txtMaXe;

private String maXeCu;


ObservableList<Xe> nvList = FXCollections.observableArrayList ();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    // TODO
                    ObservableList <String> list = FXCollections.observableArrayList("Xe giường nằm","Xe 40 chỗ ngồi","Xe limo house");
                    cbLoaiXe.setItems(list);
                try {
                    this.loadData();
                } catch (SQLException ex) {
                    Logger.getLogger(TransportController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 this.tvXe.setRowFactory((TableView<Xe> xe) ->{
            TableRow row = new TableRow();
            row.setOnMouseClicked((MouseEvent r) -> {
                Xe x = tvXe.getSelectionModel().getSelectedItem();
                this.maXeCu = x.getBienSoXe();
                this.txtMaXe.setText(x.getBienSoXe());
               this.cbLoaiXe.getSelectionModel().select(x.getLoaiXe());
            });
            return row;
        });
    }  
    
    public void loadData() throws SQLException
    {   
        
        colMaXe.setCellValueFactory(new PropertyValueFactory("bienSoXe"));
        colLoaiXe.setCellValueFactory(new PropertyValueFactory("loaiXe"));
         this.tvXe.setItems(FXCollections.observableArrayList(QuanLyXe.getXe()));
 
    }
    public void themXe() throws SQLException
    {
        if(!cbLoaiXe.getSelectionModel().isEmpty() &&txtMaXe.getText() != "")
        {
                String loaiXe = cbLoaiXe.getSelectionModel().getSelectedItem().toString();
                Xe xe = new Xe(txtMaXe.getText(),loaiXe);
                if(QuanLyXe.themXe(xe))
                {
                    this.loadData();
                    {
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                      alert.setTitle("Information Login");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Thêm thành công.");
                                      alert.showAndWait();
                        }
                }
                else
                {
                    {
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                  alert.setTitle("Information Login");
                                  alert.setHeaderText(null);
                                  alert.setContentText("Thêm không thành công. Vui lòng kiểm tra lại thông tin");
                                  alert.showAndWait();
                    }
                }
        }
        else
        {
            {
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                  alert.setTitle("Information Login");
                                  alert.setHeaderText(null);
                                  alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
                                  alert.showAndWait();
                    }
        }
    }
    
    public void capNhatXe() throws SQLException
    {
         if(!cbLoaiXe.getSelectionModel().isEmpty() &&txtMaXe.getText() != "")
         {
            String loaiXe = cbLoaiXe.getSelectionModel().getSelectedItem().toString();
            Xe xe = new Xe(txtMaXe.getText(),loaiXe);
            if(QuanLyXe.capNhatXe(xe,maXeCu))
            {
                     this.loadData();
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                  alert.setTitle("Information Login");
                                  alert.setHeaderText(null);
                                  alert.setContentText("Cập nhật thành công");
                                  alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                  alert.setTitle("Information Login");
                                  alert.setHeaderText(null);
                                  alert.setContentText("Cập nhật thất bại. Vui lòng kiểm tra lại thông tin!");
                                  alert.showAndWait();
            }
         }
         else
             {
            {
                                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                  alert.setTitle("Information Login");
                                  alert.setHeaderText(null);
                                  alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
                                  alert.showAndWait();
                    }
        }
    }
    
    public void xoaXe() throws SQLException
    {
        
        QuanLyXe.xoaXe(txtMaXe.getText());
        this.loadData();
    }
      public void btExitOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void huy()
    {
        txtMaXe.setText(null);
        cbLoaiXe.getSelectionModel().select(null);
    }
    
}
