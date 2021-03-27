package com.teckstudy.book.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.domain.entity.Board;
import com.teckstudy.book.domain.entity.BookOrder;
import com.teckstudy.book.domain.entity.Member;
import com.teckstudy.book.domain.entity.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static com.teckstudy.book.domain.entity.QMember.member;
import static com.teckstudy.book.domain.entity.QBoard.board;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BoardRepositoryTest {
    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    // queryDsl 선언
    JPAQueryFactory queryFactory;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

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

//        Board board1 = new Board(
//                member1.getName(), Category.NOTICE, "테스트합니다", "등록합니다", YesNoStatus.N, "C드라이브");

        Board board1 = Board.builder()
                .category(Category.NOTICE)
                .name("이비자")
                .subject("아이스 아메리카노")
                .content("냠냠 맛있다")
                .top_show_yn(YesNoStatus.Y)
                .file_path("D드라이브")
                .build();

        boardRepository.save(board1);
    }

    @Test
    public void boardTest() {

        List<Board> all = boardRepository.findAll();

        for (Board board1 : all) {
            System.out.println(board1);
        }

    }
}