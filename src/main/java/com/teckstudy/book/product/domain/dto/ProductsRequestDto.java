package com.teckstudy.book.product.domain.dto;

import com.teckstudy.book.entity.Product;
import com.teckstudy.book.entity.ProductOption;
import com.teckstudy.book.entity.enums.ProductType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class ProductsRequestDto {

    private Long product_sn;
    private String product_name;
    private String product_option_name;
    private Integer price;
    private Integer plus_price;
    private ProductType product_option;
    private Integer stock_cnt;

    public Product toProductEntity() {
        return Product.builder()
                .product_name(product_name)
                .product_option(product_option)
                .price(price)
                .stock_cnt(stock_cnt)
                .build();
    }

    public ProductOption toProductOptionEntity(Product productOptionNo) {
        return ProductOption.builder()
                .product(productOptionNo)
                .product_option_name(product_option_name)
                .plus_price(plus_price)
                .stock_cnt(stock_cnt)
                .build();
    }

}
