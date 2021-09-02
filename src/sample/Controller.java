package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class Controller {
    @FXML
    private TextField InputNIK;
    @FXML
    private TextField InputNAMA;
    @FXML
    private TextField InputCoupon;
    @FXML
    private TextField InputCoursus;
    @FXML
    private RadioButton lk;
    @FXML
    private RadioButton pr;
    @FXML
    private TextField InputMasuk;
    @FXML
    private TextField InputKeluar;
    @FXML
    private TableView<Coursus> coursusT;
    @FXML
    private TableColumn<Coursus, String> nikC;
    @FXML
    private TableColumn<Coursus, String> namaC;
    @FXML
    private TableColumn<Coursus, String> couponC;
    @FXML
    private TableColumn<Coursus, String> coursusC;
    @FXML
    private TableColumn<Coursus, String> jkC;
    @FXML
    private TableColumn<Coursus, String> tmC;
    @FXML
    private TableColumn<Coursus, String> tkC;

    private Connection conn;
    private ObservableList<Coursus> coursesData;

    public void initialize(){
        try {
            conn= DriverManager.getConnection("jdbc:sqlite:db\\CoursesDB.db");
            System.out.println("Koneksi Sukses");
            LoadingData();
        }catch (SQLException sqlException){
            System.out.println("Koneksi Gagal!");
        }
    }

    private void LoadingData(){
        try{
            Statement stmtn = conn.createStatement();
            String sqlSelect = "SELECT * FROM CoursesDB ORDER BY nik";
            this.coursesData = FXCollections.observableArrayList();

            ResultSet rsCoursus = stmtn.executeQuery(sqlSelect);
            while (rsCoursus .next()){
                this.coursesData.add(new Coursus(rsCoursus.getString("nik"),rsCoursus.getString("nama"),rsCoursus.getString("coupon"),rsCoursus.getString("coursus"),rsCoursus.getString("jk"),rsCoursus.getString("masuk"),rsCoursus.getString("keluar")));
            }
            this.nikC.setCellValueFactory(new PropertyValueFactory<Coursus, String>("nik"));
            this.namaC.setCellValueFactory(new PropertyValueFactory<Coursus, String>("nama"));
            this.couponC.setCellValueFactory(new PropertyValueFactory<Coursus, String>("coupon"));
            this.coursusC.setCellValueFactory(new PropertyValueFactory<Coursus, String>("coursus"));
            this.jkC.setCellValueFactory(new PropertyValueFactory<Coursus, String>("jk"));
            this.tmC.setCellValueFactory(new PropertyValueFactory<Coursus, String>("masuk"));
            this.tkC.setCellValueFactory(new PropertyValueFactory<Coursus, String>("keluar"));

            this.coursusT.setItems(this.coursesData);
        }catch (SQLException e){
            System.out.println("Gagal Loading Data!");
        }
    }

    public void Simpan(){
        String nik = InputNIK.getText().trim();
        String nama = InputNAMA.getText().trim();
        String coupon = InputCoupon.getText().trim();
        String coursus = InputCoursus.getText().trim();
        String jk;
        if (lk.isSelected()){
            jk = "Laki Laki";
        }else if(pr.isSelected()){
            jk = "Perempuan";
        }else{
            jk = "";
        }
        String masuk = InputMasuk.getText().trim();
        String keluar = InputKeluar.getText().trim();
        if (nik.equals("") || nama.equals("") || coupon.equals("") || coursus.equals("") || jk.equals("") || masuk.equals("") || keluar.equals("")){
            Myalert("Warning", "Peringatan", "Data Tidak Boleh Kosong!");
        }else {
            try {
                Statement stemt = conn.createStatement();
                int no = 0;
                String sqlSearch = "SELECT nik FROM coursesDB WHERE nik='"+ nik +"'";
                ResultSet rsSearch = stemt.executeQuery(sqlSearch);
                while (rsSearch.next()){
                    no++;
                }
                if(no > 0){
                    Myalert("warning", "Peringatan", "NIK Sudah Ada");
                }else {
                    String sqlInsert = "INSERT INTO CoursesDB VALUES('"+ nik +"','"+ nama +"','"+ coupon +"','"+ coursus +"','"+ jk +"','"+ masuk +"','"+ keluar +"')";
                    int rsInsert = stemt.executeUpdate(sqlInsert);

                    if(rsInsert > 0){
                        Myalert("Info", "Informasi", "Data berhasil disimpan");
                        InputNIK.setText("");
                        InputNAMA.setText("");
                        InputCoupon.setText("");
                        InputCoursus.setText("");
                        lk.setSelected(false);
                        pr.setSelected(false);
                        InputMasuk.setText("");
                        InputKeluar.setText("");
                    }else {
                        Myalert("warning", "Peringatan", "Data Gagal Disimpan!");
                    }
                }
            }catch (SQLException sqlException){
                System.out.println("Terjadi Kesalahan");
            }
        }
    }
    private void Myalert(String type, String title, String content){
        Alert alert;
        if (type.equals("Warning")){
            alert = new Alert(Alert.AlertType.WARNING);
        }else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}