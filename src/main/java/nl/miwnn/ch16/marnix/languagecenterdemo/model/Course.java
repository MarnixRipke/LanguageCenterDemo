package nl.miwnn.ch16.marnix.languagecenterdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Marnix Ripke
 * The concept of a language course of which the language center can have multiple lessons.
 */

@Entity
public class Course {

    @Id @GeneratedValue
    private Long courseId;

    private String title;
    private String teacher;


    @Override
    public String toString() {
        return String.format("Course title: %s, Teacher: %s", this.title, this.teacher);
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }


}
