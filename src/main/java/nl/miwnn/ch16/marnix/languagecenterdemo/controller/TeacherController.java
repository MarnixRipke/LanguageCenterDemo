package nl.miwnn.ch16.marnix.languagecenterdemo.controller;

import nl.miwnn.ch16.marnix.languagecenterdemo.model.Teacher;
import nl.miwnn.ch16.marnix.languagecenterdemo.repositories.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Marnix Ripke
 * Handle all requests related primarily to teachers
 */

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/overview")
    private String showTeacherOverview(Model datamodel) {
        datamodel.addAttribute("allTeachers", teacherRepository.findAll());
        datamodel.addAttribute("formTeacher", new Teacher());

        return "teacherOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateTeacher(@ModelAttribute("formTeacher") Teacher teacherToBeSaved,
                                       BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            teacherRepository.save(teacherToBeSaved);
        }

        return "redirect:/teacher/overview";
    }
}