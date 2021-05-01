package com.teckstudy.book.order.controller;

import com.teckstudy.book.entity.BookOrder;
import com.teckstudy.book.order.domain.dto.OrderBookResponseDto;
import com.teckstudy.book.order.domain.dto.OrderResultResponseDto;
import com.teckstudy.book.order.repository.OrderRepository;
import com.teckstudy.book.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    @GetMapping("/order")
    public String hello() {
        return "order test";
    }

    // 1. Order Data Get
    @GetMapping("/api/order/book")
    public List<BookOrder> findAll(){
        return orderRepository.findAll();
    }

    // 2. Find Order List By Id
    @GetMapping("/api/order/{memberSn}")
    public List<OrderBookResponseDto> findOrderListById(@PathVariable("id") Long memberSn){
        return orderService.findOrderListById(memberSn);
    }

    // 3. Order
    @PostMapping("/api/order")
    public List<OrderResultResponseDto> order(@RequestBody List<BookOrder> orderInfoList){
        return orderService.order(orderInfoList);
    }
}
