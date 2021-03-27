package com.teckstudy.book.repository;

import com.teckstudy.book.domain.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
