package com.teckstudy.book.repository;

import com.teckstudy.book.domain.entity.AnswerList;
import com.teckstudy.book.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerListRepository  extends JpaRepository<AnswerList, Long> {
}
