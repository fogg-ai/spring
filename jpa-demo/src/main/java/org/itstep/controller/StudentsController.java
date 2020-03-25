package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.dto.GroupDto;
import org.itstep.dto.StudentDto;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @ModelAttribute("groups")
    List<GroupDto> getGroups() {
        return academyService.findGroupsDto();
    }

    @GetMapping
    public String index(Model model) {
        log.info("index()");
        model.addAttribute("students", academyService.findStudentsDto());
        return "student/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.info("create()");
        model.addAttribute("studentDto", new StudentDto());
        return "student/form";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute StudentDto studentDto,
                         BindingResult bindingResult) {
        log.debug("Create student: " + studentDto.toString());
        if (!bindingResult.hasErrors()) {
            academyService.save(studentDto);
            log.debug("Student saved");
            return "redirect:/students";
        }
        log.error(bindingResult.toString());
        return "student/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,
                         RedirectAttributes redirectAttributes) {
        if (academyService.delete(id)) {
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
            StudentDto studentDto = academyService.getStudentDto(id);
            model.addAttribute("studentDto", studentDto);
            url = "student/form";
        } catch (EmptyResultDataAccessException ex) {
            url = "redirect:/students";
        }
        return url;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @Validated @ModelAttribute StudentDto studentDto,
                       BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            academyService.update(studentDto);
            return "redirect:/students";
        }
        return "student/form";
    }
}
