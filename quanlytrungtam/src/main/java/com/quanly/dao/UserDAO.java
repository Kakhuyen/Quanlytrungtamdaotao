package com.quanly.dao;

import com.quanly.model.NguoiDung;
import com.quanly.model.VaiTro;
import com.quanly.util.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    private static final int HOC_VIEN_ROLE_ID = 2;

    public NguoiDung checkLogin(String username, String password) {
        String sql = "SELECT nd.maND, nd.tenDangNhap, nd.matKhau, vt.maVaiTro, vt.tenVaiTro "
                + "FROM NGUOIDUNG nd "
                + "JOIN VAITRO vt ON nd.maVaiTro = vt.maVaiTro "
                + "WHERE nd.tenDangNhap = ? AND nd.matKhau = ?";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    VaiTro vaiTro = new VaiTro(
                            resultSet.getInt("maVaiTro"),
                            resultSet.getString("tenVaiTro")
                    );

                    return new NguoiDung(
                            resultSet.getInt("maND"),
                            resultSet.getString("tenDangNhap"),
                            resultSet.getString("matKhau"),
                            vaiTro
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi khi kiem tra dang nhap", e);
        }

        return null;
    }

    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM NGUOIDUNG WHERE tenDangNhap = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi khi kiem tra ten dang nhap", e);
        }
    }

    public boolean existsByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String sql = "SELECT 1 FROM HOCVIEN WHERE email = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi khi kiem tra email", e);
        }
    }

    public boolean existsByPhone(String phone) {
        String sql = "SELECT 1 FROM HOCVIEN WHERE SDT = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phone);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi khi kiem tra so dien thoai", e);
        }
    }

    public boolean registerHocVien(String username, String password, String hoTen, String email, String sdt, String diaChi) {
        String insertNguoiDungSql = "INSERT INTO NGUOIDUNG (tenDangNhap, matKhau, maVaiTro) VALUES (?, ?, ?)";
        String insertHocVienSql = "INSERT INTO HOCVIEN (maND, hoTen, email, SDT, diaChi) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBContext.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement userStatement = connection.prepareStatement(insertNguoiDungSql, Statement.RETURN_GENERATED_KEYS)) {
                userStatement.setString(1, username);
                userStatement.setString(2, password);
                userStatement.setInt(3, HOC_VIEN_ROLE_ID);
                int affectedRows = userStatement.executeUpdate();

                if (affectedRows == 0) {
                    connection.rollback();
                    return false;
                }

                int maND;
                try (ResultSet generatedKeys = userStatement.getGeneratedKeys()) {
                    if (!generatedKeys.next()) {
                        connection.rollback();
                        return false;
                    }
                    maND = generatedKeys.getInt(1);
                }

                try (PreparedStatement hocVienStatement = connection.prepareStatement(insertHocVienSql)) {
                    hocVienStatement.setInt(1, maND);
                    hocVienStatement.setString(2, hoTen);
                    hocVienStatement.setString(3, email == null || email.trim().isEmpty() ? null : email);
                    hocVienStatement.setString(4, sdt);
                    hocVienStatement.setString(5, diaChi == null || diaChi.trim().isEmpty() ? null : diaChi);
                    int hocVienRows = hocVienStatement.executeUpdate();
                    if (hocVienRows == 0) {
                        connection.rollback();
                        return false;
                    }
                }

                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi khi dang ky hoc vien", e);
        }
    }
}