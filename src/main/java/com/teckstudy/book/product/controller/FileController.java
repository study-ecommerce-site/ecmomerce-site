package com.teckstudy.book.product.controller;

import com.teckstudy.book.lib.common.UploadResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @PostMapping(value = "/upload")
    public UploadResult upload(@RequestParam(value = "file") MultipartFile file) {
        return UploadResult.builder()
                .code(100)
                .path("/files/"+file.getOriginalFilename())
                .build();
    }
}