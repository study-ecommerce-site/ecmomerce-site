package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private YesNoStatus use_yn;

    // 전시코너명
    @Column(length = 20)
    private String name;

    // 전시코너 노출유형
    @Enumerated(EnumType.STRING)
    private ExhibitionType exhibitionType;

    // 전시기간 설정
    @Enumerated(EnumType.STRING)
    private YesNoStatus date_yn;

    // 전시코너 이미지
    private String image;

    // 전시코너 설명
    private String description;

    // 전시코너 Html URL
    private String url;

    // 전시기간 시작 날짜
    private String exhibition_start;

    // 전시기간 종료 날짜
    private String exhibition_end;

    // 컨텐츠 타입 묶음컨텐츠 최대 개수
    private int bundleContentCnt;

    // 컨텐츠 유형
    @OneToMany(mappedBy = "exhibition")
    @Builder.Default
    private List<ContentsType> contentsType = new ArrayList<>();

    public Exhibition(YesNoStatus use_yn, String name, ExhibitionType exhibitionType,
                      YesNoStatus date_yn, String image, String description, String url,
                      String exhibition_start, String exhibition_end, int bundleContentCnt) {
        this.use_yn = use_yn;
        this.name = name;
        this.exhibitionType = exhibitionType;
        this.date_yn = date_yn;
        this.image = image;
        this.description = description;
        this.url = url;
        this.exhibition_start = exhibition_start;
        this.exhibition_end = exhibition_end;
        this.bundleContentCnt = bundleContentCnt;
    }

}