package org.itstep.api;

import com.sun.tools.javac.comp.Todo;
import org.itstep.model.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/api/")
public class JsonRestController {
    public static final Logger log = LoggerFactory.getLogger(JsonRestController.class);

    RestTemplate restTemplate;
    static final String URL = "http://localhost:8081/api/v1/todolist";


    @GetMapping(path="/all")
    public String all(Model model) {
        try {
        restTemplate = new RestTemplate();
        AllCategories forObject = restTemplate.getForObject(URL, AllCategories.class);
        List<ToDo> toDos = forObject.getEmbedded().getToDos();
            model.addAttribute("toDoList", toDos);
        }catch (Exception e){
            log.trace(String.valueOf(e));
        }

        return "allJson";
    }

    @PostMapping(path="/add")
    public String add(@RequestParam HashMap<String, String> allParams) {
        try {
            restTemplate = new RestTemplate();

            List<String> list = new ArrayList<>(allParams.values());
            ToDoResp ToDoResp = new ToDoResp(list.get(3),list.get(2),
                    list.get(0),list.get(1),list.get(4),Boolean.valueOf(list.get(5)));


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ToDoResp> entity = new HttpEntity<>(ToDoResp, headers);
            System.out.println(entity.getBody());
            restTemplate.postForLocation(URL, entity);
        }catch (Exception e){
            log.trace(String.valueOf(e));
        }

        return "redirect:/api/all";

    }

    @GetMapping(path="/delete")
    public String delete(@RequestParam("href") String name) {
        try {
            restTemplate = new RestTemplate();
            restTemplate.delete(name);
        }catch (Exception e){
            log.trace(String.valueOf(e));
        }

        return "redirect:/api/all";

    }
    @GetMapping(path="/add")
    public String addPage() {
        return "addJson";
    }
}
