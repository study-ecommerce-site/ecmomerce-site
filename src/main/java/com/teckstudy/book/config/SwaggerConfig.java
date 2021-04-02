package com.teckstudy.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private static final String TITLE = "제목입니다.";
    private static final String TERMS_OF_SERVICE_URL = "주소입니다.";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.teckstudy.book"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().title(TITLE).termsOfServiceUrl(TERMS_OF_SERVICE_URL).build())
                .pathMapping("/");
    }
}