package nl.miwnn.ch16.marnix.languagecenterdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Marnix Ripke
 * Represents a student at the language center
 */

@Entity
public class Student {

    @Id @GeneratedValue
    private Long studentId;

    private String name;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
