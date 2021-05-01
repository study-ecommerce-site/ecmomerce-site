package com.teckstudy.book.order.repository;

import com.teckstudy.book.order.domain.dto.OrderBookResponseDto;

import java.util.List;

public interface OrderCustomRepository {
    List<OrderBookResponseDto> findOrderListById(Long memberSn);
}
