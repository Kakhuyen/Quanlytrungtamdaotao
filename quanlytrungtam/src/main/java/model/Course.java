package model;

public class Course {
    private int maKH;
    private String tenKH;
    private String moTa;
    private double hocPhi;
    private String hinhAnh;

    public Course() {}

    public Course(int maKH, String tenKH, String moTa, double hocPhi, String hinhAnh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.moTa = moTa;
        this.hocPhi = hocPhi;
        this.hinhAnh = hinhAnh;
    }

    public int getCourseId() {
        return MaKH;
    }

    public void setCourseId(int MaKH) {
        this.MaKH = MaKH;
    }

    public String getCourseName() {
        return tenKH;
    }

    public void setCourseName(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDescription() {
        return moTa;
    }

    public void setDescription(String moTa) {
        this.moTa = moTa;
    }

    public double getTuition() {
        return hocPhi;
    }

    public void setTuition(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public String getImage() {
        return hinhAnh;
    }

    public void setImage(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}