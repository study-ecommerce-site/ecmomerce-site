package com.teckstudy.book.repository;

import com.teckstudy.book.domain.entity.PayInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayInfoRepository extends JpaRepository<PayInfo, Long> {
}
