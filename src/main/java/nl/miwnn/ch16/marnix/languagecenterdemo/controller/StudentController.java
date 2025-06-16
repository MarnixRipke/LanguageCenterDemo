package nl.miwnn.ch16.marnix.languagecenterdemo.controller;

import nl.miwnn.ch16.marnix.languagecenterdemo.model.Student;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marnix Ripke
 * Handle all requests related primarily to students
 */

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/overview")
    private String showStudentOverview(Model datamodel) {
        datamodel.addAttribute("allStudents", studentRepository.findAll());
        datamodel.addAttribute("formStudent", new Student());

        return "studentOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateStudent(@ModelAttribute("formStudent") Student studentToBeSaved,
                                       BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            studentRepository.save(studentToBeSaved);
        }

        return "redirect:/student/overview";
    }
}