package com.teckstudy.book.lib.common;

import com.teckstudy.book.lib.common.message.api.ExhibitionCode;

import java.util.List;
import java.util.regex.Pattern;

public class BoValidation {

    private static final int WORD_MIN = 1;
    private static final int WORD_MAX = 20;
    private static final int CATEGORY_MAX = 10;
//    private final String boValidation;

    public BoValidation() {

    }

    public BoValidation(String word) {
        validation(word);
//        boValidation = word;
    }

    /**
     * 문자의 한글,띄어쓰기 여부를 리턴한다
     *
     * @param word
     * @return
     */
    public static boolean getType(String word) {
        return Pattern.matches("^[가-힣\\s]*$", word);
    }

    /**
     * 글자 벨리데이션
     * @param word
     */
    public void validation(String word) {
        int wordSize = word.length();

        // 글자수가 1자이상 20자를 초과하는지 검사한다.
        if (wordSize < WORD_MIN || wordSize > WORD_MAX) {
            throw new IllegalArgumentException(ExhibitionCode.ENTER_TWENTY_KOREAN_CHARACTERS_THE_MENU.getMsg());
        }

        // 문자의 한글,띄어쓰기 여부를 체크한다.
        boolean matchesWord = Pattern.matches("^[가-힣\\s]*$", word);

        if(matchesWord == false) {
            throw new IllegalArgumentException(ExhibitionCode.ENTER_TWENTY_KOREAN_CHARACTERS_THE_MENU.getMsg());
        }
    }

    /**
     * 카테고리 개수가 10개를 초과하는지 검증
     * @param categories
     */
    public void categoryValidation(List<String> categories) {
        if (categories.size() > CATEGORY_MAX) {
            throw new IllegalArgumentException(ExhibitionCode.TEN_CATEGORIES_CAN_BE_REGISTERED.getMsg());
        }
    }
}