package com.teckstudy.book.exhibition.repository;

import com.teckstudy.book.entity.Exhibition;
import com.teckstudy.book.exhibition.domain.dto.ExhibitionRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long>, ExhibitionRepositoryCustom {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Exhibition p SET p.use_yn = :#{#exhibitionRequestDto.use_yn}, p.name = :#{#exhibitionRequestDto.name}, " +
            "p.exhibitionType = :#{#exhibitionRequestDto.exhibitionType}, p.date_yn = :#{#exhibitionRequestDto.date_yn}, " +
            "p.image = :#{#exhibitionRequestDto.image}, p.description = :#{#exhibitionRequestDto.description}, " +
            "p.url = :#{#exhibitionRequestDto.url}, p.exhibition_start = :#{#exhibitionRequestDto.exhibition_start}, " +
            "p.exhibition_end = :#{#exhibitionRequestDto.exhibition_end}, p.bundleContentCnt = :#{#exhibitionRequestDto.bundleContentCnt} " +
            "WHERE p.exhibition_sn = :id")
    void updateExhibition(@Param("exhibitionRequestDto") ExhibitionRequestDto exhibitionRequestDto, @Param("id")Long id);
}