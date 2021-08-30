package com.teckstudy.book.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.entity.*;
import com.teckstudy.book.entity.enums.*;
import com.teckstudy.book.product.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.teckstudy.book.entity.QBookOrder.bookOrder;
import static com.teckstudy.book.entity.QMember.member;
import static com.teckstudy.book.entity.QPayInfo.payInfo;
import static com.teckstudy.book.entity.QProduct.product;
import static com.teckstudy.book.entity.QRefund.refund;
import static com.teckstudy.book.entity.QReview.review;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookOrderRepositoryTest {
    @Autowired
    EntityManager em;

    // queryDsl 선언
    JPAQueryFactory queryFactory;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BookOrderRepository bookOrderRepository;

    @Autowired
    ProductOptionRepository productOptionRepository;

    @Autowired
    BookOrderProductRepository bookOrderProductRepository;

    @Autowired
    PayInfoRepository payInfoRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    RefundRepository refundRepository;

    private Random random = new Random();

    @BeforeEach
    public void testEntity() {

        queryFactory = new JPAQueryFactory(em);
        //given
        Member members = Member.builder()
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

        memberRepository.save(members);

        Product products = Product.builder()
                .product_name("클린 코드 : 에자일의 소프트웨어")
                .product_option(ProductType.RADIO)
                .price(50000)
                .stock_cnt(30)
                .build();

        productRepository.save(products);

        String[] productOptionName = {"DB 모음집 시리즈", "파우치", "전우치"};

        for(int i=0; i<productOptionName.length; i++) {
            ProductOption productOption= ProductOption.builder()
                    .product(products)
                    .product_option_name(productOptionName[i])
                    .plus_price(Integer.valueOf(random.nextInt(99999)+1))
                    .stock_cnt(Integer.valueOf(random.nextInt(99)+1))
                    .build();

            productOptionRepository.save(productOption);
        }

        BookOrder bookOrders = BookOrder.builder()
                    .member(members)
                    .order_yn(YesNoStatus.Y)
                    .order_price(11000)
                    .order_address("서울시 강남구 신사동 사자")
                    .order_status(OrderStatus.WAIT)
                    .build();

        bookOrderRepository.save(bookOrders);

        List<String> orderLists = new ArrayList<>();
        orderLists.add("1");
        orderLists.add("2");
        orderLists.add("3");
        BookOrderProduct bookOrderProductOne = BookOrderProduct.builder()
                .bookOrder(bookOrders)
                .product(products)
                .product_cnt(10)
                .product_option_sn("1, 2, 3")
                .build();

        bookOrderProductRepository.save(bookOrderProductOne);

        PayInfo payInfo = PayInfo.builder()
                .bookOrder(bookOrders)
                .total_price(bookOrders.getOrder_price())
                .build();

        payInfoRepository.save(payInfo);

        Review reviews = Review.builder()
                .member(members)
                .product(products)
                .review_sub("이책은 띵작입니다.")
                .review_content("내돈산 최고의 책이라고 감히 자부합니다.")
                .gpa(5)
                .build();

        reviewRepository.save(reviews);

        Refund refunds = Refund.builder()
                .bookOrder(bookOrders)
                .bank_name("국민은행")
                .acc_number("437601-410-3333")
                .build();

        refundRepository.save(refunds);
    }

    /**
     * 결제 테스트
     */
    @Test
    @DisplayName("결제 테스트")
    public void orderPayInfo() {

        queryFactory
                .select(payInfo.pay_sn, bookOrder.order_sn, payInfo.total_price)
                .from(payInfo)
                .fetch();

        List<PayInfo> payinfos = payInfoRepository.findAll();

        for (PayInfo pay : payinfos) {
            assertThat(pay.getBookOrder().getOrder_sn()).isEqualTo(1L);
            assertThat(pay.getTotal_price()).isEqualTo(11000);
        }
    }

    /**
     * 상품주문 테스트
     */
    @Test
    @DisplayName("상품주문 테스트")
    public void orderTest() {
        List<Tuple> result = queryFactory
                .select(bookOrder, member)
                .from(bookOrder)
                .innerJoin(member).on(member.member_id.eq(bookOrder.member.member_id))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple " + tuple);
        }
    }

    /**
     * 리뷰 테스트
     */
    @Test
    @DisplayName("리뷰 테스트")
    public void reviewTest() {

        queryFactory
                .select(member.name,
                        product.product_name,
                        review.review_sub,
                        review.review_content,
                        review.gpa)
                .from(review)
                .innerJoin(product).on(product.product_sn.eq(review.product.product_sn))
                .innerJoin(member).on(review.member.member_sn.eq(member.member_sn))
                .fetch();

        List<Review> reviews = reviewRepository.findAll();

        for (Review rev : reviews) {
            assertThat(rev.getReview_sn()).isEqualTo(1L);
            assertThat(rev.getGpa()).isEqualTo(5);
        }
    }

    /**
     * 환불관리 테스트
     */
    @Test
    @DisplayName("환불관리 테스트")
    public void refundTest() {

        queryFactory
                .select(refund.refund_sn, refund.bookOrder.order_sn, refund.bank_name, refund.acc_number)
                .from(refund)
                .fetch();

        List<Refund> refunds = refundRepository.findAll();

        for (Refund res : refunds) {
            assertThat(res.getBank_name()).isEqualTo("국민은행");
        }
    }
}