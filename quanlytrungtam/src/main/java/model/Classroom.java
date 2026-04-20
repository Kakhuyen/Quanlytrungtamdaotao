package model;
import java.time.LocalDate;
public class Classroom {

    private int maLH;
    private int maKH;
    private int maTT;
    private String tenLH;
    private int siSoMax;
    private LocalDate ngayKhaiGiang;
    private String tenKH;

    public Classroom() {
    }
    public int getMaLH() {
        return maLH;
    }

    public void setMaLH(int maLH) {
        this.maLH = maLH > 0 ? maLH : 0;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH > 0 ? maKH : 0;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT > 0 ? maTT : 0;
    }

    public String getTenLH() {
        return tenLH;
    }

    public void setTenLH(String tenLH) {

        if (tenLH == null) {
            this.tenLH = "";
            return;
        }

        tenLH = tenLH.trim();

        if (tenLH.length() < 2) {
            this.tenLH = "";
            return;
        }

        if (tenLH.length() > 100) {
            tenLH = tenLH.substring(0, 100);
        }

        this.tenLH = tenLH;
    }

    public int getSiSoMax() {
        return siSoMax;
    }

    public void setSiSoMax(int siSoMax) {

        if (siSoMax < 1) {
            this.siSoMax = 1;
        } else if (siSoMax > 30) {
            this.siSoMax = 30;
        } else {
            this.siSoMax = siSoMax;
        }
    }

    public LocalDate getNgayKhaiGiang() {
        return ngayKhaiGiang;
    }

    public void setNgayKhaiGiang(LocalDate ngayKhaiGiang) {
        this.ngayKhaiGiang = ngayKhaiGiang;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH == null ? "" : tenKH.trim();
    }
}