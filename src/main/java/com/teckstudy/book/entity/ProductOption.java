package com.teckstudy.book.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "PRODUCT_OPTION_SEQ_GENERATOR",
        sequenceName = "PRODUCT_OPTION_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 10000001,
        allocationSize = 1)
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PRODUCT_OPTION_SEQ_GENERATOR")
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
