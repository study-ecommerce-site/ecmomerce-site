package com.teckstudy.book.exhibition.controller;

import com.teckstudy.book.exhibition.domain.dto.ContentsTypeResponseDto;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionRequestDto;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionResponseDto;
import com.teckstudy.book.exhibition.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ExhibitionResponseDto> findById (@PathVariable("id") Long id) {

        List<ContentsTypeResponseDto> contents = exhibitionService.findByContetnts(id);

        ExhibitionResponseDto exhibitionResponseDto = exhibitionService.findById(id);
        exhibitionResponseDto.setContentsList(contents);

        return ResponseEntity.ok().body(exhibitionResponseDto);
    }

    /**
     * 전시카테고리 등록 및 컨텐츠 타입 등록
     */
    @PostMapping("/api/exhibition/save")
    public ResponseEntity<ExhibitionResponseDto> registerExhibition(@RequestBody ExhibitionRequestDto requestDto) {

        Long exhibitionSn = exhibitionService.exhibitionSave(requestDto);

        List<ContentsTypeResponseDto> contents = exhibitionService.findByContetnts(exhibitionSn);

        ExhibitionResponseDto exhibitionResponseDto = exhibitionService.findById(exhibitionSn);
        exhibitionResponseDto.setContentsList(contents);

        return ResponseEntity.ok().body(exhibitionResponseDto);
    }

    /**
     * 전시카테고리 등록 및 컨텐츠 타입 수정
     */
    @PutMapping("/api/exhibition/save/{id}")
    public ResponseEntity<ExhibitionResponseDto> updateExhibition(@PathVariable("id") Long id, @RequestBody ExhibitionRequestDto requestDto) {

        Long exhibitionSn = exhibitionService.exhibitionUpdate(id, requestDto);

        List<ContentsTypeResponseDto> contents = exhibitionService.findByContetnts(exhibitionSn);

        ExhibitionResponseDto exhibitionResponseDto = exhibitionService.findById(exhibitionSn);
        exhibitionResponseDto.setContentsList(contents);

        return ResponseEntity.ok().body(exhibitionResponseDto);
    }
}