package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.model.Student;
import org.itstep.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/students")
public class StudentsController {

    final AcademyService academyService;

    @Autowired
    public StudentsController(AcademyService academyService) {
        this.academyService = academyService;
    }

    @GetMapping
    public String index(Model model) {
        log.info("index()");
        model.addAttribute("students", academyService.findAll());
        return "student/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.info("create()");
        model.addAttribute("student", new Student());
        return "student/form";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute Student s,
                         BindingResult bindingResult) {
        log.debug("Create student: " + s.toString());
        if(!bindingResult.hasErrors()) {
            academyService.save(s);
            log.debug("Student saved");
            return "redirect:/students";
        }
        log.error(bindingResult.toString());
        return "student/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,
                         RedirectAttributes redirectAttributes) {
        if(academyService.delete(id)) {
            redirectAttributes.addAttribute("message", "OK");
        } else {
            redirectAttributes.addAttribute("message", "Error");
        }
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        String url;
        try {
            model.addAttribute("student", academyService.getOne(id));
            url = "student/form";
        } catch(EmptyResultDataAccessException ex) {
            url = "redirect:/students";
        }
        return url;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @Validated @ModelAttribute Student student,
                       BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            academyService.update(student);
            return "redirect:/students";
        }
        return "student/form";
    }
}
