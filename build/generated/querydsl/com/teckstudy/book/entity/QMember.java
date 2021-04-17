package com.teckstudy.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1603858955L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath birthday = createString("birthday");

    public final ListPath<BookOrder, QBookOrder> bookOrders = this.<BookOrder, QBookOrder>createList("bookOrders", BookOrder.class, QBookOrder.class, PathInits.DIRECT2);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    public final ListPath<Favorite, QFavorite> favorite = this.<Favorite, QFavorite>createList("favorite", Favorite.class, QFavorite.class, PathInits.DIRECT2);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath member_id = createString("member_id");

    public final NumberPath<Long> member_sn = createNumber("member_sn", Long.class);

    public final EnumPath<com.teckstudy.book.entity.enums.MemberStatus> member_status = createEnum("member_status", com.teckstudy.book.entity.enums.MemberStatus.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone_number = createString("phone_number");

    public final EnumPath<com.teckstudy.book.entity.enums.Gender> sex = createEnum("sex", com.teckstudy.book.entity.enums.Gender.class);

    public final EnumPath<com.teckstudy.book.entity.enums.YesNoStatus> sns_yn = createEnum("sns_yn", com.teckstudy.book.entity.enums.YesNoStatus.class);

    public final QSnsInfo snsInfo;

    public final QVertity vertity;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.snsInfo = inits.isInitialized("snsInfo") ? new QSnsInfo(forProperty("snsInfo"), inits.get("snsInfo")) : null;
        this.vertity = inits.isInitialized("vertity") ? new QVertity(forProperty("vertity"), inits.get("vertity")) : null;
    }

}

