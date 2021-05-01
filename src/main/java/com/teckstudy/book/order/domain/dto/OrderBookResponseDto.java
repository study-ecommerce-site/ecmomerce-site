package com.teckstudy.book.order.domain.dto;


import com.querydsl.core.annotations.QueryProjection;
import com.teckstudy.book.entity.PayInfo;
import com.teckstudy.book.entity.enums.OrderStatus;
import com.teckstudy.book.entity.enums.YesNoStatus;
import lombok.Data;


@Data
public class OrderBookResponseDto {

    private Long order_sn;
    private Long member_sn;
    private YesNoStatus order_yn;
    private Integer order_price;
    private String order_address;
    private OrderStatus order_status;

    @QueryProjection
    public OrderBookResponseDto(Long order_sn, Long member_sn, YesNoStatus order_yn, Integer order_price, String order_address, OrderStatus order_status) {
        this.order_sn = order_sn;
        this.member_sn = member_sn;
        this.order_yn = order_yn;
        this.order_price = order_price;
        this.order_address = order_address;
        this.order_status = order_status;
    }

}
