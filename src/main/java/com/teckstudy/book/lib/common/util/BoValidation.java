package com.teckstudy.book.lib.common.util;

import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import com.teckstudy.book.lib.common.message.api.ExhibitionCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class BoValidation {

    private static final int WORD_ZERO = 0;
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

    /**
     * 컨텐츠 벨리데이션
     * @param bundleCnt
     * @param count
     */
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

        if(wordSize == WORD_ZERO) {
            throw new IllegalArgumentException(ExhibitionCode.PLEASE_ENTER_NAME_EXHIBITION.getMsg());
        }

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
     * 전시기간 벨리데이션
     * @param dateYn
     * @param start
     * @param end
     */
    public void dateValidation(YesNoStatus dateYn, String start, String end) {
        if(dateYn.equals(YesNoStatus.Y) && ("".equals(start) || "".equals(end))) {
            throw new IllegalArgumentException(ExhibitionCode.DATE_AND_TIME.getMsg());
        }

        LocalDateTime startDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(ExhibitionCode.END_DATE_EXHIBITION_CANNOT_START_DATE.getMsg());
        }
    }

    /**
     * 이미지 벨리데이션
     * @param type
     * @param image
     */
    public void imageValidation(ExhibitionType type, String image) {
        if(type.equals(ExhibitionType.IMAGE) && "".equals(image)) {
            throw new IllegalArgumentException(ExhibitionCode.REGISTER_IMAGE_EXHIBITION.getMsg());
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