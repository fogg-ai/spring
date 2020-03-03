package org.itstep.api;

import org.itstep.model.Datum;
import org.itstep.model.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonTemplateApi {

    RestTemplate restTemplate;
    static final String URL = "https://reqres.in/api/users?per_page=100";

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Datum> getAllStudents(){
        restTemplate = new RestTemplate();
        ResponseEntity forEntity = restTemplate.getForEntity(URL, Example.class);
        Example body = (Example) forEntity.getBody();
        List<Datum> datum = body.getData();
        return datum;
    }
}
