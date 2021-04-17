package com.teckstudy.book.product.repository;

import com.teckstudy.book.product.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
