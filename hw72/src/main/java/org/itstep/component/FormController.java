package org.itstep.component;

import org.itstep.model.RegisterModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/")
@Controller
public class FormController  {
    public static final Logger log = LoggerFactory.getLogger(FormController.class);
    List<RegisterModel> list = new ArrayList<>();

    @GetMapping
    public String index(Model model) {
        model.addAttribute("registerModel",new RegisterModel());
        return "index";
    }

    @PostMapping(path = "/")
    public String create(@Validated @ModelAttribute RegisterModel register, BindingResult bindingResult) {
        log.debug(register.toString());
        if (bindingResult.hasErrors()) {
            log.debug(bindingResult.toString());
            return "index";
        }
        try {
            list.add(register);
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return "success";
    }

}