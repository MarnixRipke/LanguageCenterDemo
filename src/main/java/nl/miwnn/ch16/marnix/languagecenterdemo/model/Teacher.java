package nl.miwnn.ch16.marnix.languagecenterdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marnix Ripke
 * A person that is responsible for teaching a course at the language center
 */

@Entity
public class Teacher {

    @Id @GeneratedValue
    private Long teacherId;

    private String name;

    @ManyToMany(mappedBy = "teachers")
    private List<Course> courses = new ArrayList<>();

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
