package com.teckstudy.book.order.repository;

import com.teckstudy.book.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<BookOrder, Long>, OrderCustomRepository{
}
