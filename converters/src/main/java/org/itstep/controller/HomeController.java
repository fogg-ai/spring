package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.model.Student;
import org.itstep.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class HomeController {

    final AcademyService academyService;

    @Autowired
    public HomeController(AcademyService academyService) {
        this.academyService = academyService;
    }

    @GetMapping(path="/")
    public String index(Model model) {
        log.info("index()");
        model.addAttribute("students", academyService.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.info("create()");
        model.addAttribute("student", new Student());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute Student s,
                         BindingResult bindingResult) {
        log.debug("Create student: " + s.toString());
        if(!bindingResult.hasErrors()) {
            academyService.save(s);
            log.debug("Student saved");
            return "redirect:/";
        }
        log.error(bindingResult.toString());
        return "create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,
                         RedirectAttributes redirectAttributes) {
        if(academyService.delete(id)) {
            redirectAttributes.addAttribute("message", "OK");
        } else {
            redirectAttributes.addAttribute("message", "Error");
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        String url;
        try {
            model.addAttribute("student", academyService.getOne(id));
            url = "create";
        } catch(EmptyResultDataAccessException ex) {
            url = "redirect:/";
        }
        return url;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @Validated @ModelAttribute Student student,
                       BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            academyService.update(student);
            return "redirect:/";
        }
        return "/edit/"+id;
    }
}
