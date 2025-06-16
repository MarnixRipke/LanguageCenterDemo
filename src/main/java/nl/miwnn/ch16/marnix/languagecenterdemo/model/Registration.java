package nl.miwnn.ch16.marnix.languagecenterdemo.model;

import jakarta.persistence.*;

/**
 * @author Marnix Ripke
 * Represents a registration of a course from the language center done by a student
 */

@Entity
public class Registration {


    @Id @GeneratedValue
    private Long RegistrationId;


    @ManyToOne
    private Course course;

    public Registration(Course course) {
        this.course = course;
    }

    // exists for JPA
    public Registration() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getRegistrationId() {
        return RegistrationId;
    }

    public void setRegistrationId(Long RegistrationId) {
        this.RegistrationId = RegistrationId;
    }
}
