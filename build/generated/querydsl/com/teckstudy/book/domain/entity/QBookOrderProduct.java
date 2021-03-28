package com.teckstudy.book.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookOrderProduct is a Querydsl query type for BookOrderProduct
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookOrderProduct extends EntityPathBase<BookOrderProduct> {

    private static final long serialVersionUID = 1441683653L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookOrderProduct bookOrderProduct = new QBookOrderProduct("bookOrderProduct");

    public final QBookOrder bookOrder;

    public final NumberPath<Long> order_product_sn = createNumber("order_product_sn", Long.class);

    public final NumberPath<Long> order_sn = createNumber("order_sn", Long.class);

    public final QProduct product;

    public final NumberPath<Integer> product_cnt = createNumber("product_cnt", Integer.class);

    public final NumberPath<Long> product_option_sn = createNumber("product_option_sn", Long.class);

    public final NumberPath<Long> product_sn = createNumber("product_sn", Long.class);

    public final QProductOption productOption;

    public QBookOrderProduct(String variable) {
        this(BookOrderProduct.class, forVariable(variable), INITS);
    }

    public QBookOrderProduct(Path<? extends BookOrderProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookOrderProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookOrderProduct(PathMetadata metadata, PathInits inits) {
        this(BookOrderProduct.class, metadata, inits);
    }

    public QBookOrderProduct(Class<? extends BookOrderProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bookOrder = inits.isInitialized("bookOrder") ? new QBookOrder(forProperty("bookOrder"), inits.get("bookOrder")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
        this.productOption = inits.isInitialized("productOption") ? new QProductOption(forProperty("productOption"), inits.get("productOption")) : null;
    }

}

