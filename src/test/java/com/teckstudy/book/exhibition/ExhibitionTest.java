package com.teckstudy.book.exhibition;

import com.teckstudy.book.lib.common.message.api.ExhibitionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ExhibitionTest {

    @Test
    @DisplayName("메뉴명이 한글이 아니면 false를 발생한다.")
    public void korCharMenuTest() {
        //given
        String keyWord = "한글단어인지g검증";

        //when
        boolean keyWordChk = getType(keyWord);

        //then
        assertThat(keyWordChk).isFalse();
//                .withFailMessage(ExhibitionCode.ENTER_TWENTY_KOREAN_CHARACTERS_THE_MENU.getMsg());
    }

    @Test
    @DisplayName("메뉴명은 한글 기준 20자를 넘으면 Exception을 발생시킨다.")
    public void twentyKorCharMenuTest() {
        //given
        String keyWord = "한글데이터 검증단어 글자수 초과합니다.";
        int keyWordSize = keyWord.length();
        //when
        validation(keyWordSize);

        //then
//        assertThatExceptionOfType(IllegalArgumentException.class)
//                .isThrownBy(() -> keyWordSize > 20)
//                .withMessageMatching(ExhibitionCode.ENTER_TWENTY_KOREAN_CHARACTERS_THE_MENU.getMsg());
    }

    /**
     * 문자의 영문,숫자,한글 여부를 리턴한다
     *
     * @param word
     * @return
     */
    private static boolean getType(String word) {
        return Pattern.matches("^[가-힣\\s]*$", word);
    }

    private void validation(int number) {
        if (number < 1 || number > 20) {
            throw new IllegalArgumentException(ExhibitionCode.ENTER_TWENTY_KOREAN_CHARACTERS_THE_MENU.getMsg());
        }
    }
}