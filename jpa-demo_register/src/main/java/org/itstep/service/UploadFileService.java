package org.itstep.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UploadFileService {
    List<String> listFiles() throws IOException;

    void uploadFile(MultipartFile file) throws IOException;
}
