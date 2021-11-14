package com.teckstudy.book.exhibition.repository;

import com.teckstudy.book.exhibition.domain.dto.ContentsTypeResponseDto;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionResponseDto;

import java.util.List;

public interface ExhibitionRepositoryCustom {
    List<ExhibitionResponseDto> findExhibition(Long id) throws Exception;

    List<ContentsTypeResponseDto> findContents(Long id) throws Exception;
}