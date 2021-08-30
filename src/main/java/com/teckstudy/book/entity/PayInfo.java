package com.teckstudy.book.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pay_sn;

    @OneToOne(mappedBy = "payInfo",fetch = FetchType.LAZY)
    @JoinColumn(name = "order_sn")
    private BookOrder bookOrder;

    private Integer total_price;

}
