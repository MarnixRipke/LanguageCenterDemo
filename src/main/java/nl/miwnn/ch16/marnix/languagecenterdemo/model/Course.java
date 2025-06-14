package nl.miwnn.ch16.marnix.languagecenterdemo.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Marnix Ripke
 * The concept of a language course of which the language center can have multiple lessons.
 */

@Entity
public class Course {

    @Id @GeneratedValue
    private Long courseId;

    private String title;

    @ManyToMany
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set <Lesson> lessons;

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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}