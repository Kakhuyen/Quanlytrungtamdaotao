package dao;
import util.DBConnection;
import java.sql.*;
import java.util.*;
import model.LopHoc;
public class LopHocDAO {
    public List<LopHoc> getAll() {
        List<LopHoc> list = new ArrayList<>();
        String sql = "SELECT l.*, k.tenKH FROM LOPHOC l JOIN KHOAHOC k ON l.maKH = k.maKH";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LopHoc l = new LopHoc();
                l.setMaLH(rs.getInt("maLH"));
                l.setTenLH(rs.getString("tenLH"));
                l.setSiSoMax(rs.getInt("siSoMax"));
                l.setNgayKhaiGiang(rs.getDate("ngayKhaiGiang").toLocalDate());
                l.setTenKH(rs.getString("tenKH"));
                list.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}