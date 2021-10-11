package com.teckstudy.book.exhibition;

import com.teckstudy.book.lib.common.BoValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.teckstudy.book.lib.common.BoValidation.getType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ExhibitionTest {

    @Test
    @DisplayName("메뉴명이 한글이 아니면 Exception을 발생한다.")
    public void korCharMenuTest() {
        //given
        String keyWord = "한글단어인지g검증";

        //when
        boolean keyWordChk = getType(keyWord);

        //then
        assertThat(keyWordChk).isFalse();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoValidation(keyWord));
    }

    @Test
    @DisplayName("메뉴명은 한글 기준 20자를 넘으면 Exception을 발생시킨다.")
    public void twentyKorCharMenuTest() {
        //given
        String keyWord = "한글데이터 검증단어 글자수 초과합니다.";
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


}