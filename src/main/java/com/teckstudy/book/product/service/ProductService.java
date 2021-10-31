package com.teckstudy.book.product.service;

import com.teckstudy.book.entity.Product;
import com.teckstudy.book.product.domain.dto.ProductsRequestDto;
import com.teckstudy.book.product.domain.dto.ProductsResponseDto;
import com.teckstudy.book.product.repository.ProductOptionRepository;
import com.teckstudy.book.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductOptionRepository productOptionRepository;

    public ProductsResponseDto findById (Long id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
        return new ProductsResponseDto(entity);
    }

    // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됨.
    @Transactional(readOnly = true)
    public List<ProductsResponseDto> findAllDesc(Long id) throws Exception {
        List<ProductsResponseDto> productsResponseDtoList = productRepository.findAllDesc(id);

        return productsResponseDtoList;
    }

    // 상품 등록 및 상품 옵션 등록
    @Transactional
    public Long productsSave(ProductsRequestDto productsRequestDto) {
        Long productSn = productRepository.save(productsRequestDto.toProductEntity()).getProduct_sn();

        Optional<Product> productNo = productOptionSave(productSn);

        return productOptionRepository.save(productsRequestDto.toProductOptionEntity(productNo.get())).getProduct_option_sn();
    }

    @Transactional
    public Optional<Product> productOptionSave(Long productSn) {

        return ofNullable(productRepository.findById(productSn).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + productSn)));
    }

    // 상품 등록 및 상품 옵션 수정
    @Transactional
    public Long productsUpdate(Long id, ProductsRequestDto requestDto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        Long productSn = productRepository.save(requestDto.toProductEntity()).getProduct_sn();
        product.update(requestDto);
        Optional<Product> productNo = productOptionUpdate(productSn);

        return productOptionRepository.save(requestDto.toProductOptionEntity(productNo.get())).getProduct_option_sn();
    }

    @Transactional
    public Optional<Product> productOptionUpdate(Long productSn) {

        return ofNullable(productRepository.findById(productSn).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + productSn)));
    }
}