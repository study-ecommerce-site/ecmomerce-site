package com.teckstudy.book.product.repository;

import com.teckstudy.book.entity.ExhibitionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionCategoryRepository extends JpaRepository<ExhibitionCategory, Long> {
}