package com.teckstudy.book.lib.common.util.service;

import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Setter
    private Path root;

    public FileService() {
        // Path.of는 9부터 지원
        this.root = Paths.get("upload-dir");

        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        if(!Files.exists(root)) {
            Files.createDirectories(root);
        }
    }

    public void save(MultipartFile file) throws IOException {
        file.transferTo(root.resolve(file.getOriginalFilename()));
    }


}