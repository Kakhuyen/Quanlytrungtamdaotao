package dao;
import model.Classroom;
import util.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ClassDAO {

    public List<Classroom> getAll() throws Exception {

        List<Classroom> list = new ArrayList<>();

        String sql =
                "SELECT maLH, tenLH, siSoMax, ngayKhaiGiang " +
                        "FROM LOPHOC ORDER BY maLH";

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Classroom c = new Classroom();

            c.setMaLH(rs.getInt("maLH"));
            c.setTenLH(rs.getString("tenLH"));
            c.setSiSoMax(rs.getInt("siSoMax"));
            Date d = rs.getDate("ngayKhaiGiang");
            if (d != null) {
                c.setNgayKhaiGiang(d.toLocalDate());
            }

            list.add(c);
        }

        rs.close();
        ps.close();
        conn.close();

        return list;
    }
    public Classroom findById(int maLH) {

        if (maLH <= 0) {
            return null;
        }

        String sql =
                "SELECT l.maLH, l.maKH, l.maTT, l.tenLH, l.siSoMax, l.ngayKhaiGiang, k.tenKH " +
                        "FROM LOPHOC l " +
                        "INNER JOIN KHOAHOC k ON l.maKH = k.maKH " +
                        "WHERE l.maLH = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maLH);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Không thể tìm lớp học.", e);
        }

        return null;
    }

    public boolean insert(Classroom c) {

        validate(c, false);

        String sql =
                "INSERT INTO LOPHOC(maKH, maTT, tenLH, siSoMax, ngayKhaiGiang) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, c.getMaKH());
            ps.setInt(2, c.getMaTT());
            ps.setString(3, clean(c.getTenLH()));
            ps.setInt(4, c.getSiSoMax());
            ps.setDate(5, Date.valueOf(c.getNgayKhaiGiang()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Thêm lớp học thất bại.", e);
        }
    }

    public boolean update(Classroom c) {

        validate(c, true);

        String sql =
                "UPDATE LOPHOC " +
                        "SET maKH = ?, maTT = ?, tenLH = ?, siSoMax = ?, ngayKhaiGiang = ? " +
                        "WHERE maLH = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, c.getMaKH());
            ps.setInt(2, c.getMaTT());
            ps.setString(3, clean(c.getTenLH()));
            ps.setInt(4, c.getSiSoMax());
            ps.setDate(5, Date.valueOf(c.getNgayKhaiGiang()));
            ps.setInt(6, c.getMaLH());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Cập nhật lớp học thất bại.", e);
        }
    }

    public boolean delete(int maLH) {

        if (maLH <= 0) {
            return false;
        }

        if (hasEnroll(maLH)) {
            throw new RuntimeException("Lớp đã có học viên đăng ký, không thể xóa.");
        }

        String sql = "DELETE FROM LOPHOC WHERE maLH = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maLH);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Xóa lớp học thất bại.", e);
        }
    }

    public List<Classroom> search(String keyword) {

        List<Classroom> list = new ArrayList<>();

        keyword = clean(keyword);

        String sql =
                "SELECT l.maLH, l.maKH, l.maTT, l.tenLH, l.siSoMax, l.ngayKhaiGiang, k.tenKH " +
                        "FROM LOPHOC l " +
                        "INNER JOIN KHOAHOC k ON l.maKH = k.maKH " +
                        "WHERE l.tenLH LIKE ? OR k.tenKH LIKE ? " +
                        "ORDER BY l.maLH DESC";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Tìm kiếm thất bại.", e);
        }

        return list;
    }

    public boolean existsName(String tenLH, int ignoreId) {

        String sql =
                "SELECT 1 FROM LOPHOC " +
                        "WHERE tenLH = ? AND maLH <> ? LIMIT 1";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, clean(tenLH));
            ps.setInt(2, ignoreId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            throw new RuntimeException("Không thể kiểm tra tên lớp.", e);
        }
    }

    public boolean hasEnroll(int maLH) {

        String sql =
                "SELECT 1 FROM PHIEUDANGKY WHERE maLH = ? LIMIT 1";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maLH);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            throw new RuntimeException("Không thể kiểm tra đăng ký.", e);
        }
    }

    private Classroom mapRow(ResultSet rs) throws Exception {

        Classroom c = new Classroom();

        c.setMaLH(rs.getInt("maLH"));
        c.setMaKH(rs.getInt("maKH"));
        c.setMaTT(rs.getInt("maTT"));
        c.setTenLH(rs.getString("tenLH"));
        c.setSiSoMax(rs.getInt("siSoMax"));

        Date d = rs.getDate("ngayKhaiGiang");

        if (d != null) {
            c.setNgayKhaiGiang(d.toLocalDate());
        }

        c.setTenKH(rs.getString("tenKH"));

        return c;
    }

    private void validate(Classroom c, boolean update) {

        if (c == null) {
            throw new RuntimeException("Dữ liệu không hợp lệ.");
        }

        if (update && c.getMaLH() <= 0) {
            throw new RuntimeException("Mã lớp không hợp lệ.");
        }

        if (c.getMaKH() <= 0) {
            throw new RuntimeException("Mã khóa học không hợp lệ.");
        }

        if (c.getMaTT() <= 0) {
            throw new RuntimeException("Mã trung tâm không hợp lệ.");
        }

        String ten = clean(c.getTenLH());

        if (ten.length() < 3 || ten.length() > 100) {
            throw new RuntimeException("Tên lớp từ 3 đến 100 ký tự.");
        }

        if (!ten.matches("^[\\p{L}0-9\\s\\-]+$")) {
            throw new RuntimeException("Tên lớp chứa ký tự không hợp lệ.");
        }

        if (existsName(ten, update ? c.getMaLH() : 0)) {
            throw new RuntimeException("Tên lớp đã tồn tại.");
        }

        if (c.getSiSoMax() < 1 || c.getSiSoMax() > 30) {
            throw new RuntimeException("Sĩ số tối đa từ 1 đến 30.");
        }

        if (c.getNgayKhaiGiang() == null) {
            throw new RuntimeException("Ngày khai giảng không được rỗng.");
        }
    }

    private String clean(String s) {
        return s == null ? "" : s.trim();
    }
}