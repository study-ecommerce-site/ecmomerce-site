package com.teckstudy.book.repository;

import com.teckstudy.book.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
