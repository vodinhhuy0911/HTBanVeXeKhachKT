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
import static Interface.LoginController.chucVu;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private TableView<TuyenDuong> tvNoiDung;

    @FXML
    private TableColumn tcMaLoTrinh;

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
    @FXML
    private TextField txtFind;
private String id;
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
        this.tvNoiDung.setRowFactory((TableView<TuyenDuong> td) -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked((MouseEvent r) -> {
                TuyenDuong t = tvNoiDung.getSelectionModel().getSelectedItem();
                this.txtMaLoTrinh.setText(t.getMaTuyenDuong());
                this.txtTuyenDi.setText(t.getTuyenDi());
                this.txtTuyenDen.setText(t.getTuyenDen());
                this.cbXe.getSelectionModel().select(t.getMaXe());
                  id = t.getMaTuyenDuong();
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
            for (Xe x : xe) {
                str.add(x.getBienSoXe());
                ObservableList<String> list = FXCollections.observableArrayList(str);
                cbXe.setItems(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DistanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData() throws SQLException {
        tcMaLoTrinh.setCellValueFactory(new PropertyValueFactory("maTuyenDuong"));

        tcTuyenDi.setCellValueFactory(new PropertyValueFactory("tuyenDi"));

        tcTuyenDen.setCellValueFactory(new PropertyValueFactory("tuyenDen"));
        tcXe.setCellValueFactory(new PropertyValueFactory("maXe"));
        tcNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory("thoiGianKhoiHanh"));
        tcGioKhoiHanh.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
        this.tvNoiDung.setItems(FXCollections.observableArrayList(QuanLyTuyenDi.getDsTuyenDuong()));
    }

    @FXML
    public void themLoTring() throws SQLException, ParseException {
        if (!txtMaLoTrinh.getText().isEmpty() && !txtTuyenDi.getText().isEmpty() && !txtTuyenDen.getText().isEmpty() && !txtGioKhoiHanh.getText().isEmpty() && !cbXe.getSelectionModel().isEmpty() && dpNgayKhoiHanh.getValue() != null) {
            if (QuanLyTuyenDi.soLuongMa((txtMaLoTrinh.getText())) == 0) {

                LocalDate ngayKhoiHanh = dpNgayKhoiHanh.getValue();
                String date = String.valueOf(ngayKhoiHanh);
                String date1 = date.replace("-", "/");
                String s[] = date.split("-");
                date = s[2] + "/" + s[1] + "/" + s[0];
                
                DateFormat sfm = new SimpleDateFormat("dd/MM/yyyy");
                Date d = sfm.parse(date);

                TuyenDuong td = new TuyenDuong(txtMaLoTrinh.getText(), txtTuyenDi.getText(), txtTuyenDen.getText(), cbXe.getSelectionModel().getSelectedItem().toString(), d, txtGioKhoiHanh.getText());

                if (QuanLyTuyenDi.getSoLuongChuyenDi(txtTuyenDi.getText(),txtTuyenDen.getText(),cbXe.getSelectionModel().getSelectedItem().toString(),date1,txtGioKhoiHanh.getText())==0) {
                    if(QuanLyTuyenDi.soLuongMa(txtMaLoTrinh.getText())== 0)
                    {
                    if (QuanLyTuyenDi.themTuyenDuong(td)) {
                        this.loadData();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Login");
                        alert.setHeaderText(null);
                        alert.setContentText("Thêm thành công");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Login");
                        alert.setHeaderText(null);
                        alert.setContentText("Thêm thất bại. Vui lòng kiểm tra lại thông tin.");
                        alert.showAndWait();
                    }}
                    else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Login");
                    alert.setHeaderText(null);
                    alert.setContentText("ID đã tồn tại.");
                    alert.showAndWait();
                }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Login");
                    alert.setHeaderText(null);
                    alert.setContentText("Tuyến này đã tồn tại.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Login");
                alert.setHeaderText(null);
                alert.setContentText("Thêm thất bại. Vui lòng kiểm tra lại thông tin.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Login");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin.");
            alert.showAndWait();
        }
    }

    @FXML
    public void capNhatTuyenDuong() throws SQLException, ParseException {
        if (!txtMaLoTrinh.getText().isEmpty() && !txtTuyenDi.getText().isEmpty() && !txtTuyenDen.getText().isEmpty() && !txtGioKhoiHanh.getText().isEmpty() && !cbXe.getSelectionModel().isEmpty() && dpNgayKhoiHanh.getValue() != null) {
            if(id.equals(txtMaLoTrinh.getText()))
            {
            LocalDate ngayKhoiHanh = dpNgayKhoiHanh.getValue();
            String date = String.valueOf(ngayKhoiHanh);
            
            String date1 = date.replace("-", "/");
            String s[] = date.split("-");
            date = s[2] + "/" + s[1] + "/" + s[0];
            
            DateFormat sfm = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sfm.parse(date);
             
                   {
                       if (QuanLyTuyenDi.getSoLuongChuyenDi(txtTuyenDi.getText(),txtTuyenDen.getText(),cbXe.getSelectionModel().getSelectedItem().toString(),date1,txtGioKhoiHanh.getText())==0)
                       {
                           if(QuanLyTuyenDi.soLuongMa(txtMaLoTrinh.getText())== 1)
                           {
            if (QuanLyTuyenDi.capNhatTuyenDuong(txtTuyenDi.getText(), txtTuyenDen.getText(), txtMaLoTrinh.getText(), cbXe.getSelectionModel().getSelectedItem().toString(),
                    d, txtGioKhoiHanh.getText(),id)) {
                this.loadData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Login");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Login");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thất bại. Vui lòng kiểm tra lại thông tin");
                alert.showAndWait();
            }
                       }
                           else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Login");
                alert.setHeaderText(null);
                alert.setContentText("id không đã tồn tại.");
                alert.showAndWait();
                           }
                       }else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Login");
                alert.setHeaderText(null);
                alert.setContentText("Tuyến đường đã tồn tại.");
                alert.showAndWait();
                       }
                   }
        }
            else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Login");
            alert.setHeaderText(null);
            alert.setContentText("Không thể thay đổi mã lộ trình.");
            alert.showAndWait();
        }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Login");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
            alert.showAndWait();
        }
    }

    @FXML
    public void xoaTuyenDuong() throws SQLException {
        if (!txtMaLoTrinh.getText().isEmpty()) {
            if (QuanLyTuyenDi.xoaTuyenDuong(txtMaLoTrinh.getText())) {
                this.loadData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Login");
                alert.setHeaderText(null);
                alert.setContentText("Xóa thành công.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Login");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Login");
            alert.setHeaderText(null);
            alert.setContentText("Xóa không thành công");
            alert.showAndWait();
        }
    }

    @FXML
    public void btExitOnAction(ActionEvent event) throws IOException {
        if (chucVu.compareTo("Quản Trị Viên") == 0) {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("Employee2.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }
    //Tim kiem

    @FXML
    public void search(ActionEvent event) throws SQLException {
        if (!txtFind.getText().isEmpty()) {
            List<TuyenDuong> dsTuyenDuong = new ArrayList<>();
            dsTuyenDuong = QuanLyTuyenDi.timKiemTuyenDuong(txtFind.getText());
            tcMaLoTrinh.setCellValueFactory(new PropertyValueFactory("maTuyenDuong"));
            tcTuyenDi.setCellValueFactory(new PropertyValueFactory("tuyenDi"));
            tcTuyenDen.setCellValueFactory(new PropertyValueFactory("tuyenDen"));
            tcXe.setCellValueFactory(new PropertyValueFactory("maXe"));
            tcNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory("thoiGianKhoiHanh"));
            tcGioKhoiHanh.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
            this.tvNoiDung.setItems(FXCollections.observableArrayList(dsTuyenDuong));
        } else {
            this.loadData();
        }
    }

    public void huy() {
        txtMaLoTrinh.setText("");
        txtTuyenDen.setText("");
        txtTuyenDi.setText("");
        txtGioKhoiHanh.setText("");
        cbXe.getSelectionModel().select(null);
        dpNgayKhoiHanh.setValue(null);
    }
}
