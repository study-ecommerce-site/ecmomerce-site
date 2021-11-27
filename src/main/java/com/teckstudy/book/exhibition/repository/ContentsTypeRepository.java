package com.teckstudy.book.exhibition.repository;

import com.teckstudy.book.entity.ContentsType;
import com.teckstudy.book.entity.enums.ContentEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentsTypeRepository extends JpaRepository<ContentsType, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE ContentsType p SET p.contentEnum = :contentEnum, p.contentCnt = :contentCnt WHERE p.content_sn = :id")
    void updateContents(@Param("id")Long id, @Param("contentEnum") ContentEnum contentEnum, @Param("contentCnt") int contentCnt);

}