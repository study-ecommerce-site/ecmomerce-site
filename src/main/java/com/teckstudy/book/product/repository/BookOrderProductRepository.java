package com.teckstudy.book.product.repository;

import com.teckstudy.book.entity.BookOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderProductRepository extends JpaRepository<BookOrderProduct, Long> {

}
