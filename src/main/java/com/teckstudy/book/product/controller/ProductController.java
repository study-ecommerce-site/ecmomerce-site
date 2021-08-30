package com.teckstudy.book.product.controller;

import com.teckstudy.book.product.domain.dto.ProductsRequestDto;
import com.teckstudy.book.product.domain.dto.ProductsResponseDto;
import com.teckstudy.book.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService postsService;

    /**
     * 상품 조회
     */
    @GetMapping("/api/product/{id}")
    public ProductsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    /**
     * 상품 등록 및 상품 옵션 등록
     */
    @PostMapping("/api/products/post")
    public Long registerProduct(@RequestBody ProductsRequestDto requestDto) {
        return postsService.productsSave(requestDto);
    }

    /**
     * 상품 및 상품 옵션 수정
     */
    @PostMapping("/api/products/posts/{id}")
    public Long registerProductOption(@PathVariable Long id, @RequestBody ProductsRequestDto requestDto) {
        return postsService.productsUpdate(id, requestDto);
    }

    /**
     * 상품, 상품옵션 조회
     */
    @GetMapping("/api/productOption/{id}")
    public List<ProductsResponseDto> findAll (@PathVariable Long id) throws Exception {
        List<ProductsResponseDto> responseDtoList = postsService.findAllDesc(id);

        return responseDtoList;
    }


//    @DeleteMapping("/api/products/{id}")
}
