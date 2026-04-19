package service;

import dao.CourseDAO;
import model.Course;

import java.util.List;

public class CourseService {

    private final CourseDAO courseDAO = new CourseDAO();

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public Course getCourse(int id) {
        return courseDAO.getCourse(id);
    }

    public boolean insertCourse(Course c) {
        return courseDAO.insertCourse(c);
    }

    public boolean updateCourse(Course c) {
        return courseDAO.updateCourse(c);
    }

    public boolean deleteCourse(int id) {
        return courseDAO.deleteCourse(id);
    }

    public List<Course> searchCourses(String kw) {
        return courseDAO.searchCourses(kw);
    }

    // VALIDATE FOR courseName and tuition
    public String validate(Course c) {
        if (c.getCourseName() == null || c.getCourseName().trim().isEmpty())
            return "Tên khóa học không được để trống.";
        if (c.getTuition() < 0)
            return "Học phí không được âm hoặc để trống.";
        return null;
    }


}