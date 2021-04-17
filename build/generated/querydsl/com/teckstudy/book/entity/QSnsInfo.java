package com.teckstudy.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSnsInfo is a Querydsl query type for SnsInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSnsInfo extends EntityPathBase<SnsInfo> {

    private static final long serialVersionUID = -527458059L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSnsInfo snsInfo = new QSnsInfo("snsInfo");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    public final QMember member;

    public final NumberPath<Long> member_sn = createNumber("member_sn", Long.class);

    public final StringPath sns_code = createString("sns_code");

    public final EnumPath<com.teckstudy.book.entity.enums.SnsType> sns_type = createEnum("sns_type", com.teckstudy.book.entity.enums.SnsType.class);

    public QSnsInfo(String variable) {
        this(SnsInfo.class, forVariable(variable), INITS);
    }

    public QSnsInfo(Path<? extends SnsInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSnsInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSnsInfo(PathMetadata metadata, PathInits inits) {
        this(SnsInfo.class, metadata, inits);
    }

    public QSnsInfo(Class<? extends SnsInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

