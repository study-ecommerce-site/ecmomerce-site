package com.teckstudy.book.lib.common.util;

import com.teckstudy.book.lib.common.message.api.ExhibitionCode;

import java.util.List;
import java.util.regex.Pattern;

public class BoCategoryValidation {

    private static final int WORD_MIN = 1;
    private static final int WORD_MAX = 20;
    private static final int CATEGORY_MIN = 1;
    private static final int CATEGORY_MAX = 10;
    private static final int MAX_SIZE = 10485760;

    public BoCategoryValidation() {

    }

    public BoCategoryValidation(String word) {
        validation(word);
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
            throw new IllegalArgumentException(ExhibitionCode.TWENTY_KOREAN_MENU.getMsg());
        }
    }

    /**
     * 카테고리 개수가 10개를 초과하는지 검증
     * @param categories
     */
    public void categoryValidation(List<String> categories) {

        if (categories.size() < CATEGORY_MIN) {
            throw new IllegalArgumentException(ExhibitionCode.LEAST_ONE_CATEGORY_NAME.getMsg());
        }

        if (categories.size() > CATEGORY_MAX) {
            throw new IllegalArgumentException(ExhibitionCode.MAXIMUM_TEN_CATEGORY_NAME.getMsg());
        }
    }

    /**
     * 파일 사이즈 체크
     * @param megabyte
     */
    public void multiFileUpload(long megabyte) {
        if (megabyte > MAX_SIZE) {
            throw new IllegalArgumentException(ExhibitionCode.IMAGE_SIZE_ERROR.getMsg());
        }
    }
}