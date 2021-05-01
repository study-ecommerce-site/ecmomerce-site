package com.teckstudy.book.order.service;

import com.teckstudy.book.order.domain.dto.OrderBookResponseDto;
import com.teckstudy.book.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderBookResponseDto> findOrderListById(Long memberSn){
        return orderRepository.findOrderListById(memberSn);
    }


}
