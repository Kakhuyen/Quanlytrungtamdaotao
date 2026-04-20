package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnrollDAO {

    public boolean isDangKy(int maND, int maLH) {

        if (maND <= 0 || maLH <= 0) {
            return false;
        }

        String sql =
                "SELECT 1 FROM PHIEUDANGKY " +
                        "WHERE maND = ? AND maLH = ? LIMIT 1";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maND);
            ps.setInt(2, maLH);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            throw new RuntimeException("Khong the kiem tra dang ky.", e);
        }
    }

    public boolean isRegister(int maND, int maLH) {
        return isDangKy(maND, maLH);
    }

    public int countByClass(int maLH) {

        if (maLH <= 0) {
            return 0;
        }

        String sql =
                "SELECT COUNT(*) tong " +
                        "FROM PHIEUDANGKY " +
                        "WHERE maLH = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maLH);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("tong");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Khong the dem hoc vien.", e);
        }

        return 0;
    }

    public boolean isFull(int maLH) {

        if (maLH <= 0) {
            return true;
        }

        String sql =
                "SELECT siSoMax FROM LOPHOC WHERE maLH = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maLH);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int siSoMax = rs.getInt("siSoMax");

                    if (siSoMax < 1) {
                        return true;
                    }

                    if (siSoMax > 30) {
                        siSoMax = 30;
                    }

                    return countByClass(maLH) >= siSoMax;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Khong the kiem tra si so lop.", e);
        }

        return true;
    }

    public boolean register(int maND,
                            int maLH,
                            String hoTen,
                            String email,
                            String sdt,
                            String diaChi) {

        if (maND <= 0 || maLH <= 0) {
            return false;
        }

        hoTen = safe(hoTen);
        email = safe(email).toLowerCase();
        sdt = safe(sdt);
        diaChi = safe(diaChi);

        if (hoTen.length() < 2 || hoTen.length() > 100) {
            return false;
        }

        if (!hoTen.matches("^[A-Za-zÀ-ỹ\\s]+$")) {
            return false;
        }

        if (email.length() < 6 || email.length() > 100) {
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return false;
        }

        if (!email.endsWith("@gmail.com")
                && !email.endsWith("@outlook.com")
                && !email.endsWith("@outlook.com.vn")) {
            return false;
        }

        if (!sdt.matches("^0\\d{9}$")) {
            return false;
        }

        if (diaChi.length() < 3 || diaChi.length() > 200) {
            return false;
        }

        if (isDangKy(maND, maLH)) {
            return false;
        }

        if (isFull(maLH)) {
            return false;
        }

        String sql =
                "INSERT INTO PHIEUDANGKY " +
                        "(maND, maLH, ngayLap, trangThaiTT, tinhTrangDuyet, hoTen, email, SDT, diaChi) " +
                        "VALUES (?, ?, NOW(), 'ChuaThanhToan', 'DaDuyet', ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            if (isDangKy(maND, maLH) || isFull(maLH)) {
                conn.rollback();
                return false;
            }

            ps = conn.prepareStatement(sql);

            ps.setInt(1, maND);
            ps.setInt(2, maLH);
            ps.setString(3, hoTen);
            ps.setString(4, email);
            ps.setString(5, sdt);
            ps.setString(6, diaChi);

            int row = ps.executeUpdate();

            conn.commit();

            return row > 0;

        } catch (Exception e) {

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception ex) {
            }

            throw new RuntimeException("Dang ky that bai.", e);

        } finally {

            try {
                if (ps != null) ps.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    private String safe(String s) {
        return s == null ? "" : s.trim();
    }
}