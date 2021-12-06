package com.teckstudy.book.exhibition.controller;

import com.teckstudy.book.exhibition.domain.dto.ContentsTypeResponseDto;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionRequestDto;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionResponseDto;
import com.teckstudy.book.exhibition.service.ExhibitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "ExhibitionController v1")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    /**
     * 전시카테고리 조회
     */
    @ApiOperation(value = "전시카테고리 조회", notes = "전시카테고리 조회하기")
    @ApiImplicitParam(name = "id", value = "전시관리번호", required = true)
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
    @ApiOperation(value = "전시카테고리 등록", notes = "전시카테고리 등록 및 컨텐츠 타입 등록")
    @PostMapping("/api/exhibition/save")
    public ResponseEntity<ExhibitionResponseDto> registerExhibition(@RequestBody ExhibitionRequestDto requestDto) {

        Long exhibitionSn = exhibitionService.exhibitionSave(requestDto);

        List<ContentsTypeResponseDto> contents = exhibitionService.findByContetnts(exhibitionSn);

        ExhibitionResponseDto exhibitionResponseDto = exhibitionService.findById(exhibitionSn);
        exhibitionResponseDto.setContentsList(contents);

        return ResponseEntity.ok().body(exhibitionResponseDto);
    }

    /**
     * 전시카테고리 & 컨텐츠 타입 수정
     */
    @ApiOperation(value = "전시카테고리 & 컨텐츠 타입 수정", notes = "전시카테고리 & 컨텐츠 타입 수정")
    @ApiImplicitParam(name = "id", value = "전시관리번호", dataType = "long", required = true)
    @PutMapping("/api/exhibition/save/{id}")
    public ResponseEntity<ExhibitionResponseDto> updateExhibition(@PathVariable("id") Long id, @RequestBody ExhibitionRequestDto requestDto) {

        Long exhibitionSn = exhibitionService.exhibitionUpdate(id, requestDto);

        List<ContentsTypeResponseDto> contents = exhibitionService.findByContetnts(exhibitionSn);

        ExhibitionResponseDto exhibitionResponseDto = exhibitionService.findById(exhibitionSn);
        exhibitionResponseDto.setContentsList(contents);

        return ResponseEntity.ok().body(exhibitionResponseDto);
    }
}