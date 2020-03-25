package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.dto.GroupDto;
import org.itstep.model.Group;
import org.itstep.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping(GroupController.BASE_URL)
public class GroupController {

    public static final String BASE_URL = "/groups";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;
    public static final String VIEW_PATH = "group";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";

    final AcademyService academyService;

    @Autowired
    public GroupController(AcademyService academyService) {
        this.academyService = academyService;
    }

    @GetMapping
    public String index(Model model) {
        log.info("index()");
        model.addAttribute("groups", academyService.findGroupsDto());
        return INDEX_PATH;
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.info("create()");
        model.addAttribute("groupDto", new GroupDto());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute GroupDto groupDto,
                         BindingResult bindingResult) {
        log.debug("Create group: " + groupDto.toString());
        if (!bindingResult.hasErrors()) {
            academyService.save(groupDto);
            log.debug("Group saved");
            return REDIRECT_INDEX;
        }
        log.error(bindingResult.toString());
        return FORM_PATH;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,
                         RedirectAttributes redirectAttributes) {
        String message = "OK";
        if (!academyService.deleteGroup(id)) {
            message = "Error deleting group by id";
        }
        redirectAttributes.addAttribute("message", message);
        return REDIRECT_INDEX;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        String url;
        try {
            model.addAttribute("groupDto", academyService.getGroupDto(id));
            url = FORM_PATH;
        } catch (EmptyResultDataAccessException ex) {
            url = REDIRECT_INDEX;
        }
        return url;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @Validated @ModelAttribute GroupDto groupDto,
                       BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            academyService.update(groupDto);
            return REDIRECT_INDEX;
        }
        return FORM_PATH;
    }
}
