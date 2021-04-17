package com.teckstudy.book.product.repository;

import com.teckstudy.book.product.domain.entity.PayInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayInfoRepository extends JpaRepository<PayInfo, Long> {
}
