package dao;
import util.DBConnection;
import java.sql.*;
public class DangKyDAO {
    public boolean isDangKy(int maND, int maLH) {
        String sql = "SELECT * FROM PHIEUDANGKY WHERE maND=? AND maLH=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maND);
            ps.setInt(2, maLH);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isFull(int maLH) {
        String sql = "SELECT COUNT(*) FROM PHIEUDANGKY WHERE maLH=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maLH);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);

                String sql2 = "SELECT siSoMax FROM LOPHOC WHERE maLH=?";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, maLH);
                ResultSet rs2 = ps2.executeQuery();

                if (rs2.next()) {
                    int max = rs2.getInt(1);
                    return count >= max;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(int maND, int maLH) {
        String sql = "INSERT INTO PHIEUDANGKY(maND, maLH) VALUES(?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maND);
            ps.setInt(2, maLH);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
