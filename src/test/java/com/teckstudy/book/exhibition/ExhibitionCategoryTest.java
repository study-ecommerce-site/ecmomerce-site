package com.teckstudy.book.exhibition;

import com.teckstudy.book.lib.common.util.BoCategoryValidation;
import com.teckstudy.book.lib.common.util.BoValidation;
import com.teckstudy.book.exhibition.repository.ExhibitionCategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Transactional
public class ExhibitionCategoryTest {

    @Autowired
    ExhibitionCategoryRepository exhibitionCategoryRepository;

    @Test
    @DisplayName("메뉴명은 20자를 넘으면 Exception을 발생시킨다.")
    public void twentyKorCharMenuTest() {
        //given
        String keyWord = "데이터 검증단어 글자수 초과합니다. 20자 넘어감";
        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoValidation().nameValidation(keyWord));
    }

    @Test
    @DisplayName("최소 1개의 카테고리가 없으면 Exception을 발생시킨다.")
    public void categoryMinTest() {
        //given
        List<String> categories = new ArrayList<>();

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoCategoryValidation().categoryValidation(categories));
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
                .isThrownBy(() -> new BoCategoryValidation().categoryValidation(categories));
    }
}