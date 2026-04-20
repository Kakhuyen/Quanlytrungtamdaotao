package controller;

import com.quanly.dao.ClassDAO;
import com.quanly.model.Classroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hocvien/class-list")
public class ClassServlet extends HttpServlet {

    private final ClassDAO classDAO = new ClassDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String keyword = normalize(request.getParameter("keyword"));

        List<Classroom> dsLop = new ArrayList<>();

        try {

            if (keyword.isEmpty()) {
                dsLop = classDAO.getAll();
            } else {
                dsLop = classDAO.search(keyword);
            }

            if (dsLop.isEmpty()) {
                request.setAttribute("msg",
                        "Không tìm thấy dữ liệu lớp học.");
            }

        } catch (Exception e) {

            request.setAttribute("msg",
                    "Không thể tải dữ liệu từ database.");
        }

        request.setAttribute("dsLop", dsLop);
        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("/hocvien/class-list.jsp")
                .forward(request, response);
    }

    private String normalize(String value) {

        if (value == null) {
            return "";
        }

        value = value.trim();

        if (value.length() > 100) {
            value = value.substring(0, 100);
        }

        return value;
    }
}