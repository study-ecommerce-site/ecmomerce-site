package com.teckstudy.book.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
