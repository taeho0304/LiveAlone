package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResidenceWeight is a Querydsl query type for ResidenceWeight
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResidenceWeight extends EntityPathBase<ResidenceWeight> {

    private static final long serialVersionUID = 456422684L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResidenceWeight residenceWeight = new QResidenceWeight("residenceWeight");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCommercialCategory commercialCategory;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath weight = createString("weight");

    public QResidenceWeight(String variable) {
        this(ResidenceWeight.class, forVariable(variable), INITS);
    }

    public QResidenceWeight(Path<? extends ResidenceWeight> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResidenceWeight(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResidenceWeight(PathMetadata metadata, PathInits inits) {
        this(ResidenceWeight.class, metadata, inits);
    }

    public QResidenceWeight(Class<? extends ResidenceWeight> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commercialCategory = inits.isInitialized("commercialCategory") ? new QCommercialCategory(forProperty("commercialCategory")) : null;
    }

}

