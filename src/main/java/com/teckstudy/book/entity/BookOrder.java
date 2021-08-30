package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.OrderStatus;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_sn;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_sn")
    private Member member;

    @Enumerated(EnumType.STRING)
    private YesNoStatus order_yn;

    @Column(length = 11)
    private Integer order_price;

    @Column(length = 100)
    private String order_address;

    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_sn")
    private PayInfo payInfo;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_sn")
    private Refund refund;

    @Builder.Default
    @OneToMany(mappedBy = "bookOrder")
    private List<BookOrderProduct> bookOrderProduct = new ArrayList<>();

}
