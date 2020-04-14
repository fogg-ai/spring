package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.itstep.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceResolver;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequestMapping("/upload")
@Controller
public class UploadController {

    final UploadFileService uploadFileService;

    public UploadController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @ModelAttribute("allImages")
    List<String> allImages() throws IOException {
        return uploadFileService.listFiles();
    }

    @GetMapping
    String index() {
        log.info("index()");
        return "upload/index";
    }

    @PostMapping
    String upload(String name, MultipartFile img, Model model) {
        log.debug("name: " + name);
        log.debug("img: " + img);
        String msg = "";
        try {
            uploadFileService.uploadFile(img);
            msg = "Upload successfully";
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            msg = e.getLocalizedMessage();
        }
        model.addAttribute("message", msg);
        return "redirect:/upload";
    }

}
