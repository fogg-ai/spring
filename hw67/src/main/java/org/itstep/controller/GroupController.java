package org.itstep.controller;


import org.itstep.data.GroupRepository;
import org.itstep.data.Repository;
import org.itstep.data.StudentRepository;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/group")
@Controller
public class GroupController {
    Repository<Group, Integer> repository;


    @Autowired
    public GroupController(Repository<Group, Integer> repository) {
        this.repository = repository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("groups", repository.findAll());
        return "group/index";
    }

    @GetMapping("/new")
    public String createStudent() {
        return "group/create";
    }

    @PostMapping("/new")
    public String createStudent(Group group, RedirectAttributes redirectAttributes) {
        String message = "";
        int id = 0;
        try {
            id =  repository.save(group);
            message = "successfully saved";
        } catch (Exception ex) {
            message = "some error";
        }
        redirectAttributes.addFlashAttribute("error", message);
        return "redirect:/group/info/" + id;
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable int id, Model model) {
        model.addAttribute("groups", repository.find(id));
        return "group/info";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        System.out.println(id);
        model.addAttribute("groups", repository.delete(repository.find(id)));
        return "redirect:/group";
    }

    @GetMapping("/update/{id}")
    public String update() {
        return "group/update";
    }

    @PostMapping("/update/{id}")
    public String update(Group group) {
        repository.update(group);
        return "redirect:/group";
    }
}
