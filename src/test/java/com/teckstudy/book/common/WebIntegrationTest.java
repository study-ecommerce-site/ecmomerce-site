package com.teckstudy.book.common;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;

import static java.lang.String.format;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 실제 내장 톰캣을 사용
public class WebIntegrationTest {

    @LocalServerPort
    private int port;

    public TestRestTemplate client = new TestRestTemplate();

    public URI url(String path){
        return URI.create(format("http://localhost:%d%s", port, path));
    }
}