package com.teckstudy.book.exhibition.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.teckstudy.book.entity.ContentsType;
import com.teckstudy.book.entity.enums.ContentEnum;
import lombok.Data;
import lombok.Getter;

import java.util.List;

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

    public void ContentsTypeResponseDto(ContentsType contentsType) {
        this.exhibition_sn = contentsType.getExhibition().getExhibition_sn();
        this.content_sn = contentsType.getContent_sn();
        this.contentEnum = contentsType.getContentEnum();
        this.contentCnt = contentsType.getContentCnt();
    }

}