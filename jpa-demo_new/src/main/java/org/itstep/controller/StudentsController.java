package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.service.GroupService;
import org.itstep.service.StudentService;
import org.itstep.service.dto.StudentDto;
import org.itstep.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(StudentsController.BASE_URL)
public class StudentsController {

    public static final String BASE_URL = "/students";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;
    public static final String VIEW_PATH = "student";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";

    final StudentService studentService;
    final GroupService groupService;

    @Autowired
    public StudentsController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size) {
        log.info("index() page: {} size: {}", page, size);
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size);
        Page<StudentDto> studentDtoPage = studentService.findAll(pageable);
        model.addAttribute("students", studentDtoPage.getContent());
        model.addAttribute("page", studentDtoPage);
        return INDEX_PATH;
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.info("create()");
        model.addAttribute("studentDto", new StudentDto());
        model.addAttribute("groups", groupService.findAll(Pageable.unpaged()).getContent());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute StudentDto studentDto,
                         BindingResult bindingResult, Model model) {
        log.debug("Create student: " + studentDto.toString());
        if (bindingResult.hasErrors()) {
            model.addAttribute("groups", groupService.findAll(Pageable.unpaged()).getContent());
            log.error(bindingResult.toString());
            return FORM_PATH;
        }
        studentService.save(studentDto);
        log.debug("Student saved");
        return REDIRECT_INDEX;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        studentService.delete(id);
        return REDIRECT_INDEX;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<StudentDto> found = studentService.findOne(id);
        if (found.isPresent()) {
            model.addAttribute("groups", groupService.findAll(Pageable.unpaged()).getContent());
            model.addAttribute("studentDto", found.get());
            return FORM_PATH;
        }
        return REDIRECT_INDEX;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @Validated @ModelAttribute StudentDto studentDto,
                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("groups", groupService.findAll(Pageable.unpaged()).getContent());
            return FORM_PATH;
        }
        studentService.save(studentDto);
        return REDIRECT_INDEX;
    }
}
