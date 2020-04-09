package org.itstep.service.impl;

import org.itstep.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadFileServiceImpl implements UploadFileService {
    public static final String UPLOADS_DIR = "/uploads";
    @Autowired
    ResourceLoader resourceLoader;

    /**
     *
     * @return Список файлов в директории UPLOADS_DIR
     * @throws IOException если UPLOADS_DIR не доступна
     */
    @Override
    public List<String> listFiles() throws IOException {
        Resource resource = resourceLoader.getResource(UPLOADS_DIR);
        if(resource.exists()) {
            List<String> list = Files.list(Paths.get(resource.getURI()))
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.toList());
            return list;
        }
        return null;
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        Resource resource = resourceLoader.getResource(UPLOADS_DIR);
        if (resource.exists()) {
            File uploadDir = resource.getFile();
            Path dest = Paths.get(uploadDir.getAbsolutePath(), file.getOriginalFilename());
            file.transferTo(dest);
        }
    }
}
