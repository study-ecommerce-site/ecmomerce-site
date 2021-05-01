package com.teckstudy.book.order.repository;

import com.teckstudy.book.entity.BookOrder;
import com.teckstudy.book.order.domain.dto.OrderBookResponseDto;
import com.teckstudy.book.order.domain.dto.OrderResultResponseDto;

import java.util.List;

public interface OrderCustomRepository {
    List<OrderBookResponseDto> findOrderListById(Long memberSn);

}
