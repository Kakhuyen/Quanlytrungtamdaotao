package controller;
import dao.DangKyDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/dangky")
public class DangKyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object user = session.getAttribute("maND");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int maND = (int) user;
        int maLH = Integer.parseInt(request.getParameter("maLH"));
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");

        DangKyDAO dao = new DangKyDAO();
        String msg;

        if (dao.isDangKy(maND, maLH)) {
            msg = "Bạn đã đăng ký lớp này!";
        } else if (dao.isFull(maLH)) {
            msg = "Lớp đã đầy!";
        } else {
            dao.register(maND, maLH, hoTen, email, sdt, diaChi);
            msg = "Đăng ký thành công! Chờ duyệt.";
        }

        request.setAttribute("msg", msg);
        request.getRequestDispatcher("view/DangKy.jsp").forward(request, response);
    }
}