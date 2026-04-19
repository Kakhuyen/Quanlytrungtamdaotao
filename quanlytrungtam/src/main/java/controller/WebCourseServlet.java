package controller;

import model.Course;
import service.CourseService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/courses") // URL dành cho học viên
public class WebCourseServlet extends HttpServlet {

    private final CourseService service = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String keyword = req.getParameter("keyword");

        List<Course> courses;
        if (keyword != null && !keyword.trim().isEmpty()) {
            courses = service.searchCourses(keyword.trim());
            req.setAttribute("keyword", keyword.trim());
        } else {
            courses = service.getAllCourses();
        }

        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/web/course-grid.jsp").forward(req, resp);
    }
}