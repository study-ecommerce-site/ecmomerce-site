package com.teckstudy.book.lib.common.message.api;

/**
 * 에러코드
 *
 * TODO
 * @project 공통 > 메시지
 * @author babyBook 개발팀 백성락
 * @since 2021-10-10
 * @version 1.0
 *<pre>
 *2021.10.10 : 최초 작성
 *</pre>
 */
public enum ExhibitionCode {

    /** 유효하지 않는 PARAMETER 값   */
    TWENTY_KOREAN_MENU("BO_PD_AL_001", "메뉴명은 최대 20자까지 입력가능합니다."),
    MAXIMUM_TEN_CATEGORY_NAME("BO_PD_AL_002", "카테고리는 한번에 최대 10개까지 등록이 가능합니다."),
    LEAST_ONE_CATEGORY_NAME("BO_PD_AL_003", "최소 1개이상의 카테고리명을 입력해주세요."),
    EXHIBITION_CORNER_CANNOT_TWENTY("BO_PD_AL_004", "전시코너명은 20자를 초과할 수 없습니다."),
    DUPLICATE_CONTENT("BO_PD_AL_005", "이미 등록된 유형은 재등록할 수 없습니다."),
    PLEASE_SELECT_TWO_CONTENT("BO_PD_AL_006", "2개 이상 유형을 선택해주세요."),
    NO_SELECT_CONTENT_AGAIN("BO_PD_AL_007", "선택한 유형이 없습니다. 선택 후 재시도해주세요."),
    PLEASE_ENTER_NAME_EXHIBITION("BO_PD_AL_008","전시코너명을 입력해주세요."),
    REGISTER_IMAGE_EXHIBITION("BO_PD_AL_009", "전시코너명에 노출할 이미지를 등록해주세요."),
    DATE_AND_TIME("BO_PD_AL_010", "전시기간 및 일시를 입력해주세요."),
    END_DATE_EXHIBITION_CANNOT_START_DATE("BO_PD_AL_011", "전시기간의 종료일이 시작일보다 빠를 수 없습니다."),
    PLEASE_REGISTER_CONTENT_TYPE("BO_PD_AL_012", "컨텐츠 유형을 등록해주세요."),
    ENTER_MAXIMUM_NUMBER_EACH_CONTENT("BO_PD_AL_013", "컨텐츠별 최대개수를 입력해주세요."),
    POSSIBLE_ONLY_NUMBER_OF_N_TO_N_MATCHING("BO_PD_AL_014", "묶음 컨텐츠의 경우 N:N 매칭이 되는 개수로만 등록이 가능합니다."),
    IMAGE_SIZE_ERROR("BO_PD_AL_015", "이미지 크기가 10MB를 초과할 수 없습니다."),
    BUNDLE_SIZE_ERROR("BO_PD_AL_016", "묶음 컨텐츠는 최대 1개이상 99개까지 가능합니다."),
    CANCEL_WITHOUT_SAVING_ENTERED_CATEGORY_NAME("BO_PD_CF_001", "입력한 카테고리명을 저장하지 않고 취소하시겠습니까?"),
    REGISTER_EXHIBITION_COR("BO_PD_CF_002", "입력한 전시코너를 등록하시겠습니까?"),
    ENTERED_INFORMATION_IS_DELETED_CANCEL("BO_PD_CF_003", "취소 시 입력한 정보가 삭제됩니다. 취소하시겠습니까?")
    ;

    private String code;

    private String msg;

    ExhibitionCode(String code) {
        this.code = code;
    }
    ExhibitionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return this.code;
    }
    public String getMsg() {
        return this.msg;
    }
}