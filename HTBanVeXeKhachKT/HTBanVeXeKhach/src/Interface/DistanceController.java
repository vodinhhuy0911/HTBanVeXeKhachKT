/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BVXK.QuanLyTuyenDi;
import BVXK.QuanLyXe;
import BanVeXeKhach.TuyenDuong;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TableColumn tcTuyenDen;
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
            });
            return row;
        });
         
    }    
    
    
    public void loadData() throws SQLException
    {
        tcMaLoTrinh.setCellValueFactory(new PropertyValueFactory("maTuyenDuong"));
        
        tcTuyenDi.setCellValueFactory(new PropertyValueFactory("tuyenDi"));
        
        tcTuyenDen.setCellValueFactory(new PropertyValueFactory("tuyenDen"));
         this.tvNoiDung.setItems(FXCollections.observableArrayList(QuanLyTuyenDi.getDsTuyenDuong()));
    }
    
    public void themLoTring() throws SQLException
    {
        TuyenDuong td = new TuyenDuong(txtMaLoTrinh.getText(), txtTuyenDi.getText(), txtTuyenDen.getText());
        QuanLyTuyenDi.themTuyenDuong(td);
        this.loadData();
    }
    public void capNhatTuyenDuong() throws SQLException
    {
        QuanLyTuyenDi.capNhatTuyenDuong(txtTuyenDi.getText(), txtTuyenDen.getText(),txtMaLoTrinh.getText());
        this.loadData();
    }
    
    public void xoaTuyenDuong() throws SQLException
    {
        QuanLyTuyenDi.xoaTuyenDuong(txtMaLoTrinh.getText());
        this.loadData();
    }
}
