package nl.miwnn.ch16.marnix.languagecenterdemo.controller;


import nl.miwnn.ch16.marnix.languagecenterdemo.model.Course;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


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

    @GetMapping({"/", "/course/overview"})
    private String showCourseOverview(Model datamodel) {
        datamodel.addAttribute("allCourses", courseRepository.findAll());

        return "courseOverview";

    }

    @GetMapping("/course/new")
    private String showNewCourseForm(Model datamodel) {
        datamodel.addAttribute("formCourse", new Course());

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
}
