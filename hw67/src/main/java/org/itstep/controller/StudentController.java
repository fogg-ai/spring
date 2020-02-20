package org.itstep.controller;

import org.itstep.data.Repository;
import org.itstep.data.StudentRepository;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/students")
@Controller
public class StudentController {

    Repository<Student,Integer> repository;

    @Autowired
    public StudentController(Repository<Student,Integer> repository) {
        this.repository = repository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("students", repository.findAll());
        return "students/index";
    }

    @GetMapping("/new")
    public String createStudent() {
        return "students/create";
    }

    @PostMapping("/new")
    public String createStudent(Student student, RedirectAttributes redirectAttributes) {
        String message = "";
        int id = 0;
        try {
            id = repository.save(student);
            message = "successfully saved";
        } catch(Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            message = "some error";
        }
        redirectAttributes.addFlashAttribute("error", message);
        System.out.println(student);
        return "redirect:/students/info/" + id;
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable int id, Model model) {
        model.addAttribute("student", repository.find(id));
        return "students/info";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("student", repository.delete(repository.find(id)));
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String update() {
        return "students/update";
    }

    @PostMapping("/update/{id}")
    public String update(Student student) {
        repository.update(student);
        return "redirect:/students";
    }
}
