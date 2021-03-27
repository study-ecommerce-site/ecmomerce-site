package com.teckstudy.book.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.domain.entity.*;
import com.teckstudy.book.domain.entity.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.teckstudy.book.domain.entity.QMember.member;
import static com.teckstudy.book.domain.entity.QBookOrder.bookOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BookOrderRepositoryTest {
    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    // queryDsl 선언
    JPAQueryFactory queryFactory;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired // Autowired 대신사용 @InjectMocks에 주입
    BookOrderRepository bookOrderRepository;

    @Autowired
    ProductOptionRepository productOptionRepository;

    @Autowired
    BookOrderProductRepository bookOrderProductRepository;

    private Random random = new Random();

    @BeforeEach
    public void testEntity() {

        queryFactory = new JPAQueryFactory(em);
        //given
        Member member1 = Member.builder()
                .member_id("member1")
                .password("1234")
                .name("홍길동")
                .sex(Gender.MALE)
                .birthday("1990-09-12")
                .phone_number("010-2027-1163")
                .address("서울특별시 봉천동")
                .sns_yn(YesNoStatus.Y)
                .member_status(MemberStatus.NORMAL)
                .build();

        memberRepository.save(member1);

        Product product1 = Product.builder()
                .product_name("클린 코드 : 에자일의 소프트웨어")
                .product_option(ProductType.RADIO)
                .price(50000)
                .stock_cnt(30)
                .build();

        productRepository.save(product1);

        String[] productOptionName = {"DB 모음집 시리즈", "파우치", "전우치"};

        for(int i=0; i<productOptionName.length; i++) {
            ProductOption productOption= ProductOption.builder()
                    .product(product1)
                    .product_option_name(productOptionName[i])
                    .plus_price(Integer.valueOf(random.nextInt(99999)+1))
                    .stock_cnt(Integer.valueOf(random.nextInt(99)+1))
                    .build();

            productOptionRepository.save(productOption);
        }

        BookOrder bookOrder1 = BookOrder.builder()
                    .member(member1)
                    .order_yn(YesNoStatus.Y)
                    .order_price(11000)
                    .order_address("서울시 강남구 신사동 사자")
                    .order_status(OrderStatus.WAIT)
                    .build();

        bookOrderRepository.save(bookOrder1);

        List<String> orderList1 = new ArrayList<>();
        orderList1.add("1");
        orderList1.add("2");
        orderList1.add("3");
        BookOrderProduct bookOrderProductOne = BookOrderProduct.builder()
                .bookOrder(bookOrder1)  // order_sn(bookOrder1.getOrder_sn())
                .product(product1)      // product_sn(product1.getProduct_sn())
                .product_cnt(10)
                .product_option_sn("1, 2, 3")
                .build();

        bookOrderProductRepository.save(bookOrderProductOne);
    }

//    @Test
//    public void orderPayInfo() {
//
//        Optional<BookOrder> bookOrderOne = bookOrderRepository.findById(1L);
//
//        System.out.println(bookOrderOne);
//
////        PayInfo payInfo = PayInfo.builder()
////                .bookOrder(bookOrder.order_sn)
////                .build();
//    }

//    @Test
//    public void orderProductTest() {
//
//    }

    @Test
    public void orderTest() {
        // 내부조인(inner join)이면 join과 where을 사용하고 아닐시에는 on 절 사용
        // .where(member.member_id.eq("member1"))
        List<Tuple> result = queryFactory
                .select(bookOrder, member)
                .from(bookOrder)
                .innerJoin(member).on(member.member_id.eq("member1"))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple " + tuple);
        }
    }
}