package nl.miwnn.ch16.marnix.languagecenterdemo.controller;

import nl.miwnn.ch16.marnix.languagecenterdemo.model.Course;
import nl.miwnn.ch16.marnix.languagecenterdemo.model.Lesson;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.CourseRepository;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.LessonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Marnix Ripke
 * Handle all requests related to lessons
 */

@Controller
@RequestMapping("/lesson")
public class LessonController {
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public LessonController(CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/new/{courseId}")
    private String createNewLesson(@PathVariable("courseId") Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if (optionalCourse.isPresent()) {
            Lesson lesson = new Lesson(optionalCourse.get());
            lessonRepository.save(lesson);

        }

        return "redirect:/";
    }
}
