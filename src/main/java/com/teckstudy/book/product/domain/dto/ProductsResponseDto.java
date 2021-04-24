package com.teckstudy.book.product.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.teckstudy.book.entity.BookOrderProduct;
import com.teckstudy.book.entity.Product;
import com.teckstudy.book.entity.ProductOption;
import com.teckstudy.book.entity.Review;
import com.teckstudy.book.entity.enums.ProductType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class ProductsResponseDto {

    private Long product_option_sn;;
    private Long product_sn;
    private Integer plus_price;
    private String product_option_name;
    private Integer price;
    private String product_name;
    private ProductType product_option;
    private Integer stock_cnt;


    @QueryProjection
    public ProductsResponseDto(Long product_option_sn, Long product_sn, Integer plus_price, String product_option_name, Integer price, String product_name, ProductType product_option, Integer stock_cnt) {
        this.product_option_sn = product_option_sn;
        this.product_sn = product_sn;
        this.plus_price = plus_price;
        this.product_option_name = product_option_name;
        this.price = price;
        this.product_name = product_name;
        this.product_option = product_option;
        this.stock_cnt = stock_cnt;
    }
}
