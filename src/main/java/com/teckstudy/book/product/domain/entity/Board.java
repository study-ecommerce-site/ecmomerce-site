package com.teckstudy.book.product.domain.entity;

import com.teckstudy.book.product.domain.entity.enums.Category;
import com.teckstudy.book.product.domain.entity.enums.YesNoStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_sn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_sn")
    private Member member;

    private Category category;

    private String name;

    private String subject;

    private String content;

    private YesNoStatus top_show_yn;

    private String file_path;

    private YesNoStatus board_show_yn;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_sn")
    private AnswerList answerList;

}
