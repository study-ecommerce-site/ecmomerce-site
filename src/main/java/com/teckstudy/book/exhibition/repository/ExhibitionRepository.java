package com.teckstudy.book.exhibition.repository;

import com.teckstudy.book.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long>, ExhibitionRepositoryCustom {

}