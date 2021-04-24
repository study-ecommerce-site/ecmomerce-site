package com.teckstudy.book.product.domain.entity;

import com.sun.istack.NotNull;
import com.teckstudy.book.product.domain.entity.enums.ProductType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<BookOrderProduct> bookOrderProduct = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptions = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_sn")
    private Review review;
}