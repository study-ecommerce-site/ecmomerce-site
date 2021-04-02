package com.teckstudy.book.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.domain.entity.Product;
import com.teckstudy.book.domain.entity.ProductOption;
import com.teckstudy.book.domain.entity.enums.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.teckstudy.book.domain.entity.QProduct.product;
import static com.teckstudy.book.domain.entity.QProductOption.productOption;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ProductRepositoryTest {
    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    // queryDsl 선언
    JPAQueryFactory queryFactory;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductOptionRepository productOptionRepository;

    private Random random = new Random();

    /**
     * 상품등록 테스트
     */

    @Test
    public void testProduct() {
        queryFactory = new JPAQueryFactory(em);
        //given

        String[] bookList = {"해리포터", "반지의 제왕", "이것이 자바다", "기분이 없는 기분"};

        for(int i=0; i<bookList.length; i++) {
            Product product= Product.builder()
                    .product_name(bookList[i])
                    .product_option(random.nextInt(2) % 2 == 0 ? ProductType.RADIO : ProductType.CHECK)
                    .price(Integer.valueOf(random.nextInt(99999)+1))
                    .stock_cnt(Integer.valueOf(random.nextInt(99)+1))
                    .build();

            productRepository.save(product);
        }

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println(product.getProduct_name());
            System.out.println(product.getProduct_option());
        }
    }

    /**
     * 상품등록 및 상품 옵션 테스트
     */
    @Test
    public void testProductOption() {
        queryFactory = new JPAQueryFactory(em);
        //given
        String[] bookList = {"해리포터", "반지의 제왕", "이것이 자바다", "기분이 없는 기분"};
        for(int i=0; i<bookList.length; i++) {
            Product product= Product.builder()
                    .product_name(bookList[i])
                    .product_option(random.nextInt(2) % 2 == 0 ? ProductType.RADIO : ProductType.CHECK)
                    .price(Integer.valueOf(random.nextInt(99999)+1))
                    .stock_cnt(Integer.valueOf(random.nextInt(99)+1))
                    .build();

            productRepository.save(product);
        }

        Optional<Product> productSn = productRepository.findById(2L);

        String[] productOptionName = {"DB 모음집 시리즈", "파우치", "전우치"};

        for(int i=0; i<productOptionName.length; i++) {
            ProductOption productOption= ProductOption.builder()
                    .product(productSn.get())
                    .product_option_name(productOptionName[i])
                    .plus_price(Integer.valueOf(random.nextInt(99999)+1))
                    .stock_cnt(Integer.valueOf(random.nextInt(99)+1))
                    .build();

            productOptionRepository.save(productOption);
        }

        List<Tuple> result = queryFactory
                .select(productOption, product)
                .from(productOption)
                .leftJoin(product).on(product.product_sn.eq(productOption.product.product_sn))
                .fetch();

        for (Tuple results : result) {
            System.out.println("product = " + results);
        }
    }
}