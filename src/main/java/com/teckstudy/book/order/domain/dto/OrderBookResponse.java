package com.teckstudy.book.order.domain.dto;


import com.teckstudy.book.entity.PayInfo;
import com.teckstudy.book.entity.enums.OrderStatus;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class OrderBookResponse {

    private Long order_sn;
    private Long member_sn;
    private YesNoStatus order_yn;
    private Integer order_price;
    private String order_address;
    private OrderStatus order_status;
    private PayInfo payInfo;


}
