package controller;
import dao.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class DangKyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LopHocDAO dao = new LopHocDAO();
        request.setAttribute("list", dao.getAll());
        request.getRequestDispatcher("class-list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int maLH = Integer.parseInt(request.getParameter("maLH"));
        int maND = (int) request.getSession().getAttribute("userId");

        DangKyDAO dao = new DangKyDAO();

        if (dao.isDangKy(maND, maLH)) {
            request.setAttribute("error", "Đã đăng ký!");
        } else if (dao.isFull(maLH)) {
            request.setAttribute("error", "Lớp đã đầy!");
        } else {
            dao.register(maND, maLH);
            request.setAttribute("success", "Đăng ký thành công!");
        }

        doGet(request, response);
    }
}