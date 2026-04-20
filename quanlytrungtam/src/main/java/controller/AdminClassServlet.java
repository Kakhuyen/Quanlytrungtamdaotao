package controller;

import com.quanly.dao.ClassDAO;
import com.quanly.model.Classroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@WebServlet("/admin/class")
public class AdminClassServlet extends HttpServlet {

    private final ClassDAO classDAO = new ClassDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = safe(request.getParameter("action"));
        String msg = safe(request.getParameter("msg"));

        try {

            if ("delete".equals(action)) {

                int id = parseInt(request.getParameter("id"));

                if (id > 0) {

                    boolean ok = classDAO.delete(id);

                    if (ok) {
                        response.sendRedirect(
                                request.getContextPath()
                                        + "/admin/class?msg=Xóa thành công"
                        );
                    } else {
                        response.sendRedirect(
                                request.getContextPath()
                                        + "/admin/class?msg=Không thể xóa lớp"
                        );
                    }
                    return;
                }
            }

            if ("edit".equals(action)) {

                int id = parseInt(request.getParameter("id"));

                if (id > 0) {
                    Classroom lop = classDAO.findById(id);
                    request.setAttribute("lop", lop);
                }
            }

            String keyword = safe(request.getParameter("keyword"));

            List<Classroom> dsLop;

            if (keyword.isEmpty()) {
                dsLop = classDAO.getAll();     // lấy toàn bộ từ DATABASE
            } else {
                dsLop = classDAO.search(keyword); // tìm từ DATABASE
            }

            request.setAttribute("dsLop", dsLop);
            request.setAttribute("keyword", keyword);
            request.setAttribute("msg", msg);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("msg",
                    "Không thể tải dữ liệu từ database.");

            request.setAttribute("dsLop",
                    Collections.emptyList());
        }

        request.getRequestDispatcher("/admin/class-list.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = safe(request.getParameter("action"));

        int maLH = parseInt(request.getParameter("maLH"));
        int maKH = parseInt(request.getParameter("maKH"));
        int maTT = parseInt(request.getParameter("maTT"));
        int siSoMax = parseInt(request.getParameter("siSoMax"));

        String tenLH = safe(request.getParameter("tenLH"));
        String ngay = safe(request.getParameter("ngayKhaiGiang"));

        try {

            Classroom lop = new Classroom();

            lop.setMaLH(maLH);
            lop.setMaKH(maKH);
            lop.setMaTT(maTT);
            lop.setTenLH(tenLH);
            lop.setSiSoMax(siSoMax);
            lop.setNgayKhaiGiang(LocalDate.parse(ngay));

            boolean ok;

            if ("update".equals(action)) {
                ok = classDAO.update(lop);
            } else {
                ok = classDAO.insert(lop);
            }

            if (ok) {
                response.sendRedirect(
                        request.getContextPath()
                                + "/admin/class?msg=Thành công"
                );
            } else {
                response.sendRedirect(
                        request.getContextPath()
                                + "/admin/class?msg=Thất bại"
                );
            }

        } catch (Exception e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/admin/class?msg=Dữ liệu không hợp lệ"
            );
        }
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    private String safe(String s) {
        return s == null ? "" : s.trim();
    }
}