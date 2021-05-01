package com.teckstudy.book.order.service;

import com.teckstudy.book.entity.BookOrder;
import com.teckstudy.book.order.domain.dto.OrderBookResponseDto;
import com.teckstudy.book.order.domain.dto.OrderResultResponseDto;
import com.teckstudy.book.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderBookResponseDto> findOrderListById(Long memberSn){
        return orderRepository.findOrderListById(memberSn);
    }

    @Transactional
    public List<OrderResultResponseDto> order(List<BookOrder> orderInfoList){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<OrderResultResponseDto> orderResultResponseDtoList =
                orderRepository.saveAll(orderInfoList).stream().map(p -> modelMapper.map(p, OrderResultResponseDto.class))
                .collect(Collectors.toList());

        return orderResultResponseDtoList;
    }


}
