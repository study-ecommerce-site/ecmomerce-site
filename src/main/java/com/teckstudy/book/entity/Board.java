package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.Category;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_sn;

    private Category category;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "name")
//    private Member member;

    private String name;

    private String subject;

    private String content;

    private YesNoStatus top_show_yn;

    private String file_path;

    public Board(String name, Category category, String subject, String content, YesNoStatus top_show_yn, String file_path) {
        this.category = category;
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.top_show_yn = top_show_yn;
        this.file_path = file_path;
    }
}
