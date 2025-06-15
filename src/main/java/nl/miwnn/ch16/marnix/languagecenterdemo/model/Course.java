package nl.miwnn.ch16.marnix.languagecenterdemo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marnix Ripke
 * The concept of a language course of which the language center can have multiple lessons.
 */

@Entity
public class Course {

    private static final int MAXIMUM_AMOUNT_REGISTRATIONS = 8;

    @Id @GeneratedValue
    private Long courseId;

    private String title;
    private String imageURL;

    @ManyToMany
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Registration> registrations = new ArrayList<>();

    @Transient
    private int currentRegistrations;

    public void setCurrentRegistrations(int count) {
        this.currentRegistrations = count;
    }

    public int getNumberOfRegistrations() {
        return currentRegistrations;
    }

    public int getNumberOfAvailableRegistrations() {
        return MAXIMUM_AMOUNT_REGISTRATIONS;
    }

    public String getTeacherNames() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Teacher teacher : teachers) {
            stringBuilder.append(teacher.getName()).append(", ");
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return String.format("Course title: %s", this.title);
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

    public List<Teacher> getTeachers() {
        return teachers;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}