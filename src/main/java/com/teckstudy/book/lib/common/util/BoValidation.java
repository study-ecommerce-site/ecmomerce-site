package com.teckstudy.book.lib.common.util;

import com.teckstudy.book.entity.ContentsType;
import com.teckstudy.book.entity.enums.ContentEnum;
import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionRequestDto;
import com.teckstudy.book.lib.common.message.api.ExhibitionCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class BoValidation {

    private static final int WORD_ZERO = 0;
    private static final int WORD_MIN = 1;
    private static final int WORD_MAX = 20;
    private static final int CONTENT_DUPLICATE = 1;
    private static final int CONTENT_ZERO = 0;
    private static final int CONTENT_MIN = 1;
    private static final int MAX_SIZE = 10485760;
    private static final int BUNDLE_ZERO = 0;
    private static final int BUNDLE_MAX = 99;

    public BoValidation() {

    }

    public BoValidation(ExhibitionRequestDto exhibitionRequestDto, Map<ContentEnum, Integer> contentInfo) {

        // 글자 벨리데이션
        nameValidation(exhibitionRequestDto.getName());

        // 컨텐츠 벨리데이션(컨텐츠정보, 컨텐츠 최대갯수, 컨텐츠 묶음갯수)
        boContentValidation(contentInfo, exhibitionRequestDto.getContentsList(), exhibitionRequestDto.getBundleContentCnt());

        // 전시기간 벨리데이션
        dateValidation(exhibitionRequestDto.getDate_yn(), exhibitionRequestDto.getExhibition_start(), exhibitionRequestDto.getExhibition_end());

        // 이미지 벨리데이션
        imageValidation(exhibitionRequestDto.getExhibitionType(), exhibitionRequestDto.getImage());

        // 파일 사이즈 체크
//        multiFileUpload(exhibitionRequestDto long megabyte);
    }

    /**
     * 컨텐츠 벨리데이션
     * @param contentInfo 컨텐츠 전체 갯수
     * @param contentsList 중복 갯수
     * @param bundleMaxCnt 컨텐츠 최대 갯수
     */
    public void boContentValidation(Map<ContentEnum, Integer> contentInfo, List<ContentsType> contentsList, int bundleMaxCnt) {
        int contentsCount = contentsList.size();

        if(bundleMaxCnt <= BUNDLE_ZERO || bundleMaxCnt > BUNDLE_MAX) {
            throw new IllegalArgumentException(ExhibitionCode.BUNDLE_SIZE_ERROR.getMsg());
        }

        if (contentsCount == CONTENT_ZERO) {
            throw new IllegalArgumentException(ExhibitionCode.NO_SELECT_CONTENT_AGAIN.getMsg());
        }

        if (contentsCount == CONTENT_MIN) {
            throw new IllegalArgumentException(ExhibitionCode.PLEASE_SELECT_TWO_CONTENT.getMsg());
        }

        if (contentInfo.size() > CONTENT_DUPLICATE) {
            for (ContentEnum key : contentInfo.keySet()) {
                if(contentInfo.get(key).equals(2)) {
                    throw new IllegalArgumentException(ExhibitionCode.DUPLICATE_CONTENT.getMsg());
                }
            }
        }

        // 버튼 선택 시 컨텐츠 최대개수를 미입력한 경우
        for (ContentsType contentsType : contentsList) {
           if(contentsType.getContentCnt() == CONTENT_ZERO) {
                throw new IllegalArgumentException(ExhibitionCode.ENTER_MAXIMUM_NUMBER_EACH_CONTENT.getMsg());
            }
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
    public void nameValidation(String word) {
        int wordSize = word.length();
        boolean matchesWord = word.trim().isEmpty();

        if(wordSize == WORD_ZERO || matchesWord == true) {
            throw new IllegalArgumentException(ExhibitionCode.PLEASE_ENTER_NAME_EXHIBITION.getMsg());
        }

        // 글자수가 1자이상 20자를 초과하는지 검사한다.
        if (wordSize < WORD_MIN || wordSize > WORD_MAX) {
            throw new IllegalArgumentException(ExhibitionCode.EXHIBITION_CORNER_CANNOT_TWENTY.getMsg());
        }

        // 문자의 한글,띄어쓰기 여부를 체크한다. 사용안함
//        boolean matchesWord = Pattern.matches("^[가-힣\\s]*$", word);
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