package com.teckstudy.book.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity> {

    private static final long serialVersionUID = 1685283791L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final StringPath createdBy = createString("createdBy");

    public final DatePath<java.time.LocalDate> createdDate = createDate("createdDate", java.time.LocalDate.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DatePath<java.time.LocalDate> lastModifiedDate = createDate("lastModifiedDate", java.time.LocalDate.class);

    public QBaseEntity(String variable) {
        super(BaseEntity.class, forVariable(variable));
    }

    public QBaseEntity(Path<? extends BaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntity(PathMetadata metadata) {
        super(BaseEntity.class, metadata);
    }

}

