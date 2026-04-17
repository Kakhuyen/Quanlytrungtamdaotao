package controller;
import dao.LopHocDAO;
import model.LopHoc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/DSLop")
public class LopHocServlet extends HttpServlet {

    private LopHocDAO lopHocDAO = new LopHocDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<LopHoc> ds = lopHocDAO.getAll();

        request.setAttribute("dsLop", ds);

        request.getRequestDispatcher("view/DSLopHoc.jsp").forward(request, response);
    }
}
