package nl.miwnn.ch16.marnix.languagecenterdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;

/**
 * @author Marnix Ripke
 * Handle all requests related directly or primarly to courses.
 */

@Controller
public class CourseController {

   @GetMapping("/")
    private String showCourseOverview(Model datamodel) {
        datamodel.addAttribute("nu", LocalDateTime.now());

        return "courseOverview";

    }
}
