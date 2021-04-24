package com.teckstudy.book.product.controller;

import com.teckstudy.book.product.domain.dto.ProductsResponseDto;
import com.teckstudy.book.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RequiredArgsConstructor
 * final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
 */

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService postsService;

//    @GetMapping("/api/product/{id}")
//    public ProductsResponseDto findById (@PathVariable Long id) {
//        return postsService.findById(id);
//    }

    @GetMapping("/api/products/{id}")
    public List<ProductsResponseDto> findAll (@PathVariable Long id) throws Exception {
        List<ProductsResponseDto> responseDtoList = postsService.findAllDesc(id);

        return responseDtoList;
    }
}
