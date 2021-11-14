package com.teckstudy.book.exhibition.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.teckstudy.book.entity.ContentsType;
import com.teckstudy.book.entity.enums.ContentEnum;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ContentsTypeResponseDto {

    private Long exhibition_sn;
    private Long content_sn;
    private ContentEnum contentEnum;
    private int contentCnt;

    /**
     * 전시카테고리 컨텐츠 조회
     */
    @QueryProjection
    public ContentsTypeResponseDto(Long exhibition_sn, Long content_sn, ContentEnum contentEnum, int contentCnt) {
        this.exhibition_sn = exhibition_sn;
        this.content_sn = content_sn;
        this.contentEnum = contentEnum;
        this.contentCnt = contentCnt;
    }

    public ContentsTypeResponseDto(ContentsType contentsType) {
//        this.content_sn = contentsTypes.getContent_sn();
//        this.contentEnum = contentsTypes.getContentEnum();
//        this.contentCnt = contentsTypes.getContentCnt();
    }
}