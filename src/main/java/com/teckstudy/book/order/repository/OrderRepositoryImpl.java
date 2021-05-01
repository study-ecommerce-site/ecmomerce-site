package com.teckstudy.book.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.order.domain.dto.OrderBookResponseDto;
import com.teckstudy.book.order.domain.dto.QOrderBookResponseDto;


import javax.persistence.EntityManager;
import java.util.List;
import static com.teckstudy.book.entity.QBookOrder.bookOrder;



public class OrderRepositoryImpl implements OrderCustomRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderBookResponseDto> findOrderListById(Long memberSn) {
        return queryFactory
                .select(new QOrderBookResponseDto(
                        bookOrder.order_sn,
                        bookOrder.member_sn,
                        bookOrder.order_yn,
                        bookOrder.order_price,
                        bookOrder.order_address,
                        bookOrder.order_status
                ))
                .from(bookOrder)
                .where(bookOrder.member_sn.eq(memberSn))
                .fetch();
    }

}
