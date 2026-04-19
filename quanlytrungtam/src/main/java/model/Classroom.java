package model;

import java.sql.Date;

public class ClassRoom {
    private int maLH;
    private int maKH;
    private int maTT;
    private String tenLH;
    private int sisoMax;
    private Date ngayKhaiGiang;

    public ClassRoom() {}

    public ClassRoom(int maLH, int maKH, int maTT, String tenLH, int sisoMax, Date ngayKhaiGiang) {
        this.maLH = maLH;
        this.maKH = maKH;
        this.maTT = maTT;
        this.tenLH = tenLH;
        this.sisoMax = sisoMax;
        this.ngayKhaiGiang = ngayKhaiGiang;
    }

    public int getClassId() {
        return maLH;
    }

    public void setClassId(int maLH) {
        this.maLH = maLH;
    }

    public int getCourseId() {
        return maKH;
    }

    public void setCourseId(int maKH) {
        this.maKH = maKH;
    }

    public int getCenterId() {
        return maTT;
    }

    public void setCenterId(int maTT) {
        this.maTT = maTT;
    }

    public String getClassName() {
        return tenLH;
    }

    public void setClassName(String tenLH) {
        this.tenLH = tenLH;
    }

    public int getStuMax() {
        return sisoMax;
    }

    public void setStuMax(int sisoMax) {
        this.sisoMax = sisoMax;
    }

    public Date getStartDate() {
        return ngayKhaiGiang;
    }

    public void setStartDate(Date ngayKhaiGiang) {
        this.ngayKhaiGiang = ngayKhaiGiang;
    }
}