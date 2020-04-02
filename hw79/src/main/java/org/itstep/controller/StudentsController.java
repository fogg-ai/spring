package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.dto.GroupDto;
import org.itstep.dto.StudentDto;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.service.AcademyService;
import org.itstep.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j

@Controller
@RequestMapping("/students")
public class StudentsController {

    final AcademyService academyService;

    UploadFileService uploadFileService;


    @Autowired
    public StudentsController(UploadFileService uploadFileService, AcademyService academyService) {

        this.academyService = academyService;
        this.uploadFileService = uploadFileService;
    }

    @ModelAttribute("groups")
    List<GroupDto> getGroups() {
        return academyService.findGroupsDto();
    }

    @GetMapping
    public String index(Model model,
                        @RequestParam(required = false) Integer page,
                        @RequestParam(required = false) Integer size,
                        @RequestParam(required = false) String firstName,
                        @RequestParam(required = false) String lastName) {
        page = page == null ? 1 : page;
        size = size == null ? 5 : size;
        log.info("index()");
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        if (firstName == null && lastName == null) {
            model.addAttribute("students", academyService.findStudentsDto(size));
        } else {
            if("".equals(firstName) || "".equals(lastName)) {
                model.addAttribute("students", academyService.findStudentsForName(firstName, lastName));
            }else {
                model.addAttribute("students", academyService.findStudentsForNameAndLastName(firstName, lastName));
            }
        }

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
                         BindingResult bindingResult,
                         @RequestParam("photo") MultipartFile f) throws IOException {
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
                       BindingResult bindingResult,@RequestParam("photo") MultipartFile f) throws IOException {
        studentDto.setPhoto(uploadFileService.uploadPhoto(f));
        log.debug("Create student: " + studentDto.toString());
        if (!bindingResult.hasErrors()) {
            academyService.update(studentDto);
            return "redirect:/students";
        }
        return "student/form";
    }
}