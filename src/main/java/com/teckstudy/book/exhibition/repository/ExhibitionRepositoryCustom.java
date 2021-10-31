package com.teckstudy.book.exhibition.repository;

import com.teckstudy.book.exhibition.domain.dto.ExhibitionResponseDto;

import java.util.List;

public interface ExhibitionRepositoryCustom {
    List<ExhibitionResponseDto> findAllDesc(Long id) throws Exception;
}