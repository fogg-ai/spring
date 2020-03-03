package org.itstep.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class PersonController {
    @GetMapping
    public String getAll(){
        return "index";
    }
}
