package nl.miwnn.ch16.marnix.languagecenterdemo.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import nl.miwnn.ch16.marnix.languagecenterdemo.model.Course;
import nl.miwnn.ch16.marnix.languagecenterdemo.model.Student;
import nl.miwnn.ch16.marnix.languagecenterdemo.model.Teacher;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.CourseRepository;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.StudentRepository;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.TeacherRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Marnix Ripke
 * Set some initial data in the database for (manual) testing purposes
 */

@Controller
public class InitializeController {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final Map<String, Student> studentCache = new HashMap<>();

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
        try {
            loadStudents();
            loadTeachers();
            loadCourses();
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Failed to initialize database from CSV files", e);
        }
    }

    private void loadStudents() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/test_data/students.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] studentLine : reader) {
                Student student = new Student();

                student.setName(studentLine[1]);

                studentRepository.save(student);
                studentCache.put(student.getName(), student);
            }
        }
    }

    private void loadTeachers() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/test_data/teachers.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] teacherLine : reader) {
                Teacher teacher = new Teacher();

                teacher.setName(teacherLine[1]);

                teacherRepository.save(teacher);
            }
        }
    }

    private void loadCourses() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("test_data/courses.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] courseLine : reader) {
                Course course = new Course();

                course.setImageURL(courseLine[0]);
                course.setTitle(courseLine[2]);
                course.setCurrentRegistrations(Integer.parseInt(courseLine[4]));

                String teacherName = courseLine[3];
                Teacher teacher = teacherRepository.findByName(teacherName)
                        .orElseGet(() -> {
                            Teacher newTeacher = new Teacher();
                            newTeacher.setName(teacherName);
                            return teacherRepository.save(newTeacher);
                        });

                course.getTeachers().add(teacher); // add to list

                courseRepository.save(course);
            }
        }
    }

}