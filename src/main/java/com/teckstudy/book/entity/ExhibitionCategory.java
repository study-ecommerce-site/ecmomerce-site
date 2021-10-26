package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "CATEGORY_SEQ_GENERATOR",
        sequenceName = "CATEGORY_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 10000001,
        allocationSize = 1)
public class ExhibitionCategory extends BaseEntity{

    // 전시코너코드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CATEGORY_SEQ_GENERATOR")
    private Long category_sn;

    // 전시코너명
    private String name;

    // 메뉴노출여부
    @Enumerated(EnumType.STRING)
    private YesNoStatus menu_show_yn;

    // 메뉴사용여부
    @Enumerated(EnumType.STRING)
    private YesNoStatus menu_use_yn;

    // 메뉴노출유형
    @Enumerated(EnumType.STRING)
    private ExhibitionType menuType;

    // 타이틀노출유형
    @Enumerated(EnumType.STRING)
    private ExhibitionType titleType;

    // 전시코너수
    private int count;
}