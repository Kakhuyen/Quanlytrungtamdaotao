package controller;

import model.Course;
import service.CourseService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/courses")
public class CourseServlet extends HttpServlet {

    private final CourseService service = new CourseService();

    // GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listCourses(req, resp);
                break;
            case "add":
                req.getRequestDispatcher("/admin/course-list.jsp").forward(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteCourse(req, resp);
                break;
            case "search":
                searchCourses(req, resp);
                break;
            default:
                listCourses(req, resp);
        }
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "insert":
                insertCourse(req, resp);
                break;
            case "update":
                updateCourse(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/admin/courses");
        }
    }

    // ACTIONS
    private void listCourses(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Course> courses = service.getAllCourses();
        req.setAttribute("courses", courses);
        req.setAttribute("totalCourse", courses.size());
        req.getRequestDispatcher("/admin/course-list.jsp").forward(req, resp);
    }

    private void searchCourses(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if (keyword == null) keyword = "";
        List<Course> courses = service.searchCourses(keyword.trim());
        req.setAttribute("courses", courses);
        req.setAttribute("keyword", keyword);
        req.setAttribute("totalCourse", courses.size());
        req.getRequestDispatcher("/admin/course-list.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int courseId = Integer.parseInt(req.getParameter("id"));
            Course course = service.getCourse(courseId);
            if (course == null) {
                req.setAttribute("errorMsg", "Không tìm thấy khóa học.");
                listCourses(req, resp);
                return;
            }
            req.setAttribute("course", course);
            req.setAttribute("editMode", true);
            // Tải lại danh sách để hiển thị bảng bên cạnh
            req.setAttribute("courses", service.getAllCourses());
            req.getRequestDispatcher("/admin/course-list.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/courses");
        }
    }

    private void insertCourse(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Course course = buildCourseFromRequest(req, 0);
        String error = service.validate(course);

        if (error != null) {
            req.setAttribute("errorMsg", error);
            req.setAttribute("course", course);
            req.setAttribute("courses", service.getAllCourses());
            req.getRequestDispatcher("/admin/course-list.jsp").forward(req, resp);
            return;
        }

        boolean ok = service.insertCourse(course);
        HttpSession session = req.getSession();
        if (ok) session.setAttribute("successMsg", "Thêm khóa học thành công!");
        else session.setAttribute("errorMsg", "Thêm thất bại. Vui lòng thử lại.");

        resp.sendRedirect(req.getContextPath() + "/admin/courses");
    }

    private void updateCourse(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        Course course = buildCourseFromRequest(req, courseId);
        String error = service.validate(course);

        if (error != null) {
            req.setAttribute("errorMsg", error);
            req.setAttribute("course", course);
            req.setAttribute("editMode", true);
            req.setAttribute("courses", service.getAllCourses());
            req.getRequestDispatcher("/admin/course-list.jsp").forward(req, resp);
            return;
        }

        boolean ok = service.updateCourse(course);
        HttpSession session = req.getSession();
        if (ok) session.setAttribute("successMsg", "Cập nhật thành công!");
        else session.setAttribute("errorMsg", "Cập nhật thất bại. Vui lòng thử lại.");

        resp.sendRedirect(req.getContextPath() + "/admin/courses");
    }

    private void deleteCourse(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try {
            int courseId = Integer.parseInt(req.getParameter("id"));
            boolean ok = service.deleteCourse(courseId);
            if (ok) req.setAttribute("successMsg", "Xóa khóa học thành công!");
            else req.setAttribute("errorMsg", "Xóa thất bại.");
        } catch (NumberFormatException e) {
            req.setAttribute("errorMsg", "ID không hợp lệ.");
        }
        listCourses(req, resp);
    }

    // HELPER
    private Course buildCourseFromRequest(HttpServletRequest req, int courseId) {
        String courseName = req.getParameter("courseName");
        String desc = req.getParameter("description");
        String imageName = req.getParameter("image");
        double tuition = 0;
        try {
            tuition = Double.parseDouble(req.getParameter("tuition"));
        } catch (NumberFormatException ignored) {
        }

        Course c = new Course();
        c.setCourseId(courseId);
        c.setCourseName(courseName != null ? courseName.trim() : "");
        c.setDescription(desc);
        c.setTuition(tuition);
        c.setImage(imageName != null ? imageName.trim() : "");
        return c;
    }
}