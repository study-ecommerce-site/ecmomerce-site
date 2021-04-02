package com.teckstudy.book.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookOrder is a Querydsl query type for BookOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookOrder extends EntityPathBase<BookOrder> {

    private static final long serialVersionUID = 302707914L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookOrder bookOrder = new QBookOrder("bookOrder");

    public final ListPath<BookOrderProduct, QBookOrderProduct> bookOrderProduct = this.<BookOrderProduct, QBookOrderProduct>createList("bookOrderProduct", BookOrderProduct.class, QBookOrderProduct.class, PathInits.DIRECT2);

    public final NumberPath<Long> member_sn = createNumber("member_sn", Long.class);

    public final StringPath order_address = createString("order_address");

    public final NumberPath<Integer> order_price = createNumber("order_price", Integer.class);

    public final NumberPath<Long> order_sn = createNumber("order_sn", Long.class);

    public final EnumPath<com.teckstudy.book.domain.entity.enums.OrderStatus> order_status = createEnum("order_status", com.teckstudy.book.domain.entity.enums.OrderStatus.class);

    public final EnumPath<com.teckstudy.book.domain.entity.enums.YesNoStatus> order_yn = createEnum("order_yn", com.teckstudy.book.domain.entity.enums.YesNoStatus.class);

    public final QPayInfo payInfo;

    public QBookOrder(String variable) {
        this(BookOrder.class, forVariable(variable), INITS);
    }

    public QBookOrder(Path<? extends BookOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookOrder(PathMetadata metadata, PathInits inits) {
        this(BookOrder.class, metadata, inits);
    }

    public QBookOrder(Class<? extends BookOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.payInfo = inits.isInitialized("payInfo") ? new QPayInfo(forProperty("payInfo"), inits.get("payInfo")) : null;
    }

}

