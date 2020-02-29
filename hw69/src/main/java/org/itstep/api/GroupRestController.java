package org.itstep.api;


import org.itstep.data.Repository;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupRestController {
    Repository<Group,Integer> repositoryGroup;

    @Autowired
    public GroupRestController(Repository<Group, Integer> repositoryGroup) {
        this.repositoryGroup = repositoryGroup;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> getAllGroups(){
        return repositoryGroup.findAll();
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Group oneGroup(@PathVariable int id){
        return repositoryGroup.find(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Group createGroup(@RequestBody Group group){
        Integer i = repositoryGroup.save(group);
        return repositoryGroup.find(i);
    }

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteGroup(@PathVariable int id){
        return repositoryGroup.delete(repositoryGroup.find(id));
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Group updateGroup(@PathVariable int id,@RequestBody Group data){
        data.setId(id);
        repositoryGroup.update(data);
        return repositoryGroup.find(id);
    }
}
