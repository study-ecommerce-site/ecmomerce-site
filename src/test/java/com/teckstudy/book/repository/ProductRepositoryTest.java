package com.teckstudy.book.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.entity.Product;
import com.teckstudy.book.entity.ProductOption;
import com.teckstudy.book.entity.enums.ProductType;
import com.teckstudy.book.product.repository.ProductOptionRepository;
import com.teckstudy.book.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.teckstudy.book.entity.QProduct.product;
import static com.teckstudy.book.entity.QProductOption.productOption;
import static org.assertj.core.api.Assertions.assertThat;

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


    @Test
    @DisplayName("상품등록 및 상품 옵션 테스트")
    public void testProductOption() {
        queryFactory = new JPAQueryFactory(em);
        
        //given
        String[] bookList = {"이솝우화", "헨젤과 그레텔", "백설공주", "신데렐라"};
        for(int i=0; i<bookList.length; i++) {
            Product product= Product.builder()
                    .product_name(bookList[i])
                    .product_option(random.nextInt(2) % 2 == 0 ? ProductType.RADIO : ProductType.CHECK)
                    .price(Integer.valueOf(random.nextInt(99999)+1))
                    .stock_cnt(Integer.valueOf(random.nextInt(99)+1))
                    .build();

            productRepository.save(product);
        }

        List<Product> all = productRepository.findAll();

        for (Product product1 : all) {
            System.out.println("product1 : " + product1);
        }

        Optional<Product> productSn = productRepository.findById(10000001L);
        System.out.println("productSn : " + productSn);
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

        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("상품등록 테스트")
    public void testProduct() {
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

        assertThat(products.size()).isEqualTo(8);
    }
}