package com.teckstudy.book.product.repository;

import com.teckstudy.book.product.domain.entity.BookOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderProductRepository extends JpaRepository<BookOrderProduct, Long> {

}
