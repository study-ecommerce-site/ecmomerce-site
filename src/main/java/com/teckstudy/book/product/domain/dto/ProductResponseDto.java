package com.teckstudy.book.product.domain.dto;

import com.querydsl.core.Tuple;
import com.querydsl.core.annotations.QueryProjection;
import com.teckstudy.book.product.domain.entity.BookOrderProduct;
import com.teckstudy.book.product.domain.entity.Product;
import com.teckstudy.book.product.domain.entity.ProductOption;
import com.teckstudy.book.product.domain.entity.Review;
import com.teckstudy.book.product.domain.entity.enums.ProductType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductResponseDto {

    private Long product_sn;
    private String product_name;
    private ProductType product_option;
    private Integer price;
    private Integer stock_cnt;
    private List<BookOrderProduct> bookOrderProduct = new ArrayList<>();
    private List<ProductOption> productOptions = new ArrayList<>();
    private Review review;

    public ProductResponseDto(Product entity) {
        this.product_sn = entity.getProduct_sn();
        this.product_name = entity.getProduct_name();
        this.product_option = entity.getProduct_option();
        this.price = entity.getPrice();
        this.stock_cnt = entity.getStock_cnt();
        this.bookOrderProduct = entity.getBookOrderProduct();
        this.productOptions = entity.getProductOptions();
        this.review = entity.getReview();
    }

}
