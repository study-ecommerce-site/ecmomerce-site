package com.teckstudy.book.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.entity.*;
import com.teckstudy.book.entity.enums.*;
import com.teckstudy.book.product.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static com.teckstudy.book.entity.QMember.member;
import static com.teckstudy.book.entity.QSnsInfo.snsInfo;
import static com.teckstudy.book.entity.QVertity.vertity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {
    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    // queryDsl  선언
    JPAQueryFactory queryFactory;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void testEntity() {
        queryFactory = new JPAQueryFactory(em);
        //given

        Member member1 = Member.builder()
                    .member_id("member1")
                    .password("1234")
                    .name("홍길동")
                    .sex(Gender.MALE)
                    .birthday("1990-09-12")
                    .phone_number("010-2027-1163")
                    .address("서울특별시 봉천동")
                    .sns_yn(YesNoStatus.Y)
                    .member_status(MemberStatus.NORMAL)
                    .build();

        memberRepository.save(member1);

        SnsInfo snsInfo1 = SnsInfo.builder()
                    .member_sn(member1.getMember_sn())
                    .sns_code("1234-9988")
                    .sns_type(SnsType.F)
                    .build();
        em.persist(snsInfo1);

        Vertity vertity = Vertity.builder()
                    .member_sn(member1.getMember_sn())
                    .auth_code("HOT-1163")
                    .auth_yn(YesNoStatus.Y)
                    .member_auth_type(AuthType.PHONE)
                    .build();

        em.persist(vertity);

        // 영속 컨텍스트를 플러시 하는방법
        // 1. em.flush();
        // 2. 트랜잭션 커밋 : 플러시 자동호출
        // 3. JPQL 쿼리실행 - 플러시 자동 호출
    }

    /**
     *  회원가입 테스트
     */
    @Test
    @DisplayName("회원가입 테스트")
    public void memberTest() {
        // 엔티티가 없을때는 반드시 빌드를 해줘야합니다. (Gradle -> querydsl < Tasks < other < compileQuerydsl
        // QMember m = new QMember("m"); // 방법 1
        // QMember m = QMember.member; // 방법 2
        // 방법 : 매개변수 파라미터에 QMember.member 선언 후 alt+enter를 통해 static 선언
        List<Member> findMember = queryFactory
                .selectFrom(member)
                .join(member.snsInfo, snsInfo)
                .join(member.vertity, vertity)
                .where(member.member_id.eq("member1"))
                .fetch();

        for (Member member1 : findMember) {
            assertThat(member1.getMember_id()).isEqualTo("member1");
        }

    }

}