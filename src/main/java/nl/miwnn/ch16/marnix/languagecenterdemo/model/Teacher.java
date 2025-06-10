package nl.miwnn.ch16.marnix.languagecenterdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Marnix Ripke
 * A person that is responsible for teaching a course at the language center
 */

@Entity
public class Teacher {

    @Id @GeneratedValue
    private Long teacherId;

    private String name;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
