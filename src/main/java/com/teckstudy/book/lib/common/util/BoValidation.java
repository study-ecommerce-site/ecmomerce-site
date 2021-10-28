package com.teckstudy.book.lib.common.util;

import com.teckstudy.book.lib.common.message.api.ExhibitionCode;

import java.util.regex.Pattern;

public class BoValidation {

    private static final int WORD_MIN = 1;
    private static final int WORD_MAX = 20;
    private static final int CONTENT_DUPLICATE = 1;
    private static final int CONTENT_ZERO = 0;
    private static final int CONTENT_MIN = 1;
    private static final int MAX_SIZE = 10485760;

    public BoValidation() {

    }

    public BoValidation(String word) {
        validation(word);
    }

    public void BoContentValidation(int bundleCnt, int count) {
        if (count == CONTENT_ZERO) {
            throw new IllegalArgumentException(ExhibitionCode.NO_SELECT_CONTENT_AGAIN.getMsg());
        }

        if (count == CONTENT_MIN) {
            throw new IllegalArgumentException(ExhibitionCode.PLEASE_SELECT_TWO_CONTENT.getMsg());
        }

        if (bundleCnt > CONTENT_DUPLICATE) {
            throw new IllegalArgumentException(ExhibitionCode.DUPLICATE_CONTENT.getMsg());
        }

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
            throw new IllegalArgumentException(ExhibitionCode.EXHIBITION_CORNER_CANNOT_TWENTY.getMsg());
        }

        // 문자의 한글,띄어쓰기 여부를 체크한다. 사용안함
//        boolean matchesWord = Pattern.matches("^[가-힣\\s]*$", word);
//        if(matchesWord == false) {
//            throw new IllegalArgumentException(ExhibitionCode.TWENTY_KOREAN_MENU.getMsg());
//        }
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