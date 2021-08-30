package com.teckstudy.book.product.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.teckstudy.book.entity.Product;
import com.teckstudy.book.entity.enums.ProductType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Getter
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

    public ProductsResponseDto(Product entity) {
        this.product_sn = entity.getProduct_sn();
        this.product_name = entity.getProduct_name();
        this.product_option = entity.getProduct_option();
        this.price = entity.getPrice();
        this.stock_cnt = entity.getStock_cnt();
    }
}
