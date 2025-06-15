package nl.miwnn.ch16.marnix.languagecenterdemo.controller;

import nl.miwnn.ch16.marnix.languagecenterdemo.model.Course;
import nl.miwnn.ch16.marnix.languagecenterdemo.model.Student;
import nl.miwnn.ch16.marnix.languagecenterdemo.model.Teacher;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.CourseRepository;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.StudentRepository;
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
    private final StudentRepository studentRepository;

    public InitializeController(TeacherRepository teacherRepository, CourseRepository courseRepository,
                                StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
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

        Student henk = createStudent("Henk");
        Student annie = createStudent("Annie");

        Course frenchA22 = makeCourse("French A2.2",
                "https://frans.nl/wp-content/uploads/2020/04/shutterstock_1157463325.jpg"
                , fatale);

        Course germanB21 = makeCourse("German B2.1",
                "https://prod-media.bab.la/images/pic/living/Germany/Language_learning/bannergermanidiom.jpg",
                steinmeier);

        Course frenchB12 = makeCourse("French B1.2",
                "https://img.freepik.com/premium-vector/frans-leren-concept-illustratie_277904-9078.jpg",
                poulet, fatale);
    }

    private Teacher createTeacher(String name) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacherRepository.save(teacher);
        return teacher;
    }

    private Student createStudent(String name) {
        Student student = new Student();
        student.setName(name);
        studentRepository.save(student);
        return student;
    }

    private Course makeCourse (String title, String image, Teacher ... teachers) {
        Course course = new Course();

        course.setTitle(title);
        course.setImageURL(image);
        Set<Teacher> teacherSet = new HashSet<>(Arrays.asList(teachers));
        course.setTeachers(teacherSet);

        courseRepository.save(course);
        return course;
    }
}