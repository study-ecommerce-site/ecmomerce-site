package com.teckstudy.book.exhibition.service;

import com.teckstudy.book.entity.ContentsType;
import com.teckstudy.book.entity.Exhibition;
import com.teckstudy.book.exhibition.domain.dto.ContentsTypeResponseDto;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionRequestDto;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionResponseDto;
import com.teckstudy.book.exhibition.repository.ContentsTypeRepository;
import com.teckstudy.book.exhibition.repository.ExhibitionRepository;
import com.teckstudy.book.lib.common.message.api.ExhibitionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;

    private final ContentsTypeRepository contentsTypeRepository;

    /**
     * 전시카테고리 조회
     * @param id
     * @return
     */
    public ExhibitionResponseDto findById(Long id) {
        Exhibition entity = exhibitionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("전시카테고리 정보가 없습니다. id=" + id));
        return new ExhibitionResponseDto(entity);
    }

    /**
     * 전시카테고리 등록
     * @param exhibitionRequestDto
     * @return
     */
    @Transactional
    public ExhibitionResponseDto exhibitionSave(ExhibitionRequestDto exhibitionRequestDto) {

        Exhibition exhibition = exhibitionRepository.save(exhibitionRequestDto.toExhibitionEntity());

        // 컨텐츠타입 적재
        try{
            for(ContentsType ContentsTypes : exhibitionRequestDto.getContentsList()){
                contentsTypeRepository.save(exhibitionRequestDto.toContentsEntity(ContentsTypes, exhibition));
            }

            List<ContentsTypeResponseDto> contents = exhibitionRepository.findContents(exhibition.getExhibition_sn());

            ExhibitionResponseDto exhibitionResponseDto = new ExhibitionResponseDto(exhibition);
            exhibitionResponseDto.setContentsList(contents);

            return exhibitionResponseDto;
        }catch(Exception e){
            return new ExhibitionResponseDto(ExhibitionCode.PLEASE_REGISTER_CONTENT_TYPE.getMsg() + " : " + exhibition.getExhibition_sn());
        }
    }

    /**
     * @param id
     * @return
     */
    // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됨.
    @Transactional(readOnly = true)
    public List<ExhibitionResponseDto> findExhibition(Long id) throws Exception {
        List<ExhibitionResponseDto> exhibitionResponseDtoList = exhibitionRepository.findExhibition(id);

        return exhibitionResponseDtoList;
    }
}