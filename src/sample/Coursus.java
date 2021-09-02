package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Coursus {
    private final StringProperty nik;
    private final StringProperty nama;
    private final StringProperty coupon;
    private final StringProperty coursus;
    private final StringProperty jk;
    private final StringProperty masuk;
    private final StringProperty keluar;

    public String getNik() {
        return nik.get();
    }

    public StringProperty nikProperty() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik.set(nik);
    }

    public String getNama() {
        return nama.get();
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getCoupon() {
        return coupon.get();
    }

    public StringProperty couponProperty() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon.set(coupon);
    }

    public String getCoursus() {
        return coursus.get();
    }

    public StringProperty coursusProperty() {
        return coursus;
    }

    public void setCoursus(String coursus) {
        this.coursus.set(coursus);
    }

    public String getJk() {
        return jk.get();
    }

    public StringProperty jkProperty() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk.set(jk);
    }

    public String getMasuk() {
        return masuk.get();
    }

    public StringProperty masukProperty() {
        return masuk;
    }

    public void setMasuk(String masuk) {
        this.masuk.set(masuk);
    }

    public String getKeluar() {
        return keluar.get();
    }

    public StringProperty keluarProperty() {
        return keluar;
    }

    public void setKeluar(String keluar) {
        this.keluar.set(keluar);
    }

    public Coursus(String nik, String nama, String coupon, String coursus, String jk, String masuk, String keluar){
        this.nik = new SimpleStringProperty(nik);
        this.nama = new SimpleStringProperty(nama);
        this.coupon = new SimpleStringProperty(coupon);
        this.coursus = new SimpleStringProperty(coursus);
        this.jk = new SimpleStringProperty(jk);
        this.masuk = new SimpleStringProperty(masuk);
        this.keluar = new SimpleStringProperty(keluar);


    }
}
