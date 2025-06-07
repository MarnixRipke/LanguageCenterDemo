package nl.miwnn.ch16.marnix.languagecenterdemo.controller;


import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Marnix Ripke
 * Handle all requests related directly or primarly to courses.
 */

@Controller
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/")
    private String showCourseOverview(Model datamodel) {
        datamodel.addAttribute("allCourses", courseRepository.findAll());

        return "courseOverview";

    }
}
