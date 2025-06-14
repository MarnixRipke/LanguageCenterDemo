package nl.miwnn.ch16.marnix.languagecenterdemo.controller;

import nl.miwnn.ch16.marnix.languagecenterdemo.model.Course;
import nl.miwnn.ch16.marnix.languagecenterdemo.model.Lesson;
import nl.miwnn.ch16.marnix.languagecenterdemo.model.Teacher;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.CourseRepository;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.LessonRepository;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.TeacherRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marnix Ripke
 * Set some initial data in the database for (manual) testing purposes
 */

@Controller
public class InitializeController {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public InitializeController(TeacherRepository teacherRepository, CourseRepository courseRepository,
                                LessonRepository lessonRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if(teacherRepository.count() == 0) {
            initializeDB();
        }
    }

    private void initializeDB() {
        Teacher fatale = createTeacher("Madame Fatale");
        Teacher steinmeier = createTeacher("Herr Steinmeier");
        Teacher poulet = createTeacher("Monsieur Poulet");

        Course frenchA22 = makeCourse("French A2.2", fatale);
        makeLessons(frenchA22, 8);

        Course dutchB21 = makeCourse("Dutch B2.1", steinmeier);
        makeLessons(dutchB21, 12);
    }

    private Teacher createTeacher(String name) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacherRepository.save(teacher);
        return teacher;
    }

    private Course makeCourse (String title, Teacher ... teachers) {
        Course course = new Course();

        course.setTitle(title);
        Set<Teacher> teacherSet = new HashSet<>(Arrays.asList(teachers));
        course.setTeachers(teacherSet);

        courseRepository.save(course);
        return course;
    }

    private Lesson makeLesson (Course course, boolean notFull) {
        Lesson lesson = new Lesson();

        lesson.setCourse(course);
        lesson.setNotFull(notFull);

        lessonRepository.save(lesson);
        return lesson;
    }

    private void makeLessons (Course course, int numberOfLessons) {
        for (int i = 0; i < numberOfLessons; i++) {
            makeLesson(course, i < 30);
        }
    }
}
