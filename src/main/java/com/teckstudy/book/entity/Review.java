package com.teckstudy.book.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_sn;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_sn")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_sn")
    private Product product;

    @Column(length = 40, nullable = false)
    private String review_sub;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String review_content;

    @Column(length = 1, nullable = false)
    private int gpa;
}
