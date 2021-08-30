package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.AuthType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vertity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auth_serial_sn;

    @OneToOne(mappedBy = "vertity", fetch = LAZY)
    private Member member;

    private Long member_sn;

    @Column(length = 100)
    private String auth_code;

    @CreatedDate
    private LocalDateTime auth_date;

    @Enumerated(EnumType.STRING)
    private YesNoStatus auth_yn;

    @Enumerated(EnumType.STRING)
    private AuthType member_auth_type;

}
