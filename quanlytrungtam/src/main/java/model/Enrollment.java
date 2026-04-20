package model;
import java.util.Date;
public class Enrollment {
    private int maDK;
    private int maND;
    private int maLH;

    private String hoTen;
    private String email;
    private String sdt;
    private String diaChi;

    private Date ngayLap;

    private String trangThaiTT;
    private String tinhTrangDuyet;

    public Enrollment() {
        this.trangThaiTT = "ChuaThanhToan";
        this.tinhTrangDuyet = "DaDuyet";
    }

    public int getMaDK() {
        return maDK;
    }

    public void setMaDK(int maDK) {
        this.maDK = maDK;
    }

    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
    }

    public int getMaLH() {
        return maLH;
    }

    public void setMaLH(int maLH) {
        this.maLH = maLH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen == null ? "" : hoTen.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim().toLowerCase();
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt == null ? "" : sdt.trim();
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi == null ? "" : diaChi.trim();
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTrangThaiTT() {
        return trangThaiTT;
    }

    public void setTrangThaiTT(String trangThaiTT) {
        if (trangThaiTT == null || trangThaiTT.trim().isEmpty()) {
            this.trangThaiTT = "ChuaThanhToan";
        } else {
            this.trangThaiTT = trangThaiTT.trim();
        }
    }

    public String getTinhTrangDuyet() {
        return tinhTrangDuyet;
    }

    public void setTinhTrangDuyet(String tinhTrangDuyet) {
        if (tinhTrangDuyet == null || tinhTrangDuyet.trim().isEmpty()) {
            this.tinhTrangDuyet = "DaDuyet";
        } else {
            this.tinhTrangDuyet = tinhTrangDuyet.trim();
        }
    }
}