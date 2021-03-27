package com.teckstudy.book.repository;

import com.teckstudy.book.domain.entity.Product;
import com.teckstudy.book.domain.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
}
