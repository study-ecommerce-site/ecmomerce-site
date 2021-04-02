package com.teckstudy.book.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1990422804L;

    public static final QProduct product = new QProduct("product");

    public final ListPath<BookOrderProduct, QBookOrderProduct> bookOrderProduct = this.<BookOrderProduct, QBookOrderProduct>createList("bookOrderProduct", BookOrderProduct.class, QBookOrderProduct.class, PathInits.DIRECT2);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath product_name = createString("product_name");

    public final EnumPath<com.teckstudy.book.domain.entity.enums.ProductType> product_option = createEnum("product_option", com.teckstudy.book.domain.entity.enums.ProductType.class);

    public final NumberPath<Long> product_sn = createNumber("product_sn", Long.class);

    public final ListPath<ProductOption, QProductOption> productOptions = this.<ProductOption, QProductOption>createList("productOptions", ProductOption.class, QProductOption.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stock_cnt = createNumber("stock_cnt", Integer.class);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

