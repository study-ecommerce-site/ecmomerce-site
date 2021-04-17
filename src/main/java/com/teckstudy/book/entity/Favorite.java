package com.teckstudy.book.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    @Id
    private Integer member_sn;
    private Integer product_sn;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Favorite(Integer member_sn, Integer product_sn) {
        this.member_sn = member_sn;
        this.product_sn = product_sn;
    }
}
