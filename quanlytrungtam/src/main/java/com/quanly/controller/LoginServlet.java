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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

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
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        NguoiDung user = userDAO.checkLogin(username, password);
        if (user == null) {
                    request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        int maVaiTro = user.getVaiTro().getMaVaiTro();
        if (maVaiTro == 1) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
            return;
        }

        if (maVaiTro == 2) {
            response.sendRedirect(request.getContextPath() + "/hocvien/home.jsp");
            return;
        }

        session.invalidate();
        request.setAttribute("error", "Vai trò không hợp lệ.");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}