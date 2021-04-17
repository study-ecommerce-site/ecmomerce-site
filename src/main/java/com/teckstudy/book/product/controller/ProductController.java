package com.teckstudy.book.product.controller;

import com.teckstudy.book.product.domain.dto.ProductResponseDto;
import com.teckstudy.book.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @RequiredArgsConstructor
 * final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
 */

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService postsService;

    @GetMapping("/api/product/{id}")
    public ProductResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

//    @GetMapping("/api/product/{id}")
//    public ProductResponseDto findAll () {
//        return (ProductResponseDto) postsService.findAllDesc();
//    }
}
