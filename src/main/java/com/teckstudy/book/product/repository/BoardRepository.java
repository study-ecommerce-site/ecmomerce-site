package com.teckstudy.book.product.repository;

import com.teckstudy.book.product.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
