package com.teckstudy.book.exhibition.domain.dto;

import com.teckstudy.book.entity.ContentsType;
import com.teckstudy.book.entity.Exhibition;
import com.teckstudy.book.entity.enums.ContentEnum;
import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class ExhibitionRequestDto {

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
    private ContentEnum contentEnum;
    private int contentCnt;

    public Exhibition toExhibitionEntity() {
        return Exhibition.builder()
                .use_yn(use_yn)
                .name(name)
                .exhibitionType(exhibitionType)
                .date_yn(date_yn)
                .image(image)
                .description(description)
                .url(url)
                .exhibition_start(exhibition_start)
                .exhibition_end(exhibition_end)
                .build();
    }

    public ContentsType toContentsEntity(Exhibition exhibitionNo) {
        return ContentsType.builder()
                .exhibition(exhibitionNo)
                .contentEnum(contentEnum)
                .contentCnt(contentCnt)
                .build();
    }
}