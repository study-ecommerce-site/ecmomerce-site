package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "EXHIBITION_SEQ_GENERATOR",
        sequenceName = "EXHIBITION_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 10000001,
        allocationSize = 1)
public class Exhibition extends BaseEntity{

    // 전시코너코드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EXHIBITION_SEQ_GENERATOR")
    private Long exhibition_sn;

    // 전시코너 사용여부
    private YesNoStatus use_yn;

    // 전시코너명
    private String name;

    // 전시코너 노출유형
    @Enumerated(EnumType.STRING)
    private ExhibitionType exhibitionType;

    // 전시기간 설정
    private YesNoStatus date_yn;

    // 전시코너 이미지
    private String image;

    // 전시코너 설명
    private String description;

    // 전시코너 Html URL
    private String url;

    // 컨텐츠 유형
}