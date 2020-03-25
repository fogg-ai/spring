package org.itstep.controller;

import org.itstep.dto.GroupDto;
import org.itstep.dto.StudentDto;
import org.itstep.dto.TeacherDto;
import org.itstep.model.Teacher;
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

@Controller
@RequestMapping(path = "/teacher")
public class TeacherController {

    final AcademyService academyService;


    @Autowired
    public TeacherController(AcademyService academyService) {
        this.academyService = academyService;
    }

    @ModelAttribute("groups")
    List<GroupDto> groupGroups() {
        return academyService.findGroupsDto();
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("teachers", academyService.getAllTeacherDto());
        return "teacher/index";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("teacherDto", new TeacherDto());
        return "teacher/form";
    }

    @PostMapping(value = "/create")
    public String create(TeacherDto teacherDto){
        academyService.save(teacherDto);
        return "redirect:/teacher";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,
                         RedirectAttributes redirectAttributes) {
        if (academyService.deleteTeacher(id)) {
            redirectAttributes.addAttribute("message", "OK");
        } else {
            redirectAttributes.addAttribute("message", "Error");
        }
        return "redirect:/teacher";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        String url;
        try {
            TeacherDto studentDto = academyService.getTeacherDto(id);
            model.addAttribute("teacherDto", studentDto);
            url = "teacher/form";
        } catch (EmptyResultDataAccessException ex) {
            url = "redirect:/teacher";
        }
        return url;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @Validated @ModelAttribute TeacherDto teacherDto,
                       BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            academyService.update(teacherDto);
            return "redirect:/teacher";
        }
        return "teacher/form";
    }
}
