package com.teckstudy.book.order.controller;

import com.teckstudy.book.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order")
    public String hello() {
        return "order test";
    }

    // 1. Order Data Get
    @GetMapping("/api/order/book")
    public String findAll(){
        return "";
    }

}
