package dao;
import util.DBConnection;
import java.sql.*;
public class DangKyDAO {
    public boolean isDangKy(int maND, int maLH) {
        String sql = "SELECT 1 FROM PHIEUDANGKY WHERE maND=? AND maLH=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maND);
            ps.setInt(2, maLH);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isFull(int maLH) {
        String sql = "SELECT COUNT(*) AS soLuong, l.siSoMax " +
                "FROM PHIEUDANGKY p " +
                "JOIN LOPHOC l ON p.maLH = l.maLH " +
                "WHERE p.maLH=? AND p.tinhTrangDuyet='DaDuyet'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maLH);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int soLuong = rs.getInt("soLuong");
                int siSoMax = rs.getInt("siSoMax");
                return soLuong >= siSoMax;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(int maND, int maLH, String hoTen, String email, String sdt, String diaChi) {

        String sql = "INSERT INTO PHIEUDANGKY(maND, maLH, hoTen, email, SDT, diaChi) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maND);
            ps.setInt(2, maLH);
            ps.setString(3, hoTen);
            ps.setString(4, email);
            ps.setString(5, sdt);
            ps.setString(6, diaChi);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            // lỗi trùng đăng ký (UNIQUE)
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("Đã đăng ký trước đó!");
            }

            e.printStackTrace();
        }
        return false;
    }

    public boolean duyetDangKy(int maDK) {
        String sql = "UPDATE PHIEUDANGKY SET tinhTrangDuyet='DaDuyet' WHERE maDK=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDK);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}