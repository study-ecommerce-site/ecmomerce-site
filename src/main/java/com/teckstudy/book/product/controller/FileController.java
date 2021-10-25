package com.teckstudy.book.product.controller;

import antlr.collections.List;
import com.teckstudy.book.lib.common.UploadResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class FileController {

    /**
     * 단일파일 업로드
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public UploadResult upload(@RequestParam(value = "file") MultipartFile file) {
        return UploadResult.builder()
                .code(100)
//                .path(List.of("/files/"+file.getOriginalFilename()))
                .path(Arrays.asList("/files/"+file.getOriginalFilename()))
                .build();
    }

    /**
     * 여러개 멀티파일 업로드
     * @param file
     * @return
     */
    @PostMapping(value = "/uploads")
    public UploadResult uploads(@RequestParam(value = "files") MultipartFile[] file) {
        return UploadResult.builder()
                .code(100)
                .path(Arrays.stream(file).map(f -> "/files/"+f.getOriginalFilename())
                .collect(Collectors.toList()))
                .build();
    }
}