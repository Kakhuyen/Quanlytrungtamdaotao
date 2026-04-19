package model;

import java.sql.Date;

public class ClassRoom {
    private int    classId;
    private int    courseId;
    private String className;
    private int    stuMax;
    private Date   startDate;

    public ClassRoom() {}

    public ClassRoom(int classId, int courseId, String className,
                     int stuMax, Date startDate) {
        this.classId   = classId;
        this.courseId  = courseId;
        this.className = className;
        this.stuMax    = stuMax;
        this.startDate = startDate;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStuMax() {
        return stuMax;
    }

    public void setStuMax(int stuMax) {
        this.stuMax = stuMax;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}