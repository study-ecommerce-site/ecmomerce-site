package com.teckstudy.book.entity;

import com.teckstudy.book.entity.enums.Gender;
import com.teckstudy.book.entity.enums.MemberStatus;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_sn;

    @Column(length = 30)
    private String member_id;

    @Column(length = 30)
    private String password;

    @Column(length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender sex;

    @Column(length = 20)
    private String birthday;

    @Column(length = 20)
    private String phone_number;

    @Column(length = 100)
    private String address;

    @Enumerated(EnumType.STRING)
    private YesNoStatus sns_yn;

    @Enumerated(EnumType.STRING)
    private MemberStatus member_status;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_sn")
    private SnsInfo snsInfo;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_sn")
    private Vertity vertity;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<BookOrder> bookOrders = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Favorite> favorite = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_sn")
    private Review review;
}
