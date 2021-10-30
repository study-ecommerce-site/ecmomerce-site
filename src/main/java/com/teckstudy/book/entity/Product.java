package com.teckstudy.book.entity;

import com.sun.istack.NotNull;
import com.teckstudy.book.entity.enums.ProductType;
import com.teckstudy.book.product.domain.dto.ProductsRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "PRODUCT_SEQ_GENERATOR",
        sequenceName = "PRODUCT_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 10000001,
        allocationSize = 1)
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PRODUCT_SEQ_GENERATOR")
    private Long product_sn;

    @Column(length = 30)
    private String product_name;

    @Enumerated(EnumType.STRING)
    private ProductType product_option;

    @Column(length = 11)
    @NotNull
    private Integer price;

    @Column(length = 10)
    private Integer stock_cnt;

    @OneToMany(mappedBy = "product")
    @Builder.Default
    private List<BookOrderProduct> bookOrderProduct = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @Builder.Default
    private List<ProductOption> productOptions = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_sn")
    private Review review;

    public Product(String product_name, ProductType product_option, Integer price, Integer stock_cnt) {
        this.product_name = product_name;
        this.product_option = product_option;
        this.price = price;
        this.stock_cnt = stock_cnt;
    }

    public Product update(ProductsRequestDto productsRequestDto) {
        this.product_name = productsRequestDto.getProduct_name();
        this.price = productsRequestDto.getPrice();
        this.product_option = productsRequestDto.getProduct_option();
        this.stock_cnt = productsRequestDto.getStock_cnt();
        return this;
    }
}
