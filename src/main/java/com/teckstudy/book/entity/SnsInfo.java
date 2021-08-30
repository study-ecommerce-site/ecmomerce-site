package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.SnsType;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnsInfo extends BaseEntity {

    @Id
    private Long member_sn;

    @Column(length = 100)
    private String sns_code;

    @OneToOne(mappedBy = "snsInfo", fetch = LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    private SnsType sns_type;

}
