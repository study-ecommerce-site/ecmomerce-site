package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.ContentEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ContentsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long content_sn;

    @Enumerated(EnumType.STRING)
    private ContentEnum contentEnum;

    private int contentCnt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exhibition_sn")
    private Exhibition exhibition;
}