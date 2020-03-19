package org.itstep.controller;


import lombok.extern.slf4j.Slf4j;
import org.itstep.model.Group;
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

@Slf4j
@Controller
@RequestMapping("/groups")
public class GroupController {
    final AcademyService academyService;

    @Autowired
    GroupController(AcademyService academyService){
        this.academyService = academyService;
    }

    @GetMapping
    public String index(Model model){
        log.info("index()");
        model.addAttribute("groups",academyService.getGroups());
        return "group/index";
    }


    @GetMapping("/create")
    public String create(Model model){
        log.info("form()");
        model.addAttribute("group",new Group());
        return "group/form";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute Group s,
                         BindingResult bindingResult){
        log.info("create");
        if(!bindingResult.hasErrors()){
            academyService.saveGroup(s);
            return "redirect:/groups";
        }
        log.error(bindingResult.toString());
        return "group/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,
                         RedirectAttributes redirectAttributes) {
        if(academyService.deleteGroup(id)) {
            redirectAttributes.addAttribute("message", "OK");
        } else {
            redirectAttributes.addAttribute("message", "Error");
        }
        return "redirect:/groups";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        String url;
        try {
            model.addAttribute("group", academyService.getOneGroups(id));
            url = "group/form";
        } catch(EmptyResultDataAccessException ex) {
            url = "redirect:/groups";
        }
        return url;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @Validated @ModelAttribute Group group,
                       BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            academyService.updateGroup(group);
            return "redirect:/groups";
        }
        return "group/form";
    }
}
