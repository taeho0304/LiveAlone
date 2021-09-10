package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserfilter is a Querydsl query type for Userfilter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserfilter extends EntityPathBase<Userfilter> {

    private static final long serialVersionUID = 772547653L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserfilter userfilter = new QUserfilter("userfilter");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCommercialCategory commercialCategory;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QUser user;

    public QUserfilter(String variable) {
        this(Userfilter.class, forVariable(variable), INITS);
    }

    public QUserfilter(Path<? extends Userfilter> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserfilter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserfilter(PathMetadata metadata, PathInits inits) {
        this(Userfilter.class, metadata, inits);
    }

    public QUserfilter(Class<? extends Userfilter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commercialCategory = inits.isInitialized("commercialCategory") ? new QCommercialCategory(forProperty("commercialCategory")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

