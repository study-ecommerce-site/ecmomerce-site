package com.teckstudy.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVertity is a Querydsl query type for Vertity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVertity extends EntityPathBase<Vertity> {

    private static final long serialVersionUID = 1877743756L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVertity vertity = new QVertity("vertity");

    public final StringPath auth_code = createString("auth_code");

    public final DateTimePath<java.time.LocalDateTime> auth_date = createDateTime("auth_date", java.time.LocalDateTime.class);

    public final NumberPath<Long> auth_serial_sn = createNumber("auth_serial_sn", Long.class);

    public final EnumPath<com.teckstudy.book.entity.enums.YesNoStatus> auth_yn = createEnum("auth_yn", com.teckstudy.book.entity.enums.YesNoStatus.class);

    public final QMember member;

    public final EnumPath<com.teckstudy.book.entity.enums.AuthType> member_auth_type = createEnum("member_auth_type", com.teckstudy.book.entity.enums.AuthType.class);

    public final NumberPath<Long> member_sn = createNumber("member_sn", Long.class);

    public QVertity(String variable) {
        this(Vertity.class, forVariable(variable), INITS);
    }

    public QVertity(Path<? extends Vertity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVertity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVertity(PathMetadata metadata, PathInits inits) {
        this(Vertity.class, metadata, inits);
    }

    public QVertity(Class<? extends Vertity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

