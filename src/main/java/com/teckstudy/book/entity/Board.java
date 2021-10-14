package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.Category;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    private YesNoStatus top_show_yn;

    private String file_path;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_sn")
    private AnswerList answerList;

}
