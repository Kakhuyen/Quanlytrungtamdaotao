package com.quanly.model;

import java.io.Serializable;

public class NguoiDung implements Serializable {
    private int maND;
    private String tenDangNhap;
    private String matKhau;
    private VaiTro vaiTro;

    public NguoiDung() {
    }

    public NguoiDung(int maND, String tenDangNhap, String matKhau, VaiTro vaiTro) {
        this.maND = maND;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public VaiTro getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }
}