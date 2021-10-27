package com.teckstudy.book.lib.common.util.controller;

import com.teckstudy.book.lib.common.UploadResult;
import com.teckstudy.book.lib.common.util.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    /**
     * 단일파일 업로드
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public UploadResult upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        fileService.save(file);
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
                .path(Arrays.stream(file).map(f -> {
                    try {
                        fileService.save(f);
                        return "/files/"+f.getOriginalFilename();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "error to save : "+f.getOriginalFilename();
                    }
                })
                .collect(Collectors.toList()))
                .build();
    }
}