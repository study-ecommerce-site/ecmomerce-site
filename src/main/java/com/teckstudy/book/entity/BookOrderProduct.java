package com.teckstudy.book.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookOrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_product_sn;

    private String product_option_sn;

    @Column(length = 10)
    @NotNull
    private Integer product_cnt;

    @OneToOne(mappedBy = "bookOrderProduct", fetch = LAZY)
    private ProductOption productOption;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_sn")
    private BookOrder bookOrder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_sn")
    private Product product;

}
