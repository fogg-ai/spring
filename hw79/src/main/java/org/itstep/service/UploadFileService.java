package org.itstep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
public class UploadFileService {
    @Autowired
    ResourceLoader resourceLoader;

    public String uploadPhoto(MultipartFile file) throws IOException {
        Resource resource = resourceLoader.getResource("/upload" + File.separator + file.getOriginalFilename());
        if(!resource.exists()) {
            File uploadDir = resource.getFile();
            file.transferTo(uploadDir);
            return uploadDir.getAbsolutePath().substring(uploadDir.getAbsolutePath().lastIndexOf("\\") + 1);
        }
        return resource.getURI().getPath().substring(1);
    }

}
