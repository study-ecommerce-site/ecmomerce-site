package com.teckstudy.book.product.repository;

import com.teckstudy.book.product.domain.dto.ProductsResponseDto;

import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductsResponseDto> findAllDesc(Long id) throws Exception;

}
