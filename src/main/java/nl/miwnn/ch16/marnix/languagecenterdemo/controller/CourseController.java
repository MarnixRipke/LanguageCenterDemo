package nl.miwnn.ch16.marnix.languagecenterdemo.controller;


import nl.miwnn.ch16.marnix.languagecenterdemo.model.Course;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.CourseRepository;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.RegistrationRepository;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Marnix Ripke
 * Handle all requests related directly or primarly to courses.
 */

@Controller
public class CourseController {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final RegistrationRepository registrationRepository;

    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.registrationRepository = registrationRepository;
    }

    @GetMapping({"/", "/course/overview"})
    private String showCourseOverview(Model datamodel) {
        List<Course> allCourses = courseRepository.findAll();

        for (Course course : allCourses) {
            int count = registrationRepository.countByCourse(course);
            course.setCurrentRegistrations(count);
        }

        datamodel.addAttribute("allCourses", allCourses);
        return "courseOverview";
    }

    @GetMapping("/course/new")
    private String showNewCourseForm(Model datamodel) {
        datamodel.addAttribute("formCourse", new Course());
        datamodel.addAttribute("allTeachers", teacherRepository.findAll());
        return "courseForm";
    }

    @PostMapping("/course/save")
    private String saveOrUpdateCourse(@ModelAttribute("formCourse") Course courseToBeSaved,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
        } else {
            courseRepository.save(courseToBeSaved);
        }

        return "redirect:/course/overview";
    }

    @GetMapping("/course/delete/{courseId}")
    private String deleteCourse(@PathVariable("courseId") Long courseId) {
        courseRepository.deleteById(courseId);

        return "redirect:/course/overview";
    }

}