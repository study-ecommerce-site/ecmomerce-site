package com.teckstudy.book.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.product.domain.entity.AnswerList;
import com.teckstudy.book.product.domain.entity.Board;
import com.teckstudy.book.product.domain.entity.Member;
import com.teckstudy.book.product.domain.entity.enums.*;
import com.teckstudy.book.product.repository.AnswerListRepository;
import com.teckstudy.book.product.repository.BoardRepository;
import com.teckstudy.book.product.repository.MemberRepository;
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
class BoardEventTest {
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
                .member_id("coupang1")
                .password("1234")
                .name("쿠팡 이벤트팀")
                .sex(Gender.MALE)
                .birthday("1990-09-12")
                .phone_number("010-1234-9876")
                .address("서울특별시 봉천동")
                .sns_yn(YesNoStatus.Y)
                .member_status(MemberStatus.NORMAL)
                .build();

        Member member2 = Member.builder()
                .member_id("user_1234")
                .password("4322")
                .name("김길동")
                .sex(Gender.FEMALE)
                .birthday("1999-11-13")
                .phone_number("010-1234-9876")
                .address("서울특별시 강남구")
                .sns_yn(YesNoStatus.Y)
                .member_status(MemberStatus.NORMAL)
                .build();

        Board board = Board.builder()
                .category(Category.EVENT)
                .name(member1.getName())
                .subject("대규모 위인전 50% 할인 판매")
                .content("정기구독시 할인 적용되는 이벤트 지금 바로 참여하세요.")
                .top_show_yn(YesNoStatus.N)
                .file_path("C:\\Users\\wjdal\\Desktop\\배민")
                .board_show_yn(YesNoStatus.Y)
                .member(member1)
                .build();

        AnswerList answerList = AnswerList.builder()
                .board(board)
                .content("구매의향 있습니다.")
                .answer_show_yn(AnswerStatus.USE)
                .build();

        // when
        memberRepository.save(member1);
        boardRepository.save(board);
        answerListRepository.save(answerList);

    }

    /**
     * 이벤트 게시판 상세정보 조회
     */
    @Test
    @DisplayName("이벤트 상세 목록을 조회합니다.")
    public void boardListTest() {

        List<Board> all = boardRepository.findAll();

        for (Board boardValue : all) {
            assertThat(boardValue.getName()).isEqualTo("쿠팡 이벤트팀");
            assertThat(boardValue.getBoard_sn()).isEqualTo(1);
            assertThat(boardValue.getCategory()).isEqualTo(Category.EVENT);
            assertThat(boardValue.getSubject()).isEqualTo("대규모 위인전 50% 할인 판매");
            assertThat(boardValue.getContent()).isEqualTo("정기구독시 할인 적용되는 이벤트 지금 바로 참여하세요.");
            assertThat(boardValue.getFile_path()).isEqualTo("C:\\Users\\wjdal\\Desktop\\배민");
            assertThat(boardValue.getTop_show_yn()).isEqualTo(YesNoStatus.N);
            assertThat(boardValue.getBoard_show_yn()).isEqualTo(YesNoStatus.Y);
        }
    }

    /**
     * 답글 테스트
     */
    @Test
    @DisplayName("이벤트 답글 테스트")
    public void answerTest() {

        List<AnswerList> answerLists = answerListRepository.findAll();

        for (AnswerList answer : answerLists) {
            assertThat(answer.getContent()).isEqualTo("구매의향 있습니다.");
//            assertThat(answer.getBoard().getName()).isEqualTo("펩시맨");
        }
    }
}