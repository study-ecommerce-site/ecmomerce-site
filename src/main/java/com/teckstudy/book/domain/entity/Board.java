package com.teckstudy.book.domain.entity;

import com.teckstudy.book.domain.entity.enums.Category;
import com.teckstudy.book.domain.entity.enums.YesNoStatus;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_sn")
    private AnswerList answerList;

}
