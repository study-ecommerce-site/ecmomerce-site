package com.teckstudy.book.exhibition;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.entity.ContentsType;
import com.teckstudy.book.entity.Exhibition;
import com.teckstudy.book.entity.Product;
import com.teckstudy.book.entity.enums.ContentEnum;
import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import com.teckstudy.book.lib.common.BoValidation;
import com.teckstudy.book.product.repository.ExhibitionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@Transactional
public class ExhibitionTest {

    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    // queryDsl 선언
    JPAQueryFactory queryFactory;

    @Autowired
    ExhibitionRepository exhibitionRepository;

    private Random random = new Random();

//    @Test
//    @DisplayName("메뉴명이 한글이 아니면 Exception을 발생한다.")
//    public void korCharMenuTest() {
//        //given
//        String keyWord = "한글단어인지g검증";
//
//        //when
//        boolean keyWordChk = getType(keyWord);
//
//        //then
//        assertThat(keyWordChk).isFalse();
//        assertThatExceptionOfType(IllegalArgumentException.class)
//                .isThrownBy(() -> new BoValidation(keyWord));
//    }

    @Test
    @DisplayName("메뉴명은 20자를 넘으면 Exception을 발생시킨다.")
    public void twentyKorCharMenuTest() {
        //given
        String keyWord = "데이터 검증단어 글자수 초과합니다. 20자 넘어감";
        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoValidation(keyWord));
    }

    @Test
    @DisplayName("최소 1개의 카테고리가 없으면 Exception을 발생시킨다.")
    public void categoryMinTest() {
        //given
        List<String> categories = new ArrayList<>();

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoValidation().categoryValidation(categories));
    }

    @Test
    @DisplayName("카테고리가 10개를 넘어으면 Exception을 발생시킨다.")
    public void categoryMaxTest() {
        //given
        List<String> categories = new ArrayList<>();
        String category = "카테고리명";

        //when
        for (int i = 1; i <= 11; i++) {
            categories.add(category + i);
        }

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoValidation().categoryValidation(categories));
    }

    @Test
    @DisplayName("전시관리 등록 테스트")
    public void exhibitionReqTest() {
        //given
        Map<ContentEnum, Integer> contentMap = new HashMap<>();

        contentMap.put(ContentEnum.PRODUCT, 8);
        contentMap.put(ContentEnum.TEXT, 9);
        contentMap.put(ContentEnum.IMAGE, 10);
        contentMap.put(ContentEnum.VIDEO, 5);

        for (int i = 0; i < 10; i++) {
            Exhibition exData = Exhibition.builder()
                    .name("전시관리: " + i)
                    .use_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                    .date_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                    .image("테스트이미지.jpg" + i)
                    .exhibitionType(random.nextInt(2) % 2 == 0 ? ExhibitionType.IMAGE : ExhibitionType.TEXT)
                    .description("자유롭게 설명합니다 " + i)
                    .url("www.naver.com")
                    .build();
            exhibitionRepository.save(exData);
        }

        Optional<Exhibition> exhibitionSn = exhibitionRepository.findById(10000001L);

        ContentsType data = ContentsType.builder()
                .exhibition(exhibitionSn.get())
                .contentEnum(ContentEnum.TEXT)
                .contentCnt(10)
                .build();

        System.out.println("=========");
        System.out.println(data);

        //when
        List<Exhibition> all = exhibitionRepository.findAll();

        for (Exhibition exhibition : all) {
            System.out.println("exhibitionValue : " + exhibition);
        }

        //then
    }
}