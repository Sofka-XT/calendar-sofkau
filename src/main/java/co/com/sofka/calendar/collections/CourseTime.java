package co.com.sofka.calendar.collections;

import java.util.List;

public class CourseTime {

    private String courseId;
    private String courseName;
    private List<Time> categories;

    public CourseTime(String courseId, String courseName, List<Time> categories) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.categories = categories;
    }

    public CourseTime() {

    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Time> getCategories() {
        return categories;
    }

    public void setCategories(List<Time> categories) {
        this.categories = categories;
    }
}