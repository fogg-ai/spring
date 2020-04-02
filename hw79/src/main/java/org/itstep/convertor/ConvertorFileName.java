package org.itstep.convertor;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ConvertorFileName implements Converter<MultipartFile, String> {
    @Autowired
    ResourceLoader resourceLoader;

    @SneakyThrows
    @Override
    public String convert(MultipartFile file) {
        Resource resource = resourceLoader.getResource("/upload" + File.separator + file.getOriginalFilename());
        if (!resource.exists()) {
            File uploadDir = null;
            try {
                uploadDir = resource.getFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            file.transferTo(uploadDir);
            return uploadDir.getAbsolutePath().substring(uploadDir.getAbsolutePath().lastIndexOf("\\") + 1);
        }
        return resource.getURI().getPath().substring(1);
    }
}