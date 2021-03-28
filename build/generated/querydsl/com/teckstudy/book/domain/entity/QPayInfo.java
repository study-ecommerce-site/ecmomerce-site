package com.teckstudy.book.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayInfo is a Querydsl query type for PayInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPayInfo extends EntityPathBase<PayInfo> {

    private static final long serialVersionUID = 1512151451L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayInfo payInfo = new QPayInfo("payInfo");

    public final QBookOrder bookOrder;

    public final NumberPath<Long> order_sn = createNumber("order_sn", Long.class);

    public final NumberPath<Long> pay_sn = createNumber("pay_sn", Long.class);

    public final NumberPath<Integer> total_price = createNumber("total_price", Integer.class);

    public QPayInfo(String variable) {
        this(PayInfo.class, forVariable(variable), INITS);
    }

    public QPayInfo(Path<? extends PayInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayInfo(PathMetadata metadata, PathInits inits) {
        this(PayInfo.class, metadata, inits);
    }

    public QPayInfo(Class<? extends PayInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bookOrder = inits.isInitialized("bookOrder") ? new QBookOrder(forProperty("bookOrder"), inits.get("bookOrder")) : null;
    }

}

