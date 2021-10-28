package com.teckstudy.book.common.util;

import com.teckstudy.book.common.WebIntegrationTest;
import com.teckstudy.book.lib.common.UploadResult;
import com.teckstudy.book.lib.common.util.service.FileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUploadTest extends WebIntegrationTest {

    @Autowired
    private FileService fileService;

    private Path root;

    @BeforeEach
    void before() throws IOException {
        // 다이렉트 사용방법
//        File rootDir = new File("src/test/resources/files");
//        fileService.setRoot(rootDir.toPath());
        // 랜덤하게 사용
//        ClassPathResource testPath = new ClassPathResource("files");
//        Path rootDir = testPath.getFile().toPath();
        Path rootDir = new File("src/test/resources/files").toPath();
        root = rootDir.resolve("" + Math.abs(new Random().nextLong()));
        fileService.setRoot(root);
        fileService.init();
    }
    
    // 테스트 후 파일 삭제
    @AfterEach
    void after() throws IOException {
        if(Files.exists(root)) {
            FileSystemUtils.deleteRecursively(root);
        }
    }

    @Test
    @DisplayName("서버에서 넘겨준 파일 업로드시 10MB를 초과하면 Exception을 발생시킨다.")
    public void fileServerUploadTest() {
        HttpEntity request = makeRequest(new ClassPathResource("testimage1.jpg"));

        ResponseEntity<UploadResult> response = client.postForEntity(url("/upload"), request, UploadResult.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("/files/testimage1.jpg", response.getBody().getPath().get(0));
    }

    @Test
    @DisplayName("2개 이상의 파일을 업로드 한다.")
    public void fileServerUploadsTest() {
        HttpEntity request = makeRequest(
                new ClassPathResource("testimage1.jpg"),
                new ClassPathResource("testimage2.jpg")
        );

        ResponseEntity<UploadResult> response = client.postForEntity(url("/uploads"), request, UploadResult.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("/files/testimage1.jpg", response.getBody().getPath().get(0));
        assertEquals("/files/testimage2.jpg", response.getBody().getPath().get(1));
    }

    private HttpEntity makeRequest(Resource... resources) {
        MultiValueMap form = new LinkedMultiValueMap();
        if(resources.length == 1) {
            form.add("file", resources[0]);
        } else {
            Arrays.stream(resources).forEach(res -> form.add("files", res));
        }
        HttpEntity request = new HttpEntity(form, null);
        return request;
    }
}