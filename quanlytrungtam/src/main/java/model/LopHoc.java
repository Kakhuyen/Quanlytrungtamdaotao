package model;
import java.time.LocalDate;
public class LopHoc {
    private int maLH;
    private String tenLH;
    private int siSoMax;
    private LocalDate ngayKhaiGiang;
    private String tenKH;

    public int getMaLH() { return maLH; }
    public void setMaLH(int maLH) { this.maLH = maLH; }

    public String getTenLH() { return tenLH; }
    public void setTenLH(String tenLH) { this.tenLH = tenLH; }

    public int getSiSoMax() { return siSoMax; }
    public void setSiSoMax(int siSoMax) { this.siSoMax = siSoMax; }

    public LocalDate getNgayKhaiGiang() { return ngayKhaiGiang; }
    public void setNgayKhaiGiang(LocalDate ngayKhaiGiang) { this.ngayKhaiGiang = ngayKhaiGiang; }

    public String getTenKH() { return tenKH; }
    public void setTenKH(String tenKH) { this.tenKH = tenKH; }
}