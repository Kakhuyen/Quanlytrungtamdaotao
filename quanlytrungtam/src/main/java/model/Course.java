package model;

public class Course {
    private int    courseId;
    private String courseName;
    private String description;
    private double tuition;
    private String image;

    public Course() {}

    public Course(int courseId, String courseName, String description,
                  double tuition, String image) {
        this.courseId    = courseId;
        this.courseName  = courseName;
        this.description = description;
        this.tuition     = tuition;
        this.image       = image;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTuition() {
        return tuition;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}