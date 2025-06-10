package nl.miwnn.ch16.marnix.languagecenterdemo.model;

import jakarta.persistence.*;

/**
 * @author Marnix Ripke
 * Represents a lesson of a course from the language center
 */

@Entity
public class Lesson {

    private static final boolean DEAFULT_AVAILABLE = true;

    @Id @GeneratedValue
    private Long lessonId;

    private Boolean notFull;

    @ManyToOne
    private Course course;

    public Lesson(Course course) {
        this.course = course;
        this.notFull = DEAFULT_AVAILABLE;
    }

    // exists for JPA
    public Lesson() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Boolean getNotFull() {
        return notFull;
    }

    public void setNotFull(Boolean notFull) {
        this.notFull = notFull;
    }
}
