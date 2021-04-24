package com.teckstudy.book.product.service;

import com.querydsl.core.Tuple;
import com.teckstudy.book.entity.Product;
import com.teckstudy.book.entity.ProductOption;
import com.teckstudy.book.product.domain.dto.ProductsResponseDto;
import com.teckstudy.book.product.domain.dto.QProductsResponseDto;
import com.teckstudy.book.product.repository.ProductRepository;
import com.teckstudy.book.product.repository.ProductRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductRepositorySupport productRepositorySupport;

//    public ProductsResponseDto findById (Long id) {
//        Product entity = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
//        return new ProductsResponseDto(entity);
//    }

    // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됨.
    @Transactional(readOnly = true)
    public List<ProductsResponseDto> findAllDesc(Long id) throws Exception {
        List<ProductsResponseDto> productsResponseDtoList = productRepositorySupport.findAllDesc(id);

        return productsResponseDtoList;
    }
}
