package com.teckstudy.book.domain.entity;

import com.teckstudy.book.domain.entity.enums.Category;
import com.teckstudy.book.domain.entity.enums.YesNoStatus;
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

    private YesNoStatus top_show_yn;

    private String file_path;

    public Board(Category category, String subject, String content, YesNoStatus top_show_yn, String file_path) {
        this.category = category;
        this.subject = subject;
        this.content = content;
        this.top_show_yn = top_show_yn;
        this.file_path = file_path;
    }
}
