package com.teckstudy.book.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    @Id
    private Integer member_sn;
    private Integer product_sn;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
