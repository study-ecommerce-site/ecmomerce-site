package com.teckstudy.book.exhibition.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.teckstudy.book.entity.Exhibition;
import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
@Getter
public class ExhibitionResponseDto {

    private Long exhibition_sn;
    private YesNoStatus use_yn;
    private String name;
    private ExhibitionType exhibitionType;
    private YesNoStatus date_yn;
    private String image;
    private String description;
    private String url;
    private String exhibition_start;
    private String exhibition_end;

    @QueryProjection
    public ExhibitionResponseDto(Long exhibition_sn, YesNoStatus use_yn, String name, ExhibitionType exhibitionType, YesNoStatus date_yn, String image, String description, String url, String exhibition_start, String exhibition_end) {
        this.exhibition_sn = exhibition_sn;
        this.use_yn = use_yn;
        this.name = name;
        this.exhibitionType = exhibitionType;
        this.date_yn = date_yn;
        this.image = image;
        this.description = description;
        this.url = url;
        this.exhibition_start = exhibition_start;
        this.exhibition_end = exhibition_end;
    }

    public ExhibitionResponseDto(Exhibition entity) {
        this.exhibition_sn = entity.getExhibition_sn();
        this.use_yn = entity.getUse_yn();
        this.name = entity.getName();
        this.exhibitionType = entity.getExhibitionType();
        this.date_yn = entity.getDate_yn();
        this.image = entity.getImage();
        this.description = entity.getDescription();
        this.url = entity.getUrl();
        this.exhibition_start = entity.getExhibition_start();
        this.exhibition_end = entity.getExhibition_end();
    }
}