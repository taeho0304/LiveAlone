package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommercialInfo is a Querydsl query type for CommercialInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommercialInfo extends EntityPathBase<CommercialInfo> {

    private static final long serialVersionUID = 1323442010L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommercialInfo commercialInfo = new QCommercialInfo("commercialInfo");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCommercialCategory commercialCategory;

    public final StringPath content = createString("content");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath lat = createString("lat");

    public final StringPath lon = createString("lon");

    public final StringPath name = createString("name");

    public QCommercialInfo(String variable) {
        this(CommercialInfo.class, forVariable(variable), INITS);
    }

    public QCommercialInfo(Path<? extends CommercialInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommercialInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommercialInfo(PathMetadata metadata, PathInits inits) {
        this(CommercialInfo.class, metadata, inits);
    }

    public QCommercialInfo(Class<? extends CommercialInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commercialCategory = inits.isInitialized("commercialCategory") ? new QCommercialCategory(forProperty("commercialCategory")) : null;
    }

}

