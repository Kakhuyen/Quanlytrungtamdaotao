package com.quanly.controller;

import com.quanly.dao.UserDAO;
import com.quanly.model.NguoiDung;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]{4,50}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            NguoiDung user = (NguoiDung) session.getAttribute("user");
            if (user != null && user.getVaiTro() != null) {
                int maVaiTro = user.getVaiTro().getMaVaiTro();
                if (maVaiTro == 1) {
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
                    return;
                }
                if (maVaiTro == 2) {
                    response.sendRedirect(request.getContextPath() + "/hocvien/home.jsp");
                    return;
                }
            }
        }

        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = normalize(request.getParameter("username"));
        String password = normalize(request.getParameter("password"));
        String confirmPassword = normalize(request.getParameter("confirmPassword"));
        String hoTen = normalize(request.getParameter("hoTen"));
        String email = normalize(request.getParameter("email"));
        String sdt = normalize(request.getParameter("sdt"));
        String diaChi = normalize(request.getParameter("diaChi"));

        request.setAttribute("username", username);
        request.setAttribute("hoTen", hoTen);
        request.setAttribute("email", email);
        request.setAttribute("sdt", sdt);
        request.setAttribute("diaChi", diaChi);

        boolean hasError = false;

        if (isBlank(username)) {
            setError(request, "usernameError", "Tên đăng nhập là bắt buộc.");
            hasError = true;
        }
        if (isBlank(hoTen)) {
            setError(request, "hoTenError", "Họ tên là bắt buộc.");
            hasError = true;
        }
        if (isBlank(password)) {
            setError(request, "passwordError", "Mật khẩu là bắt buộc.");
            hasError = true;
        }
        if (isBlank(confirmPassword)) {
            setError(request, "confirmPasswordError", "Vui lòng xác nhận mật khẩu.");
            hasError = true;
        }
        if (isBlank(sdt)) {
            setError(request, "sdtError", "Số điện thoại là bắt buộc.");
            hasError = true;
        }

        if (!isBlank(username) && !USERNAME_PATTERN.matcher(username).matches()) {
            setError(request, "usernameError", "Tên đăng nhập phải từ 4 đến 50 ký tự, chỉ gồm chữ, số và dấu gạch dưới.");
            hasError = true;
        }

        if (!isBlank(hoTen) && (hoTen.length() < 2 || hoTen.length() > 100)) {
            setError(request, "hoTenError", "Họ tên phải từ 2 đến 100 ký tự.");
            hasError = true;
        }

        if (!isBlank(password) && (password.length() < 6 || password.length() > 50)) {
            setError(request, "passwordError", "Mật khẩu phải từ 6 đến 50 ký tự.");
            hasError = true;
        }

        if (!isBlank(password) && !isBlank(confirmPassword) && !password.equals(confirmPassword)) {
            setError(request, "confirmPasswordError", "Mật khẩu xác nhận không khớp.");
            hasError = true;
        }

        if (!isBlank(sdt) && !sdt.matches("\\d{10}")) {
            setError(request, "sdtError", "Số điện thoại phải gồm đúng 10 chữ số.");
            hasError = true;
        }

        if (email != null && !email.isEmpty() && !EMAIL_PATTERN.matcher(email).matches()) {
            setError(request, "emailError", "Email không hợp lệ.");
            hasError = true;
        }

        if (diaChi != null && diaChi.length() > 200) {
            setError(request, "diaChiError", "Địa chỉ không được vượt quá 200 ký tự.");
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("error", "Vui lòng kiểm tra lại các trường dữ liệu bên dưới.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (userDAO.existsByUsername(username)) {
            setError(request, "usernameError", "Tên đăng nhập đã tồn tại.");
            request.setAttribute("error", "Vui lòng kiểm tra lại các trường dữ liệu bên dưới.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (userDAO.existsByPhone(sdt)) {
            setError(request, "sdtError", "Số điện thoại đã được sử dụng.");
            request.setAttribute("error", "Vui lòng kiểm tra lại các trường dữ liệu bên dưới.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (userDAO.existsByEmail(email)) {
            setError(request, "emailError", "Email đã được sử dụng.");
            request.setAttribute("error", "Vui lòng kiểm tra lại các trường dữ liệu bên dưới.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        try {
            boolean created = userDAO.registerHocVien(username, password, hoTen, email, sdt, diaChi);
            if (!created) {
                request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
        } catch (RuntimeException e) {
            request.setAttribute("error", "Đăng ký thất bại. Vui lòng kiểm tra dữ liệu và thử lại.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/login?message=registered");
    }

    private String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private void setError(HttpServletRequest request, String attributeName, String message) {
        request.setAttribute(attributeName, message);
    }
}