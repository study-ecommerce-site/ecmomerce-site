package com.teckstudy.book.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.product.domain.entity.AnswerList;
import com.teckstudy.book.product.domain.entity.Board;
import com.teckstudy.book.product.domain.entity.Member;
import com.teckstudy.book.product.domain.entity.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BoardRepositoryTest {
    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    JPAQueryFactory queryFactory;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    AnswerListRepository answerListRepository;

    private Mock mvc;

    @BeforeEach
    public void testEntity() {
        queryFactory = new JPAQueryFactory(em);
        //given
        Member member1 = Member.builder()
                .member_id("member1")
                .password("1234")
                .name("펩시맨")
                .sex(Gender.MALE)
                .birthday("1990-09-12")
                .phone_number("010-2027-1163")
                .address("서울특별시 봉천동")
                .sns_yn(YesNoStatus.Y)
                .member_status(MemberStatus.NORMAL)
                .build();
        memberRepository.save(member1);

        Board board1 = Board.builder()
                .category(Category.EVENT)
                .name(member1.getName())
                .subject("아이스 아메리카노")
                .content("냠냠 맛있다")
                .top_show_yn(YesNoStatus.Y)
                .file_path("D드라이브")
                .build();

        boardRepository.save(board1);

        AnswerList answerList = AnswerList.builder()
                .board(board1)
                .content("관리자가 댓글 답니다.")
                .build();

        answerListRepository.save(answerList);
    }

    /**
     * 게시판 목록 조회
     */
    @Test
    @DisplayName("게시판 관리 목록을 조회합니다.")
    public void boardListTest() {

        List<Board> all = boardRepository.findAll();

        for (Board boardValue : all) {
            assertThat(boardValue.getName()).isEqualTo("펩시맨");
            assertThat(boardValue.getBoard_sn()).isEqualTo(1);
            assertThat(boardValue.getCategory()).isEqualTo(Category.EVENT);
            assertThat(boardValue.getSubject()).isEqualTo("아이스 아메리카노");
            assertThat(boardValue.getContent()).isEqualTo("냠냠 맛있다");
            assertThat(boardValue.getTop_show_yn()).isEqualTo(YesNoStatus.Y);
        }
    }

    /**
     * 답글 테스트
     */
    @Test
    @DisplayName("답글 테스트")
    public void answerTest() {

        List<AnswerList> answerLists = answerListRepository.findAll();

        for (AnswerList answer : answerLists) {
            assertThat(answer.getContent()).isEqualTo("관리자가 댓글 답니다.");
            assertThat(answer.getBoard().getBoard_sn()).isEqualTo(1);
        }
    }
}