package com.teckstudy.book.order.controller;

import com.teckstudy.book.entity.BookOrder;
import com.teckstudy.book.order.repository.OrderRepository;
import com.teckstudy.book.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
