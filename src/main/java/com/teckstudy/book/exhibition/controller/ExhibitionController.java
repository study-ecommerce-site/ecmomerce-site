package com.teckstudy.book.exhibition.controller;

import com.teckstudy.book.exhibition.domain.dto.ExhibitionRequestDto;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionResponseDto;
import com.teckstudy.book.exhibition.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    /**
     * 전시카테고리 조회
     */
    @GetMapping("/api/exhibition/{id}")
    public ExhibitionResponseDto findById (@PathVariable Long id) {
        return exhibitionService.findById(id);
    }

    /**
     * 전시카테고리 등록 및 컨텐츠 타입 등록
     */
    @PostMapping("/api/exhibition/post")
    public ExhibitionResponseDto registerProduct(@RequestBody ExhibitionRequestDto requestDto) {
        return exhibitionService.exhibitionSave(requestDto);
    }

    /**
     * 전시카테고리 및 컨텐츠타입 조회
     */
    @GetMapping("/api/exhibitionAll/{id}")
    public List<ExhibitionResponseDto> findAll (@PathVariable Long id) throws Exception {
        List<ExhibitionResponseDto> exhibitionDtoList = exhibitionService.findExhibition(id);

        return exhibitionDtoList;
    }
}
