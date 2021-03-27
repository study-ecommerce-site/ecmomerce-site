package com.teckstudy.book.domain.entity;

import com.sun.istack.NotNull;
import com.teckstudy.book.domain.entity.enums.ProductType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

}
