package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.ExhibitionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class Exhibition {

    // 전시코너코드
    private Long exhibition_sn;

    // 전시코너 사용여부
    private boolean use_yn;

    // 전시코너명
    private String name;

    // 전시코너 노출유형
    @Enumerated(EnumType.STRING)
    private ExhibitionType exhibitionType;

    // 전시기간 설정
    private boolean date_yn;

    // 전시코너 이미지
    private String image;

    // 전시코너 설명
    private String description;

    // 전시코너 Html URL
    private String url;

    // 컨텐츠 유형
}