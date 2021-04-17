package com.teckstudy.book.product.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.product.domain.entity.Product;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.teckstudy.book.product.domain.entity.QProduct.product;
import static com.teckstudy.book.product.domain.entity.QProductOption.productOption;

@Repository
public class ProductRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public ProductRepositorySupport(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }

    public List<Tuple> findAllDesc() {
        return queryFactory
                .select(productOption, product)
                .from(productOption)
                .leftJoin(product).on(product.product_sn.eq(productOption.product.product_sn))
                .fetch();
    }

}
