package com.teckstudy.book.domain.entity;

import com.sun.istack.NotNull;
import com.teckstudy.book.domain.entity.enums.ProductType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_option_sn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_sn")
    private Product product;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_option_sn")
    private BookOrderProduct bookOrderProduct;

    @Column(length = 100)
    private String product_option_name;

    @Column(length = 11)
    @NotNull
    private Integer plus_price;

    @Column(length = 10)
    private Integer stock_cnt;

}
