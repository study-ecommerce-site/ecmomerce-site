package com.teckstudy.book.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refund_sn;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_sn")
    private BookOrder bookOrder;

    @Column(length = 20, nullable = false)
    private String bank_name;

    @Column(length = 30, nullable = false)
    private String acc_number;

}
