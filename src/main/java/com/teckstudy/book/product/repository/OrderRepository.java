package com.teckstudy.book.product.repository;


import com.teckstudy.book.product.domain.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<BookOrder, Long> {
}
