package com.teckstudy.book.product.repository;

import com.teckstudy.book.product.domain.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
