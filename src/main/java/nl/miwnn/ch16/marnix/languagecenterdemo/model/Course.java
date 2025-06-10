package nl.miwnn.ch16.marnix.languagecenterdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

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

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;

    public int getNumberOfLessons() {
        return lessons.size();
    }

    public int getNumberOfAvailableLessons () {
        int count = 0;

        for (Lesson lesson : lessons) {
            if (lesson.getNotFull()) {
                count++;
            }
        }

        return count;
    }

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

    public List<Lesson> getLessons() {
        return lessons;
    }
}
