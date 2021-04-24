package com.teckstudy.book.product.domain.entity;

import com.teckstudy.book.product.domain.entity.enums.AnswerStatus;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_sn;

    @OneToOne(mappedBy = "answerList", fetch = LAZY)
    private Board board;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private AnswerStatus answer_show_yn;
}
