package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.service.GroupService;
import org.itstep.service.TeacherService;
import org.itstep.service.impl.StudentServiceImpl;
import org.itstep.service.dto.GroupDto;
import org.itstep.service.dto.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(TeacherController.BASE_URL)
public class TeacherController {

    public static final String BASE_URL = "/teachers";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;
    public static final String VIEW_PATH = "teacher";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";

    final TeacherService teacherService;
    final GroupService groupService;

    public TeacherController(TeacherService teacherService, GroupService groupService) {
        this.teacherService = teacherService;
        this.groupService = groupService;
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size) {
        log.info("index() page: {} size: {}", page, size);
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size);
        Page<TeacherDto> teacherDtoPage = teacherService.findAll(pageable);
        model.addAttribute("teachersDto", teacherDtoPage.getContent());
        model.addAttribute("page", teacherDtoPage);
        return INDEX_PATH;
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.info("create()");
        model.addAttribute("teacherDto", new TeacherDto());
        model.addAttribute("groups", groupService.findAll(Pageable.unpaged()).getContent());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute TeacherDto teacherDto,
                         BindingResult bindingResult, Model model) {
        log.debug("Create teacher: " + teacherDto.toString());
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.toString());
            model.addAttribute("groups", groupService.findAll(Pageable.unpaged()).getContent());
            return FORM_PATH;
        }
        teacherService.save(teacherDto);
        log.debug("Teacher saved");
        return REDIRECT_INDEX;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        teacherService.delete(id);
        return REDIRECT_INDEX;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<TeacherDto> found = teacherService.findOne(id);
        if (found.isPresent()) {
            model.addAttribute("teacherDto", found.get());
            model.addAttribute("groups", groupService.findAll(Pageable.unpaged()).getContent());
            return FORM_PATH;
        }
        return REDIRECT_INDEX;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @Validated @ModelAttribute TeacherDto teacherDto,
                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("groups", groupService.findAll(Pageable.unpaged()).getContent());
            return FORM_PATH;
        }
        teacherService.save(teacherDto);
        return REDIRECT_INDEX;
    }
}
