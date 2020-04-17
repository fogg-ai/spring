package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.service.GroupService;
import org.itstep.service.dto.GroupDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(GroupController.BASE_URL)
public class GroupController {

    public static final String BASE_URL = "/groups";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;
    public static final String VIEW_PATH = "group";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";

    final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size) {
        log.info("index() page: {} size: {}", page, size);
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size);
        Page<GroupDto> groupDtoPage = groupService.findAllWithEagerRelationships(pageable); //groupService.findAll(pageable);
        model.addAttribute("groups", groupDtoPage.getContent());
        model.addAttribute("page", groupDtoPage);
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
            groupService.save(groupDto);
            log.debug("Group saved");
            return REDIRECT_INDEX;
        }
        log.error(bindingResult.toString());
        return FORM_PATH;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        groupService.delete(id);
        return REDIRECT_INDEX;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        String url;
        Optional<GroupDto> found = groupService.findOne(id);
        if(found.isPresent()) {
            model.addAttribute("groupDto", found.get());
            url = FORM_PATH;
        } else {
            url = REDIRECT_INDEX;
        }
        return url;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @Validated @ModelAttribute GroupDto groupDto,
                       BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            groupService.save(groupDto);
            return REDIRECT_INDEX;
        }
        return FORM_PATH;
    }
}
