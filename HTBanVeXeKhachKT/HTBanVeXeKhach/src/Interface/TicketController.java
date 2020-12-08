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
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    private ComboBox cbLoTrinh;
    
    @FXML
    private ComboBox cbXe;
    
    @FXML
    private TextField txtLoaiXe;
    
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
        try {
            // TODO

            List <Xe> xe = QuanLyXe.getXe();
            int i = 0;
            for(Xe x : xe)
            {
               str.add(x.getBienSoXe());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
         list = FXCollections.observableArrayList(str);
                    cbXe.setItems(list);
                    
                    
        
         
    }    
    public void chonXe()
    {
        try {
            if(cbXe.getSelectionModel().getSelectedItem().toString() != null)
            {
                txtLoaiXe.setText(QuanLyXe.getLoaiXe(cbXe.getSelectionModel().getSelectedItem().toString()));
            }   
        } catch (SQLException ex) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void themVe()
    {
        
    }
}
